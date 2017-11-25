package com.centripio.envios.repository;

import com.centripio.domain.Envio;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnviosRepository extends CrudRepository<Envio, Long> {

    List<Envio> findAll();

    Envio save(Envio envio);

    Envio findEnvioById(Long envioId);

}
