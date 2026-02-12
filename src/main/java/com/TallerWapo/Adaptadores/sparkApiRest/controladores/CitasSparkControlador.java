package com.TallerWapo.Adaptadores.sparkApiRest.controladores;

import com.TallerWapo.Adaptadores.sparkApiRest.controladores.base.SparkController;
import com.TallerWapo.dominio.bo.RespuestaHttpBO;
import com.TallerWapo.dominio.dto.calendario.CitaDTO;
import com.TallerWapo.dominio.interfaces.puertos.ApiRest.EstadoRespuestaHTTP;
import com.TallerWapo.dominio.interfaces.puertos.ApiRest.controladores.CitasControlador;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static spark.Spark.*;

public class CitasSparkControlador extends CitasControlador implements SparkController {

    private static final Logger logger = LoggerFactory.getLogger(CitasSparkControlador.class);

    private CitasSparkControlador() {
        throw new AssertionError("No se puede instanciar CitasSparkControlador");
    }

    public static void init() {

        path(rutaBase, () -> {

            /**
             * Crear nueva cita
             */
            post(crearNuevaCita, (req, res) -> {
                res.type(tipoJSON);
                logger.info("Guardando nueva cita");

                RespuestaHttpBO respuesta;

                try {
                    CitaDTO citaDTO = SparkController.JsonToDTO(req, CitaDTO.class);
                    respuesta = guardarNuevaCita(citaDTO);
                    res.status(respuesta.getStatus());

                } catch (Exception e) {
                    logger.error("Error creando cita", e);
                    res.status(EstadoRespuestaHTTP.INTERNAL_SERVER_ERROR.getCodigo());
                    respuesta = new RespuestaHttpBO();
                    respuesta.setMensaje(e.getMessage());
                }

                return gson.toJson(respuesta);
            });

            /**
             * Actualizar cita
             */
            post(actualizarCita, (req, res) -> {
                res.type(tipoJSON);
                logger.info("Actualizando cita");

                RespuestaHttpBO respuesta;

                try {
                    CitaDTO citaDTO = SparkController.JsonToDTO(req, CitaDTO.class);
                    respuesta = actualizarCita(citaDTO);
                    res.status(respuesta.getStatus());

                } catch (Exception e) {
                    logger.error("Error actualizando cita", e);
                    res.status(EstadoRespuestaHTTP.INTERNAL_SERVER_ERROR.getCodigo());
                    respuesta = new RespuestaHttpBO();
                    respuesta.setMensaje(e.getMessage());
                }

                return gson.toJson(respuesta);
            });

            /**
             * Eliminar cita
             */
            post(eliminarCita, (req, res) -> {
                res.type(tipoJSON);
                logger.info("Eliminando cita");

                RespuestaHttpBO respuesta;

                try {
                    CitaDTO citaDTO = SparkController.JsonToDTO(req, CitaDTO.class);
                    respuesta = eliminarCita(citaDTO);
                    res.status(respuesta.getStatus());

                } catch (Exception e) {
                    logger.error("Error eliminando cita", e);
                    res.status(EstadoRespuestaHTTP.INTERNAL_SERVER_ERROR.getCodigo());
                    respuesta = new RespuestaHttpBO();
                    respuesta.setMensaje(e.getMessage());
                }

                return gson.toJson(respuesta);
            });

            /**
             * Buscar citas por vehículo
             */
            get(buscarPorVehiculo, (req, res) -> {
                res.type(tipoJSON);
                logger.info("Buscando citas por vehículo");

                RespuestaHttpBO respuesta;

                try {
                    String vehiculoUuid = req.queryParams("vehiculoUuid");
                    respuesta = buscarCitasPorVehiculo(vehiculoUuid);
                    res.status(respuesta.getStatus());

                } catch (Exception e) {
                    logger.error("Error buscando citas por vehículo", e);
                    res.status(EstadoRespuestaHTTP.INTERNAL_SERVER_ERROR.getCodigo());
                    respuesta = new RespuestaHttpBO();
                    respuesta.setMensaje(e.getMessage());
                }

                return gson.toJson(respuesta);
            });

            /**
             * Buscar todas las citas
             */
            get(buscarTodas, (req, res) -> {
                res.type(tipoJSON);
                logger.info("Buscando todas las citas");

                RespuestaHttpBO respuesta;

                try {
                    respuesta = buscarTodas();
                    res.status(respuesta.getStatus());

                } catch (Exception e) {
                    logger.error("Error buscando todas las citas", e);
                    res.status(EstadoRespuestaHTTP.INTERNAL_SERVER_ERROR.getCodigo());
                    respuesta = new RespuestaHttpBO();
                    respuesta.setMensaje(e.getMessage());
                }

                return gson.toJson(respuesta);
            });

            /**
             * Buscar citas por semana del anio
             */
            get(buscarCitasSemanaAnio, (req, res) -> {
                res.type(tipoJSON);
                logger.info("Buscando citas por semana del anio");

                RespuestaHttpBO respuesta;

                try {
                    String anio = req.queryParams("anio");
                    String semana = req.queryParams("semana");

                    respuesta = buscarCitasSemanaAnio(Integer.parseInt(anio),Integer.parseInt(semana));
                    res.status(respuesta.getStatus());

                } catch (Exception e) {
                    logger.error("Error buscando citas por vehículo", e);
                    res.status(EstadoRespuestaHTTP.INTERNAL_SERVER_ERROR.getCodigo());
                    respuesta = new RespuestaHttpBO();
                    respuesta.setMensaje(e.getMessage());
                }

                return gson.toJson(respuesta);
            });



        });
    }
}
