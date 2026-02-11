package com.TallerWapo.dominio.interfaces.puertos.ApiRest.controladores;

import com.TallerWapo.dominio.bo.RespuestaHttpBO;
import com.TallerWapo.dominio.dto.calendario.CitaDTO;
import com.TallerWapo.dominio.fachadas.negocio.calendario.CitasFachadaConsultasImpl;
import com.TallerWapo.dominio.fachadas.negocio.calendario.CitasFachadaEjecutarImpl;
import com.TallerWapo.dominio.interfaces.base.ControladoresBase;
import com.TallerWapo.dominio.interfaces.puertos.ApiRest.EstadoRespuestaHTTP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public abstract class CitasControlador implements ControladoresBase {

    static final Logger logger = LoggerFactory.getLogger(CitasControlador.class);

    // RUTAS
    protected static final String rutaBase = "/api/citas";
    protected static final String buscarTodas = "/buscarTodas";
    protected static final String buscarPorVehiculo = "/buscarPorVehiculo";
    protected static final String crearNuevaCita = "/crearNuevaCita";
    protected static final String actualizarCita = "/actualizarCita";
    protected static final String eliminarCita = "/eliminarCita";

    protected static RespuestaHttpBO buscarTodas() {
        logger.info("Buscando todas las citas");

        RespuestaHttpBO respuesta = new RespuestaHttpBO();
        CitasFachadaConsultasImpl fachada = new CitasFachadaConsultasImpl();
        List<CitaDTO> citas = fachada.buscarTodas();

        if (citas != null && !citas.isEmpty()) {
            respuesta.setObjeto(citas);
            respuesta.setIsOk(true);
            respuesta.setStatus(EstadoRespuestaHTTP.OK.getCodigo());
            respuesta.setMensaje("Devolviendo lista de citas");
        } else {
            respuesta.setObjeto(null);
            respuesta.setIsOk(false);
            respuesta.setStatus(EstadoRespuestaHTTP.NO_CONTENT.getCodigo());
            respuesta.setMensaje("No se han encontrado citas");
        }

        return respuesta;
    }

    protected static RespuestaHttpBO buscarCitasPorVehiculo(String vehiculoUuid) {
        logger.info("Buscando citas por vehiculo uuid: {}", vehiculoUuid);

        RespuestaHttpBO respuesta = new RespuestaHttpBO();
        CitasFachadaConsultasImpl fachada = new CitasFachadaConsultasImpl();
        List<CitaDTO> citas = fachada.buscarPorVehiculo(vehiculoUuid);

        if (citas != null && !citas.isEmpty()) {
            respuesta.setObjeto(citas);
            respuesta.setIsOk(true);
            respuesta.setStatus(EstadoRespuestaHTTP.OK.getCodigo());
            respuesta.setMensaje("Citas encontradas");
        } else {
            respuesta.setObjeto(null);
            respuesta.setIsOk(false);
            respuesta.setStatus(EstadoRespuestaHTTP.NO_CONTENT.getCodigo());
            respuesta.setMensaje("Citas no encontradas");
        }

        return respuesta;
    }

    protected static RespuestaHttpBO guardarNuevaCita(CitaDTO citaDTO) {
        logger.info("Creando cita");

        RespuestaHttpBO respuesta = new RespuestaHttpBO();
        CitasFachadaEjecutarImpl fachada = new CitasFachadaEjecutarImpl();

        try {
            fachada.guardarNuevaCita(citaDTO);
            respuesta.setIsOk(true);
            respuesta.setStatus(EstadoRespuestaHTTP.OK.getCodigo());
            respuesta.setMensaje("Cita guardada correctamente");

        } catch (Exception e) {
            logger.error("Error guardando cita", e);
            respuesta.setIsOk(false);
            respuesta.setStatus(EstadoRespuestaHTTP.INTERNAL_SERVER_ERROR.getCodigo());
            respuesta.setMensaje("Error guardando cita: " + e.getMessage());
        }

        return respuesta;
    }

    protected static RespuestaHttpBO actualizarCita(CitaDTO citaDTO) {
        logger.info("Actualizando cita");

        RespuestaHttpBO respuesta = new RespuestaHttpBO();
        CitasFachadaEjecutarImpl fachada = new CitasFachadaEjecutarImpl();

        try {
            fachada.actualizarCita(citaDTO);
            respuesta.setIsOk(true);
            respuesta.setStatus(EstadoRespuestaHTTP.OK.getCodigo());
            respuesta.setMensaje("Cita actualizada correctamente");

        } catch (Exception e) {
            logger.error("Error actualizando cita", e);
            respuesta.setIsOk(false);
            respuesta.setStatus(EstadoRespuestaHTTP.INTERNAL_SERVER_ERROR.getCodigo());
            respuesta.setMensaje("Error actualizando cita: " + e.getMessage());
        }

        return respuesta;
    }

    protected static RespuestaHttpBO eliminarCita(CitaDTO citaDTO) {
        logger.info("Eliminando cita");

        RespuestaHttpBO respuesta = new RespuestaHttpBO();
        CitasFachadaEjecutarImpl fachada = new CitasFachadaEjecutarImpl();

        try {
            fachada.eliminarCita(citaDTO);
            respuesta.setIsOk(true);
            respuesta.setStatus(EstadoRespuestaHTTP.OK.getCodigo());
            respuesta.setMensaje("Cita eliminada correctamente");

        } catch (Exception e) {
            logger.error("Error eliminando cita", e);
            respuesta.setIsOk(false);
            respuesta.setStatus(EstadoRespuestaHTTP.INTERNAL_SERVER_ERROR.getCodigo());
            respuesta.setMensaje("Error eliminando cita: " + e.getMessage());
        }

        return respuesta;
    }
}
