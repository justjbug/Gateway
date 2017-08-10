package com.gateway.portal.web.controller.dto;

import java.util.List;

import com.gateway.portal.model.cpi.CpiMethodParam;

/**
 * Created by sun on 2017/8/5 下午4:12.
 */
public class Api {
	/** 名称*/
	private String name;

	/** 接口名称／地址*/
	private String apiName;

	/** 参数列表*/
	private List<CpiMethodParam> params;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getApiName() {
		return apiName;
	}

	public void setApiName(String apiName) {
		this.apiName = apiName;
	}

	public List<CpiMethodParam> getParams() {
		return params;
	}

	public void setParams(List<CpiMethodParam> params) {
		this.params = params;
	}
}
