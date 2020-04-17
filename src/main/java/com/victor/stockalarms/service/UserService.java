package com.victor.stockalarms.service;

import com.victor.stockalarms.entity.User;
import com.victor.stockalarms.model.CreateUserRequest;
import com.victor.stockalarms.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(final UserRepository userRepository,
                       final BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRepository = userRepository;
    }

    public void saveUser(final CreateUserRequest request) {
        userRepository.save(new User(request.getName(), bCryptPasswordEncoder.encode(request.getPassword()), request.getEmail()));
    }

    public User findByUserName(final String name) {
        return userRepository.findByName(name);
    }

}