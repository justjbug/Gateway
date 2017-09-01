/**
 * mario.com Inc.
 * Copyright (c) 2014-2017 All Rights Reserved.
 */
package com.gateway.portal.biz.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import com.alibaba.dubbo.config.*;
import com.gateway.portal.core.utils.common.LoadConfig;
import com.gateway.portal.core.utils.logger.LoggerUtils;
import com.gateway.portal.model.cpi.CpiMethod;
import com.google.common.base.Joiner;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.rpc.service.GenericService;
import com.gateway.portal.biz.service.CpiMethodService;
import com.gateway.portal.core.utils.spring.BeanUtils;

/**
 * 
 * @author liming
 * @version $Id: GatewayFactoryBean.java, v 0.1 2017年7月3日 上午11:02:02 liming Exp
 *          $
 */
@Service("gatewayFactoryBean")
public class GatewayFactoryBean {
	
	@Resource
	private BeanUtils			beanUtils;


	private ConcurrentHashMap<String, GenericService> GENERIC_SERVICE = new ConcurrentHashMap<>();

	private ConcurrentHashMap<String, ReferenceConfig<GenericService>> referenceConfigCache = new ConcurrentHashMap<>();

	private ApplicationConfig applicationConfig;
	private RegistryConfig registryConfig;
	private ConsumerConfig consumerConfig;
	private static final Integer DEFAULT_TIMEOUT = 5000;

	private static final Logger INIT_RPC_LOGGER	= Logger.getLogger( GatewayFactoryBean.class );
	
	private static final String	BEAN_NAME	= "_rpcInvokeMap";

	private static final String DUBBO_NAME = "xkhstar-server-provider";

	private static final String REGISTER_CENTER = "zookeeper";

	private static final String ZOOKEEPER_CONNECTION = LoadConfig.getInstance().getValue( "zookeeper.ip.config" );

	@PostConstruct
	public void init(){
		beanUtils.addBean( ConcurrentHashMap.class.getName(), BEAN_NAME, null );
	}

	/**
	 * 初始化gs，并把gs放入缓存
	 * @param p
	 * @return
	 */
	public GenericService buildGenericService(CpiMethod p){
		GENERIC_SERVICE = beanUtils.getBean(BEAN_NAME, ConcurrentHashMap.class);
		//
		applicationConfig = new ApplicationConfig(DUBBO_NAME);
		//
		registryConfig = new RegistryConfig( ZOOKEEPER_CONNECTION );
		registryConfig.setProtocol( REGISTER_CENTER );
		//
		consumerConfig = new ConsumerConfig();
		consumerConfig.setCheck(false);
		consumerConfig.setSticky(true);
		String genericKey = Joiner.on('_').join(p.getApiName(), p.getApiVersion());
		GenericService gs = GENERIC_SERVICE.get( genericKey );
		if (gs != null) {
			return gs;
		}
		try {
			String interfaceName;
			String dubboVersion;
			interfaceName = p.getPackageName();
			dubboVersion = p.getApiVersion();
			String key = Joiner.on('_').join(interfaceName, dubboVersion);
			ReferenceConfig<GenericService> reference;
			//防止创建过多的ReferenceConfig, 因为api是基于方法的，而 GenericService 是基于dubbo服务提供的接口和版本的
			if (referenceConfigCache.containsKey(key)) {
				reference = referenceConfigCache.get(key);
			} else {
				reference = new ReferenceConfig<GenericService>();
				reference.setInterface(interfaceName);
				reference.setGeneric(true);
				reference.setApplication(applicationConfig);
				reference.setRegistry(registryConfig);
				reference.setConsumer(consumerConfig);
				//reference.setProtocol("dubbo");
				reference.setVersion(dubboVersion);

				referenceConfigCache.put(key, reference);
			}

			List<MethodConfig> methods = reference.getMethods();
			if (methods == null) {
				methods = new ArrayList<>();
			}
			MethodConfig methodConfig = new MethodConfig();
			methodConfig.setName(p.getRpcMethodName());
			methodConfig.setTimeout(DEFAULT_TIMEOUT);
			methods.add(methodConfig);
			reference.setMethods(methods);
			gs = reference.get();
			GENERIC_SERVICE.put(genericKey, gs);

			INIT_RPC_LOGGER.info( "新增rpc远程接口-->" + genericKey );
		} catch (Exception e) {
			LoggerUtils.defaultPrint( e, "初始化rpc远程调用失败，原因：" );
		}
		return gs;
	}
	
	@SuppressWarnings("unchecked")
	public GenericService getGenericService(String packageName) {
		ConcurrentHashMap<String, Object> map = beanUtils.getBean( BEAN_NAME, ConcurrentHashMap.class );
		return (GenericService) map.get( packageName );
	}
	
}
