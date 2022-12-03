package com.afidal.usuariosservice.repository;

import com.afidal.usuariosservice.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IMongoUserRepository extends MongoRepository<User,String> {
}


