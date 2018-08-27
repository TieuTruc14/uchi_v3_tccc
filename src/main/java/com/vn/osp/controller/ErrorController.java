package com.vn.osp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by tranv on 3/6/2017.
 */
@Controller
public class ErrorController {
    @RequestMapping(value = "/400", method = RequestMethod.GET)
    public String error400() {
        return "/error/404";
    }

    @RequestMapping(value = "/404", method = RequestMethod.GET)
    public String error404() {
        return "/error/404";
    }

    @RequestMapping(value = "/500", method = RequestMethod.GET)
    public String error500() {

        return "/error/404";
    }
}
