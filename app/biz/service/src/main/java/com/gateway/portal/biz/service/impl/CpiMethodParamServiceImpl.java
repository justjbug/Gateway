/**
 * mario.com Inc.
 * Copyright (c) 2014-2017 All Rights Reserved.
 */
package com.gateway.portal.biz.service.impl;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gateway.portal.biz.service.CpiMethodParamService;
import com.gateway.portal.core.service.impl.BaseServiceImpl;
import com.gateway.portal.dal.dao.CpiMethodParamDAO;
import com.gateway.portal.model.cpi.CpiMethodParam;

/**
 * 
 * @author liming
 * @version $Id: CpiMethodParamServiceImpl.java, v 0.1 2017年7月2日 下午5:06:03
 *          liming Exp $
 */
@Service("cpiMethodParamService")
public class CpiMethodParamServiceImpl extends BaseServiceImpl<CpiMethodParam> implements CpiMethodParamService {
	
	@Resource
	private CpiMethodParamDAO	cpiMethodParamDAO;
	
	@PostConstruct
	public void init() {
		super.setBaseDao( cpiMethodParamDAO );
	}
	
}
