package com.btg.fondos_api.persistence.repository;

import com.btg.fondos_api.persistence.model.ClienteModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends MongoRepository<ClienteModel, String> {
}
