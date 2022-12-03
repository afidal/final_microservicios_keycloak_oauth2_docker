package com.afidal.usuariosservice.controller;

import com.afidal.usuariosservice.exceptions.ResourceNotFoundException;
import com.afidal.usuariosservice.model.User;
import com.afidal.usuariosservice.model.dto.KeycloakUser;
import com.afidal.usuariosservice.model.dto.KeycloakUserUsernameEmail;
import com.afidal.usuariosservice.model.dto.KeycloakUserWithoutID;
import com.afidal.usuariosservice.model.dto.UserRequest;
import com.afidal.usuariosservice.service.IUserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.ws.rs.BadRequestException;
import java.security.Principal;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/users")
public class UserController {

  private final IUserService userService;

  public UserController(IUserService userService) {
    this.userService = userService;
  }

  // Nuevos métodos

  // Listar todos los usuarios de Keycloak que no son administradores
  // Endpoint unicamente accesible para usuarios autenticados y que además sean admins
  @GetMapping("/admin")
  @PreAuthorize("hasAuthority('GROUP_ADMIN') AND hasRole('ROLE_ADMIN')")
  public List<KeycloakUserUsernameEmail> getNonAdminUsers() {
    return userService.findKeycloakUsersNotAdmins();
  }

  // Listar todos los usuarios de Keycloak con toda su información
  // Endpoint unicamente accesible para usuarios autenticados y que además sean admins
  @GetMapping("/admin/all")
  @PreAuthorize("hasAuthority('GROUP_ADMIN') AND hasRole('ROLE_ADMIN')")
  public List<KeycloakUser> findAllKeycloakUsers() {
    return userService.findAllKeycloakUsers();
  }

  // Buscar un usuario en Keycloak por su ID
  // Endpoint unicamente accesible para usuarios autenticados y que además sean admins
  @GetMapping("admin/id/{id}")
  @PreAuthorize("hasAuthority('GROUP_ADMIN') AND hasRole('ROLE_ADMIN')")
  public KeycloakUser findKeycloakUserById(@PathVariable String id) throws ResourceNotFoundException {
    return userService.findKeycloakUserById(id);
  }

  // Buscar un usuario en Keycloak por su nombre de usuario (username)
  // Este endpoint NO lo hice accesible para usuarios autenticados y que además sean admins ya que es el método que utiliza facturacion-service a través de Feign
  // Según la consigna de Feign este método debe ser accesible para usuarios clientes
  // En conclusion el método tambien es accesible solo para usuarios CLIENTES
  @GetMapping("/username/{username}")
  @PreAuthorize("hasAuthority('GROUP_CLIENT') AND hasRole('ROLE_CLIENT')")
  public KeycloakUser findKeycloakUserByUsername(@PathVariable String username) throws ResourceNotFoundException {
    return userService.findKeycloakUserByUsername(username);
  }

  // Crea un nuevo usuario en Keycloak (no crea el password, hay que asignarselo desde el panel de administraci ón)
  // Keycloak asigna el ID automaticamente
  // Endpoint unicamente accesible para usuarios autenticados y que además sean admins
  @PostMapping("admin")
  @PreAuthorize("hasAuthority('GROUP_ADMIN') AND hasRole('ROLE_ADMIN')")
  public KeycloakUserWithoutID createNewKeycloakUser(@RequestBody KeycloakUserWithoutID keycloakUser) throws BadRequestException {
    return userService.createNewKeycloakUser(keycloakUser);
  }

  // Métodos que ya venian hechos

  @GetMapping("/me")
  public User getUserExtra(Principal principal) {
    return userService.validateAndGetUserExtra(principal.getName());
  }


  @PostMapping("/me")
  public User saveUserExtra(@Valid @RequestBody UserRequest updateUserRequest, Principal principal) {
    Optional<User> userOptional = userService.getUserExtra(principal.getName());
    User userExtra = userOptional.orElseGet(() -> new User(principal.getName()));
    userExtra.setAvatar(updateUserRequest.getAvatar());
    return userService.saveUserExtra(userExtra);
  }
}
