package com.projectX.projectX.Controllers;

import com.projectX.projectX.domain.Party;
import com.projectX.projectX.pojos.PartyPOJO;
import com.projectX.projectX.service.ImageService;
import com.projectX.projectX.service.PartyServiceImpl;
import com.projectX.projectX.service.UserDetailsImpl;
import com.projectX.projectX.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PartiesController {

    private final PartyServiceImpl partyService;
    private final ImageService imageService;
    private final UserService userService;

    @Autowired
    public PartiesController(PartyServiceImpl partyService, ImageService imageService, UserService userService) {
        this.partyService = partyService;
        this.imageService = imageService;
        this.userService = userService;
    }

    @RequestMapping("/parties")
    public List<Party> getParties()
    {
        return partyService.getAll();
    }

    @RequestMapping("/parties/{id}")
    public Party getParty(@PathVariable Long id)
    {
        return partyService.getParty(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/parties")
    public void addParty(@RequestBody PartyPOJO partyPOJO)
    {
        UserDetailsImpl userDetails = (UserDetailsImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Party party = new Party(partyPOJO);
        party.setImage(imageService.getImageEntity(partyPOJO.getImageName()));
        party.setOrganizer(userService.getUser(userDetails.getUsername()));

        partyService.saveParty(party);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/parties/{id}")
    public void updateParty(@RequestBody Party party, @PathVariable Long id)
    {
        partyService.updateParty(party, id);
    }


}
