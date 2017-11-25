package com.centripio.repositories;

import com.centripio.config.AppConfig;
import com.centripio.cuentas.repository.CuentaRepository;
import com.centripio.domain.Cuenta;
import com.centripio.domain.Envio;
import com.centripio.envios.repository.EnviosRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
@Transactional
public class EnviosRepositoryTest {

    @Autowired
    CuentaRepository cuentaRepository;

    @Autowired
    EnviosRepository enviosRepository;

    private Envio envio;
    private Cuenta cuenta;

    @Before
    public void init(){
        envio = new Envio();

        Cuenta cuenta = cuentaRepository.findCuentaById(1L);

        envio.setCuenta(cuenta);
        envio.setFechaTransaccion(new Date());
        envio.setCantidad(new BigDecimal(10.0));
        envio.setCostoEnvio(new BigDecimal(15.0));
        envio.setImpuestos(new BigDecimal(0.15));

    }

    private Envio createEnvio(Cuenta cuenta){
        Envio envio = new Envio();

        envio.setCuenta(cuenta);
        envio.setFechaTransaccion(new Date());
        envio.setCantidad(new BigDecimal(10.0));
        envio.setCostoEnvio(new BigDecimal(15.0));
        envio.setImpuestos(new BigDecimal(0.15));

        return envio;
    }

    @Test
    public void crearEnvio_tets(){
        Envio result = enviosRepository.save(envio);
        assertThat(result, is(notNullValue()));

        Envio envioReturned = enviosRepository.findEnvioById(result.getId());
        assertThat(envioReturned.getId(), is(result.getId()));
    }

    @Test
    public void mostrarTodosEnvios_test(){

        Cuenta cuenta = cuentaRepository.findCuentaById(1L);

        Envio result = enviosRepository.save(envio);

        result = createEnvio(cuenta);

        enviosRepository.save(result);

        List<Envio> envios = enviosRepository.findAll();
        assertThat(envios.size(), is(2));
    }
}
