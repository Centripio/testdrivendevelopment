package com.centripio.service;

import com.centripio.domain.Cuenta;
import com.centripio.domain.Monedero;
import com.centripio.envios.exception.CuentaNoExistenteException;
import com.centripio.envios.service.CuentaServicio;
import com.centripio.envios.service.EnviosServicio;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class EnviosServiceTest {

    private EnviosServicio envioService;

    @Mock
    private Cuenta cuentaDestino;
    @Mock
    private Monedero monedero;

    @Mock
    private CuentaServicio cuentaServicio;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Before
    public void init(){
        envioService = new EnviosServicio(monedero, cuentaDestino, cuentaServicio);

        when(monedero.getSaldo()).thenReturn(new BigDecimal(10.4));
    }

    @Test
    public void monederoNotNull_test(){
        assertEquals(true, envioService.containsMonedero());
    }

    @Test
    public void monederoConSaldo_test(){
        assertEquals(true, envioService.isMonederoNotZero());
    }

    @Test
    public void cuentaDestinoNotNull_test(){
        assertEquals(true, envioService.containsCuentaDestino());
    }

    @Test
    public void cuentaDestinoExistente_test(){
        when(cuentaServicio.isCuentaValida(cuentaDestino)).thenReturn(true);

        boolean result = false;

        try {
            result = envioService.isCuentaDestinoValida();
        }
        catch(CuentaNoExistenteException ex){
            result = false;
        }

        assertEquals(true, result);
    }

    @Test
    public void cuentaDestinoNoExistente_test() {
        when(cuentaServicio.isCuentaValida(cuentaDestino)).thenReturn(false);

        try {
            envioService.isCuentaDestinoValida();
        }
        catch(CuentaNoExistenteException ex){
            String message = "Numero de cuenta no existente:" + cuentaDestino.getNumerocuenta();
            assertEquals(message, ex.getMessage());
        }
    }

    @Test
    public void calcularCostoEnvio_test(){
        //Costo de envio dlls
        double costoEnvio = 10;
        //Cantidad a enviar
        double cantidadEnviar = 150;
        //10% de impuestos
        double impuestos = 0.10;

        double costoTotalEnvio = cantidadEnviar + costoEnvio;

        double total = costoTotalEnvio + (costoTotalEnvio * impuestos);

        assertEquals(total, envioService.calcularCostoEnvio(cantidadEnviar, costoEnvio, impuestos),
                                                            0.01);
    }

}
