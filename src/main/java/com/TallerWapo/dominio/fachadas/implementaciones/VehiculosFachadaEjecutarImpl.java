package com.TallerWapo.dominio.fachadas.implementaciones;


import com.TallerWapo.dominio.BOs.vehiculos.VehiculoBO;
import com.TallerWapo.dominio.Puertos.baseDatos.Daos.vehiculoDao;
import com.TallerWapo.dominio.contexto.ContextoDaos;
import com.TallerWapo.dominio.fachadas.implementaciones.base.FachadaEjecutarBase;
import com.TallerWapo.dominio.servicios.negocio.VehiculosService;


public class VehiculosFachadaEjecutarImpl extends FachadaEjecutarBase {

    private final vehiculoDao  vehiculoDao = ContextoDaos.getVehiculoDao();

    public void crearNuevoVehiculo(VehiculoBO vehiculo) throws Exception {
        VehiculosService.validarVehiculo(vehiculo);

        ejecutarEnTransaccion(() -> {
            try {
                vehiculoDao.guardarNuevoVehiculo(vehiculo);

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }


    public void actualizarVehiculo(VehiculoBO vehiculo) {
        VehiculosService.validarVehiculo(vehiculo);

        ejecutarEnTransaccion(() -> {
            try {
                vehiculoDao.actualizarVehiculo(vehiculo);

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void eliminarVehiculo(String matricula) {

        ejecutarEnTransaccion(() -> {
            try {
                VehiculoBO vehiculo = vehiculoDao.findByMatricula(matricula);

                if(vehiculo == null){
                    throw new RuntimeException("Vehiculo con matricula " + matricula + " no encontrado");
                }

                vehiculoDao.borrarVehiculo(vehiculo);

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void eliminarVehiculo(VehiculoBO vehiculo) {
        ejecutarEnTransaccion(() -> {
            try {
                vehiculoDao.borrarVehiculo(vehiculo);

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }
}
