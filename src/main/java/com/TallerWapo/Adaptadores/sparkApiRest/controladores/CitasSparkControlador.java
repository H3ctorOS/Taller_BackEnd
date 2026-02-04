package com.TallerWapo.Adaptadores.sparkApiRest.controladores;


import com.TallerWapo.Adaptadores.sparkApiRest.controladores.base.SparkController;
import com.TallerWapo.dominio.bo.RespuestaHttpBO;
import com.TallerWapo.dominio.bo.vehiculos.CitaBO;
import com.TallerWapo.dominio.interfaces.puertos.ApiRest.EstadoRespuestaHTTP;
import com.TallerWapo.dominio.interfaces.puertos.ApiRest.controladores.CitasControlador;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static spark.Spark.*;


public class CitasSparkControlador extends CitasControlador implements SparkController {
    static final Logger logger = LoggerFactory.getLogger(CitasSparkControlador.class);

    private CitasSparkControlador() {
        throw new AssertionError("No se puede instanciar clientesSpark");
    }

    public static void init() {
        path(rutaBase, () -> {
            /**
             *  crear nueva cita
             */
            post(crearNuevaCita, (req, res) -> {
                res.type(tipoJSON);  // Siempre setea JSON
                logger.info("Guardando nueva cita");
                RespuestaHttpBO respuesta;

                try{
                    //Rescatar objeto
                    CitaBO cita = SparkController.JsonToBO(req, CitaBO.class);
                    respuesta = guardarNuevaCita(cita);
                    res.status(respuesta.getStatus());

                }catch(Exception e){
                    res.status(EstadoRespuestaHTTP.INTERNAL_SERVER_ERROR.getCodigo());
                    respuesta = new RespuestaHttpBO();
                    respuesta.setMensaje(e.getMessage());
                }

                return gson.toJson(respuesta);
            });

            /**
             *  Actualizar cita
             */
            post(actualizarCita, (req, res) -> {
                res.type(tipoJSON);
                logger.info("Actualizando cita");
                RespuestaHttpBO respuesta;

                try {
                    //rescatar json
                    CitaBO cita = SparkController.JsonToBO(req, CitaBO.class);
                    respuesta = actualizarCita(cita);
                    res.status(respuesta.getStatus());

                } catch (Exception e) {
                    res.status(EstadoRespuestaHTTP.INTERNAL_SERVER_ERROR.getCodigo());
                    respuesta = new RespuestaHttpBO();
                    respuesta.setMensaje(e.getMessage());
                }

                return gson.toJson(respuesta);
            });

            /**
             *  Eliminar cita
             */
            post(eliminarCita, (req, res) -> {
                res.type(tipoJSON);
                logger.info("Eliminando cita");
                RespuestaHttpBO respuesta;

                try {
                    //Rescatar objeto
                    CitaBO cita = SparkController.JsonToBO(req, CitaBO.class);
                    respuesta = eliminarCita(cita);
                    res.status(respuesta.getStatus());

                } catch (Exception e) {
                    res.status(EstadoRespuestaHTTP.INTERNAL_SERVER_ERROR.getCodigo());
                    respuesta = new RespuestaHttpBO();
                    respuesta.setMensaje(e.getMessage());
                }

                return gson.toJson(respuesta);
            });

            /**
             *  Buscar cita por vehiculo
             */
            get(buscarPorVehiculo, (req, res) -> {
                res.type(tipoJSON);
                logger.info("Buscando citas por vehiculo");
                logger.info("Get recibido: {}", req.toString());

                RespuestaHttpBO respuesta;

                try {
                    // Rescata el parámetro de query
                    String vehiculoUuid = req.queryParams("vehiculoUuid");

                    respuesta = buscarCitasPorVehiculo(vehiculoUuid);
                    res.status(respuesta.getStatus());

                } catch (Exception e) {
                    res.status(EstadoRespuestaHTTP.INTERNAL_SERVER_ERROR.getCodigo());
                    respuesta = new RespuestaHttpBO();
                    respuesta.setMensaje(e.getMessage());
                }

                return gson.toJson(respuesta);
            });


            /**
             *  Buscar todas
             */
            get(buscarTodas, (req, res) -> {
                res.type(tipoJSON);
                logger.info("Buscando todas las citas");
                RespuestaHttpBO respuesta;
                try {
                    respuesta = buscarTodas();
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