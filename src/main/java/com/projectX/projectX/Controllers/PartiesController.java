package com.projectX.projectX.Controllers;

import com.projectX.projectX.domain.Party;
import com.projectX.projectX.service.ImageService;
import com.projectX.projectX.service.PartyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
public class PartiesController {

    private final PartyServiceImpl partyService;
    private final ImageService imageService;

    @Autowired
    public PartiesController(PartyServiceImpl partyService, ImageService imageService) {
        this.partyService = partyService;
        this.imageService = imageService;
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
    public void addParty(@RequestBody Party party)
    {
        if(party.getImage()!=null)
            party.setImage(imageService.getImageEntity(party.getImage().getName()));
        if(party.getOrganizer()!=null)

        partyService.addParty(party);
    }
    @RequestMapping(method = RequestMethod.PUT, value = "/parties/{id}")
    public void updateParty(@RequestBody Party party, @PathVariable Long id)
    {
        partyService.updateParty(party, id);
    }


}
