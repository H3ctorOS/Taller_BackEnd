package com.TallerWapo.Adaptadores.sparkApiRest.controladores;


import com.TallerWapo.Adaptadores.sparkApiRest.controladores.base.SparkController;
import com.TallerWapo.dominio.bo.Clientes.ClienteBO;
import com.TallerWapo.dominio.bo.RespuestaHttpBO;
import com.TallerWapo.dominio.interfaces.puertos.ApiRest.EstadoRespuestaHTTP;
import com.TallerWapo.dominio.interfaces.puertos.ApiRest.controladores.ClientesControlador;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static spark.Spark.*;


public class ClientesSparkControlador extends ClientesControlador implements SparkController {
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
                RespuestaHttpBO respuesta;

                try{
                    //Rescatar objeto
                    ClienteBO cliente = SparkController.JsonToDTO(req, ClienteBO.class);
                    respuesta = crearCliente(cliente);
                    //Dar respuesta Ok
                    res.status(respuesta.getStatus());

                }catch(Exception e){
                    res.status(EstadoRespuestaHTTP.INTERNAL_SERVER_ERROR.getCodigo());
                    respuesta = new RespuestaHttpBO();
                    respuesta.setMensaje(e.getMessage());
                }

                return gson.toJson(respuesta);
            });

            /**
             *  Actualizar cliente
             */
            post(actualizarCliente, (req, res) -> {
                res.type(tipoJSON);
                logger.info("Actualizando cliente");
                RespuestaHttpBO respuesta;

                try {
                    //rescatar json
                    ClienteBO cliente = SparkController.JsonToDTO(req, ClienteBO.class);
                    respuesta = actualizarCliente(cliente);
                    //Dar respuesta Ok
                    res.status(respuesta.getStatus());

                } catch (Exception e) {
                    res.status(EstadoRespuestaHTTP.INTERNAL_SERVER_ERROR.getCodigo());
                    respuesta = new RespuestaHttpBO();
                    respuesta.setMensaje(e.getMessage());
                }

                return gson.toJson(respuesta);
            });


            /**
             *  Eliminar cliente
             */
            post(eliminarCliente, (req, res) -> {
                res.type(tipoJSON);
                logger.info("Eliminando cliente");
                RespuestaHttpBO respuesta;

                try {
                    String dni = req.queryParams("dni");  // Rescata el parámetro de query
                    respuesta = eliminarCliente(dni);
                    //Dar respuesta Ok
                    res.status(respuesta.getStatus());

                } catch (Exception e) {
                    res.status(EstadoRespuestaHTTP.INTERNAL_SERVER_ERROR.getCodigo());
                    respuesta = new RespuestaHttpBO();
                    respuesta.setMensaje(e.getMessage());
                }

                return gson.toJson(respuesta);
            });


            /**
             *  Buscar cliente por dni
             */
            get(buscarClienteDni, (req, res) -> {
                res.type(tipoJSON);
                logger.info("Buscando cliente");
                RespuestaHttpBO respuesta;

                try {
                    String dni = req.queryParams("dni");  // Rescata el parámetro de query
                    respuesta = buscarClientePorDni(dni);
                    //Dar respuesta Ok y retornar vehiculo encontrado
                    res.status(respuesta.getStatus());

                } catch (Exception e) {
                    res.status(EstadoRespuestaHTTP.INTERNAL_SERVER_ERROR.getCodigo());
                    respuesta = new RespuestaHttpBO();
                    respuesta.setMensaje(e.getMessage());
                }

                return gson.toJson(respuesta);
            });

            /**
             *  Buscar todos los clientes
             */
            get(buscarTodosLosClientes, (req, res) -> {
                res.type(tipoJSON);
                logger.info("Buscando todos los clientes");
                RespuestaHttpBO respuesta;

                try {
                    respuesta = buscarTodos();
                    //Dar respuesta Ok y retornar vehiculo encontrado
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