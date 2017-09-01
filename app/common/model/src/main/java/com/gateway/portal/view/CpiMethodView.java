package com.gateway.portal.view;

import java.io.Serializable;

/**
 * The type Cpi method view.
 *
 * @author
 * @version $$Id : , v 0.1 Exp $$
 */
public class CpiMethodView implements Serializable {
	
	private static final long	serialVersionUID	= 3905493899515332772L;
	
	/**
	 * 名称
	 */
	private String				name;
	/**
	 * api版本号
	 */
	private String				apiVersion;
	/**
	 * api名称
	 */
	private String				apiName;
	/**
	 * 是否登录限制
	 */
	private String				logon;
	/**
	 * 接口开关状态
	 */
	private String				open;
	/**
	 * 主键id
	 */
	private Integer				id;
	/**
	 * 报名方法名参数
	 */
	private String				classpathMethod;
	
	private String				mode;
	
	/**
	 * Instantiates a new Cpi method view.
	 *
	 * @param name the name
	 * @param apiVersion the api version
	 * @param apiName the api name
	 * @param logon the logon
	 * @param open the open
	 * @param id the id
	 * @param classpathMethod the classpath method
	 * @param mode the mode
	 */
	public CpiMethodView(String name, String apiVersion, String apiName, String logon, String open, Integer id, String classpathMethod, String mode) {
		this.name = name;
		this.apiVersion = apiVersion;
		this.apiName = apiName;
		this.logon = logon;
		this.open = open;
		this.id = id;
		this.classpathMethod = classpathMethod;
		this.mode = mode;
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
	
	/**
	 * Gets logon.
	 *
	 * @return the logon
	 */
	public String getLogon() {
		return logon;
	}
	
	/**
	 * Sets logon.
	 *
	 * @param logon the logon
	 */
	public void setLogon(String logon) {
		this.logon = logon;
	}
	
	/**
	 * Gets open.
	 *
	 * @return the open
	 */
	public String getOpen() {
		return open;
	}
	
	/**
	 * Sets open.
	 *
	 * @param open the open
	 */
	public void setOpen(String open) {
		this.open = open;
	}
	
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
	 * Gets classpath method.
	 *
	 * @return the classpath method
	 */
	public String getClasspathMethod() {
		return classpathMethod;
	}
	
	/**
	 * Sets classpath method.
	 *
	 * @param classpathMethod the classpath method
	 */
	public void setClasspathMethod(String classpathMethod) {
		this.classpathMethod = classpathMethod;
	}
	
	/**
	 * Gets mode.
	 *
	 * @return the mode
	 */
	public String getMode() {
		return mode;
	}
	
	/**
	 * Sets mode.
	 *
	 * @param mode the mode
	 */
	public void setMode(String mode) {
		this.mode = mode;
	}
}
