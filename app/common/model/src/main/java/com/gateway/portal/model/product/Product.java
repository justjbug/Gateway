package com.gateway.portal.model.product;

import java.io.Serializable;
import java.util.Date;

/**
 * The type Product.
 *
 * @author
 * @version $$Id : , v 0.1 Exp $$
 */
public class Product implements Serializable {
	
	private Integer	id;
	
	private String	name;
	
	private String	code;
	
	private Date	createTime;
	
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
	 * Gets create time.
	 *
	 * @return the create time
	 */
	public Date getCreateTime() {
		return createTime;
	}
	
	/**
	 * Sets create time.
	 *
	 * @param createTime the create time
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
