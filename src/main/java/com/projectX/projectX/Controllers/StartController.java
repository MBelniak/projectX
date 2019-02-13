package com.projectX.projectX.Controllers;

import com.projectX.projectX.service.PartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class StartController {

    private PartyService partyService;

    @Autowired
    public StartController(PartyService partyService) {
        this.partyService = partyService;
    }

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
    public String getParty(Model model, @PathVariable Long id)
    {
        model.addAttribute("party", partyService.getParty(id));
        return "party";
    }
    @RequestMapping("/party_added")
    public String partyAdded()
    {
        return "partyAdded";
    }




}
