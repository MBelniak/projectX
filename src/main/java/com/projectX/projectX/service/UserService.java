package com.projectX.projectX.service;

import com.projectX.projectX.domain.User;
import com.projectX.projectX.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
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
