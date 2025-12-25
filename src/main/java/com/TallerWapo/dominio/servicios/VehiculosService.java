package com.TallerWapo.dominio.servicios;

import com.TallerWapo.dominio.BOs.vehiculos.VehiculoBO;

public class VehiculosService {

    public static boolean validarVehiculo(VehiculoBO vehiculo){

        if (vehiculo.getMatricula() == null) {
            throw new IllegalArgumentException("Matrícula requerida");
        }

        return true;
    }


}
