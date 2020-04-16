package com.victor.stockalarms.service;

import com.victor.stockalarms.entity.User;
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

    //TODO: just pass request object?
    public void saveUser(final String name, final String password, final String email) {
        userRepository.save(new User(name, bCryptPasswordEncoder.encode(password), email));
    }

}