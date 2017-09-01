package com.gateway.portal.web.controller.admin.method;

import javax.annotation.Resource;

import com.gateway.portal.core.utils.PageModel;
import com.gateway.portal.model.cpi.CpiMethod;
import com.gateway.portal.view.CpiMethodView;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gateway.portal.biz.facade.CpiMethodFacade;
import com.gateway.portal.web.controller.BaseController;

/**
 * 方法列表控制器
 * @author
 * @version $$Id: , v 0.1    Exp $$
 */
@Controller
@Scope("prototype")
public class MethodListController extends BaseController{


    @Resource
    private CpiMethodFacade cpiMethodFacade;

    /**
     * 方法列表
     * @param apiName
     * @param apiVersion
     * @param name
     * @param currPage
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/admin/method/list", method = { RequestMethod.GET })
    public ModelAndView methodList(String apiName,String apiVersion,String name,Integer currPage,Integer pageSize) {
        ModelAndView result = new ModelAndView();

        PageModel<CpiMethodView> page = this.cpiMethodFacade.queryCpiMethodByPage(apiName,apiVersion,name,currPage,pageSize);

        result.addObject("data",page.getDatas());
        result.addObject("count",page.getCount());

        super.setSuccessful(result,"查询成功！");
        return result;
    }

}
