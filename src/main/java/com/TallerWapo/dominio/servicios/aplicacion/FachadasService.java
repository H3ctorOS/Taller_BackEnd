package com.TallerWapo.dominio.servicios.aplicacion;

import com.TallerWapo.dominio.contexto.FachadasContexto;
import com.TallerWapo.fachadas.implementaciones.VehiculosFAchadaAccionesImpl;

public class FachadasService {

    public static void arrancarFachadas() {
        //Vehiculos
        VehiculosFAchadaAccionesImpl vehiculosFAchadaAcciones = new VehiculosFAchadaAccionesImpl();
        FachadasContexto.vehiculosFachadaAcciones = vehiculosFAchadaAcciones;


    }
}
