package com.TallerWapo.dominio.servicios;

import com.TallerWapo.dominio.BOs.Clientes.ClienteBO;
import com.TallerWapo.dominio.BOs.vehiculos.VehiculoBO;
import com.TallerWapo.dominio.contexto.Contexto;
import com.TallerWapo.dominio.factorias.ContextoDaos;
import com.TallerWapo.dominio.interfaces.Daos.ClientesDao;
import com.TallerWapo.dominio.interfaces.Daos.VehiculosDao;


public class VehiculosService {

    private static final VehiculosDao vehiculoConsultaDao = ContextoDaos.getVehiculoDao(Contexto.SESION_SOLO_LECTURA);
    private static final ClientesDao clientesConsultaDao = ContextoDaos.getClienteDao(Contexto.SESION_SOLO_LECTURA);

    private final VehiculosDao vehiculoDao;


    public VehiculosService(VehiculosDao vehiculoDao) {
        this.vehiculoDao = vehiculoDao;
    }


    public static void validarVehiculo(VehiculoBO vehiculo){

        //La matricula tiene que venir informada
        if (vehiculo.getMatricula() == null || "".equals (vehiculo.getMatricula()) ) {
            throw new IllegalArgumentException("Debes informar la matricula");
        }

        //No pueden haber matriculas repetidas
        VehiculoBO vehiculoGemelo;
        try {
            vehiculoGemelo = vehiculoConsultaDao.buscarPorMatricula(vehiculo.getMatricula());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (vehiculoGemelo != null) {
            throw new IllegalArgumentException("Ya existe otro vehiculo con la misma matricula");
        }


        //Debe de venir informado un propietario valido
        if (vehiculo.getUuidPropietario() == 0) {
            throw new IllegalArgumentException("Se debe informar un propietario");

        }else{
            try {
                ClienteBO propietario = clientesConsultaDao.buscarPorId(vehiculo.getUuidPropietario());
                if (propietario == null) {
                    throw new IllegalArgumentException("El propietario no existe en la base de datos");
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }

    }

    public void darAltaPropietario(VehiculoBO vehiculo) throws Exception {
        //Buscar el cliente
        ClienteBO cliente = clientesConsultaDao.buscarPorId(vehiculo.getUuidPropietario());

        //Si el cliente es nulo tirar error
        if (cliente == null) {
            throw new IllegalArgumentException("El propietario no existe en la base de datos");
        }

        vehiculoDao.altaPropietario(vehiculo, cliente);

    }

}
