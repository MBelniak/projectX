package com.projectX.projectX.controllers;

import com.projectX.projectX.domain.Party;
import com.projectX.projectX.domain.User;
import com.projectX.projectX.pojos.PartyPOJO;
import com.projectX.projectX.service.ImageService;
import com.projectX.projectX.service.PartyService;
import com.projectX.projectX.service.UserDetailsImpl;
import com.projectX.projectX.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
public class PartiesController {

    private final PartyService partyService;
    private final ImageService imageService;
    private final UserService userService;
    private UserDetailsImpl userDetails;

    @Autowired
    public PartiesController(PartyService partyService, ImageService imageService, UserService userService) {
        this.partyService = partyService;
        this.imageService = imageService;
        this.userService = userService;
    }

    @RequestMapping("/parties")
    public Iterable<Party> getParties()
    {
        userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return partyService.getAll()
                .stream()
                .filter(party -> !party.isPriv() || party.getInvitedUsers().stream().anyMatch(user -> user.getEmail().equals(userDetails.getUsername())))
                .collect(Collectors.toList());
    }

    @RequestMapping("/parties/{id}")
    public Party getParty(@PathVariable Long id)
    {
        Party result = partyService.getParty(id);
        if (result.isPriv()) {
            userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (result.getInvitedUsers()
                    .stream()
                    .anyMatch(user -> user.getEmail().equals(userDetails.getUsername())))
                return result;
            else
                return null;
        }
        return result;
    }

    @RequestMapping("/parties/{id}/guests")
    public Iterable<User> getGuests(@PathVariable Long id) {
        Party party = partyService.getParty(id);
        if (party.isPriv()) {
            userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (party.getInvitedUsers()
                    .stream()
                    .anyMatch(user -> user.getEmail().equals(userDetails.getUsername())))
                return party.getInvitedUsers();
            else
                return null;
        }
        return party.getInvitedUsers();
    }

    @RequestMapping("/current_user/organized_parties")
    public Iterable<Party> getUsersParties() {
        userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return partyService.getPartiesOrganizedBy(userDetails.getId());
    }

    @RequestMapping(method = RequestMethod.POST, value = "/parties")
    public void addParty(@RequestBody PartyPOJO partyPOJO)
    {
        userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Party party = new Party(partyPOJO);
        party.setImage(imageService.getImageEntity(partyPOJO.getImageName()));
        party.setOrganizer(userService.getUser(userDetails.getUsername()));
        partyService.saveParty(party);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/parties")
    public void updateParty(@RequestBody Party party)
    {
        partyService.updateParty(party);
    }


}
