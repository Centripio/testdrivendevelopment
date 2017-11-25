package com.centripio.repositories;

import com.centripio.config.AppConfig;
import com.centripio.cuentas.repository.CuentaRepository;
import com.centripio.domain.Cuenta;
import com.centripio.domain.Envio;
import com.centripio.envios.service.CuentaServicio;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
@Transactional
public class CuentaRepositoryTest {
    @Autowired
    CuentaRepository cuentaRepository;

    @Test
    public void mostrarTodasCuentas_test(){
        List<Cuenta> cuentas = cuentaRepository.findAll();
        assertThat(cuentas.size(), is(1));
    }

    @Test
    public void obtenerCuentaPorID_test(){
        Long cuentaId = 1L;

        Cuenta cuenta = cuentaRepository.findCuentaById(cuentaId);

        assertThat(cuenta, is(notNullValue()));
    }
}
