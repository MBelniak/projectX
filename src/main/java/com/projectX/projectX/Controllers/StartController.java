package com.projectX.projectX.Controllers;

import com.projectX.projectX.service.PartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;


@Controller
public class StartController {

    private PartyService partyService;

    @Autowired
    public StartController(PartyService partyService) {
        this.partyService = partyService;
    }


    @RequestMapping("/new_party")
    public String addPartyPage() {
        return "addParty";
    }

    @RequestMapping("/search_parties")
    public String searchPartyPage()
    {
        return "parties";
    }

    @RequestMapping("/search_party/{id}")
    public String partyPage(Model model, @PathVariable Long id)
    {
        model.addAttribute("party", partyService.getParty(id));
        return "party";
    }

    @RequestMapping("/party_added")
    public String partyAddedPage()
    {
        return "partyAdded";
    }

    @RequestMapping("/login")
    public String loginPage(@RequestParam Map<String, String> queryParameter, RedirectAttributes redirectAttributes)
    {
        if(queryParameter.containsKey("register")) {
            redirectAttributes.addFlashAttribute("flash.registerMessage", "Successfully signed up :)");
            return "redirect:login";
        }
        return "/login";
    }

    @RequestMapping("/logout-success")
    public String logout()
    {
        return "logoutSuccess";
    }

    @RequestMapping("/register")
    public String registerPage()
    {
        return "register";
    }


}
