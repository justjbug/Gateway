package com.gateway.portal.biz.redis;

/**
 * Created by sun on 2017/7/21 上午10:27.
 */
public enum RedisCacheNameSpace {
	/****************************     缓存name         *********************************/
	METHOD_LIST("METHOD_LIST", "方法对象集合");


	String code;
	String desc;
	RedisCacheNameSpace(String code, String desc){
		this.code = code;
		this.desc = desc;
	}
	public String getCode(){
		return code;
	}
	public String getDesc(){
		return desc;
	}

}
