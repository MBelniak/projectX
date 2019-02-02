package com.projectX.projectX;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class StartController {

//    @Autowired
//    private PartyService partyService;

    @RequestMapping(value="/")
    public String getIndex() {
        return "index";
    }

//    @RequestMapping("/parties")
//    public Iterable<Party> getParties()
//    {
//        return partyService.getAll();
//    }



}
