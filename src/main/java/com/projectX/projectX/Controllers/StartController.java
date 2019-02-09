package com.projectX.projectX.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StartController {

    @RequestMapping(value="/new_party")
    public String getIndex() {
        return "addParty";
    }
    @RequestMapping(value = "/search_parties")
    public String getParties()
    {
        return "parties";
    }
    @RequestMapping("/search_party/{id}")
    public String getParty()
    {
        return "party";
    }
    @RequestMapping("/party_added")
    public String partyAdded()
    {
        return "partyAdded";
    }


}
