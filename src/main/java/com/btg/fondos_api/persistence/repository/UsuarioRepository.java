package com.btg.fondos_api.persistence.repository;

import com.btg.fondos_api.persistence.model.UsuarioModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends MongoRepository<UsuarioModel, String> {
    Optional<UsuarioModel> findByUsername(String username);}
