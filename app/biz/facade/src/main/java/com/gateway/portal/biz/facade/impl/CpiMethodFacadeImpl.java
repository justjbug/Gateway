package com.gateway.portal.biz.facade.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.gateway.portal.biz.context.ParamClazzContext;
import com.gateway.portal.biz.facade.CpiMethodFacade;
import com.gateway.portal.biz.redis.RedisManager;
import com.gateway.portal.biz.service.CpiMethodParamService;
import com.gateway.portal.biz.service.CpiMethodService;
import com.gateway.portal.dto.CpiMethodDTO;
import com.gateway.portal.dto.CpiParamsDTO;
import com.gateway.portal.model.cpi.CpiMethod;
import com.gateway.portal.model.cpi.CpiMethodParam;
import com.google.common.base.Joiner;

/**
 * @author
 * @version $$Id: , v 0.1 Exp $$
 */
@Service("cpiMethodFacade")
public class CpiMethodFacadeImpl implements CpiMethodFacade {
	
	@Resource
	private CpiMethodService			cpiMethodService;
	
	@Resource
	private RedisManager				redisManager;
	@Resource
	private CpiMethodParamService		cpiMethodParamService;
	
	private Map<String, CpiMethodDTO>	interfaceMap	= null;
	
	public CpiMethodFacadeImpl() {
		interfaceMap = new HashMap<>();
	}
	
	@Override
	public CpiMethodDTO getCpimethodDTO(String methodName, String version) {
		
		String key = Joiner.on( '_' ).join( methodName, version );
		
		CpiMethodDTO method = interfaceMap.get( key );
		
		if (method == null) {
			method = this.refreshCache( methodName, version );
		}
		return method;
	}
	
	/**
	 * 刷新缓存
	 * 
	 * @param methodName
	 * @param version
	 * @return
	 */
	private CpiMethodDTO refreshCache(String methodName, String version) {
		Map<String, Object> params = new HashMap<>();
		params.put( "apiName", methodName );
		params.put( "apiVersion", version );
		CpiMethod cpiMethod = this.cpiMethodService.queryByPO( params );
		if (cpiMethod == null) {
			return null;
		}
		CpiMethodDTO method = new CpiMethodDTO();
		method.setApiName( cpiMethod.getApiName() );
		method.setApiVersion( cpiMethod.getApiVersion() );
		method.setLogon( cpiMethod.getLogon() );
		method.setMethodName( cpiMethod.getRpcMethodName() );
		method.setPackageName( cpiMethod.getPackageName() );
		
		params.clear();
		params.put( "methodId", cpiMethod.getId() );
		
		List<CpiMethodParam> paramList = this.cpiMethodParamService.queryByPage( params, -1, -1 );
		
		if (CollectionUtils.isEmpty( paramList )) {
			return method;
		}
		
		List<CpiParamsDTO> methodParams = new ArrayList<>();
		for (CpiMethodParam param : paramList) {
			methodParams.add( new CpiParamsDTO( param.getParamName(), ParamClazzContext.getClazz( param.getParamType() ), param.getIndex(),
					param.getRequired(), param.getParamDesc(), param.getDomain(), param.getLength() ) );
		}
		method.setParams( methodParams );
		// 放入缓存中
		String key = Joiner.on( '_' ).join( methodName, version );
		interfaceMap.put( key, method );
		return method;
	}
	
}
