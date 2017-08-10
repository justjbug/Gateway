/**
 * mario.com Inc.
 * Copyright (c) 2014-2017 All Rights Reserved.
 */
package com.gateway.portal.model.cpi;

import java.io.Serializable;

/**
 * The type Cpi method param.
 *
 * @author liming
 * @version $Id : CpiMethodParam.java, v 0.1 2017年7月2日 下午5:00:51 liming Exp $
 */
public class CpiMethodParam implements Serializable {
	
	/**  */
	private static final long	serialVersionUID	= -1784803374044478391L;
	
	private Integer				id;
	
	private Integer				methodId;
	
	private String				paramName;
	
	private String				paramType;
	
	private String				paramDesc;
	
	private Integer				index;
	
	private String				required;
	
	private String				domain;
	
	private Integer				length;
	
	/** 示例值 */
	private String				exampleValue;
	
	/**
	 * Getter method for property <tt>length</tt>.
	 *
	 * @return property value of length
	 */
	public Integer getLength() {
		return length;
	}
	
	/**
	 * Setter method for property <tt>length</tt>.
	 *
	 * @param length value to be assigned to property length
	 */
	public void setLength(Integer length) {
		this.length = length;
	}
	
	/**
	 * Getter method for property <tt>domain</tt>.
	 *
	 * @return property value of domain
	 */
	public String getDomain() {
		return domain;
	}
	
	/**
	 * Setter method for property <tt>domain</tt>.
	 *
	 * @param domain value to be assigned to property domain
	 */
	public void setDomain(String domain) {
		this.domain = domain;
	}
	
	/**
	 * Getter method for property <tt>required</tt>.
	 *
	 * @return property value of required
	 */
	public String getRequired() {
		return required;
	}
	
	/**
	 * Setter method for property <tt>required</tt>.
	 *
	 * @param required value to be assigned to property required
	 */
	public void setRequired(String required) {
		this.required = required;
	}
	
	/**
	 * Getter method for property <tt>id</tt>.
	 *
	 * @return property value of id
	 */
	public Integer getId() {
		return id;
	}
	
	/**
	 * Setter method for property <tt>id</tt>.
	 *
	 * @param id value to be assigned to property id
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * Getter method for property <tt>methodId</tt>.
	 *
	 * @return property value of methodId
	 */
	public Integer getMethodId() {
		return methodId;
	}
	
	/**
	 * Setter method for property <tt>methodId</tt>.
	 *
	 * @param methodId value to be assigned to property methodId
	 */
	public void setMethodId(Integer methodId) {
		this.methodId = methodId;
	}
	
	/**
	 * Getter method for property <tt>paramName</tt>.
	 *
	 * @return property value of paramName
	 */
	public String getParamName() {
		return paramName;
	}
	
	/**
	 * Setter method for property <tt>paramName</tt>.
	 *
	 * @param paramName value to be assigned to property paramName
	 */
	public void setParamName(String paramName) {
		this.paramName = paramName;
	}
	
	/**
	 * Getter method for property <tt>paramType</tt>.
	 *
	 * @return property value of paramType
	 */
	public String getParamType() {
		return paramType;
	}
	
	/**
	 * Setter method for property <tt>paramType</tt>.
	 *
	 * @param paramType value to be assigned to property paramType
	 */
	public void setParamType(String paramType) {
		this.paramType = paramType;
	}
	
	/**
	 * Getter method for property <tt>paramDesc</tt>.
	 *
	 * @return property value of paramDesc
	 */
	public String getParamDesc() {
		return paramDesc;
	}
	
	/**
	 * Setter method for property <tt>paramDesc</tt>.
	 *
	 * @param paramDesc value to be assigned to property paramDesc
	 */
	public void setParamDesc(String paramDesc) {
		this.paramDesc = paramDesc;
	}
	
	/**
	 * Getter method for property <tt>index</tt>.
	 *
	 * @return property value of index
	 */
	public Integer getIndex() {
		return index;
	}
	
	/**
	 * Setter method for property <tt>index</tt>.
	 *
	 * @param index value to be assigned to property index
	 */
	public void setIndex(Integer index) {
		this.index = index;
	}
	
	/**
	 * Gets example value.
	 *
	 * @return the example value
	 */
	public String getExampleValue() {
		return exampleValue;
	}
	
	/**
	 * Sets example value.
	 *
	 * @param exampleValue the example value
	 */
	public void setExampleValue(String exampleValue) {
		this.exampleValue = exampleValue;
	}
}
