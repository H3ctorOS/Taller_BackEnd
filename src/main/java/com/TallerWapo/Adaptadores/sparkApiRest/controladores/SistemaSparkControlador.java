package com.TallerWapo.Adaptadores.sparkApiRest.controladores;

import com.TallerWapo.Adaptadores.sparkApiRest.controladores.base.SparkController;
import com.TallerWapo.dominio.bo.RespuestaHttpBO;
import com.TallerWapo.dominio.interfaces.puertos.ApiRest.EstadoRespuestaHTTP;
import com.TallerWapo.dominio.interfaces.puertos.ApiRest.controladores.SistemaControlador;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static spark.Spark.*;

public class SistemaSparkControlador extends SistemaControlador implements SparkController {

    private static final Logger logger = LoggerFactory.getLogger(SistemaSparkControlador.class);

    private SistemaSparkControlador() {
        throw new AssertionError("No se puede instanciar SistemaSparkControlador");
    }

    public static void init() {

        path(rutaBase, () -> {

            post(apagarEquipo, (req, res) -> {
                res.type(tipoJSON);
                logger.info("Apagando el pc");

                RespuestaHttpBO respuesta;

                try {

                    respuesta = apagarEquipo();
                    res.status(respuesta.getStatus());

                } catch (Exception e) {
                    logger.error("Error apagando el equipo", e);
                    res.status(EstadoRespuestaHTTP.INTERNAL_SERVER_ERROR.getCodigo());
                    respuesta = new RespuestaHttpBO();
                    respuesta.setMensaje(e.getMessage());
                }

                return gson.toJson(respuesta);
            });


            /**
             * Buscar citas por vehículo
             */
            get(estadoSistema, (req, res) -> {
                res.type(tipoJSON);
                logger.info("Dar resumen del sistema");

                RespuestaHttpBO respuesta;

                try {
                    respuesta = estadoSistema();
                    res.status(respuesta.getStatus());

                } catch (Exception e) {
                    logger.error("Error buscando el estado del sistema", e);
                    res.status(EstadoRespuestaHTTP.INTERNAL_SERVER_ERROR.getCodigo());
                    respuesta = new RespuestaHttpBO();
                    respuesta.setMensaje(e.getMessage());
                }

                return gson.toJson(respuesta);
            });

            /**
             * Buscar semanas del aniu
             */
            get(semanasDelAnio, (req, res) -> {
                res.type(tipoJSON);
                logger.info("Buscando todas las semanas del anio");
                RespuestaHttpBO respuesta;

                try {
                    String anio = req.queryParams("anio");
                    respuesta = obtenerSemanasAnio(Integer.parseInt(anio));
                    res.status(respuesta.getStatus());

                } catch (Exception e) {
                    logger.error("Error buscando las semanas del anio", e);
                    res.status(EstadoRespuestaHTTP.INTERNAL_SERVER_ERROR.getCodigo());
                    respuesta = new RespuestaHttpBO();
                    respuesta.setMensaje(e.getMessage());
                }

                return gson.toJson(respuesta);
            });


        });//el del init
    }
}
