package com.gateway.portal.web.controller.admin.method;

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
 * 更新方法接口
 * @author
 * @version $$Id: , v 0.1    Exp $$
 */
@Controller
@Scope("prototype")
public class UpdateMethodController extends BaseController{


    @Resource
    private CpiMethodFacade cpiMethodFacade;

    @RequestMapping(value = "/admin/method/update", method = { RequestMethod.POST })
    public ModelAndView update(Integer id,String apiName,String apiVersion,String name,String packageName,String rpcMethodName,String open,String logon,String mode,String desc) {
        ModelAndView result = new ModelAndView();

        ResultModel<Integer> resultModel = this.cpiMethodFacade.updateCpiMethod(id, apiName, apiVersion, name, packageName, rpcMethodName, open, logon, mode, desc);

        if(resultModel.isSuccess()){
            super.setSuccessful(result,resultModel.getMessage());
        }else {
            super.setFailMessage(result,resultModel.getMessage());
        }

        return result;
    }

}
