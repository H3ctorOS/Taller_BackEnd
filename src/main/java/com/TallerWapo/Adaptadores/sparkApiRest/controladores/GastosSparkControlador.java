package com.TallerWapo.Adaptadores.sparkApiRest.controladores;

import com.TallerWapo.Adaptadores.sparkApiRest.controladores.base.SparkController;
import com.TallerWapo.dominio.bo.RespuestaHttpBO;
import com.TallerWapo.dominio.dto.contabilidad.GastoDTO;
import com.TallerWapo.dominio.dto.contabilidad.GastoConCitaDTO;
import com.TallerWapo.dominio.interfaces.puertos.ApiRest.EstadoRespuestaHTTP;
import com.TallerWapo.dominio.interfaces.puertos.ApiRest.controladores.GastosControlador;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static spark.Spark.*;

public class GastosSparkControlador extends GastosControlador implements SparkController {
    static final Logger logger = LoggerFactory.getLogger(GastosSparkControlador.class);

    private GastosSparkControlador() {
        throw new AssertionError("No se puede instanciar GastosSparkControlador");
    }

    public static void init() {
        path(rutaBase, () -> {

            /**
             * Crear nuevo gasto con cita
             */
            post(crearNuevoGasto, (req, res) -> {
                res.type(tipoJSON);
                logger.info("Guardando nuevo gasto con cita");
                RespuestaHttpBO respuesta;

                try {
                    GastoConCitaDTO gastoDTO = SparkController.JsonToDTO(req, GastoConCitaDTO.class);
                    respuesta = guardarNuevoGasto(gastoDTO);
                    res.status(respuesta.getStatus());
                } catch (Exception e) {
                    res.status(EstadoRespuestaHTTP.INTERNAL_SERVER_ERROR.getCodigo());
                    respuesta = new RespuestaHttpBO();
                    respuesta.setMensaje(e.getMessage());
                }

                return gson.toJson(respuesta);
            });

            /**
             * Actualizar gasto
             */
            post(actualizarGasto, (req, res) -> {
                res.type(tipoJSON);
                logger.info("Actualizando gasto DTO");
                RespuestaHttpBO respuesta;

                try {
                    GastoDTO gastoDTO = SparkController.JsonToDTO(req, GastoDTO.class);
                    respuesta = actualizarGasto(gastoDTO);
                    res.status(respuesta.getStatus());
                } catch (Exception e) {
                    res.status(EstadoRespuestaHTTP.INTERNAL_SERVER_ERROR.getCodigo());
                    respuesta = new RespuestaHttpBO();
                    respuesta.setMensaje(e.getMessage());
                }

                return gson.toJson(respuesta);
            });

            /**
             * Eliminar gasto
             */
            post(eliminarGasto, (req, res) -> {
                res.type(tipoJSON);
                logger.info("Eliminando gasto DTO");
                RespuestaHttpBO respuesta;

                try {
                    GastoDTO gastoDTO = SparkController.JsonToDTO(req, GastoDTO.class);
                    respuesta = eliminarGasto(gastoDTO);
                    res.status(respuesta.getStatus());
                } catch (Exception e) {
                    res.status(EstadoRespuestaHTTP.INTERNAL_SERVER_ERROR.getCodigo());
                    respuesta = new RespuestaHttpBO();
                    respuesta.setMensaje(e.getMessage());
                }

                return gson.toJson(respuesta);
            });

            /**
             * Buscar gasto por cita
             */
            get(buscarPorCita, (req, res) -> {
                res.type(tipoJSON);
                logger.info("Buscando gastos por cita");
                RespuestaHttpBO respuesta;

                try {
                    String citaUuid = req.queryParams("citaUuid");
                    respuesta = buscarGastosPorCita(citaUuid);
                    res.status(respuesta.getStatus());
                } catch (Exception e) {
                    res.status(EstadoRespuestaHTTP.INTERNAL_SERVER_ERROR.getCodigo());
                    respuesta = new RespuestaHttpBO();
                    respuesta.setMensaje(e.getMessage());
                }

                return gson.toJson(respuesta);
            });

            /**
             * Buscar todos los gastos
             */
            get(buscarTodos, (req, res) -> {
                res.type(tipoJSON);
                logger.info("Buscando todos los gastos");
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
