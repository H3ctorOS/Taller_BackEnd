package com.TallerWapo.fachadas.interfaces.Vehiculos;

import com.TallerWapo.dominio.interfacez.FachadasBase;

public interface VehiculosFachadaAcciones extends FachadasBase {
    void crearNuevoVehiculo();
    void actualizarVehiculo();
    void borrarVehiculo();
}
