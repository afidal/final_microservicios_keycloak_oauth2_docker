package com.afidal.facturacionservice.repositories;

import com.afidal.facturacionservice.models.dto.KeycloakUser;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository implements IUserRepository{

    private final FeignUserRepository feignUserRepository;
    public UserRepository(FeignUserRepository feignUserRepository) {
        this.feignUserRepository = feignUserRepository;
    }


    @Override
    public KeycloakUser findByUsername(String username) {
        ResponseEntity<KeycloakUser> response = feignUserRepository.findByUsername(username);
        return response.getBody();
    }

}
