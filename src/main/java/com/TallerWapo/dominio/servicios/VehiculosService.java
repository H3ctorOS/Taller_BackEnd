package com.TallerWapo.dominio.servicios;

import com.TallerWapo.dominio.BOs.vehiculos.VehiculoBO;
import com.TallerWapo.dominio.contexto.Contexto;
import com.TallerWapo.dominio.factorias.ContextoDaos;
import com.TallerWapo.dominio.interfaces.Daos.VehiculosDao;


public class VehiculosService {

    private static final VehiculosDao vehiculoDao = ContextoDaos.getVehiculoDao(Contexto.SESION_SOLO_LECTURA);


    public static void validarVehiculo(VehiculoBO vehiculo){

        //La matricula tiene que venir informada
        if (vehiculo.getMatricula() == null || "".equals (vehiculo.getMatricula()) ) {
            throw new IllegalArgumentException("Debes informar la matricula");
        }

        //No pueden haber matriculas repetidas
        VehiculoBO vehiculoGemelo;
        try {
            vehiculoGemelo = vehiculoDao.buscarPorMatricula(vehiculo.getMatricula());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (vehiculoGemelo != null) {
            throw new IllegalArgumentException("Ya existe otro vehiculo con la misma matricula");
        }
    }
}
