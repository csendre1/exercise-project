package edu.example.loginapp.autentication;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import edu.example.loginapp.autentication.entities.dto.AuthUserDTO;

@RestController
public class AuthController {

    @Autowired
    private IAuthService authService;

    @PostMapping(path = "/register", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<HttpStatus> registration(@RequestParam final String authUser,
            @RequestParam MultipartFile file)
            throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        AuthUserDTO modelDTO = mapper.readValue(authUser, AuthUserDTO.class);

        return authService.registration(modelDTO, file);
    }

}
