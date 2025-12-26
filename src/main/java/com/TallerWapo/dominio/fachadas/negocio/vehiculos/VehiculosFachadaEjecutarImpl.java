package com.TallerWapo.dominio.fachadas.negocio.vehiculos;

import com.TallerWapo.dominio.BOs.vehiculos.VehiculoBO;
import com.TallerWapo.dominio.interfaces.Daos.VehiculosDao;
import com.TallerWapo.dominio.contexto.FactoriaDaos;
import com.TallerWapo.dominio.fachadas.base.FachadaEjecutarBase;
import com.TallerWapo.dominio.servicios.VehiculosService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class VehiculosFachadaEjecutarImpl extends FachadaEjecutarBase {
    static final Logger logger = LoggerFactory.getLogger(VehiculosFachadaEjecutarImpl.class);

    public void crearNuevoVehiculo(VehiculoBO vehiculo) throws Exception {
        logger.info("Creando nuevo vehiculo: {}", vehiculo.toString());

        VehiculosService.validarVehiculo(vehiculo);

        ejecutarEnTransaccion(conexion ->{
            try {
                VehiculosDao vehiculoDao = FactoriaDaos.getVehiculoDao(conexion);
                vehiculo.setCodidoEstado("ACTI");
                vehiculoDao.guardarNuevo(vehiculo);

            } catch (Exception e) {
                throw new RuntimeException("Error interno al crear vehiculo",e);
            }
        });

        logger.info("Vehiculo creado");
    }


    public void actualizarVehiculo(VehiculoBO vehiculo) {
        logger.info("Actualizando vehiculo: {}", vehiculo.toString());

        VehiculosService.validarVehiculo(vehiculo);

        ejecutarEnTransaccion(conexion ->{
            try {
                VehiculosDao vehiculoDao = FactoriaDaos.getVehiculoDao(conexion);
                vehiculoDao.actualizar(vehiculo);

            } catch (Exception e) {
                throw new RuntimeException("Error interno al actualizar vehiculo",e);
            }
        });

        logger.info("Vehiculo actualizado");
    }

    public void eliminarVehiculo(String matricula) {
        logger.info("Eliminando vehiculo: {}", matricula);

        ejecutarEnTransaccion(conexion ->{
            try {
                VehiculosDao vehiculoDao = FactoriaDaos.getVehiculoDao(conexion);
                VehiculoBO vehiculo = vehiculoDao.buscarPorMatricula(matricula);

                if(vehiculo == null){
                    throw new RuntimeException("Vehiculo con matricula " + matricula + " no encontrado");
                }

                vehiculoDao.borrar(vehiculo);

            } catch (Exception e) {
                throw new RuntimeException("Error interno al eliminar vehiculo",e);
            }
        });

        logger.info("Vehiculo eliminado");
    }

    public void eliminarVehiculo(VehiculoBO vehiculo) {
        logger.info("Eliminando vehiculo: {}", vehiculo.toString());

        ejecutarEnTransaccion(conexion ->{
            try {
                VehiculosDao vehiculoDao = FactoriaDaos.getVehiculoDao(conexion);
                vehiculoDao.borrar(vehiculo);

            } catch (Exception e) {
                throw new RuntimeException("Error interno al eliminar vehiculo",e);
            }
        });

        logger.info("Vehiculo eliminado");
    }
}
