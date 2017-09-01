package com.gateway.portal.web.controller.admin.group;

import com.gateway.portal.biz.facade.GroupMethodFacade;
import com.gateway.portal.dto.GroupIntroductionDTO;
import com.gateway.portal.model.ResultModel;
import com.gateway.portal.web.controller.BaseController;
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
public class DeleteGroupController extends BaseController{


    @Resource
    private GroupMethodFacade groupMethodFacade;

    @RequestMapping(value = "/admin/group/delete", method = { RequestMethod.POST })
    public ModelAndView delete(Integer id) {
        ModelAndView result = new ModelAndView();
        ResultModel<String> resultModel = this.groupMethodFacade.deleteGroup(id);
        super.setResultModel(result,resultModel);
        return result;
    }

}
