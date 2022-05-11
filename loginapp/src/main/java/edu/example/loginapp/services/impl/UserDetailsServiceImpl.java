package edu.example.loginapp.services.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.example.loginapp.model.AuthUser;
import edu.example.loginapp.repositories.IUserRepository;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private IUserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        AuthUser user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Not found " + username));

        log.info("User with username [{}] found.", username);

        return new User(user.getUsername(), user.getPassword(), new ArrayList<>());
    }

}
