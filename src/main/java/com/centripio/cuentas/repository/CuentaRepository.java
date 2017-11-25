package com.centripio.cuentas.repository;

import com.centripio.domain.Cuenta;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CuentaRepository extends CrudRepository<Cuenta, Long> {

    Cuenta findCuentaById(Long id);

    List<Cuenta> findAll();
}
