package com.gateway.portal.biz.facade;

import com.gateway.portal.dto.ProductIntroductionDTO;
import com.gateway.portal.model.ResultModel;
import com.gateway.portal.model.product.Product;

import java.util.List;

/**
 * @author
 * @version $$Id: , v 0.1    Exp $$
 */
public interface ProductFacade {


    /**
     * 添加产品
     * @param name
     * @param code
     * @return
     */
    ResultModel<String> createProduct(String name,String code);

    /**
     * 删除id
     * @param id
     * @return
     */
    ResultModel<String> deleteProduct(Integer id);


    /**
     * 查询产品
     * @param currPage
     * @param pageSize
     * @return
     */
    List<ProductIntroductionDTO> queryProductIntroductionDTOByPage(Integer currPage, Integer pageSize);


    /**
     * 更新产品
     * @param id
     * @param name
     * @param code
     * @return
     */
    ResultModel<String> updateProduct(Integer id,String name,String code);
}
