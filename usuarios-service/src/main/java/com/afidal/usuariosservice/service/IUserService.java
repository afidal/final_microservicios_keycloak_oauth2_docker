package com.afidal.usuariosservice.service;

import com.afidal.usuariosservice.exceptions.ResourceNotFoundException;
import com.afidal.usuariosservice.model.User;
import com.afidal.usuariosservice.model.dto.KeycloakUser;
import com.afidal.usuariosservice.model.dto.KeycloakUserUsernameEmail;
import com.afidal.usuariosservice.model.dto.KeycloakUserWithoutID;

import javax.ws.rs.BadRequestException;
import java.util.List;
import java.util.Optional;

public interface IUserService {

  User validateAndGetUserExtra(String username);

  Optional<User> getUserExtra(String username);

  User saveUserExtra(User userExtra);

  List<KeycloakUserUsernameEmail> findKeycloakUsersNotAdmins();

  List<KeycloakUser> findAllKeycloakUsers();

 KeycloakUser findKeycloakUserById(String id) throws ResourceNotFoundException;

  KeycloakUser findKeycloakUserByUsername(String username) throws ResourceNotFoundException;

  KeycloakUserWithoutID createNewKeycloakUser(KeycloakUserWithoutID user) throws BadRequestException;

}
