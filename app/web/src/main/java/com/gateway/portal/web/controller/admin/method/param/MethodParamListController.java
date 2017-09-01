package com.gateway.portal.web.controller.admin.method.param;

import com.gateway.portal.biz.facade.CpiMethodFacade;
import com.gateway.portal.core.utils.PageModel;
import com.gateway.portal.model.cpi.CpiMethodParam;
import com.gateway.portal.web.controller.BaseController;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * @author
 * @version $$Id: , v 0.1    Exp $$
 */
@Controller
@Scope("prototype")
public class MethodParamListController extends BaseController{

    @Resource
    private CpiMethodFacade cpiMethodFacade;

    /**
     *
     * @param currPage
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/admin/method/param/list", method = { RequestMethod.GET })
    public ModelAndView methodParamList(Integer methodId,Integer currPage, Integer pageSize) {
        ModelAndView result = new ModelAndView();

        if(methodId == null){
            super.setFailMessage(result,"methodId不能为空！");
            return result;
        }

        PageModel<CpiMethodParam> page = cpiMethodFacade.queryCpiMethodParamByPage(methodId,currPage,pageSize);
        result.addObject("data",page.getDatas());
        result.addObject("count",page.getCount());

        return result;
    }

}
