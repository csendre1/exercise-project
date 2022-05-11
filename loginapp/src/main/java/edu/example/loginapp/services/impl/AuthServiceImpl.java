package edu.example.loginapp.services.impl;

import java.io.IOException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import edu.example.loginapp.dto.AuthUserDTO;
import edu.example.loginapp.exception.NotUniqueException;
import edu.example.loginapp.model.Attachment;
import edu.example.loginapp.model.AuthUser;
import edu.example.loginapp.repositories.IUserRepository;
import edu.example.loginapp.services.AttachmentService;
import edu.example.loginapp.services.AuthService;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AttachmentService attachmentService;

    @Override
    @Transactional
    public ResponseEntity<HttpStatus> registration(AuthUserDTO authUserDTO, MultipartFile file)
            throws IOException {
        log.info("Registration started...");

        if (authUserDTO == null) {
            log.error("The new user is null.");
            throw new NullPointerException("New user can't be null.");
        }
        userRepository.findByUsername(authUserDTO.getUsername()).ifPresent(user -> {
            log.warn("User with username {} is already exists.", user.getUsername());
            throw new NotUniqueException("Username is not unique!");
        });

        authUserDTO.setPassword(passwordEncoder.encode(authUserDTO.getPassword()));
        Attachment attachment = attachmentService.save(file);
        AuthUser authUser = mapToUserFromUserDTO(authUserDTO, attachment);
        attachment.setUser(authUser);
        userRepository.save(authUser);

        log.info("User with username : [{}] saved in the database.", authUserDTO.getUsername());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    private AuthUser mapToUserFromUserDTO(AuthUserDTO userDTO, Attachment profilePicture) {
        return AuthUser.builder().name(userDTO.getName()).password(userDTO.getPassword())
                .username(userDTO.getPassword()).profilePicture(profilePicture).build();
    }

}
