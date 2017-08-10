package com.gateway.portal.dal.dao.impl;

import com.gateway.portal.dal.dao.ProductDAO;
import org.springframework.stereotype.Repository;

import com.gateway.portal.core.dao.impl.BaseDAOImpl;
import com.gateway.portal.model.product.Product;

/**
 * @author
 * @version $$Id: , v 0.1    Exp $$
 */
@Repository("productDAO")
public class ProductDAOImpl extends BaseDAOImpl<Product> implements ProductDAO{

    public ProductDAOImpl() {
        super(Product.class);
    }
}
