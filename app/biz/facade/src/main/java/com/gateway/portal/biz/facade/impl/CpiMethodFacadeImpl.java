package com.gateway.portal.biz.facade.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.dubbo.rpc.RpcException;
import com.alibaba.dubbo.rpc.service.GenericException;
import com.alibaba.dubbo.rpc.service.GenericService;
import com.gateway.portal.biz.bean.GatewayFactoryBean;
import com.gateway.portal.core.utils.PageModel;
import com.gateway.portal.core.utils.spring.BeanUtils;
import com.gateway.portal.view.CpiMethodView;
import com.xiaoleilu.hutool.util.CollectionUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.gateway.portal.biz.cache.LocalMethodCache;
import com.gateway.portal.biz.context.ParamClazzContext;
import com.gateway.portal.biz.facade.CpiMethodFacade;
import com.gateway.portal.biz.service.CpiMethodParamService;
import com.gateway.portal.biz.service.CpiMethodService;
import com.gateway.portal.dto.CpiMethodDTO;
import com.gateway.portal.dto.CpiParamsDTO;
import com.gateway.portal.model.ResultModel;
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
	private CpiMethodService		cpiMethodService;
	
	@Resource
	private CpiMethodParamService	cpiMethodParamService;
	@Resource
	private GatewayFactoryBean gatewayFactoryBean;
	@Resource
	private BeanUtils beanUtils;
	
	@Override
	public CpiMethodDTO getCpiMethodDTO(String methodName, String version) {
		
		String key = Joiner.on( '_' ).join( methodName, version );
		
		CpiMethodDTO method = LocalMethodCache.interfaceMap.get( key );
		
		if (method == null) {
			method = this.refreshCache( methodName, version );
		}
		return method;
	}
	
	@Override
	public ResultModel<Integer> createCpiMethod(String apiName, String apiVersion, String name, String packageName, String rpcMethodName, String open,
			String logon, String mode, String desc) {
		ResultModel<Integer> result = new ResultModel<>();
		
		Map<String, Object> params = new HashMap<>();
		params.put( "apiName", apiName );
		params.put( "apiVersion", apiVersion );
		
		int count = this.cpiMethodService.queryByCount( params );
		if (count > 0) {
			result.setMessage( "当前apiName已存在。" );
			return result;
		}
		
		CpiMethod method = new CpiMethod();
		method.setApiName( apiName );
		method.setApiVersion( apiVersion );
		method.setName( name );
		method.setPackageName( packageName );
		method.setRpcMethodName( rpcMethodName );
		method.setOpen( open );
		method.setLogon( logon );
		method.setMode( mode );
		method.setDesc( desc );
		int flag = (int) this.cpiMethodService.create( method );
		
		if (flag > 0) {
			CpiMethodDTO dto = this.refreshCache( method );
			result.setSuccess( true );
			result.setMessage( "添加接口成功！" );

			//检查是否存在gs
			this.gatewayFactoryBean.buildGenericService(method);

		} else {
			result.setMessage( "添加失败！" );
		}

		return result;
	}
	
	@Override
	public ResultModel<Integer> deleteCpiMethod(Integer id) {
		ResultModel<Integer> result = new ResultModel<>();
		CpiMethod cpiMethod = this.cpiMethodService.findById( id );
		if (cpiMethod == null) {
			result.setMessage( "该方法不存在！" );
			return result;
		}
		
		CpiMethod m = new CpiMethod();
		m.setId( String.valueOf( id ) );
		int flag = this.cpiMethodService.delete( m, false );
		if (flag > 0) {
			String key = Joiner.on( '_' ).join( cpiMethod.getApiName(), cpiMethod.getApiVersion() );
			LocalMethodCache.interfaceMap.remove( key );
			// 删除参数
			CpiMethodParam delParam = new CpiMethodParam();
			delParam.setMethodId( id );
			this.cpiMethodParamService.delete( delParam, false );
			
			result.setSuccess( true );
			result.setMessage( "删除成功！" );
		}
		return result;
	}
	
	@Override
	public ResultModel<Integer> updateCpiMethod(Integer id, String apiName, String apiVersion, String name, String packageName, String rpcMethodName,
			String open, String logon, String mode, String desc) {
		ResultModel<Integer> result = new ResultModel<>();
		CpiMethod method = this.cpiMethodService.findById( id );
		if (method == null) {
			result.setMessage( "该方法不存在！" );
			return result;
		}
		
		method.setApiName( apiName );
		method.setApiVersion( apiVersion );
		method.setName( name );
		method.setPackageName( packageName );
		method.setRpcMethodName( rpcMethodName );
		method.setOpen( open );
		method.setLogon( logon );
		method.setMode( mode );
		method.setDesc( desc );
		int flag = this.cpiMethodService.update( method );
		if (flag > 0) {
			result.setMessage( "更新成功！" );
			result.setSuccess( true );
			
			this.refreshCache( method );
		}
		return result;
	}
	
	private CpiMethodDTO refreshCache(CpiMethod cpiMethod) {
		if (cpiMethod == null) {
			return null;
		}
		CpiMethodDTO method = new CpiMethodDTO();
		method.setApiName( cpiMethod.getApiName() );
		method.setApiVersion( cpiMethod.getApiVersion() );
		method.setLogon( cpiMethod.getLogon() );
		method.setMethodName( cpiMethod.getRpcMethodName() );
		method.setPackageName( cpiMethod.getPackageName() );
		method.setOpen(cpiMethod.getOpen());
		
		Map<String, Object> params = new HashMap<>();
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
		String key = Joiner.on( '_' ).join( cpiMethod.getApiName(), cpiMethod.getApiVersion() );
		LocalMethodCache.interfaceMap.put( key, method );
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
		
		return this.refreshCache( cpiMethod );
	}
	
	@Override
	public ResultModel<String> createCpiMethodParam(Integer methodId, String paramName, String paramType, String paramDesc, Integer index,
			String require, String domain, Integer length) {
		ResultModel<String> result = new ResultModel<>();
		CpiMethod method = this.cpiMethodService.findById( methodId );
		if (method == null) {
			result.setMessage( "该方法不存在！" );
			return result;
		}

		CpiMethodParam param = new CpiMethodParam();
		param.setMethodId( methodId );
		param.setParamName( paramName );
		param.setParamType( paramType );
		param.setParamDesc( paramDesc );
		param.setIndex( index );
		param.setRequired( require );
		param.setDomain( domain );
		param.setLength( length );
		
		this.cpiMethodParamService.create( param );
		
		result.setMessage( "添加成功！" );
		result.setSuccess( true );
		
		this.refreshCache( method );
		return result;
	}
	
	@Override
	public ResultModel<String> deleteCpiMethodParam(Integer id) {
		
		ResultModel<String> result = new ResultModel<>();
		
		CpiMethodParam param = this.cpiMethodParamService.findById( id );
		if (param == null) {
			result.setMessage( "参数不存在！" );
			return result;
		}
		
		int flag = this.cpiMethodParamService.delete( param, false );
		if (flag > 0) {
			result.setMessage( "删除成功！" );
			result.setSuccess( true );
			CpiMethod method = this.cpiMethodService.findById( param.getMethodId() );
			this.refreshCache( method );
			return result;
		}
		return result;
	}
	
	@Override
	public ResultModel<String> updateCpiMethodParam(Integer id, Integer methodId, String paramName, String paramType, String paramDesc, Integer index,
			String require, String domain, Integer length) {
		ResultModel<String> result = new ResultModel<>();
		CpiMethod method = this.cpiMethodService.findById( methodId );
		if (method == null) {
			result.setMessage( "该方法不存在！" );
			return result;
		}
		
		CpiMethodParam param = new CpiMethodParam();
		param.setId(id);
		param.setMethodId( methodId );
		param.setParamName( paramName );
		param.setParamType( paramType );
		param.setParamDesc( paramDesc );
		param.setIndex( index );
		param.setRequired( require );
		param.setDomain( domain );
		param.setLength( length );
		
		this.cpiMethodParamService.update( param );
		
		result.setMessage( "更新成功！" );
		result.setSuccess( true );
		
		this.refreshCache( method );
		return result;
	}
	
	@Override
	public PageModel<CpiMethodView> queryCpiMethodByPage(String apiName, String apiVersion, String name, Integer currPage, Integer pageSize) {
		PageModel<CpiMethodView> page = new PageModel<>();
		Map<String, Object> params = new HashMap<>();
		params.put("likeApiName",apiName);
		params.put("apiVersion",apiVersion);
		params.put("likeName",name);
		List<CpiMethod> datas = this.cpiMethodService.queryByPage( params, currPage, pageSize );
		page.setCount( this.cpiMethodService.queryByCount( params ) );

		if(CollectionUtil.isNotEmpty(datas)){
			List<CpiMethodView> views = new ArrayList<>();

			for (CpiMethod data : datas) {
				params.clear();
				params.put("methodId",data.getId());
				StringBuffer classpathMethod = new StringBuffer(data.getPackageName());
				classpathMethod.append(".").append(data.getRpcMethodName()).append("(");
				List<CpiMethodParam> ps  = this.cpiMethodParamService.queryByPage(params,-1,-1);
				String str = null;
				if(CollectionUtil.isNotEmpty(ps)){
					for (CpiMethodParam p : ps) {
						classpathMethod.append(p.getParamType()).append(",");
					}
					str = classpathMethod.substring(0,classpathMethod.length()-1) + ")";
				}else {
					classpathMethod.append(")");
					str = classpathMethod.toString();
				}
				//String name, String apiVersion, String apiName, String logon, String open, Integer id, String classpathMethod, String mode
				views.add(new CpiMethodView(data.getName(),data.getApiVersion(),data.getApiName(),data.getLogon(),data.getOpen(),Integer.parseInt(data.getId()),str,data.getMode()));
			}
			page.setDatas( views );
		}

		return page;
	}


	@Override
	public PageModel<CpiMethodParam> queryCpiMethodParamByPage(Integer methodId, Integer currPage, Integer pageSize) {
		PageModel<CpiMethodParam> page = new PageModel<>();

		Map<String,Object> params = new HashMap<>();
		params.put("methodId",methodId);

		List<CpiMethodParam> datas = this.cpiMethodParamService.queryByPage(params,currPage,pageSize);

		page.setDatas(datas);
		page.setCount(this.cpiMethodParamService.queryByCount(params));
		return page;
	}


	@Override
	public ResultModel<String> batchUpdateCpiMethodParam(List<CpiMethodParam> updates) {

		ResultModel<String> result = new ResultModel<>();

		CpiMethod method = this.cpiMethodService.findById(updates.get(0).getMethodId());

		int flag = this.cpiMethodParamService.batchUpdate(updates);
		if(flag > 0){
			result.setMessage("更新成功！");
			result.setSuccess(true);

			this.refreshCache(method);
		}

		return result;
	}


	@Override
	public ResultModel<String> basicMethodTest(Integer id) {
		CpiMethod method = this.cpiMethodService.findById(id);

		CpiMethodDTO methodDTO = this.getCpiMethodDTO( method.getApiName(), method.getApiVersion() );

		List<String> keys = new ArrayList<>();
		List<Object> values = new ArrayList<>();
		if (methodDTO.getParams() != null) {
			for (int i = 0; i < methodDTO.getParams().size(); i++) {
				CpiParamsDTO p = methodDTO.getParams().get(i);
				keys.add(p.getClazz());
				values.add(null);
			}
		}

		if(method.getLogon().equals("Y")){
			RpcContext.getContext().setAttachment( "sso", "test-logon" );
		}

		String genericKey = Joiner.on( '_' ).join( method.getApiName(), method.getApiVersion() );

		GenericService genericService = this.gatewayFactoryBean.getGenericService( genericKey );
		ResultModel<String> result = new ResultModel<>();
		try{
			String[] ks = new String[keys.size()];
			keys.toArray(ks);
			Object o = genericService.$invoke( methodDTO.getMethodName(), ks, values.toArray() );
			if(o != null){
				result.setSuccess(true);
				result.setMessage("该方法可用！");
			}else {
				result.setMessage("改方法不可用！");
			}
		} catch (RpcException rpcException){
			result.setMessage(rpcException.getMessage());
		} catch (GenericException ex){
			result.setSuccess(true);
			result.setMessage("该方法可用！");
		}

		return result;
	}
}
