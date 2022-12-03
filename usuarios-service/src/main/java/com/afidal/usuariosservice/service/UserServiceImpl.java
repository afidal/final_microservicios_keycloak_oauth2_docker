package com.afidal.usuariosservice.service;

import com.afidal.usuariosservice.exceptions.ResourceNotFoundException;
import com.afidal.usuariosservice.model.User;
import com.afidal.usuariosservice.model.dto.KeycloakUser;
import com.afidal.usuariosservice.model.dto.KeycloakUserUsernameEmail;
import com.afidal.usuariosservice.model.dto.KeycloakUserWithoutID;
import com.afidal.usuariosservice.repository.KeycloakUserRepository;
import com.afidal.usuariosservice.repository.MongoUserRepository;
import org.springframework.stereotype.Service;

import javax.ws.rs.BadRequestException;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

  private final MongoUserRepository userRepository;
  private final KeycloakUserRepository keycloakUserRepository;

  public UserServiceImpl(MongoUserRepository userRepository, KeycloakUserRepository keycloakUserRepository) {
    this.userRepository = userRepository;
    this.keycloakUserRepository = keycloakUserRepository;
  }

  @Override
  public List<KeycloakUserUsernameEmail> findKeycloakUsersNotAdmins() {
    return keycloakUserRepository.findKeycloakUsersNotAdmins();
  }

  @Override
  public List<KeycloakUser> findAllKeycloakUsers() {
    return keycloakUserRepository.findAllKeycloakUsers();
  }

  @Override
  public KeycloakUser findKeycloakUserById(String id) throws ResourceNotFoundException {
    return keycloakUserRepository.findKeycloakUserById(id);
  }

  @Override
  public KeycloakUser findKeycloakUserByUsername(String username) throws ResourceNotFoundException {
    return keycloakUserRepository.findKeycloakUserByUsername(username);
  }

  @Override
  public KeycloakUserWithoutID createNewKeycloakUser(KeycloakUserWithoutID user) throws BadRequestException {
    return keycloakUserRepository.createNewKeycloakUser(user);
  }

  @Override
  public User validateAndGetUserExtra(String username) {
    return userRepository.validateAndGetUser(username);
  }

  @Override
  public Optional<User> getUserExtra(String username) {
    return userRepository.getUserExtra(username);
  }

  @Override
  public User saveUserExtra(User user) {
    return userRepository.saveUserExtra(user);
  }
}
