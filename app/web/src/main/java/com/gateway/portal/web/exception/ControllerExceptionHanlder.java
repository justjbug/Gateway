/**
 * mario.com Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package com.gateway.portal.web.exception;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gateway.portal.biz.bean.GatewayFactoryBean;
import com.gateway.portal.core.utils.logger.LoggerUtils;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @author liming
 * @version $Id: ControllerExceptionHanlder.java, v 0.1 2015年5月23日 下午5:47:47 liming Exp $
 */
public class ControllerExceptionHanlder implements HandlerExceptionResolver{

	private static final Logger INIT_RPC_LOGGER	= Logger.getLogger( ControllerExceptionHanlder.class );

	/** 
	 * @see org.springframework.web.servlet.HandlerExceptionResolver#resolveException(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, java.lang.Exception)
	 */
	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		Map<String, Object> model = new HashMap<String, Object>();  
        model.put("ex", ex);
		LoggerUtils.defaultPrint(ex,"error");
        return new ModelAndView("error", model);  
	}
	
	

}
