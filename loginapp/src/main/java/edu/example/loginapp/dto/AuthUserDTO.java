package edu.example.loginapp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthUserDTO {

    private String name;

    private String username;

    private String password;

    private AttachmentDTO profilePicture;

}
