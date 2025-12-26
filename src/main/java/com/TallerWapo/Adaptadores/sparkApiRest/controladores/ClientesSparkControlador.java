package com.TallerWapo.Adaptadores.sparkApiRest.controladores;


import com.TallerWapo.dominio.BOs.Clientes.ClienteBO;
import com.TallerWapo.dominio.BOs.RespuestaHttpBO;
import com.TallerWapo.dominio.interfaces.puertos.ApiRest.EstadoRespuestaHTTP;
import com.TallerWapo.dominio.interfaces.puertos.ApiRest.controladores.ClientesControlador;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static spark.Spark.*;


public class ClientesSparkControlador extends ClientesControlador implements SparkController{
    static final Logger logger = LoggerFactory.getLogger(ClientesSparkControlador.class);

    private ClientesSparkControlador() {
        throw new AssertionError("No se puede instanciar clientesSpark");
    }

    public static void init() {
        path(rutaBase, () -> {
            /**
             *  crear vehiculo
             */
            post(crearCliente, (req, res) -> {
                res.type(tipoJSON);  // Siempre setea JSON
                logger.info("Creando cliente");
                try{
                    //Rescatar objeto
                    ClienteBO cliente = SparkController.JsonToBO(req, ClienteBO.class);

                    RespuestaHttpBO respuesta = crearCliente(cliente);

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
            post(actualizarCliente, (req, res) -> {
                res.type(tipoJSON);
                logger.info("Actualizando cliente");

                try {
                    //rescatar json
                    ClienteBO cliente = SparkController.JsonToBO(req, ClienteBO.class);

                    RespuestaHttpBO respuesta = actualizarCliente(cliente);

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
            post(eliminarCliente, (req, res) -> {
                res.type(tipoJSON);
                logger.info("Eliminando vehiculo");

                try {
                    String dni = req.queryParams("dni");  // Rescata el parámetro de query

                    RespuestaHttpBO respuesta = eliminarCliente(dni);

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
            get(buscarClienteDni, (req, res) -> {
                res.type(tipoJSON);
                logger.info("Buscando vehiculo");

                try {
                    String dni = req.queryParams("dni");  // Rescata el parámetro de query

                    RespuestaHttpBO respuesta = buscarClientePorDni(dni);

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