package edu.example.loginapp.services;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import edu.example.loginapp.dto.AuthUserDTO;

@Service
public interface AuthService {

    ResponseEntity<HttpStatus> registration(final AuthUserDTO authUser, MultipartFile file) throws IOException;
}
