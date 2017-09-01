/**
 * mario.com Inc.
 * Copyright (c) 2014-2017 All Rights Reserved.
 */
package com.gateway.portal.web.controller;

import java.util.*;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.dubbo.rpc.RpcException;
import com.alibaba.dubbo.rpc.service.GenericService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.gateway.common.model.Result;
import com.gateway.portal.biz.bean.GatewayFactoryBean;
import com.gateway.portal.biz.facade.CpiMethodFacade;
import com.gateway.portal.core.utils.logger.LoggerUtils;
import com.gateway.portal.dto.CpiMethodDTO;
import com.gateway.portal.dto.CpiParamsDTO;
import com.google.common.base.Joiner;
import com.xiaoleilu.hutool.util.StrUtil;

/**
 * 
 * @author liming
 * @version $Id: GatewayController.java, v 0.1 2017年7月2日 下午2:54:08 liming Exp $
 */
@Controller
@Scope("prototype")
public class GatewayController extends BaseController {
	
	/***/
	private static final long	serialVersionUID	= -3830222581615420845L;
	@Resource
	private GatewayFactoryBean	gatewayFactoryBean;
	@Resource
	private CpiMethodFacade		cpiMethodFacade;
	
	private static final Logger	DEFAULT_LOGGER		= Logger.getLogger( GatewayController.class );
	
	private static final String	_Y					= "Y";
	
	private static final String	_N					= "N";
	
	/**
	 * 网关入口
	 * 
	 * @param method
	 * @param bizParams
	 * @param version
	 * @param format
	 * @param sso
	 * @param timestamp
	 * @return
	 */
	@RequestMapping(value = "/gateway", method = { RequestMethod.POST, RequestMethod.GET })
	public Result gateway(String method, String bizParams, String version, String format, String sso, String timestamp) {
		Result result = new Result();
		
		long allTime = System.currentTimeMillis();
		if (StringUtils.isEmpty( method )) {
			// super.setFailMessage( result, );
			result.setMessage( "方法名不能为空！" );
			return result;
		}
		if (StringUtils.isEmpty( version )) {
			// super.setFailMessage( result, "接口版本号不能为空！" );
			return result;
		}
		
		CpiMethodDTO methodDTO = this.cpiMethodFacade.getCpiMethodDTO( method, version );
		if (methodDTO == null) {
			result.setMessage( "该方法不存在或未开放！" );
			return result;
		}
		
		List<Object> paramsValue = new ArrayList<>();
		List<String> paramsType = new ArrayList<>();
		if (StrUtil.equals( methodDTO.getLogon(), _Y )) {
			if (StrUtil.isEmpty( sso )) {
				result.setMessage( "sso不能为空！" );
				return result;
			}
		}
		
		if (StrUtil.equals( methodDTO.getOpen(), _N )) {
			result.setMessage( "该方法未开放！" );
			return result;
		}
		// 使用隐式传参方式 将sso token传入服务
		RpcContext.getContext().setAttachment( "sso", sso );
		if (CollectionUtils.isNotEmpty( methodDTO.getParams() )) {
			
			List<CpiParamsDTO> paramList = methodDTO.getParams();
			JSONObject json = null;
			try {
				json = JSONObject.parseObject( bizParams );
			} catch (Exception e) {
				result.setMessage( "bizParams解析错误！" );
				return result;
			}
			for (CpiParamsDTO p : paramList) {
				paramsType.add( p.getClazz() );
				// 是否是自定义对象
				try {
					if (StrUtil.equals( p.getDomain(), _N )) {
						Object value = json.getObject( p.getName(), Class.forName( p.getClazz() ) );
						
						if (StrUtil.equals( p.getRequired(), _Y ) && value == null) {
							result.setMessage( p.getName() + "不能为空！" );
							return result;
						}
						if (value instanceof String) {
							if (p.getLength() != null && p.getLength() < String.valueOf( value ).length()) {
								result.setMessage( p.getName() + "长度大于" + p.getLength() + "位字符！" );
								return result;
							}
						}
						paramsValue.add( value );
					} else {
						Map<String, Object> domain = JSON.parseObject( json.getString( p.getName() ), new TypeReference<Map<String, Object>>() {
						} );
						paramsValue.add( domain );
					}
				} catch (NullPointerException e) {
					if (StringUtils.equals( p.getRequired(), _Y )) {
						result.setMessage( p.getName() + "不能为空！" );
						return result;
					}
				} catch (ClassNotFoundException e) {
					result.setMessage( p.getName() + "参数类型错误！" );
					return result;
				}
			}
		}
		
		try {
			long rpcTime = System.currentTimeMillis();
			String genericKey = Joiner.on( '_' ).join( methodDTO.getApiName(), methodDTO.getApiVersion() );
			GenericService genericService = this.gatewayFactoryBean.getGenericService( genericKey );
			String[] strings = new String[paramsType.size()];
			paramsType.toArray( strings );
			Object o = genericService.$invoke( methodDTO.getMethodName(), strings, paramsValue.toArray() );
			if (o != null) {
				removeMapKeyIfClass( o );
				result = (Result) o;
			}
			DEFAULT_LOGGER.info( "rpc request method [" + methodDTO.getApiName() + "] time [" + (System.currentTimeMillis() - rpcTime) + "ms]" );
		} catch (RpcException e) {
			result.setMessage( "网络繁忙，请稍候再试！" );
			result.setCode( "100001" );
			LoggerUtils.defaultPrint( e, "rpc request method [" + methodDTO.getApiName() + "]" );
		} catch (Exception e) {
			result.setCode( "100001" );
			result.setMessage( "网络繁忙，请稍候再试！" );
			LoggerUtils.defaultPrint( e, "rpc request method [" + methodDTO.getApiName() + "]" );
		}
		DEFAULT_LOGGER.info( "all request method [" + methodDTO.getApiName() + "] time [" + (System.currentTimeMillis() - allTime) + "ms]" );
		return result;
	}
	
	/**
	 * 移除返回map 结构数据 key 为class的值
	 * 
	 * @param object
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Object removeMapKeyIfClass(Object object) {
		if (object == null) {
			return null;
		}
		
		if (object instanceof Map) {// 对象删除
			Map<Object, Object> objMap = (Map) object;
			objMap.remove( "class" );
			Set<Object> keys = objMap.keySet();
			for (Object key : keys) {
				Object value = objMap.get( key );
				Object fixValue = removeMapKeyIfClass( value );
				objMap.put( key, fixValue );
			}
			return objMap;
		} else if (object instanceof Collection) {// 集合删除
			Collection<Object> c = (Collection) object;
			for (Object obj : c) {
				removeMapKeyIfClass( obj );
			}
			return c;
		} else if (object.getClass().isArray()) {// 数组删除
			Object[] objs = (Object[]) object;
			for (Object obj : objs) {
				removeMapKeyIfClass( obj );
			}
			return objs;
		} else {// 其他直接返回
			return object;
		}
		
	}
	
}
