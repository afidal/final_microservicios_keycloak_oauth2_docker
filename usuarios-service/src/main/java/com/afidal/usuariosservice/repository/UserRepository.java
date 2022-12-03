package com.afidal.usuariosservice.repository;

import com.afidal.usuariosservice.model.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public class UserRepository {

  private IUserRepository userRepository;


  public User validateAndGetUser(String username) {
    return  userRepository.validateAndGetUser(username);
  }

  public Optional<User> getUserExtra(String username) {
    return userRepository.getUserExtra(username);
  }

  public User saveUserExtra(User user) {
    return userRepository.saveUserExtra(user);
  }
}
