package com.afidal.facturacionservice.repositories;

import com.afidal.facturacionservice.models.dto.KeycloakUser;


public interface IUserRepository {

    KeycloakUser findByUsername(String username);

}
