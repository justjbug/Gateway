package com.gateway.portal.web.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author
 * @version $$Id: , v 0.1    Exp $$
 */
@Controller
@Scope("prototype")
public class LogonController {

    @RequestMapping(value = "/logon", method = { RequestMethod.GET })
    public ModelAndView logon() {
        ModelAndView result = new ModelAndView();
        return result;
    }

}
