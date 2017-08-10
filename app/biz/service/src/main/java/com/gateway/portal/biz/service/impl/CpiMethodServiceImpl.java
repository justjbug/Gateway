/**
 * mario.com Inc.
 * Copyright (c) 2014-2017 All Rights Reserved.
 */
package com.gateway.portal.biz.service.impl;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gateway.portal.biz.service.CpiMethodService;
import com.gateway.portal.core.service.impl.BaseServiceImpl;
import com.gateway.portal.dal.dao.CpiMethodDAO;
import com.gateway.portal.model.cpi.CpiMethod;

/**
 * 
 * @author liming
 * @version $Id: CpiMethodServiceImpl.java, v 0.1 2017年7月2日 下午2:52:45 liming Exp
 *          $
 */
@Service("cpiMethodService")
public class CpiMethodServiceImpl extends BaseServiceImpl<CpiMethod> implements CpiMethodService {
	
	@Resource
	private CpiMethodDAO	cpiMethodDAO;
	
	@PostConstruct
	public void init() {
		super.setBaseDao( cpiMethodDAO );
	}
	
}
