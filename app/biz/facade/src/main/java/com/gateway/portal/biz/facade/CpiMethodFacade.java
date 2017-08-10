package com.gateway.portal.biz.facade;

import com.gateway.portal.dto.CpiMethodDTO;

/**
 * @author
 * @version $$Id: , v 0.1    Exp $$
 */
public interface CpiMethodFacade {

    /**
     * 根据方法名和版本号，获取方法对象
     * @param methodName
     * @param version
     * @return
     */
    CpiMethodDTO getCpimethodDTO(String methodName, String version);
}
