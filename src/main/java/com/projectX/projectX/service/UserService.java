package com.projectX.projectX.service;

import com.projectX.projectX.domain.User;
import com.projectX.projectX.pojos.UserPOJO;
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

    public User getUser(Long id) {
        return userRepository.findUserById(id);
    }

    public void saveUser(User user)
    {
        userRepository.save(user);
    }

    public void updateUser(User user, UserPOJO userPOJO) {
        user.setFirst_name(userPOJO.getFirst_name());
        user.setSurname(userPOJO.getSurname());
        user.setDate_of_birth(userPOJO.getDate_of_birth());
        userRepository.save(user);
    }
}
