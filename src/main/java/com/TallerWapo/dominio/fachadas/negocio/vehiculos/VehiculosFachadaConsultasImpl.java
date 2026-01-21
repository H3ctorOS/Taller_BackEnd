package com.TallerWapo.dominio.fachadas.negocio.vehiculos;


import com.TallerWapo.dominio.BOs.vehiculos.VehiculoBO;
import com.TallerWapo.dominio.interfaces.Daos.VehiculosDao;
import com.TallerWapo.dominio.factorias.ContextoDaos;
import com.TallerWapo.dominio.fachadas.base.FachadaConsultaBase;
import com.TallerWapo.dominio.interfaces.puertos.ApiRest.controladores.VehiculosControlador;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class VehiculosFachadaConsultasImpl extends FachadaConsultaBase {
    private final VehiculosDao vehiculoDao = ContextoDaos.getVehiculoDao(SESION);

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
