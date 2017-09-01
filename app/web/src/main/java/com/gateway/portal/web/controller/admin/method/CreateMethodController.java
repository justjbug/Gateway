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
 * @author
 * @version $$Id: , v 0.1    Exp $$
 */
@Controller
@Scope("prototype")
public class CreateMethodController extends BaseController{


    @Resource
    private CpiMethodFacade cpiMethodFacade;

    /**
     * 添加方法
     * @param apiName
     * @param apiVersion
     * @param name
     * @param packageName
     * @param rpcMethodName
     * @param open
     * @param logon
     * @param mode
     * @param desc
     * @return
     */
    @RequestMapping(value = "/admin/method/create", method = { RequestMethod.POST })
    public ModelAndView create(String apiName,String apiVersion,String name,String packageName,String rpcMethodName,String open,String logon,String mode,String desc) {
        ModelAndView result = new ModelAndView();

        ResultModel<Integer> resultModel =  this.cpiMethodFacade.createCpiMethod(apiName, apiVersion, name, packageName, rpcMethodName, open, logon, mode, desc);

        if(resultModel.isSuccess()){
            super.setSuccessful(result,resultModel.getMessage());
        }else {
            super.setFailMessage(result,resultModel.getMessage());
        }
        return result;
    }
}
