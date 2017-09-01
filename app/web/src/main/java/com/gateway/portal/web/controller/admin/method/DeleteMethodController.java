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
 * @version $$Id: , v 0.1 Exp $$
 */
@Controller
@Scope("prototype")
public class DeleteMethodController extends BaseController {


    @Resource
    private CpiMethodFacade cpiMethodFacade;

    /**
     * 删除方法
     * @param id
     * @return
     */
	@RequestMapping(value = "/admin/method/delete", method = { RequestMethod.POST })
	public ModelAndView delete(Integer id) {
		ModelAndView result = new ModelAndView();

        ResultModel<Integer> resultModel = this.cpiMethodFacade.deleteCpiMethod(id);

        if(resultModel.isSuccess()){
            super.setSuccessful(result,resultModel.getMessage());
        }else {
            super.setFailMessage(result,resultModel.getMessage());
        }
		return result;
	}
}
