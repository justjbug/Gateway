package com.gateway.portal.dto;

import java.io.Serializable;

/**
 * The type GroupMethod introduction dto.
 *
 * @author
 * @version $$Id : , v 0.1 Exp $$
 */
public class GroupIntroductionDTO implements Serializable {
	
	/** 产品id */
	private Integer	id;
	
	/** 产品名称 */
	private String	name;
	
	/** code */
	private String	code;
	
	/** 接口数量 */
	private Integer	interfaceCount;
	
	/** 当日调用量 */
	private String	requestCount;
	
	/**
	 * Gets id.
	 *
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	
	/**
	 * Sets id.
	 *
	 * @param id the id
	 */
	public void setId(Integer id) {
		this.id = id;
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
	 * Gets code.
	 *
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	
	/**
	 * Sets code.
	 *
	 * @param code the code
	 */
	public void setCode(String code) {
		this.code = code;
	}
	
	/**
	 * Gets interface count.
	 *
	 * @return the interface count
	 */
	public Integer getInterfaceCount() {
		return interfaceCount;
	}
	
	/**
	 * Sets interface count.
	 *
	 * @param interfaceCount the interface count
	 */
	public void setInterfaceCount(Integer interfaceCount) {
		this.interfaceCount = interfaceCount;
	}
	
	/**
	 * Gets request count.
	 *
	 * @return the request count
	 */
	public String getRequestCount() {
		return requestCount;
	}
	
	/**
	 * Sets request count.
	 *
	 * @param requestCount the request count
	 */
	public void setRequestCount(String requestCount) {
		this.requestCount = requestCount;
	}
	
	/**
	 * Instantiates a new GroupMethod introduction dto.
	 *
	 * @param id the group id
	 * @param name the name
	 * @param code the code
	 * @param interfaceCount the interface count
	 * @param requestCount the request count
	 */
	public GroupIntroductionDTO(Integer id, String name, String code, Integer interfaceCount, String requestCount) {
		this.id = id;
		this.name = name;
		this.code = code;
		this.interfaceCount = interfaceCount;
		this.requestCount = requestCount;
	}
}
