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
             *  crear cliente
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
             *  Actualizar cliente
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
             *  Eliminar cliente
             */
            post(eliminarCliente, (req, res) -> {
                res.type(tipoJSON);
                logger.info("Eliminando cliente");

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
             *  Buscar cliente por dni
             */
            get(buscarClienteDni, (req, res) -> {
                res.type(tipoJSON);
                logger.info("Buscando cliente");

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

            /**
             *  Buscar todos los clientes
             */
            get(buscarTodosLosClientes, (req, res) -> {
                res.type(tipoJSON);
                logger.info("Buscando todos los clientes");

                try {

                    RespuestaHttpBO respuesta = buscarTodos();

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