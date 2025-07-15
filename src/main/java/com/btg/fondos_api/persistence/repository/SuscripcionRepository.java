package com.btg.fondos_api.persistence.repository;

import com.btg.fondos_api.persistence.model.SuscripcionModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface SuscripcionRepository extends MongoRepository<SuscripcionModel, String> {
    Optional<SuscripcionModel> findByClienteIdAndFondoIdAndActiva(String clienteId, String fondoId, boolean estado);
}
