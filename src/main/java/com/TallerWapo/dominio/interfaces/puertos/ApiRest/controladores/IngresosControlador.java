package com.TallerWapo.dominio.interfaces.puertos.ApiRest.controladores;

import com.TallerWapo.dominio.bo.RespuestaHttpBO;
import com.TallerWapo.dominio.dto.contabilidad.IngresoConCitaDTO;
import com.TallerWapo.dominio.dto.contabilidad.IngresoDTO;
import com.TallerWapo.dominio.fachadas.negocio.contabilidad.IngresosFachadaConsultasImpl;
import com.TallerWapo.dominio.fachadas.negocio.contabilidad.IngresosFachadaEjecutarImpl;
import com.TallerWapo.dominio.interfaces.base.ControladoresBase;
import com.TallerWapo.dominio.interfaces.puertos.ApiRest.EstadoRespuestaHTTP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public abstract class IngresosControlador implements ControladoresBase {
    static final Logger logger = LoggerFactory.getLogger(IngresosControlador.class);

    // RUTAS
    protected static final String rutaBase = "/api/ingresos";
    protected static final String buscarTodos = "/buscarTodos";
    protected static final String buscarPorCita = "/buscarPorCita";
    protected static final String crearNuevoIngresoCita = "/crearNuevoIngresoCita";
    protected static final String actualizarIngreso = "/actualizarIngreso";
    protected static final String eliminarIngreso = "/eliminarIngreso";

    protected static RespuestaHttpBO buscarTodos() {
        logger.info("Buscando todos los ingresos");
        RespuestaHttpBO respuesta = new RespuestaHttpBO();
        IngresosFachadaConsultasImpl fachada = new IngresosFachadaConsultasImpl();

        List<IngresoDTO> ingresos = fachada.buscarTodos();

        if (ingresos != null && !ingresos.isEmpty()) {
            respuesta.setObjeto(ingresos);
            respuesta.setIsOk(true);
            respuesta.setStatus(EstadoRespuestaHTTP.OK.getCodigo());
            respuesta.setMensaje("Devolviendo lista de ingresos");
        } else {
            respuesta.setObjeto(null);
            respuesta.setIsOk(false);
            respuesta.setStatus(EstadoRespuestaHTTP.NO_CONTENT.getCodigo());
            respuesta.setMensaje("No se han encontrado ingresos");
        }

        return respuesta;
    }

    protected static RespuestaHttpBO buscarIngresosPorCita(String citaUuid) {
        logger.info("Buscando ingresos por cita uuid: {}", citaUuid);
        RespuestaHttpBO respuesta = new RespuestaHttpBO();
        IngresosFachadaConsultasImpl fachada = new IngresosFachadaConsultasImpl();

        List<IngresoDTO> ingresos = fachada.buscarPorCita(citaUuid);

        if (ingresos != null && !ingresos.isEmpty()) {
            respuesta.setObjeto(ingresos);
            respuesta.setIsOk(true);
            respuesta.setStatus(EstadoRespuestaHTTP.OK.getCodigo());
            respuesta.setMensaje("Ingresos encontrados");
        } else {
            respuesta.setObjeto(null);
            respuesta.setIsOk(false);
            respuesta.setStatus(EstadoRespuestaHTTP.NO_CONTENT.getCodigo());
            respuesta.setMensaje("No se han encontrado ingresos para la cita");
        }

        return respuesta;
    }

    protected static RespuestaHttpBO guardarNuevoIngresoCita(IngresoConCitaDTO ingreso) {
        logger.info("Creando nuevo ingreso en cita");
        RespuestaHttpBO respuesta = new RespuestaHttpBO();
        IngresosFachadaEjecutarImpl fachada = new IngresosFachadaEjecutarImpl();

        try {
            fachada.guardarNuevoIngresoCita(ingreso); // ahora fachada acepta DTO
            respuesta.setIsOk(true);
            respuesta.setStatus(EstadoRespuestaHTTP.OK.getCodigo());
            respuesta.setMensaje("Ingreso guardado correctamente");

        } catch (Exception e) {
            respuesta.setIsOk(false);
            respuesta.setStatus(EstadoRespuestaHTTP.INTERNAL_SERVER_ERROR.getCodigo());
            respuesta.setMensaje("Error guardando ingreso: " + e.getMessage());
        }

        return respuesta;
    }

    protected static RespuestaHttpBO actualizarIngreso(IngresoDTO ingreso) {
        logger.info("Actualizando ingreso");
        RespuestaHttpBO respuesta = new RespuestaHttpBO();
        IngresosFachadaEjecutarImpl fachada = new IngresosFachadaEjecutarImpl();

        try {
            fachada.actualizarIngreso(ingreso); // fachada acepta DTO
            respuesta.setIsOk(true);
            respuesta.setStatus(EstadoRespuestaHTTP.OK.getCodigo());
            respuesta.setMensaje("Ingreso actualizado correctamente");
        } catch (Exception e) {
            respuesta.setIsOk(false);
            respuesta.setStatus(EstadoRespuestaHTTP.INTERNAL_SERVER_ERROR.getCodigo());
            respuesta.setMensaje("Error actualizando ingreso: " + e.getMessage());
        }

        return respuesta;
    }

    protected static RespuestaHttpBO eliminarIngreso(IngresoDTO ingreso) {
        logger.info("Eliminando ingreso");
        RespuestaHttpBO respuesta = new RespuestaHttpBO();
        IngresosFachadaEjecutarImpl fachada = new IngresosFachadaEjecutarImpl();

        try {
            fachada.eliminarIngreso(ingreso); // fachada acepta DTO
            respuesta.setIsOk(true);
            respuesta.setStatus(EstadoRespuestaHTTP.OK.getCodigo());
            respuesta.setMensaje("Ingreso eliminado correctamente");
        } catch (Exception e) {
            respuesta.setIsOk(false);
            respuesta.setStatus(EstadoRespuestaHTTP.INTERNAL_SERVER_ERROR.getCodigo());
            respuesta.setMensaje("Error eliminando ingreso: " + e.getMessage());
        }

        return respuesta;
    }
}
