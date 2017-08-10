/**
 * mario.com Inc.
 * Copyright (c) 2014-2017 All Rights Reserved.
 */
package com.gateway.portal.biz.bean;

import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Resource;

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
	private CpiMethodService	cpiMethodService;
	@Resource
	private BeanUtils			beanUtils;
	
	private static final String	BEAN_NAME	= "_rpcInvokeMap";
	
//	@SuppressWarnings("unchecked")
//	@PostConstruct
//	public void init() {
//		Map<String, Object> params = new HashMap<String, Object>();
//		params.put( "packageNameGroup", true );
//		List<CpiMethod> datas = this.cpiMethodService.queryByPage( params, -1, -1 );
//		beanUtils.addBean( ConcurrentHashMap.class.getName(), BEAN_NAME, null );
//		ConcurrentHashMap<String, Object> map = beanUtils.getBean( BEAN_NAME, ConcurrentHashMap.class );
//		if (CollectionUtils.isNotEmpty( datas )) {
//			ReferenceConfig<GenericService> reference = new ReferenceConfig<GenericService>();
//			reference.setGeneric( true );
//			reference.setApplication( new ApplicationConfig( "xkhstar-server-provider" ) );
//			String connectionUrl = LoadConfig.getInstance().getValue( "zookeeper.ip.config" );
//			RegistryConfig config = new RegistryConfig( connectionUrl );
//			config.setProtocol( "zookeeper" );
//			reference.setRegistry( config );
//			for (CpiMethod p : datas) {
//				try {
//					reference.setInterface( p.getPackageName() );
//					GenericService pService = reference.get();
//					map.put( p.getPackageName(), pService );
//				} catch (Exception e) {
//					LoggerUtils.defaultPrint( e, "初始化rpc远程调用失败，原因：" );
//				}
//			}
//		}
//	}
	
	@SuppressWarnings("unchecked")
	public GenericService getRpcInvokeService(String packageName) {
		ConcurrentHashMap<String, Object> map = beanUtils.getBean( BEAN_NAME, ConcurrentHashMap.class );
		return (GenericService) map.get( packageName );
	}
	
}
