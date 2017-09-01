package com.gateway.portal.biz.cache;

import com.gateway.portal.dto.CpiMethodDTO;

import java.util.HashMap;
import java.util.Map;

/**
 * @author
 * @version $$Id: , v 0.1    Exp $$
 */
public class LocalMethodCache {

    public static Map<String, CpiMethodDTO> interfaceMap	= null;

    static {
        interfaceMap = new HashMap<>();
    }



}
