package com.victor.stockalarms.service;

import com.victor.stockalarms.dto.UserDTO;
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

    public void saveUser(final UserDTO userDTO) {
        userRepository.save(new User(userDTO.getName(), bCryptPasswordEncoder.encode(userDTO.getPassword()), userDTO.getEmail()));
    }

    public User findByUserName(final String name) {
        return userRepository.findByName(name);
    }

}