package com.TallerWapo.dominio.fachadas.negocio.vehiculos;

import com.TallerWapo.dominio.BOs.vehiculos.ReparacionBO;
import com.TallerWapo.dominio.BOs.vehiculos.VehiculoBO;
import com.TallerWapo.dominio.fachadas.base.FachadaEjecutarBase;
import com.TallerWapo.dominio.factorias.FactoriaDaos;
import com.TallerWapo.dominio.interfaces.Daos.ReparacionesDao;
import com.TallerWapo.dominio.interfaces.Daos.VehiculosDao;
import com.TallerWapo.dominio.servicios.ReparacionesService;
import com.TallerWapo.dominio.servicios.VehiculosService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ReparacionesFachadaEjecutarImpl extends FachadaEjecutarBase {
    static final Logger logger = LoggerFactory.getLogger(ReparacionesFachadaEjecutarImpl.class);

    public void guardarNueva(ReparacionBO reparacion) throws Exception {
        logger.info("Guardando nueva reparacion: {}", reparacion.toString());

        ejecutarEnTransaccion(sesion ->{
            try {
                ReparacionesService.validarReparacion(sesion,reparacion);

                ReparacionesDao reparacionesDao = FactoriaDaos.getReparacionesDao(sesion);
                reparacion.setCodigoEstado("ACTI");
                reparacionesDao.guardarNueva(reparacion);

            }catch (Exception e) {
                throw new RuntimeException("Error interno al guardar la nueva reparacion",e);
            }
        });

        logger.info("Reparacion creada");
    }


    public void actualizarVehiculo(VehiculoBO vehiculo) {
        logger.info("Actualizando vehiculo: {}", vehiculo.toString());

        VehiculosService.validarVehiculo(vehiculo);

        ejecutarEnTransaccion(sesion ->{
            try {
                VehiculosDao vehiculoDao = FactoriaDaos.getVehiculoDao(sesion);
                vehiculoDao.actualizar(vehiculo);

            } catch (Exception e) {
                throw new RuntimeException("Error interno al actualizar vehiculo",e);
            }
        });

        logger.info("Vehiculo actualizado");
    }

    public void eliminarVehiculo(String matricula) {
        logger.info("Eliminando vehiculo: {}", matricula);

        ejecutarEnTransaccion(sesion ->{
            try {
                VehiculosDao vehiculoDao = FactoriaDaos.getVehiculoDao(sesion);
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

        ejecutarEnTransaccion(sesion ->{
            try {
                VehiculosDao vehiculoDao = FactoriaDaos.getVehiculoDao(sesion);
                vehiculoDao.borrar(vehiculo);

            } catch (Exception e) {
                throw new RuntimeException("Error interno al eliminar vehiculo",e);
            }
        });

        logger.info("Vehiculo eliminado");
    }
}
