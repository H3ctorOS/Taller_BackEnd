package com.TallerWapo.Adaptadores.sparkApiRest.controladores;


import com.TallerWapo.dominio.interfaces.puertos.ApiRest.EstadoRespuestaHTTP;
import com.TallerWapo.dominio.interfaces.puertos.ApiRest.controladores.VehiculosControlador;
import com.TallerWapo.dominio.BOs.RespuestaHttpBO;
import com.TallerWapo.dominio.BOs.vehiculos.VehiculoBO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static spark.Spark.*;



public class VehiculosSparkControlador extends VehiculosControlador implements SparkController{
    static final Logger logger = LoggerFactory.getLogger(VehiculosSparkControlador.class);

    private VehiculosSparkControlador() {
        throw new AssertionError("No se puede instanciar vehiculosSpark");
    }

    public static void init() {
        path(rutaBase, () -> {
            /**
             *  crear vehiculo
             */
            post(crearVehiculo, (req, res) -> {
                res.type(tipoJSON);  // Siempre setea JSON
                logger.info("Creando vehiculo");
                try{
                    //Rescatar objeto
                    VehiculoBO vehiculo = SparkController.JsonToBO(req, VehiculoBO.class);

                    RespuestaHttpBO respuesta = crearVehiculo(vehiculo);

                    //Dar respuesta Ok
                    res.status(respuesta.getStatus());
                    return gson.toJson(respuesta.getMensaje());

                }catch(Exception e){
                    res.status(EstadoRespuestaHTTP.INTERNAL_SERVER_ERROR.getCodigo());
                    return gson.toJson(e.getMessage());
                }

            });

            /**
             *  Actualizar vehiculo
             */
            post(actualizarVehiculo, (req, res) -> {
                res.type(tipoJSON);
                logger.info("Actualizando vehiculo");

                try {
                    //rescatar json
                    VehiculoBO vehiculo = SparkController.JsonToBO(req, VehiculoBO.class);

                    RespuestaHttpBO respuesta = actualizarVehiculo(vehiculo);

                    //Dar respuesta Ok
                    res.status(respuesta.getStatus());
                    return gson.toJson(respuesta.getMensaje());

                } catch (Exception e) {
                    res.status(EstadoRespuestaHTTP.INTERNAL_SERVER_ERROR.getCodigo());
                    return gson.toJson(e.getMessage());
                }

            });


            /**
             *  Eliminar vehiculo
             */
            post(eliminarVehiculo, (req, res) -> {
                res.type(tipoJSON);
                logger.info("Eliminando vehiculo");

                try {
                    String matricula = req.queryParams("matricula");  // Rescata el parámetro de query

                    RespuestaHttpBO respuesta = eliminarVehiculo(matricula);

                    //Dar respuesta Ok
                    res.status(respuesta.getStatus());
                    return gson.toJson(respuesta.getMensaje());

                } catch (Exception e) {
                    res.status(EstadoRespuestaHTTP.INTERNAL_SERVER_ERROR.getCodigo());
                    return gson.toJson(e.getMessage());
                }
            });


            /**
             *  Buscar vehiculos segun matricula
             */
            get(buscarVehiculoMatricula, (req, res) -> {
                res.type(tipoJSON);
                logger.info("Buscando vehiculo");

                try {
                    String matricula = req.queryParams("matricula");  // Rescata el parámetro de query

                    RespuestaHttpBO respuesta = buscarVehiculoPorMatricula(matricula);

                    //Dar respuesta Ok y retornar vehiculo encontrado
                    res.status(respuesta.getStatus());
                    return gson.toJson(respuesta.getObjeto());

                } catch (Exception e) {
                    res.status(EstadoRespuestaHTTP.INTERNAL_SERVER_ERROR.getCodigo());
                    return gson.toJson(e.getMessage());
                }
            });

        });
    }
}