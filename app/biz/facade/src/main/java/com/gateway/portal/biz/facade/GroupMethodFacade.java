package com.gateway.portal.biz.facade;

import com.gateway.portal.dto.GroupIntroductionDTO;
import com.gateway.portal.model.ResultModel;

import java.util.List;

/**
 * @author
 * @version $$Id: , v 0.1    Exp $$
 */
public interface GroupMethodFacade {


    /**
     * 添加产品
     * @param name
     * @param code
     * @return
     */
    ResultModel<String> createGroup(String name, String code);

    /**
     * 删除id
     * @param id
     * @return
     */
    ResultModel<String> deleteGroup(Integer id);


    /**
     * 查询产品
     * @param currPage
     * @param pageSize
     * @return
     */
    List<GroupIntroductionDTO> queryGroupIntroductionDTOByPage(Integer currPage, Integer pageSize);


    /**
     * 更新产品
     * @param id
     * @param name
     * @param code
     * @return
     */
    ResultModel<String> updateGreoup(Integer id, String name, String code);
}
