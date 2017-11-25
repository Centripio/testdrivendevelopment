package com.centripio.envios.controller;

import com.centripio.envios.dto.EnvioResponse;
import com.centripio.envios.service.EnviosServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class EnviosController {

    @Autowired
    EnviosServicio enviosService;

    @RequestMapping("/envios/enviarDinero/{cuentaId}/{cantidad}")
    @ResponseBody
    public ResponseEntity<EnvioResponse> enviarDinero(@PathVariable Long cuentaId, @PathVariable BigDecimal cantidad){
         enviosService.enviar(cuentaId, cantidad);
         return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping("/envios/list")
    @ResponseBody
    public ResponseEntity<EnvioResponse> mostarTodosEnvios(){
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
