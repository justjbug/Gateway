package com.gateway.portal.biz.facade.impl;

import com.gateway.portal.biz.facade.ProductFacade;
import com.gateway.portal.biz.service.CpiMethodService;
import com.gateway.portal.biz.service.ProductService;
import com.gateway.portal.dto.ProductIntroductionDTO;
import com.gateway.portal.model.ResultModel;
import com.gateway.portal.model.product.Product;
import com.xiaoleilu.hutool.util.ArrayUtil;
import com.xiaoleilu.hutool.util.CollectionUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author
 * @version $$Id: , v 0.1    Exp $$
 */
@Service("productFacade")
public class ProductFacadeImpl implements ProductFacade{

    @Resource
    private ProductService productService;
    @Resource
    private CpiMethodService cpiMethodService;
    @Override
    public ResultModel<String> createProduct(String name, String code) {
        ResultModel<String> result = new ResultModel<>();

        Map<String,Object> params = new HashMap<>();
        params.put("code",code);
        int count = this.productService.queryByCount(params);
        if(count > 0){
            result.setMessage("code已存在！");
            return result;
        }

        Product product = new Product();
        product.setName(name);
        product.setCode(code);
        int flag = (int)this.productService.create(product);
        if(flag > 0){
            result.setMessage("添加成功！");
            result.setSuccess(true);
        }else {
            result.setMessage("添加失败！");
        }
        return result;
    }

    @Override
    public ResultModel<String> deleteProduct(Integer id) {
        ResultModel<String> result = new ResultModel<>();

        Product p = new Product();
        p.setId(id);
        int flag = this.productService.delete(p,false);
        if(flag > 0){
            //TODO 删除项目后，是否要删除接口
            result.setMessage("删除成功！");
            result.setSuccess(true);
        }else {
            result.setMessage("删除失败！");
        }
        return result;
    }

    @Override
    public List<ProductIntroductionDTO> queryProductIntroductionDTOByPage(Integer currPage, Integer pageSize) {
        List<Product> datas = this.productService.queryByPage(null,currPage,pageSize);
        List<ProductIntroductionDTO> result = new ArrayList<>();
        if(CollectionUtil.isNotEmpty(datas)){
            for (Product product : datas) {
                Map<String,Object> params = new HashMap<>();
                params.put("likeApiName",product.getCode());
                int interfaceCount = this.cpiMethodService.queryByCount(params);
                Integer count = 54342983;
                DecimalFormat df = new DecimalFormat("#,###");
                String requestCount = df.format(count);
                result.add(new ProductIntroductionDTO(product.getId(),product.getName(),product.getCode(),interfaceCount,requestCount));
            }
        }
        return result;
    }

    @Override
    public ResultModel<String> updateProduct(Integer id, String name, String code) {
        ResultModel<String> result = new ResultModel<>();

        Map<String,Object> params = new HashMap<>();
        params.put("code",code);
        params.put("noId",id);
        int count = this.productService.queryByCount(params);
        if(count > 0){
            result.setMessage("code已存在！");
            return result;
        }

        Product p = new Product();
        p.setId(id);
        p.setName(name);
        p.setCode(code);
        int flag = this.productService.update(p);
        if(flag > 0){
            //TODO 更新后是否级联更新接口
            result.setMessage("更新成功！");
            result.setSuccess(true);
        }else {
            result.setMessage("更新失败！");
        }
        return result;
    }
}
