package com.centripio.envios.service;

import com.centripio.domain.Cuenta;
import com.centripio.domain.Envio;
import com.centripio.domain.Monedero;
import com.centripio.envios.dto.EnviosResponse;
import com.centripio.envios.exception.CuentaNoExistenteException;
import com.centripio.envios.repository.EnviosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class EnviosServicio {

    private Monedero monedero;
    private Cuenta cuentaDestino;
    private CuentaServicio cuentaServicio;

    @Autowired
    EnviosRepository enviosRepository;

    public EnviosServicio(){
        super();
    }

    public EnviosServicio(Monedero monedero, Cuenta cuentaDestino, CuentaServicio cuentaServicio){
        this.monedero = monedero;
        this.cuentaDestino = cuentaDestino;
        this.cuentaServicio = cuentaServicio;
    }

    public double calcularCostoEnvio(double cantidadEnviar, double costoServicio,double impuestos){
        double result = 0.0;
        double costoTotalEnvio = 0.0;

        costoTotalEnvio = (cantidadEnviar + costoServicio);

        result = costoTotalEnvio + (costoTotalEnvio * impuestos);

        return result;
    }


    public boolean containsMonedero() {
        return monedero != null;
    }

    public boolean isMonederoNotZero() {
        return monedero.getSaldo().intValue() > 0;
    }

    public boolean containsCuentaDestino(){
        return cuentaDestino != null;
    }

    public boolean isCuentaDestinoValida() throws CuentaNoExistenteException {
       boolean result = cuentaServicio.isCuentaValida(cuentaDestino);

       if (!result){
           String message = "Numero de cuenta no existente:" + cuentaDestino.getNumerocuenta();
           throw new CuentaNoExistenteException(message);
       }

       return result;
    }

    public EnviosResponse enviar(Long cuentaId, BigDecimal cantidad){

        EnviosResponse result = null;

        if (containsCuentaDestino() && containsCuentaDestino() && isMonederoNotZero()){
            Envio envio = new Envio();

            enviosRepository.save(envio);
        }

        return result;
    }

}
