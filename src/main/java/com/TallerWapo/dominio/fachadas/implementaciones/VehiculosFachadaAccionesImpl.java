package com.TallerWapo.dominio.fachadas.implementaciones;


import com.TallerWapo.dominio.BOs.vehiculos.VehiculoBO;
import com.TallerWapo.dominio.fachadas.implementaciones.base.FachadaBaseImpl;

public class VehiculosFachadaAccionesImpl extends FachadaBaseImpl {

    public boolean crearNuevoVehiculo(VehiculoBO vehiculo) {
        return true;
    }

    public boolean actualizarVehiculo(VehiculoBO vehiculo) {
        return true;
    }

    public boolean eliminarVehiculo(String matricula) {
        return true;
    }
}
