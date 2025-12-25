package com.TallerWapo.dominio.fachadas.negocio.vehiculos;

import com.TallerWapo.dominio.BOs.vehiculos.VehiculoBO;
import com.TallerWapo.dominio.interfaces.Daos.vehiculosDao;
import com.TallerWapo.dominio.contexto.ContextoDaos;
import com.TallerWapo.dominio.fachadas.base.FachadaEjecutarBase;
import com.TallerWapo.dominio.servicios.VehiculosService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class VehiculosFachadaEjecutarImpl extends FachadaEjecutarBase {
    static final Logger logger = LoggerFactory.getLogger(VehiculosFachadaEjecutarImpl.class);

    private final vehiculosDao vehiculoDao = ContextoDaos.getVehiculoDao();

    public void crearNuevoVehiculo(VehiculoBO vehiculo) throws Exception {
        VehiculosService.validarVehiculo(vehiculo);

        logger.info("Creando nuevo vehiculo: {}", vehiculo.toString());

        ejecutarEnTransaccion(() -> {
            try {
                vehiculo.setCodidoEstado("ACTI");
                vehiculoDao.guardarNuevo(vehiculo);

            } catch (Exception e) {
                throw new RuntimeException("Error interno al crear vehiculo",e);
            }
        });

        logger.info("Vehiculo creado");
    }


    public void actualizarVehiculo(VehiculoBO vehiculo) {
        VehiculosService.validarVehiculo(vehiculo);

        ejecutarEnTransaccion(() -> {
            try {
                vehiculoDao.actualizar(vehiculo);

            } catch (Exception e) {
                throw new RuntimeException("Error interno al actualizar vehiculo",e);
            }
        });
    }

    public void eliminarVehiculo(String matricula) {

        ejecutarEnTransaccion(() -> {
            try {
                VehiculoBO vehiculo = vehiculoDao.buscarPorMatricula(matricula);

                if(vehiculo == null){
                    throw new RuntimeException("Vehiculo con matricula " + matricula + " no encontrado");
                }

                vehiculoDao.borrar(vehiculo);

            } catch (Exception e) {
                throw new RuntimeException("Error interno al eliminar vehiculo",e);
            }
        });
    }

    public void eliminarVehiculo(VehiculoBO vehiculo) {
        ejecutarEnTransaccion(() -> {
            try {
                vehiculoDao.borrar(vehiculo);

            } catch (Exception e) {
                throw new RuntimeException("Error interno al eliminar vehiculo",e);
            }
        });
    }
}
