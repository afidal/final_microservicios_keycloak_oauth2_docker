package com.afidal.usuariosservice.configuration;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KeycloakConfiguration {
    @Value("${afidal.keycloak.serverUrl}")
    private String serverUrl;
    @Value("${afidal.keycloak.realm}")
    private String realm;
    @Value("${afidal.keycloak.clientId}")
    private String clientId;
    @Value("${afidal.keycloak.clientSecret}")
    private String clientSecret;

    @Bean
    public Keycloak buildClient(){
        return KeycloakBuilder.builder()
                .serverUrl(serverUrl)
                .realm(realm)
                .clientId(clientId)
                .clientSecret(clientSecret)
                .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
                .build();
    }
}
