package com.TallerWapo.dominio.interfaces.puertos.ApiRest.controladores;

import com.TallerWapo.dominio.BOs.Clientes.ClienteBO;
import com.TallerWapo.dominio.BOs.RespuestaHttpBO;
import com.TallerWapo.dominio.BOs.vehiculos.CitaBO;
import com.TallerWapo.dominio.BOs.vehiculos.VehiculoBO;
import com.TallerWapo.dominio.fachadas.negocio.vehiculos.CitasFachadaConsultasImpl;
import com.TallerWapo.dominio.fachadas.negocio.vehiculos.CitasFachadaEjecutarImpl;
import com.TallerWapo.dominio.interfaces.base.ControladoresBase;
import com.TallerWapo.dominio.interfaces.puertos.ApiRest.EstadoRespuestaHTTP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


public abstract class CitasControlador implements ControladoresBase {
    static final Logger logger = LoggerFactory.getLogger(CitasControlador.class);

    //RUTAS
    protected static final String rutaBase = "/api/citas";
    protected static  final String buscarTodas = "/buscarTodas";
    protected static final String buscarPorVehiculo = "/buscarPorVehiculo";
    protected static  final String crearNuevaCita = "/crearNuevaCita";
    protected static  final String actualizarCita = "/actualizarCita";
    protected static  final String eliminarCita = "/eliminarCita";

    protected static RespuestaHttpBO buscarTodas() {
        logger.info("Buscando todas las citas");
        RespuestaHttpBO respuesta = new RespuestaHttpBO();
        CitasFachadaConsultasImpl fachadaCitasConsulta = new CitasFachadaConsultasImpl();
        List<CitaBO> citas = fachadaCitasConsulta.buscarTodas();

        if (citas != null && !citas.isEmpty()) {
            respuesta.setObjeto(citas);
            respuesta.setIsOk(true);
            respuesta.setStatus(EstadoRespuestaHTTP.OK.getCodigo());
            respuesta.setMensaje("Devolviendo lista de citas");

        }else {
            respuesta.setObjeto(null);
            respuesta.setIsOk(false);
            respuesta.setStatus(EstadoRespuestaHTTP.NO_CONTENT.getCodigo());
            respuesta.setMensaje("No se ha encontrado citas");
        }

        return respuesta;
    }

    protected static RespuestaHttpBO buscarCitasPorVehiculo(VehiculoBO vehiculo){
        logger.info("Buscando citas por vehiculo");
        RespuestaHttpBO respuesta = new RespuestaHttpBO();
        CitasFachadaConsultasImpl fachadaCitasConsulta = new CitasFachadaConsultasImpl();
        List<CitaBO> citas = fachadaCitasConsulta.buscarPorVehiculo(vehiculo);

        if (citas != null) {
            respuesta.setObjeto(citas);
            respuesta.setIsOk(true);
            respuesta.setStatus(EstadoRespuestaHTTP.OK.getCodigo());
            respuesta.setMensaje("Citas encontradas");

        }else {
            respuesta.setObjeto(null);
            respuesta.setIsOk(false);
            respuesta.setStatus(EstadoRespuestaHTTP.NO_CONTENT.getCodigo());
            respuesta.setMensaje("Citas no encontradas");
        }

        return respuesta;
    }


    protected static RespuestaHttpBO guardarNuevaCita(CitaBO cita){
        logger.info("Creando cita");
        RespuestaHttpBO respuesta = new RespuestaHttpBO();
        CitasFachadaEjecutarImpl fachadaCitasEjecutar= new CitasFachadaEjecutarImpl();

        try {
            fachadaCitasEjecutar.guardarNuevaCita(cita);
            respuesta.setIsOk(true);
            respuesta.setStatus(EstadoRespuestaHTTP.OK.getCodigo());
            respuesta.setMensaje("Cita guardada correctamente");

        } catch (Exception e) {
            respuesta.setIsOk(false);
            respuesta.setStatus(EstadoRespuestaHTTP.INTERNAL_SERVER_ERROR.getCodigo());
            respuesta.setMensaje("Error guardando cita" + e.getMessage());
        }

        return respuesta;
    }


    protected static RespuestaHttpBO actualizarCita(CitaBO cita){
        logger.info("Actualizando vehiculo");
        RespuestaHttpBO respuesta = new RespuestaHttpBO();
        CitasFachadaEjecutarImpl fachadaCitasEjecutar= new CitasFachadaEjecutarImpl();

        try {
            fachadaCitasEjecutar.actualizarCita(cita);
            respuesta.setIsOk(true);
            respuesta.setStatus(EstadoRespuestaHTTP.OK.getCodigo());
            respuesta.setMensaje("Cita actualizada correctamente");

        }catch (Exception e) {
            respuesta.setIsOk(false);
            respuesta.setStatus(EstadoRespuestaHTTP.INTERNAL_SERVER_ERROR.getCodigo());
            respuesta.setMensaje("Error actualizando cita" + e.getMessage());
        }

        return respuesta;
    }


    protected static RespuestaHttpBO eliminarCita(CitaBO cita){
        logger.info("Eliminando cliente");
        RespuestaHttpBO respuesta = new RespuestaHttpBO();
        CitasFachadaEjecutarImpl fachadaCitasEjecutar= new CitasFachadaEjecutarImpl();

        try {
            fachadaCitasEjecutar.eliminarCita(cita);
            respuesta.setIsOk(true);
            respuesta.setStatus(EstadoRespuestaHTTP.OK.getCodigo());
            respuesta.setMensaje("Cita eliminada correctamente");

        }catch (Exception e) {
            respuesta.setIsOk(false);
            respuesta.setStatus(EstadoRespuestaHTTP.INTERNAL_SERVER_ERROR.getCodigo());
            respuesta.setMensaje("Error eliminando cita" + e.getMessage());
        }
        return respuesta;
    }
}