package com.gateway.portal.web.controller.admin.group;

import com.gateway.portal.biz.facade.GroupMethodFacade;
import com.gateway.portal.model.ResultModel;
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
public class UpdateGroupController extends BaseController{

    @Resource
    private GroupMethodFacade groupMethodFacade;

    @RequestMapping(value = "/admin/group/update", method = { RequestMethod.POST })
    public ModelAndView create(Integer id,String name, String code) {
        ModelAndView result = new ModelAndView();
        ResultModel<String> resultModel = this.groupMethodFacade.updateGreoup(id,name,code);
        super.setResultModel(result,resultModel);
        return result;
    }
}
