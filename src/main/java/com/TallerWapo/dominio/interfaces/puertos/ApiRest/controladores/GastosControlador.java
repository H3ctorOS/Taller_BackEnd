package com.TallerWapo.dominio.interfaces.puertos.ApiRest.controladores;

import com.TallerWapo.dominio.bo.RespuestaHttpBO;
import com.TallerWapo.dominio.dto.contabilidad.GastoDTO;
import com.TallerWapo.dominio.dto.contabilidad.GastoConCitaDTO;
import com.TallerWapo.dominio.fachadas.negocio.contabilidad.GastosFachadaConsultasImpl;
import com.TallerWapo.dominio.fachadas.negocio.contabilidad.GastosFachadaEjecutarImpl;
import com.TallerWapo.dominio.interfaces.base.ControladoresBase;
import com.TallerWapo.dominio.interfaces.puertos.ApiRest.EstadoRespuestaHTTP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public abstract class GastosControlador implements ControladoresBase {
    static final Logger logger = LoggerFactory.getLogger(GastosControlador.class);

    // RUTAS
    protected static final String rutaBase = "/api/gastos";
    protected static final String buscarTodos = "/buscarTodos";
    protected static final String buscarPorCita = "/buscarPorCita";
    protected static final String crearNuevoGasto = "/crearNuevoGasto";
    protected static final String actualizarGasto = "/actualizarGasto";
    protected static final String eliminarGasto = "/eliminarGasto";

    protected static RespuestaHttpBO buscarTodos() {
        logger.info("Buscando todos los gastos");
        RespuestaHttpBO respuesta = new RespuestaHttpBO();
        GastosFachadaConsultasImpl fachadaConsulta = new GastosFachadaConsultasImpl();

        List<GastoDTO> gastos = fachadaConsulta.buscarTodos();

        if (gastos != null && !gastos.isEmpty()) {
            respuesta.setObjeto(gastos);
            respuesta.setIsOk(true);
            respuesta.setStatus(EstadoRespuestaHTTP.OK.getCodigo());
            respuesta.setMensaje("Devolviendo lista de gastos");
        } else {
            respuesta.setObjeto(null);
            respuesta.setIsOk(false);
            respuesta.setStatus(EstadoRespuestaHTTP.NO_CONTENT.getCodigo());
            respuesta.setMensaje("No se han encontrado gastos");
        }

        return respuesta;
    }

    protected static RespuestaHttpBO buscarGastosPorCita(String citaUuid) {
        logger.info("Buscando gastos por cita uuid: {}", citaUuid);
        RespuestaHttpBO respuesta = new RespuestaHttpBO();
        GastosFachadaConsultasImpl fachadaConsulta = new GastosFachadaConsultasImpl();

        List<GastoDTO> gastos = fachadaConsulta.buscarPorCita(citaUuid);

        if (gastos != null && !gastos.isEmpty()) {
            respuesta.setObjeto(gastos);
            respuesta.setIsOk(true);
            respuesta.setStatus(EstadoRespuestaHTTP.OK.getCodigo());
            respuesta.setMensaje("Gastos encontrados");
        } else {
            respuesta.setObjeto(null);
            respuesta.setIsOk(false);
            respuesta.setStatus(EstadoRespuestaHTTP.NO_CONTENT.getCodigo());
            respuesta.setMensaje("Gastos no encontrados");
        }

        return respuesta;
    }

    protected static RespuestaHttpBO guardarNuevoGasto(GastoConCitaDTO gastoConCitaDTO) {
        logger.info("Creando nuevo gasto con cita DTO: {}", gastoConCitaDTO);
        RespuestaHttpBO respuesta = new RespuestaHttpBO();
        GastosFachadaEjecutarImpl fachadaEjecutar = new GastosFachadaEjecutarImpl();

        try {
            // Todo se maneja como DTO
            fachadaEjecutar.guardarNuevoGastoCita(gastoConCitaDTO);
            respuesta.setIsOk(true);
            respuesta.setStatus(EstadoRespuestaHTTP.OK.getCodigo());
            respuesta.setMensaje("Gasto guardado correctamente");
        } catch (Exception e) {
            respuesta.setIsOk(false);
            respuesta.setStatus(EstadoRespuestaHTTP.INTERNAL_SERVER_ERROR.getCodigo());
            respuesta.setMensaje("Error guardando gasto: " + e.getMessage());
        }

        return respuesta;
    }

    protected static RespuestaHttpBO actualizarGasto(GastoDTO gastoDTO) {
        logger.info("Actualizando gasto DTO: {}", gastoDTO);
        RespuestaHttpBO respuesta = new RespuestaHttpBO();
        GastosFachadaEjecutarImpl fachadaEjecutar = new GastosFachadaEjecutarImpl();

        try {
            fachadaEjecutar.actualizarGasto(gastoDTO);
            respuesta.setIsOk(true);
            respuesta.setStatus(EstadoRespuestaHTTP.OK.getCodigo());
            respuesta.setMensaje("Gasto actualizado correctamente");
        } catch (Exception e) {
            respuesta.setIsOk(false);
            respuesta.setStatus(EstadoRespuestaHTTP.INTERNAL_SERVER_ERROR.getCodigo());
            respuesta.setMensaje("Error actualizando gasto: " + e.getMessage());
        }

        return respuesta;
    }

    protected static RespuestaHttpBO eliminarGasto(GastoDTO gastoDTO) {
        logger.info("Eliminando gasto DTO: {}", gastoDTO);
        RespuestaHttpBO respuesta = new RespuestaHttpBO();
        GastosFachadaEjecutarImpl fachadaEjecutar = new GastosFachadaEjecutarImpl();

        try {
            fachadaEjecutar.eliminarGasto(gastoDTO);
            respuesta.setIsOk(true);
            respuesta.setStatus(EstadoRespuestaHTTP.OK.getCodigo());
            respuesta.setMensaje("Gasto eliminado correctamente");
        } catch (Exception e) {
            respuesta.setIsOk(false);
            respuesta.setStatus(EstadoRespuestaHTTP.INTERNAL_SERVER_ERROR.getCodigo());
            respuesta.setMensaje("Error eliminando gasto: " + e.getMessage());
        }

        return respuesta;
    }
}
