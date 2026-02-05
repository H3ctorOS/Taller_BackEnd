package com.TallerWapo.Adaptadores.sparkApiRest.controladores;

import com.TallerWapo.Adaptadores.sparkApiRest.controladores.base.SparkController;
import com.TallerWapo.dominio.bo.RespuestaHttpBO;
import com.TallerWapo.dominio.bo.vehiculos.IngresoBO;
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

            /**
             * Crear nuevo ingreso
             */
            post(crearNuevoIngreso, (req, res) -> {
                res.type(tipoJSON);
                logger.info("Guardando nuevo ingreso");
                RespuestaHttpBO respuesta;

                try {
                    IngresoBO ingreso = SparkController.JsonToBO(req, IngresoBO.class);
                    respuesta = guardarNuevoIngreso(ingreso);
                    res.status(respuesta.getStatus());
                } catch (Exception e) {
                    res.status(EstadoRespuestaHTTP.INTERNAL_SERVER_ERROR.getCodigo());
                    respuesta = new RespuestaHttpBO();
                    respuesta.setMensaje(e.getMessage());
                }

                return gson.toJson(respuesta);
            });

            /**
             * Actualizar ingreso
             */
            post(actualizarIngreso, (req, res) -> {
                res.type(tipoJSON);
                logger.info("Actualizando ingreso");
                RespuestaHttpBO respuesta;

                try {
                    IngresoBO ingreso = SparkController.JsonToBO(req, IngresoBO.class);
                    respuesta = actualizarIngreso(ingreso);
                    res.status(respuesta.getStatus());
                } catch (Exception e) {
                    res.status(EstadoRespuestaHTTP.INTERNAL_SERVER_ERROR.getCodigo());
                    respuesta = new RespuestaHttpBO();
                    respuesta.setMensaje(e.getMessage());
                }

                return gson.toJson(respuesta);
            });

            /**
             * Eliminar ingreso
             */
            post(eliminarIngreso, (req, res) -> {
                res.type(tipoJSON);
                logger.info("Eliminando ingreso");
                RespuestaHttpBO respuesta;

                try {
                    IngresoBO ingreso = SparkController.JsonToBO(req, IngresoBO.class);
                    respuesta = eliminarIngreso(ingreso);
                    res.status(respuesta.getStatus());
                } catch (Exception e) {
                    res.status(EstadoRespuestaHTTP.INTERNAL_SERVER_ERROR.getCodigo());
                    respuesta = new RespuestaHttpBO();
                    respuesta.setMensaje(e.getMessage());
                }

                return gson.toJson(respuesta);
            });

            /**
             * Buscar ingreso por cita
             */
            get(buscarPorCita, (req, res) -> {
                res.type(tipoJSON);
                logger.info("Buscando ingresos por cita");
                RespuestaHttpBO respuesta;

                try {
                    String citaUuid = req.queryParams("citaUuid");
                    respuesta = buscarIngresosPorCita(citaUuid);
                    res.status(respuesta.getStatus());
                } catch (Exception e) {
                    res.status(EstadoRespuestaHTTP.INTERNAL_SERVER_ERROR.getCodigo());
                    respuesta = new RespuestaHttpBO();
                    respuesta.setMensaje(e.getMessage());
                }

                return gson.toJson(respuesta);
            });

            /**
             * Buscar todos los ingresos
             */
            get(buscarTodos, (req, res) -> {
                res.type(tipoJSON);
                logger.info("Buscando todos los ingresos");
                RespuestaHttpBO respuesta;

                try {
                    respuesta = buscarTodos();
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
