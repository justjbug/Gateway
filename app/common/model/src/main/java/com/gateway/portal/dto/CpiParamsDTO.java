package com.gateway.portal.dto;

import java.io.Serializable;

/**
 * The type Cpi params dto.
 *
 * @author
 * @version $$Id : , v 0.1 Exp $$
 */
public class CpiParamsDTO implements Serializable {
	
	private static final long	serialVersionUID	= -8010541505669189577L;
	/** 参数名 */
	private String				name;
	/** 参数类型 */
	private String				clazz;
	/** 排序 */
	private Integer				index;
	/** 是否必填 */
	private String				required;
	/** 描述 */
	private String				desc;
	
	private String				domain;
	
	private Integer				length;
	
	/**
	 * Instantiates a new Cpi params dto.
	 */
	public CpiParamsDTO() {
	}
	
	/**
	 * Instantiates a new Cpi params dto.
	 *
	 * @param name the name
	 * @param clazz the clazz
	 * @param index the index
	 * @param required the required
	 * @param desc the desc
	 * @param domain the domain
	 * @param length the length
	 */
	public CpiParamsDTO(String name, String clazz, Integer index, String required, String desc, String domain, Integer length) {
		this.name = name;
		this.clazz = clazz;
		this.index = index;
		this.required = required;
		this.desc = desc;
		this.domain = domain;
		this.length = length;
	}
	
	/**
	 * Gets length.
	 *
	 * @return the length
	 */
	public Integer getLength() {
		return length;
	}
	
	/**
	 * Sets length.
	 *
	 * @param length the length
	 */
	public void setLength(Integer length) {
		this.length = length;
	}
	
	/**
	 * Gets domain.
	 *
	 * @return the domain
	 */
	public String getDomain() {
		return domain;
	}
	
	/**
	 * Sets domain.
	 *
	 * @param domain the domain
	 */
	public void setDomain(String domain) {
		this.domain = domain;
	}
	
	/**
	 * Gets name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets name.
	 *
	 * @param name the name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Gets clazz.
	 *
	 * @return the clazz
	 */
	public String getClazz() {
		return clazz;
	}
	
	/**
	 * Sets clazz.
	 *
	 * @param clazz the clazz
	 */
	public void setClazz(String clazz) {
		this.clazz = clazz;
	}
	
	/**
	 * Gets index.
	 *
	 * @return the index
	 */
	public Integer getIndex() {
		return index;
	}
	
	/**
	 * Sets index.
	 *
	 * @param index the index
	 */
	public void setIndex(Integer index) {
		this.index = index;
	}
	
	/**
	 * Gets required.
	 *
	 * @return the required
	 */
	public String getRequired() {
		return required;
	}
	
	/**
	 * Sets required.
	 *
	 * @param required the required
	 */
	public void setRequired(String required) {
		this.required = required;
	}
	
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
}
