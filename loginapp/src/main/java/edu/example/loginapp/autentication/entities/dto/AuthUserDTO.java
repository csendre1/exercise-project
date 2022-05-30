package edu.example.loginapp.autentication.entities.dto;

import edu.example.loginapp.attachments.entities.dto.AttachmentDTO;
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
