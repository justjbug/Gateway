package com.gateway.portal.dto;

import java.io.Serializable;

/**
 * The type Product introduction dto.
 *
 * @author
 * @version $$Id : , v 0.1 Exp $$
 */
public class ProductIntroductionDTO implements Serializable {
	
	/** 产品id */
	private Integer	productId;
	
	/** 产品名称 */
	private String	name;
	
	/** code */
	private String	code;
	
	/** 接口数量 */
	private Integer	interfaceCount;
	
	/** 当日调用量 */
	private String	requestCount;
	
	/**
	 * Gets product id.
	 *
	 * @return the product id
	 */
	public Integer getProductId() {
		return productId;
	}
	
	/**
	 * Sets product id.
	 *
	 * @param productId the product id
	 */
	public void setProductId(Integer productId) {
		this.productId = productId;
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
	 * Instantiates a new Product introduction dto.
	 *
	 * @param productId the product id
	 * @param name the name
	 * @param code the code
	 * @param interfaceCount the interface count
	 * @param requestCount the request count
	 */
	public ProductIntroductionDTO(Integer productId, String name, String code, Integer interfaceCount, String requestCount) {
		this.productId = productId;
		this.name = name;
		this.code = code;
		this.interfaceCount = interfaceCount;
		this.requestCount = requestCount;
	}
}
