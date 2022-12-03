package com.afidal.usuariosservice.repository;

import com.afidal.usuariosservice.exceptions.ResourceNotFoundException;
import com.afidal.usuariosservice.model.dto.KeycloakUser;
import com.afidal.usuariosservice.model.dto.KeycloakUserUsernameEmail;
import com.afidal.usuariosservice.model.dto.KeycloakUserWithoutID;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.GroupResource;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.ws.rs.BadRequestException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Repository
public class KeycloakUserRepository {

    @Autowired
    private Keycloak keycloakClient;

    @Value("${afidal.keycloak.realm}")
    private String realm;

    public List<KeycloakUserUsernameEmail> findKeycloakUsersNotAdmins(){
      List<UserRepresentation> usersFromKeycloak = keycloakClient.realm(realm).users().list();
      List<UserRepresentation> usersEnabled = usersFromKeycloak.stream().filter(userRepresentation -> keycloakClient.realm(realm).users().get(userRepresentation.getId()).groups().stream().noneMatch(s -> Objects.equals(s.getName(), "admin"))).collect(Collectors.toList());
      return usersEnabled.stream().map(this::toKeycloakUserUsernameEmail).collect(Collectors.toList());
    }

    private KeycloakUserUsernameEmail toKeycloakUserUsernameEmail (UserRepresentation userRepresentation) {
        return new KeycloakUserUsernameEmail(userRepresentation.getUsername(), userRepresentation.getEmail());
    }

    public List<KeycloakUser> findAllKeycloakUsers() {
        List<UserRepresentation> keycloakUsers = keycloakClient.realm(realm).users().list();
        return keycloakUsers.stream().map(this::toKeycloakUser).collect(Collectors.toList());
    }

    private KeycloakUser toKeycloakUser(UserRepresentation userRepresentation) {
        return new KeycloakUser(userRepresentation.getId(),userRepresentation.getUsername(), userRepresentation.getFirstName(), userRepresentation.getLastName(),userRepresentation.getEmail());
    }

    public KeycloakUser findKeycloakUserById(String id) throws ResourceNotFoundException {
        List<KeycloakUser> keycloakUsers = findAllKeycloakUsers();
        for (KeycloakUser keycloakUser : keycloakUsers) {
            if (keycloakUser.getId().equals(id)) {
                return keycloakUser;
            }
        }
        throw new ResourceNotFoundException("No se ha encontrado en Keycloak un usuario con el id solicitado.");
    }

    public KeycloakUser findKeycloakUserByUsername(String username) throws ResourceNotFoundException {
        List<KeycloakUser> keycloakUsers = findAllKeycloakUsers();
        for (KeycloakUser keycloakUser : keycloakUsers) {
            if (keycloakUser.getUsername().equals(username)) {
                return keycloakUser;
            }
        }
        throw new ResourceNotFoundException("No se ha encontrado en Keycloak un usuario con el nombre de usuario (username) solicitado.");
    }

    public KeycloakUserWithoutID createNewKeycloakUser(KeycloakUserWithoutID user) throws BadRequestException {
        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setUsername(user.getUsername());
        userRepresentation.setFirstName(user.getFirstname());
        userRepresentation.setLastName(user.getLastname());
        userRepresentation.setEmail(user.getEmail());
        userRepresentation.setEmail(user.getEmail());
        keycloakClient.realm(realm).users().create(userRepresentation);
        return  toKeycloakUserWithoutID(userRepresentation);
    }

    private KeycloakUserWithoutID toKeycloakUserWithoutID(UserRepresentation userRepresentation) {
        return new KeycloakUserWithoutID(userRepresentation.getUsername(), userRepresentation.getFirstName(), userRepresentation.getLastName(),userRepresentation.getEmail());
    }

}