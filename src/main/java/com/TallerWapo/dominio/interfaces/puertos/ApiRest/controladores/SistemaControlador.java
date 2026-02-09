package com.TallerWapo.dominio.interfaces.puertos.ApiRest.controladores;

import com.TallerWapo.dominio.bo.RespuestaHttpBO;
import com.TallerWapo.dominio.dto.CitaDTO;
import com.TallerWapo.dominio.fachadas.negocio.vehiculos.CitasFachadaConsultasImpl;
import com.TallerWapo.dominio.fachadas.negocio.vehiculos.CitasFachadaEjecutarImpl;
import com.TallerWapo.dominio.fachadas.sistema.GestionSistemaFachadaEjecutarImpl;
import com.TallerWapo.dominio.interfaces.base.ControladoresBase;
import com.TallerWapo.dominio.interfaces.puertos.ApiRest.EstadoRespuestaHTTP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public abstract class SistemaControlador implements ControladoresBase {

    static final Logger logger = LoggerFactory.getLogger(SistemaControlador.class);

    // RUTAS
    protected static final String rutaBase = "/api/gestion/sistema";
    protected static final String apagarEquipo = "/apagarEquipo";
    protected static final String estado = "/estado";

    protected static RespuestaHttpBO apagarEquipo() {
        logger.info("Orden apagar equipo");

        RespuestaHttpBO respuesta = new RespuestaHttpBO();
        GestionSistemaFachadaEjecutarImpl fachadaSistema = new GestionSistemaFachadaEjecutarImpl();

        try {
            fachadaSistema.ordenApagar();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return respuesta;
    }

    protected static RespuestaHttpBO estadoSistema() {
        logger.info("Buscando estado del sistema");

        RespuestaHttpBO respuesta = new RespuestaHttpBO();


        try {


        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return respuesta;
    }

}
