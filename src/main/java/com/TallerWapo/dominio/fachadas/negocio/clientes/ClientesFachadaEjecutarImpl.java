package com.TallerWapo.dominio.fachadas.negocio.clientes;

import com.TallerWapo.dominio.BOs.Clientes.ClienteBO;
import com.TallerWapo.dominio.contexto.ContextoDaos;
import com.TallerWapo.dominio.fachadas.base.FachadaEjecutarBase;
import com.TallerWapo.dominio.interfaces.Daos.ClientesDao;
import com.TallerWapo.dominio.servicios.ClientesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ClientesFachadaEjecutarImpl extends FachadaEjecutarBase {
    static final Logger logger = LoggerFactory.getLogger(ClientesFachadaEjecutarImpl.class);

    private final ClientesDao clientesDao = ContextoDaos.getClienteDao();

    public void crearNuevoCliente(ClienteBO cliente) throws Exception {
        ClientesService.validarCliente(cliente);

        logger.info("Creando nuevo cliente: {}", cliente.toString());

        ejecutarEnTransaccion(() -> {
            try {
                cliente.setEstado("ACTI");
                clientesDao.guardarNuevo(cliente);

            } catch (Exception e) {
                throw new RuntimeException("Error interno al crear cliente",e);
            }
        });

        logger.info("Cliente creado");
    }


    public void actualizarCliente(ClienteBO cliente) throws Exception {
        ClientesService.validarCliente(cliente);

        ejecutarEnTransaccion(() -> {
            try {
                clientesDao.actualizar(cliente);

            } catch (Exception e) {
                throw new RuntimeException("Error interno al actualizar vehiculo",e);
            }
        });
    }

    public void eliminarCliente(String dni) {

        ejecutarEnTransaccion(() -> {
            ClienteBO cliente;
            try {
                 cliente = clientesDao.buscarPorDni(dni);

                if(cliente == null){
                    throw new RuntimeException("Cliente con dni " + dni + " no encontrado");
                }

                clientesDao.borrar(cliente);

            } catch (Exception e) {
                throw new RuntimeException("Error interno al eliminar cliente por dni",e);
            }
        });
    }

    public void eliminarCliente(ClienteBO cliente) {
        ejecutarEnTransaccion(() -> {
            try {
                clientesDao.borrar(cliente);

            } catch (Exception e) {
                throw new RuntimeException("Error interno al eliminar cliente",e);
            }
        });
    }
}
