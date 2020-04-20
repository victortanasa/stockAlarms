package com.victor.stockalarms.service;

import static com.google.common.collect.Lists.newArrayList;

import com.victor.stockalarms.dto.UserDTO;
import com.victor.stockalarms.entity.User;
import com.victor.stockalarms.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(final UserRepository userRepository,
                       final BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRepository = userRepository;
    }

    public void saveUser(final UserDTO userDTO) {
        userRepository.save(new User(userDTO.getFirstName(), userDTO.getLastName(), bCryptPasswordEncoder.encode(userDTO.getPassword()), userDTO.getEmail()));
    }

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        final User user = userRepository.findByEmail(username);
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException(username);
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), newArrayList());
    }

    User getLoggedInUser() {
        final Object loggedInUserName = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.findByEmail(loggedInUserName.toString());
    }

}