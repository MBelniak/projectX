package com.projectX.projectX.service;

import com.projectX.projectX.domain.User;
import com.projectX.projectX.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(s);
        if(user==null)
            throw new UsernameNotFoundException("User not found");


        return new UserDetailsImpl(user);
    }

    public List<User> getAll()
    {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    public User getUser(String email)
    {
        return userRepository.findUserByEmail(email);
    }

    public void addUser(User user)
    {
        userRepository.save(user);
    }
}
