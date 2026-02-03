package com.TallerWapo.dominio.servicios;

import com.TallerWapo.dominio.BOs.vehiculos.CitaBO;
import com.TallerWapo.dominio.BOs.vehiculos.VehiculoBO;
import com.TallerWapo.dominio.contexto.Sesion;
import com.TallerWapo.dominio.factorias.ContextoDaos;
import com.TallerWapo.dominio.interfaces.Daos.VehiculosDao;

public class CitasService {

    public static void validarCita(Sesion sesion, CitaBO cita) throws Exception {
        VehiculosDao vehiculosDao = ContextoDaos.getVehiculoDao(sesion);

        //la cita no puede ser nulo
        if(cita == null){
            throw  new IllegalArgumentException("El cita no puede ser nulo.");
        }

        //la cita debe tener informada la fecha inicio
        if(cita.getFechaInicio() == null){
            throw  new IllegalArgumentException("El fecha debe de venir informada");
        }


        //Validar vehiculo
        VehiculoBO vehiculo = vehiculosDao.buscarPorId(cita.getVehiculoUuid());
        if(vehiculo == null){
            throw  new IllegalArgumentException("El vehiculo no existe");
        }
    }
}
