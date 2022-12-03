package com.afidal.usuariosservice.repository;

import com.afidal.usuariosservice.model.User;

import java.util.Optional;

public interface IUserRepository {

  User validateAndGetUser(String username);

  Optional<User> getUserExtra(String username);

  User saveUserExtra(User userExtra);
}
