package com.gateway.portal.dto;

import java.io.Serializable;
import java.util.List;

/**
 * The type Cpi method dto.
 *
 * @author
 * @version $$Id : , v 0.1 Exp $$
 */
public class CpiMethodDTO implements Serializable {
	
	private static final long	serialVersionUID	= -4929908774837674680L;
	private String				apiName;
	
	private String				apiVersion;
	
	private String				logon;
	
	private String				methodName;
	
	private String				packageName;
	
	private List<CpiParamsDTO>	params;
	
	/**
	 * Gets method name.
	 *
	 * @return the method name
	 */
	public String getMethodName() {
		return methodName;
	}
	
	/**
	 * Sets method name.
	 *
	 * @param methodName the method name
	 */
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	
	/**
	 * Gets package name.
	 *
	 * @return the package name
	 */
	public String getPackageName() {
		return packageName;
	}
	
	/**
	 * Sets package name.
	 *
	 * @param packageName the package name
	 */
	public void setPackageName(String packageName) {
		this.packageName = packageName;
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
	 * Gets params.
	 *
	 * @return the params
	 */
	public List<CpiParamsDTO> getParams() {
		return params;
	}
	
	/**
	 * Sets params.
	 *
	 * @param params the params
	 */
	public void setParams(List<CpiParamsDTO> params) {
		this.params = params;
	}
}
