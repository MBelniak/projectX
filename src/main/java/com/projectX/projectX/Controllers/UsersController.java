package com.projectX.projectX.Controllers;

import com.projectX.projectX.domain.User;
import com.projectX.projectX.service.RoleService;
import com.projectX.projectX.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UsersController {

    private UserService userService;
    private RoleService roleService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Autowired
    public UsersController(UserService userService, RoleService roleService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    @RequestMapping("/users/{email}")
    public String isEmailAvailable(@PathVariable("email") String email)
    {
        if(userService.getUser(email)!=null)
            return "exists";
        return null;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/users")
    public  List<String> addUser(@RequestBody @Valid User user, BindingResult bindingResult) {
        if(bindingResult.hasErrors())
        {
            return bindingResult.getAllErrors().stream().map(objectError -> objectError.getDefaultMessage()).collect(Collectors.toList());
        }
        user.setHash_password(bCryptPasswordEncoder.encode(user.getHash_password()));
        user.setRole(roleService.getRole("USER"));
        userService.saveUser(user);
        return null;
    }
}

