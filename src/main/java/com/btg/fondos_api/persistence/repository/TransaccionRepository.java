package com.btg.fondos_api.persistence.repository;

import com.btg.fondos_api.persistence.model.TransaccionModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TransaccionRepository extends MongoRepository<TransaccionModel, String> {
}
