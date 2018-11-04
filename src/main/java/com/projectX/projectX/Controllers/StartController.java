package com.projectX.projectX.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class StartController {
  //  @RequestMapping("/")
    public ModelAndView getParties()
    {
        ModelAndView mv = new ModelAndView();

        mv.setViewName("index.jsp");
        //mv.addObject(partyList);


        return mv;
    }

}
