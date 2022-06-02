package edu.example.loginapp.autentication;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import edu.example.loginapp.attachments.entities.dto.AttachmentDTO;
import edu.example.loginapp.autentication.entities.AuthUser;
import edu.example.loginapp.autentication.entities.dto.AuthUserDTO;

public interface IAuthService {

    AuthUser findByUsername(final String username);

    ResponseEntity<HttpStatus> registration(final AuthUserDTO authUser, MultipartFile file) throws IOException;

    AttachmentDTO findUserImage(String username);
}
