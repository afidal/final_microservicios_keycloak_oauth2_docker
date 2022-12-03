package com.afidal.facturacionservice.repositories;

import com.afidal.facturacionservice.models.dto.KeycloakUser;
import com.afidal.facturacionservice.security.feign.FeignInterceptor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name= "usuarios-service", url = "http://usuarios-service:8087", configuration = FeignInterceptor.class)
interface FeignUserRepository {

    @GetMapping("users/username/{username}")
    ResponseEntity<KeycloakUser> findByUsername(@PathVariable String username);
}
