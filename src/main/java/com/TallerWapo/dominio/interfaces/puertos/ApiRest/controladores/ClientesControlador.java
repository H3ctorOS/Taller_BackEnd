package com.TallerWapo.dominio.interfaces.puertos.ApiRest.controladores;

import com.TallerWapo.dominio.bo.Clientes.ClienteBO;
import com.TallerWapo.dominio.bo.RespuestaHttpBO;
import com.TallerWapo.dominio.fachadas.negocio.clientes.ClientesFachadaConsultasImpl;
import com.TallerWapo.dominio.fachadas.negocio.clientes.ClientesFachadaEjecutarImpl;
import com.TallerWapo.dominio.interfaces.base.ControladoresBase;
import com.TallerWapo.dominio.interfaces.puertos.ApiRest.EstadoRespuestaHTTP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


public abstract class ClientesControlador implements ControladoresBase {
    static final Logger logger = LoggerFactory.getLogger(ClientesControlador.class);

    //RUTAS
    protected static final String rutaBase = "/api/clientes";
    protected static final String crearCliente = "/crearCliente";
    protected static final String actualizarCliente = "/actualizarCliente";
    protected static  final String eliminarCliente = "/eliminarCliente";
    protected static  final String buscarClienteDni = "/buscarClienteDni";
    protected static  final String buscarTodosLosClientes = "/buscarTodosLosClientes";


    protected static RespuestaHttpBO buscarTodos() {
        logger.info("Buscando todos los clientes");
        RespuestaHttpBO respuesta = new RespuestaHttpBO();
        ClientesFachadaConsultasImpl fachadaConsultas = new ClientesFachadaConsultasImpl();
        List<ClienteBO> clientes = fachadaConsultas.buscarTodosLosClientes();

        if (clientes != null && !clientes.isEmpty()) {
            respuesta.setObjeto(clientes);
            respuesta.setIsOk(true);
            respuesta.setStatus(EstadoRespuestaHTTP.OK.getCodigo());
            respuesta.setMensaje("Devolviendo lista de clientes");

        }else {
            respuesta.setObjeto(null);
            respuesta.setIsOk(false);
            respuesta.setStatus(EstadoRespuestaHTTP.NO_CONTENT.getCodigo());
            respuesta.setMensaje("No se ha encontrado ningun cliente");
        }

        return respuesta;
    }

    protected static RespuestaHttpBO buscarClientePorDni(String dni) {
        logger.info("Buscando cliente por dni");
        RespuestaHttpBO respuesta = new RespuestaHttpBO();
        ClientesFachadaConsultasImpl fachadaConsultas = new ClientesFachadaConsultasImpl();
        ClienteBO cliente = fachadaConsultas.buscarClienteDni(dni);

        if (cliente != null) {
            respuesta.setObjeto(cliente);
            respuesta.setIsOk(true);
            respuesta.setStatus(EstadoRespuestaHTTP.OK.getCodigo());
            respuesta.setMensaje("Cliente encontrado");
        }else {
            respuesta.setObjeto(null);
            respuesta.setIsOk(false);
            respuesta.setStatus(EstadoRespuestaHTTP.NO_CONTENT.getCodigo());
            respuesta.setMensaje("Cliente no encontrado");
        }

        return respuesta;
    }


    protected static RespuestaHttpBO crearCliente(ClienteBO cliente){
        logger.info("Creando cliente");
        RespuestaHttpBO respuesta = new RespuestaHttpBO();
        ClientesFachadaEjecutarImpl fachadaEjecutar  = new ClientesFachadaEjecutarImpl();

        try {
            fachadaEjecutar.crearNuevoCliente(cliente);
            respuesta.setIsOk(true);
            respuesta.setStatus(EstadoRespuestaHTTP.OK.getCodigo());
            respuesta.setMensaje("Cliente creado correctamente");

        } catch (Exception e) {
            respuesta.setIsOk(false);
            respuesta.setStatus(EstadoRespuestaHTTP.INTERNAL_SERVER_ERROR.getCodigo());
            respuesta.setMensaje("Error creando cliente: " + e.getMessage());
        }

        return respuesta;
    }


    protected static RespuestaHttpBO actualizarCliente(ClienteBO cliente){
        logger.info("Actualizando vehiculo");
        RespuestaHttpBO respuesta = new RespuestaHttpBO();
        ClientesFachadaEjecutarImpl fachadaEjecutar  = new ClientesFachadaEjecutarImpl();

        try {
            fachadaEjecutar.actualizarCliente(cliente);
            respuesta.setIsOk(true);
            respuesta.setStatus(EstadoRespuestaHTTP.OK.getCodigo());
            respuesta.setMensaje("Cliente actualizado correctamente");

        }catch (Exception e) {
            respuesta.setIsOk(false);
            respuesta.setStatus(EstadoRespuestaHTTP.INTERNAL_SERVER_ERROR.getCodigo());
            respuesta.setMensaje("Error actualizando cliente" + e.getMessage());
        }

        return respuesta;
    }


    protected static RespuestaHttpBO eliminarCliente(String dni){
        logger.info("Eliminando cliente");
        RespuestaHttpBO respuesta = new RespuestaHttpBO();
        ClientesFachadaEjecutarImpl fachadaEjecutar  = new ClientesFachadaEjecutarImpl();

        try {
            fachadaEjecutar.eliminarCliente(dni);
            respuesta.setIsOk(true);
            respuesta.setStatus(EstadoRespuestaHTTP.OK.getCodigo());
            respuesta.setMensaje("Cliente eliminado correctamente");

        }catch (Exception e) {
            respuesta.setIsOk(false);
            respuesta.setStatus(EstadoRespuestaHTTP.INTERNAL_SERVER_ERROR.getCodigo());
            respuesta.setMensaje("Error eliminando  cliente" + e.getMessage());
        }
        return respuesta;
    }
}
