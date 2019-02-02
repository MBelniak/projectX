package com.projectX.projectX.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class StartController {

    @RequestMapping(value="/add")
    public ModelAndView getIndex() {
        return new ModelAndView("add");
    }



}
