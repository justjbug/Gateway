package com.gateway.portal.web.controller.admin.method.param;

import com.gateway.portal.biz.facade.CpiMethodFacade;
import com.gateway.portal.model.ResultModel;
import com.gateway.portal.model.cpi.CpiMethodParam;
import com.gateway.portal.web.controller.BaseController;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
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
public class BatchUpdateMethodParamController extends BaseController{

    @Resource
    private CpiMethodFacade cpiMethodFacade;


    @RequestMapping(value = "/admin/method/param/batchUpdate", method = { RequestMethod.POST})
    public ModelAndView delete(@RequestBody List<CpiMethodParam> datas) {
        ModelAndView result = new ModelAndView();

        ResultModel<String> resultModel = this.cpiMethodFacade.batchUpdateCpiMethodParam(datas);

        if(resultModel.isSuccess()){
            super.setSuccessful(result,resultModel.getMessage());
        }else {
            super.setFailMessage(result,resultModel.getMessage());
        }
        return result;
    }
}
