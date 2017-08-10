package com.gateway.portal.web.controller.admin;

import com.gateway.portal.biz.facade.ProductFacade;
import com.gateway.portal.dto.ProductIntroductionDTO;
import com.gateway.portal.model.product.Product;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author
 * @version $$Id: , v 0.1    Exp $$
 */
@Controller
@Scope("prototype")
public class IndexController {

    @Resource
    private ProductFacade productFacade;

    @RequestMapping(value = "/index", method = { RequestMethod.GET })
    public ModelAndView index() {
        ModelAndView result = new ModelAndView();
        List<ProductIntroductionDTO> productList = this.productFacade.queryProductIntroductionDTOByPage(-1,-1);

        result.addObject("productList",productList);
        return result;
    }
}
