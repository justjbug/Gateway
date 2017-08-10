/**
 * @date 2014年9月15日 上午9:22:43
 * @author lim
 * @email qq79474256.cool@163.com
 * @version V1.0 
 */
package com.gateway.portal.core.utils.spring;

import java.util.Iterator;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * spring bean utils
 * 
 * @date 2014年9月15日 上午9:22:43
 * @author lim
 * @email qq79474256.cool@163.com
 * @version V1.0
 */
public class BeanUtils implements ApplicationContextAware {
	
	private static ApplicationContext	applicationContext;
	
	public Object getBean(String beanName) {
		return applicationContext.getBean( beanName );
	}
	
	public <T> T getBean(String beanName, Class<T> clazs) {
		return clazs.cast( getBean( beanName ) );
	}
	
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		BeanUtils.applicationContext = applicationContext;
	}
	
	/**
	 * 
	 * @param className
	 *            注册class 全称
	 * @param serviceName
	 *            注册别名
	 * @param propertyMap
	 *            注入属性
	 * @param app
	 *            application上下文
	 */
	public void addBean(String className, String serviceName, Map<String, Object> propertyMap) {
		try {
			Class<?> clazz = Thread.currentThread().getContextClassLoader().loadClass( className );
			BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition( clazz );
			if (propertyMap != null) {
				Iterator<?> entries = propertyMap.entrySet().iterator();
				Map.Entry<?, ?> entry;
				while (entries.hasNext()) {
					entry = (Map.Entry<?, ?>) entries.next();
					String key = (String) entry.getKey();
					Object val = entry.getValue();
					beanDefinitionBuilder.addPropertyValue( key, val );
				}
			}
			registerBean( serviceName, beanDefinitionBuilder.getRawBeanDefinition() );
		} catch (ClassNotFoundException e) {
			System.out.println( className + ",主动注册失败." );
		}
	}
	
	/**
	 * @desc 向spring容器注册bean
	 * @param beanName
	 * @param beanDefinition
	 */
	public void registerBean(String beanName, BeanDefinition beanDefinition) {
		ConfigurableApplicationContext configurableApplicationContext = (ConfigurableApplicationContext) applicationContext;
		BeanDefinitionRegistry beanDefinitonRegistry = (BeanDefinitionRegistry) configurableApplicationContext.getBeanFactory();
		beanDefinitonRegistry.registerBeanDefinition( beanName, beanDefinition );
	}
	
}
