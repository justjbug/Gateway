/**
 * mario.com Inc.
 * Copyright (c) 2014-2017 All Rights Reserved.
 */
package com.gateway.portal.biz.task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import com.gateway.portal.biz.bean.GatewayFactoryBean;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.gateway.portal.biz.service.CpiMethodService;
import com.gateway.portal.model.cpi.CpiMethod;

/**
 * 扫描通知服务
 * 
 * @author liming
 * @version $Id: RushNotifyTask.java, v 0.1 2017年3月3日 下午6:45:20 liming Exp $
 */
@Service
public class LoopRpcInitTask {
	
	@Resource
	private CpiMethodService	cpiMethodService;

	@Resource
	private GatewayFactoryBean gatewayFactoryBean;
	
//	private static final String	BEAN_NAME		= "_rpcInvokeMap";


//	private ConcurrentHashMap<String, GenericService> GENERIC_SERVICE = new ConcurrentHashMap<>();
//
//	private ConcurrentHashMap<String, ReferenceConfig<GenericService>> referenceConfigCache = new ConcurrentHashMap<>();
//
//	private ApplicationConfig applicationConfig;
//	private RegistryConfig registryConfig;
//	private ConsumerConfig consumerConfig;
//	private static final Integer DEFAULT_TIMEOUT = 5000;
//
//	private static final Logger	INIT_RPC_LOGGER	= Logger.getLogger( LoopRpcInitTask.class );

	@PostConstruct
	public void init() {
//		beanUtils.addBean( ConcurrentHashMap.class.getName(), BEAN_NAME, null );
//		GENERIC_SERVICE = beanUtils.getBean(BEAN_NAME, ConcurrentHashMap.class);
//		//
//		applicationConfig = new ApplicationConfig("xkhstar-server-provider");
//		//
//		String connectionUrl = LoadConfig.getInstance().getValue( "zookeeper.ip.config" );
//		registryConfig = new RegistryConfig( connectionUrl );
//		registryConfig.setProtocol( "zookeeper" );
//		//
//		consumerConfig = new ConsumerConfig();
//		consumerConfig.setCheck(false);
//		consumerConfig.setSticky(true);
//		consumerConfig.setLoadbalance("consistenthash");

		refresh();
	}
	
	@SuppressWarnings("unchecked")
	public void refresh() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put( "open", "Y" );
		List<CpiMethod> datas = this.cpiMethodService.queryByPage( params, -1, -1 );

		if (CollectionUtils.isNotEmpty( datas )) {
			String interfaceName;
			String dubboVersion;
			for (CpiMethod p : datas) {
				this.gatewayFactoryBean.buildGenericService(p);
//				String genericKey = Joiner.on('_').join(p.getApiName(), p.getApiVersion());
//				if (GENERIC_SERVICE.get( genericKey ) != null) {
//					continue;
//				}
//				try {
//					interfaceName = p.getPackageName();
//					dubboVersion = p.getApiVersion();
//					String key = Joiner.on('_').join(interfaceName, dubboVersion);
//					ReferenceConfig<GenericService> reference;
//					//防止创建过多的ReferenceConfig, 因为api是基于方法的，而 GenericService 是基于dubbo服务提供的接口和版本的
//					if (referenceConfigCache.containsKey(key)) {
//						reference = referenceConfigCache.get(key);
//					} else {
//						reference = new ReferenceConfig<GenericService>();
//						reference.setInterface(interfaceName);
//						reference.setGeneric(true);
//						reference.setApplication(applicationConfig);
//						reference.setRegistry(registryConfig);
//						reference.setConsumer(consumerConfig);
//						//reference.setProtocol("dubbo");
//						reference.setVersion(dubboVersion);
//
//						referenceConfigCache.put(key, reference);
//					}
//
//					List<MethodConfig> methods = reference.getMethods();
//					if (methods == null) {
//						methods = new ArrayList<>();
//					}
//					MethodConfig methodConfig = new MethodConfig();
//					methodConfig.setName(p.getRpcMethodName());
//					methodConfig.setTimeout(DEFAULT_TIMEOUT);
//					methods.add(methodConfig);
//					reference.setMethods(methods);
//
//					GENERIC_SERVICE.put(genericKey, reference.get());
//
//					INIT_RPC_LOGGER.info( "新增rpc远程接口-->" + genericKey );
//				} catch (Exception e) {
//					LoggerUtils.defaultPrint( e, "初始化rpc远程调用失败，原因：" );
//				}
			}
		}
	}
	
	@Scheduled(cron = "0 */1 * * * ?")
	public void loopRpcInitTask() {
		refresh();
	}
}
