package com.TallerWapo.dominio.fachadas.negocio.clientes;

import com.TallerWapo.dominio.BOs.Clientes.ClienteBO;
import com.TallerWapo.dominio.factorias.ContextoDaos;
import com.TallerWapo.dominio.fachadas.base.FachadaEjecutarBase;
import com.TallerWapo.dominio.interfaces.Daos.ClientesDao;
import com.TallerWapo.dominio.servicios.ClientesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ClientesFachadaEjecutarImpl extends FachadaEjecutarBase {
    static final Logger logger = LoggerFactory.getLogger(ClientesFachadaEjecutarImpl.class);

    public void crearNuevoCliente(ClienteBO cliente) throws Exception {
        logger.info("Creando nuevo cliente: {}", cliente.toString());

        ClientesService.validarCliente(cliente);

        ejecutarEnTransaccion(sesion -> {
            try {
                ClientesDao clientesDao = ContextoDaos.getClienteDao(sesion);
                cliente.setEstado("ACTI");
                clientesDao.guardarNuevo(cliente);

            } catch (Exception e) {
                throw new RuntimeException("Error interno al crear cliente: ",e);
            }
        });

        logger.info("Cliente creado");
    }


    public void actualizarCliente(ClienteBO cliente) throws Exception {
        logger.info("Actualizando cliente: {}", cliente.toString());

        ClientesService.validarCliente(cliente);
        ejecutarEnTransaccion(sesion -> {
            try {
                ClientesDao clientesDao = ContextoDaos.getClienteDao(sesion);
                clientesDao.actualizar(cliente);

            } catch (Exception e) {
                throw new RuntimeException("Error interno al actualizar vehiculo",e);
            }
        });

        logger.info("Cliente actualizado");
    }

    public void eliminarCliente(String dni) {
        logger.info("Eliminando cliente: {}", dni);

        ejecutarEnTransaccion(sesion -> {
            try {
                ClientesDao clientesDao = ContextoDaos.getClienteDao(sesion);
                ClienteBO cliente = clientesDao.buscarPorDni(dni);

                if(cliente == null){
                    throw new RuntimeException("Cliente con dni " + dni + " no encontrado");
                }

                clientesDao.borrar(cliente);

            } catch (Exception e) {
                throw new RuntimeException("Error interno al eliminar cliente por dni",e);
            }
        });

        logger.info("Eliminando cliente: {}", dni);
    }
}
