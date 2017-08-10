/**
 * mario.com Inc.
 * Copyright (c) 2014-2017 All Rights Reserved.
 */
package com.gateway.portal.dal.dao.impl;

import org.springframework.stereotype.Repository;

import com.gateway.portal.core.dao.impl.BaseDAOImpl;
import com.gateway.portal.dal.dao.CpiMethodParamDAO;
import com.gateway.portal.model.cpi.CpiMethodParam;

/**
 * 
 * @author liming
 * @version $Id: CpiMethodParamDAOImpl.java, v 0.1 2017年7月2日 下午5:04:47 liming
 *          Exp $
 */
@Repository("cpiMethodParamDAO")
public class CpiMethodParamDAOImpl extends BaseDAOImpl<CpiMethodParam> implements CpiMethodParamDAO {
	
	/**
	 * @param clazz
	 */
	public CpiMethodParamDAOImpl() {
		super( CpiMethodParam.class );
	}
	
}
