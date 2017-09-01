/**
 * mario.com Inc.
 * Copyright (c) 2014-2017 All Rights Reserved.
 */
package com.gateway.portal.model.cpi;

import java.io.Serializable;

/**
 * The type Cpi method.
 *
 * @author liming
 * @version $Id : CpiMethod.java, v 0.1 2017年7月2日 下午2:46:50 liming Exp $
 */
public class CpiMethod implements Serializable {
	
	/**  */
	private static final long	serialVersionUID	= -3820946427759493330L;
	
	private String				id;
	
	private String				name;
	
	private String				packageName;
	
	private String				rpcMethodName;
	
	private String				open;
	
	private String				apiVersion;
	
	private String				apiName;
	
	private String				logon;
	
	private String				mode;
	
	private String				desc;
	
	/**
	 * Gets desc.
	 *
	 * @return the desc
	 */
	public String getDesc() {
		return desc;
	}
	
	/**
	 * Sets desc.
	 *
	 * @param desc the desc
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	/**
	 * Getter method for property <tt>mode</tt>.
	 *
	 * @return property value of mode
	 */
	public String getMode() {
		return mode;
	}
	
	/**
	 * Setter method for property <tt>mode</tt>.
	 *
	 * @param mode value to be assigned to property mode
	 */
	public void setMode(String mode) {
		this.mode = mode;
	}
	
	/**
	 * Getter method for property <tt>logon</tt>.
	 *
	 * @return property value of logon
	 */
	public String getLogon() {
		return logon;
	}
	
	/**
	 * Setter method for property <tt>logon</tt>.
	 *
	 * @param logon value to be assigned to property logon
	 */
	public void setLogon(String logon) {
		this.logon = logon;
	}
	
	/**
	 * Getter method for property <tt>id</tt>.
	 *
	 * @return property value of id
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * Setter method for property <tt>id</tt>.
	 *
	 * @param id value to be assigned to property id
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * Getter method for property <tt>name</tt>.
	 *
	 * @return property value of name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Setter method for property <tt>name</tt>.
	 *
	 * @param name value to be assigned to property name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Getter method for property <tt>packageName</tt>.
	 *
	 * @return property value of packageName
	 */
	public String getPackageName() {
		return packageName;
	}
	
	/**
	 * Setter method for property <tt>packageName</tt>.
	 *
	 * @param packageName value to be assigned to property packageName
	 */
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	
	/**
	 * Getter method for property <tt>rpcMethodName</tt>.
	 *
	 * @return property value of rpcMethodName
	 */
	public String getRpcMethodName() {
		return rpcMethodName;
	}
	
	/**
	 * Setter method for property <tt>rpcMethodName</tt>.
	 *
	 * @param rpcMethodName value to be assigned to property rpcMethodName
	 */
	public void setRpcMethodName(String rpcMethodName) {
		this.rpcMethodName = rpcMethodName;
	}
	
	/**
	 * Getter method for property <tt>open</tt>.
	 *
	 * @return property value of open
	 */
	public String getOpen() {
		return open;
	}
	
	/**
	 * Setter method for property <tt>open</tt>.
	 *
	 * @param open value to be assigned to property open
	 */
	public void setOpen(String open) {
		this.open = open;
	}
	
	/**
	 * Gets api version.
	 *
	 * @return the api version
	 */
	public String getApiVersion() {
		return apiVersion;
	}
	
	/**
	 * Sets api version.
	 *
	 * @param apiVersion the api version
	 */
	public void setApiVersion(String apiVersion) {
		this.apiVersion = apiVersion;
	}
	
	/**
	 * Gets api name.
	 *
	 * @return the api name
	 */
	public String getApiName() {
		return apiName;
	}
	
	/**
	 * Sets api name.
	 *
	 * @param apiName the api name
	 */
	public void setApiName(String apiName) {
		this.apiName = apiName;
	}
}
