package edu.example.loginapp.autentication;

import java.io.IOException;

import javax.transaction.Transactional;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import edu.example.loginapp.attachments.AttachmentServiceException;
import edu.example.loginapp.attachments.IAttachmentService;
import edu.example.loginapp.attachments.entities.Attachment;
import edu.example.loginapp.attachments.entities.dto.AttachmentDTO;
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

    private static final IUserMapper MAPPER = Mappers.getMapper(IUserMapper.class);

    @Override
    public AuthUser findByUsername(String username) {
        return this.userRepository.findByUsername(username).orElseThrow(() -> new AuthenticationServiceException(
                "User not found with the given username: [ " + username + " }]"));
    }

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

        try {
            performSave(authUserDTO, file);
            log.info("User with username : [{}] saved in the database.", authUserDTO.getUsername());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (AttachmentServiceException e) {
            throw new AuthenticationServiceException("Failed to save user profile picture.");

        }
    }

    @Override
    @Transactional
    public AttachmentDTO findUserImage(final String username) {
        return this.attachmentService.findUserImage(findByUsername(username));
    }

    private void performSave(final AuthUserDTO user, final MultipartFile file) throws IOException {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        final Attachment attachment = attachmentService.save(file);
        final AuthUser authUser = MAPPER.mapDtoToAuthUser(user);
        authUser.setProfilePicture(attachment);
        attachment.setUser(authUser);
        userRepository.save(authUser);
    }

    private AuthUser mapToUserFromUserDTO(AuthUserDTO userDTO, Attachment profilePicture) {
        return AuthUser.builder().name(userDTO.getName()).password(userDTO.getPassword())
                .username(userDTO.getPassword()).profilePicture(profilePicture).build();
    }

}
