package com.gateway.portal.web.controller.admin.group;

import com.gateway.portal.biz.facade.GroupMethodFacade;
import com.gateway.portal.dto.GroupIntroductionDTO;
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
public class GroupListController {

    @Resource
    private GroupMethodFacade groupMethodFacade;

    @RequestMapping(value = "/admin/group/list", method = { RequestMethod.GET })
    public ModelAndView index() {
        ModelAndView result = new ModelAndView();
        List<GroupIntroductionDTO> groupList = this.groupMethodFacade.queryGroupIntroductionDTOByPage(-1,-1);

        result.addObject("data",groupList);
        return result;
    }
}
