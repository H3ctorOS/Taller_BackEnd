package com.TallerWapo.dominio.fachadas.negocio.vehiculos;


import com.TallerWapo.dominio.bo.Clientes.ClienteBO;
import com.TallerWapo.dominio.bo.vehiculos.VehiculoBO;
import com.TallerWapo.dominio.interfaces.Daos.ClientesDao;
import com.TallerWapo.dominio.interfaces.Daos.VehiculosDao;
import com.TallerWapo.dominio.factorias.ContextoDaos;
import com.TallerWapo.dominio.fachadas.base.FachadaConsultaBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class VehiculosFachadaConsultasImpl extends FachadaConsultaBase {
    private final VehiculosDao vehiculoDao = ContextoDaos.getVehiculoDao(SESION);
    private final ClientesDao clientesDao = ContextoDaos.getClienteDao(SESION);

    static final Logger logger = LoggerFactory.getLogger(VehiculosFachadaConsultasImpl.class);
    public VehiculoBO buscarVehiculo(String matricula) {
        logger.info("Buscando vehiculo por matricula: {}", matricula);

        VehiculoBO vehiculo;
        try {
            vehiculo = vehiculoDao.buscarPorMatricula(matricula);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        logger.info("Vehiculo encontrado: {}", vehiculo.toString() );
        return vehiculo;
    }

    public List<VehiculoBO> buscarVehiculosPorCliente(int clienteUuid) {
        logger.info("Buscando  todos los vehiculos del cliente : {}", clienteUuid);

        List<VehiculoBO> listaVehiculos;

        try {
            ClienteBO cliente = clientesDao.buscarPorId(clienteUuid);

            listaVehiculos = vehiculoDao.buscarPorCliente(cliente);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        logger.info("Encontrados {} vehiculos", listaVehiculos.size());

        return listaVehiculos;
    }


    public List<VehiculoBO> buscarTodosLosVehiculos() {
        logger.info("Buscando  todos los vehiculos");

        List<VehiculoBO> listaVehiculos;

        try {
            listaVehiculos = vehiculoDao.buscarTodos();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        logger.info("Encontrados {} vehiculos", listaVehiculos.size());
        return listaVehiculos;
    }
}
