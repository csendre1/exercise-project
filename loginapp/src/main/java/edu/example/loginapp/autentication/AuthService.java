package edu.example.loginapp.autentication;

import java.io.IOException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import edu.example.loginapp.attachments.IAttachmentService;
import edu.example.loginapp.attachments.entities.Attachment;
import edu.example.loginapp.autentication.entities.AuthUser;
import edu.example.loginapp.autentication.entities.dto.AuthUserDTO;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class AuthService implements IAuthService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private IAttachmentService attachmentService;

    @Override
    @Transactional
    public ResponseEntity<HttpStatus> registration(AuthUserDTO authUserDTO, MultipartFile file)
            throws IOException {
        log.info("Registration started...");

        if (authUserDTO == null) {
            throw new AuthenticationServiceException("New user can't be null.");
        }
        userRepository.findByUsername(authUserDTO.getUsername()).ifPresent(user -> {
            throw new AuthenticationServiceException("Username is not unique!");
        });

        // sub-method
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
