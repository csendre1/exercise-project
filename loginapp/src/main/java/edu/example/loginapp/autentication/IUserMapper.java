package edu.example.loginapp.autentication;

import org.mapstruct.Mapper;

import edu.example.loginapp.autentication.entities.AuthUser;
import edu.example.loginapp.autentication.entities.dto.AuthUserDTO;

@Mapper
public interface IUserMapper {
    AuthUser mapDtoToAuthUser(AuthUserDTO source);

    AuthUserDTO mapAuthUserTDto(AuthUser source);

}
