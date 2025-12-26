package com.TallerWapo.dominio.servicios;

import com.TallerWapo.dominio.BOs.Clientes.ClienteBO;
import com.TallerWapo.dominio.BOs.vehiculos.CitaBO;
import com.TallerWapo.dominio.BOs.vehiculos.VehiculoBO;
import com.TallerWapo.dominio.contexto.Sesion;
import com.TallerWapo.dominio.factorias.FactoriaDaos;
import com.TallerWapo.dominio.interfaces.Daos.ClientesDao;
import com.TallerWapo.dominio.interfaces.Daos.VehiculosDao;

public class CitasService {

    public static void validarCita(Sesion sesion, CitaBO cita) throws Exception {
        ClientesDao clientesDao = FactoriaDaos.getClienteDao(sesion);
        VehiculosDao vehiculosDao = FactoriaDaos.getVehiculoDao(sesion);

        //el usuario debe de venir informado
        if(cita == null){
            throw  new IllegalArgumentException("El cita no puede ser nulo.");
        }

        if(cita.getFecha() == null){
            throw  new IllegalArgumentException("El fecha debe de venir informada");
        }

        //Validar cliente
        ClienteBO cliente = clientesDao.buscarPorId(cita.getClienteUuid());
        if(cliente == null){
            throw  new IllegalArgumentException("El cliente de esta cita no existe");
        }

        //Validar vehiculo
        VehiculoBO vehiculo = vehiculosDao.buscarPorId(cita.getVehiculoUuid());
        if(vehiculo == null){
            throw  new IllegalArgumentException("El vehiculo no existe");
        }
    }
}
