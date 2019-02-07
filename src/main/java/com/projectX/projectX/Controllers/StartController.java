package com.projectX.projectX.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class StartController {

    @RequestMapping(value="/new_party")
    public String getIndex() {
        return "addParty";
    }
    @RequestMapping(value = "/search_party")
    public String getParties()
    {
        return "parties";
    }


}
