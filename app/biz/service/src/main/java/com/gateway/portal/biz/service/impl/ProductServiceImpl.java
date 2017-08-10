package com.gateway.portal.biz.service.impl;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import com.gateway.portal.biz.service.ProductService;
import org.springframework.stereotype.Service;

import com.gateway.portal.core.service.impl.BaseServiceImpl;
import com.gateway.portal.dal.dao.ProductDAO;
import com.gateway.portal.model.product.Product;

/**
 * @author
 * @version $$Id: , v 0.1 Exp $$
 */
@Service("productService")
public class ProductServiceImpl extends BaseServiceImpl<Product> implements ProductService{

    @Resource
    private ProductDAO productDAO;

    @PostConstruct
    public void init(){
        super.setBaseDao(productDAO);
    }

}
