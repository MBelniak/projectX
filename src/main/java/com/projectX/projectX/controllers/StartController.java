package com.projectX.projectX.controllers;

import com.projectX.projectX.domain.Party;
import com.projectX.projectX.service.PartyService;
import com.projectX.projectX.service.UserDetailsImpl;
import com.projectX.projectX.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;


@Controller
public class StartController {

    private final PartyService partyService;
    private final UserService userService;

    @Autowired
    public StartController(PartyService partyService, UserService userService) {
        this.userService = userService;
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

    @RequestMapping("/search_parties/{id}")
    public String partyPage(Model model, @PathVariable Long id, RedirectAttributes attributes)
    {

        Party party = partyService.getParty(id);
        if (party == null) {
            attributes.addFlashAttribute("message", "Couldn't find what you are looking for.");
            return "redirect:/search_parties";
        }
        if (party.isPriv()) {
            UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (party.getInvitedUsers()
                    .stream()
                    .anyMatch(user -> user.getEmail().equals(userDetails.getUsername())))
                model.addAttribute("party", party);
            else {
                model.addAttribute("error_message", "Permission denied.");
                return "party";
            }

        } else
            model.addAttribute("party", party);

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
        if (queryParameter.containsKey("register") && queryParameter.get("register").equals("ok")) {
            redirectAttributes.addFlashAttribute("flash.registerMessage", "Successfully signed up :)");
            return "redirect:login";
        }
        if (queryParameter.containsKey("error") && queryParameter.get("error").equals("true")) {
            redirectAttributes.addFlashAttribute("flash.errorMessage", "Username or password is incorrect.");
            return "redirect:login";
        }
        return "login";
    }
    @RequestMapping("/register")
    public String registerPage()
    {
        return "register";
    }

    @RequestMapping("/")
    public String indexPage(@RequestParam Map<String, String> queryParameter, RedirectAttributes redirectAttributes) {
        if (queryParameter.containsKey("login") && queryParameter.get("login").equals("success")) {
            redirectAttributes.addFlashAttribute("flash.loginSuccessMessage", "Successfully signed in.");
            return "redirect:/";
        }
        if (queryParameter.containsKey("logout") && queryParameter.get("logout").equals("success")) {
            redirectAttributes.addFlashAttribute("flash.logoutSuccessMessage", "Successfully signed out.");
            return "redirect:/";
        }
        return "index";
    }

    @RequestMapping("/user_details")
    public String getUserPage(Model model) {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", userService.getUser(userDetails.getUsername()));
        return "user";
    }

}
