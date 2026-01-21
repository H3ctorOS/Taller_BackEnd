package com.TallerWapo.dominio.servicios;

import com.TallerWapo.dominio.BOs.vehiculos.ReparacionBO;
import com.TallerWapo.dominio.BOs.vehiculos.VehiculoBO;
import com.TallerWapo.dominio.contexto.Sesion;
import com.TallerWapo.dominio.factorias.ContextoDaos;
import com.TallerWapo.dominio.interfaces.Daos.VehiculosDao;

public class ReparacionesService {
    public static void validarReparacion(Sesion sesion, ReparacionBO reparacion) throws Exception {
        VehiculosDao vehiculosDao = ContextoDaos.getVehiculoDao(sesion);

        //
        if(reparacion.getConcepto() == null || reparacion.getConcepto().isEmpty()){
            throw  new IllegalArgumentException("La reparacion debe de tener alguna concepto que defina lo que se hace");
        }

        //Validar vehiculo
        VehiculoBO vehiculo = vehiculosDao.buscarPorId(reparacion.getVehiculoUuid());
        if(vehiculo == null){
            throw  new IllegalArgumentException("El vehiculo no existe");
        }

    }
}
