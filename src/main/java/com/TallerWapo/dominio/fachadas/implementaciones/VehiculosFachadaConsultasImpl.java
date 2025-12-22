package com.TallerWapo.dominio.fachadas.implementaciones;


import com.TallerWapo.dominio.BOs.vehiculos.VehiculoBO;
import com.TallerWapo.dominio.fachadas.implementaciones.base.FachadaBaseImpl;

public class VehiculosFachadaConsultasImpl extends FachadaBaseImpl {


    public VehiculoBO buscarVehiculoMatricula(String matricula) {
        VehiculoBO  vehiculo = new VehiculoBO();
        vehiculo.setMarca("opel");
        vehiculo.setModelo("opel");
        vehiculo.setMatricula("43545");
        vehiculo.setUuid(324);

        return vehiculo;
    }
}
