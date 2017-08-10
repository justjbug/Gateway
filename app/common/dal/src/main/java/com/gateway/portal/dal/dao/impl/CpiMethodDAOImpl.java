/**
 * mario.com Inc.
 * Copyright (c) 2014-2017 All Rights Reserved.
 */
package com.gateway.portal.dal.dao.impl;

import org.springframework.stereotype.Repository;

import com.gateway.portal.core.dao.impl.BaseDAOImpl;
import com.gateway.portal.dal.dao.CpiMethodDAO;
import com.gateway.portal.model.cpi.CpiMethod;

/**
 * 
 * @author liming
 * @version $Id: CpiMethodDAOImpl.java, v 0.1 2017年7月2日 下午2:51:11 liming Exp $
 */
@Repository("cpiMethodDAO")
public class CpiMethodDAOImpl extends BaseDAOImpl<CpiMethod> implements CpiMethodDAO {
	
	/**
	 * @param clazz
	 */
	public CpiMethodDAOImpl() {
		super( CpiMethod.class );
	}
	
}
