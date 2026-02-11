package com.TallerWapo.dominio.interfaces.puertos.ApiRest.controladores;

import com.TallerWapo.dominio.bo.RespuestaHttpBO;
import com.TallerWapo.dominio.dto.calendario.SemanasDelAnioDTO;
import com.TallerWapo.dominio.dto.gestion.ResumenDatosAppDTO;
import com.TallerWapo.dominio.fachadas.sistema.GestionSistemaFachadaConsultasImpl;
import com.TallerWapo.dominio.fachadas.sistema.GestionSistemaFachadaEjecutarImpl;
import com.TallerWapo.dominio.interfaces.base.ControladoresBase;
import com.TallerWapo.dominio.interfaces.puertos.ApiRest.EstadoRespuestaHTTP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class SistemaControlador implements ControladoresBase {

    static final Logger logger = LoggerFactory.getLogger(SistemaControlador.class);

    // RUTAS
    protected static final String rutaBase = "/api/gestion/sistema";
    protected static final String apagarEquipo = "/apagarEquipo";
    protected static final String estadoSistema = "/estado";
    protected static final String semanasDelAnio = "/semanasDelAnio";

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

    protected static RespuestaHttpBO estadoSistema() throws Exception {
        logger.info("Buscando estado del sistema");

        RespuestaHttpBO respuesta = new RespuestaHttpBO();
        GestionSistemaFachadaConsultasImpl consultasSistema = new GestionSistemaFachadaConsultasImpl();

        ResumenDatosAppDTO datos = consultasSistema.estadoDelSistema();
        respuesta.setIsOk(true);
        respuesta.setStatus(EstadoRespuestaHTTP.OK.getCodigo());
        respuesta.setMensaje("Sistema todo OK");
        respuesta.setObjeto(datos);

        return respuesta;
    }

    protected static RespuestaHttpBO obtenerSemanasAnio(int anio) throws Exception {
        logger.info("Buscando las semanas del anio: " + anio);

        RespuestaHttpBO respuesta = new RespuestaHttpBO();
        GestionSistemaFachadaConsultasImpl consultasSistema = new GestionSistemaFachadaConsultasImpl();

        SemanasDelAnioDTO semanas = consultasSistema.obtenerSemanasDelAnio(anio);
        respuesta.setIsOk(true);
        respuesta.setStatus(EstadoRespuestaHTTP.OK.getCodigo());
        respuesta.setMensaje("OK");
        respuesta.setObjeto(semanas);

        return respuesta;
    }

}
