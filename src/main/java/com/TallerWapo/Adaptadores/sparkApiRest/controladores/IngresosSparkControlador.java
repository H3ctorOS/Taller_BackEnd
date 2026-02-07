package com.TallerWapo.Adaptadores.sparkApiRest.controladores;

import com.TallerWapo.Adaptadores.sparkApiRest.controladores.base.SparkController;
import com.TallerWapo.dominio.dto.contabilidad.IngresoConCitaDTO;
import com.TallerWapo.dominio.dto.contabilidad.IngresoDTO;
import com.TallerWapo.dominio.bo.RespuestaHttpBO;
import com.TallerWapo.dominio.interfaces.puertos.ApiRest.EstadoRespuestaHTTP;
import com.TallerWapo.dominio.interfaces.puertos.ApiRest.controladores.IngresosControlador;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static spark.Spark.*;

public class IngresosSparkControlador extends IngresosControlador implements SparkController {
    static final Logger logger = LoggerFactory.getLogger(IngresosSparkControlador.class);

    private IngresosSparkControlador() {
        throw new AssertionError("No se puede instanciar IngresosSparkControlador");
    }

    public static void init() {
        path(rutaBase, () -> {

            // Crear nuevo ingreso
            post(crearNuevoIngresoCita, (req, res) -> {
                res.type(tipoJSON);
                logger.info("Guardando nuevo ingreso en cita");
                RespuestaHttpBO respuesta;

                try {
                    IngresoConCitaDTO ingresoCitaDTO = SparkController.JsonToDTO(req, IngresoConCitaDTO.class);
                    respuesta = guardarNuevoIngresoCita(ingresoCitaDTO);
                    res.status(respuesta.getStatus());

                } catch (Exception e) {
                    res.status(EstadoRespuestaHTTP.INTERNAL_SERVER_ERROR.getCodigo());
                    respuesta = new RespuestaHttpBO();
                    respuesta.setMensaje(e.getMessage());
                }

                return gson.toJson(respuesta);
            });

            // Actualizar ingreso
            post(actualizarIngreso, (req, res) -> {
                res.type(tipoJSON);
                logger.info("Actualizando ingreso");
                RespuestaHttpBO respuesta;

                try {
                    IngresoDTO ingresoDTO = SparkController.JsonToDTO(req, IngresoDTO.class);
                    respuesta = actualizarIngreso(ingresoDTO);
                    res.status(respuesta.getStatus());
                } catch (Exception e) {
                    res.status(EstadoRespuestaHTTP.INTERNAL_SERVER_ERROR.getCodigo());
                    respuesta = new RespuestaHttpBO();
                    respuesta.setMensaje(e.getMessage());
                }

                return gson.toJson(respuesta);
            });

            // Eliminar ingreso
            post(eliminarIngreso, (req, res) -> {
                res.type(tipoJSON);
                logger.info("Eliminando ingreso");
                RespuestaHttpBO respuesta;

                try {
                    IngresoDTO ingresoDTO = SparkController.JsonToDTO(req, IngresoDTO.class);
                    respuesta = eliminarIngreso(ingresoDTO);
                    res.status(respuesta.getStatus());
                } catch (Exception e) {
                    res.status(EstadoRespuestaHTTP.INTERNAL_SERVER_ERROR.getCodigo());
                    respuesta = new RespuestaHttpBO();
                    respuesta.setMensaje(e.getMessage());
                }

                return gson.toJson(respuesta);
            });

            // Buscar ingreso por cita
            get(buscarPorCita, (req, res) -> {
                res.type(tipoJSON);
                logger.info("Buscando ingresos por cita");
                RespuestaHttpBO respuesta;

                try {
                    String citaUuid = req.queryParams("citaUuid");
                    respuesta = buscarIngresosPorCita(citaUuid); // asumir que ahora devuelve DTOs
                    res.status(respuesta.getStatus());
                } catch (Exception e) {
                    res.status(EstadoRespuestaHTTP.INTERNAL_SERVER_ERROR.getCodigo());
                    respuesta = new RespuestaHttpBO();
                    respuesta.setMensaje(e.getMessage());
                }

                return gson.toJson(respuesta);
            });

            // Buscar todos los ingresos
            get(buscarTodos, (req, res) -> {
                res.type(tipoJSON);
                logger.info("Buscando todos los ingresos");
                RespuestaHttpBO respuesta;

                try {
                    respuesta = buscarTodos(); // asumir que ahora devuelve DTOs
                    res.status(respuesta.getStatus());
                } catch (Exception e) {
                    res.status(EstadoRespuestaHTTP.INTERNAL_SERVER_ERROR.getCodigo());
                    respuesta = new RespuestaHttpBO();
                    respuesta.setMensaje(e.getMessage());
                }

                return gson.toJson(respuesta);
            });
        });
    }
}
