package com.gateway.portal.web.controller.admin.method.param;

import com.gateway.portal.biz.facade.CpiMethodFacade;
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
public class UpdateMethodParamController extends BaseController{

    @Resource
    private CpiMethodFacade cpiMethodFacade;

    @RequestMapping(value = "/admin/method/param/update", method = { RequestMethod.POST})
    public ModelAndView delete(Integer id,Integer methodId,String paramName,String paramType,String paramDesc,Integer index,String require,String domain,Integer length) {
        ModelAndView result = new ModelAndView();

        ResultModel<String> resultModel = this.cpiMethodFacade.updateCpiMethodParam(id, methodId, paramName, paramType, paramDesc, index, require, domain, length);
        if(resultModel.isSuccess()){
            super.setSuccessful(result,resultModel.getMessage());
        }else {
            super.setFailMessage(result,resultModel.getMessage());
        }

        return result;
    }


}
