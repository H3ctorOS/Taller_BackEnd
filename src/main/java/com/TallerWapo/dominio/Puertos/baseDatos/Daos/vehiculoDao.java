package com.TallerWapo.dominio.Puertos.baseDatos.Daos;

import com.TallerWapo.dominio.BOs.vehiculos.VehiculoBO;
import com.TallerWapo.dominio.interfacez.base.DaoBase;

import java.util.List;

public interface vehiculoDao extends DaoBase {
    List<VehiculoBO> findAll() throws Exception;

    VehiculoBO findByMatricula(String matricula) throws Exception;

    boolean guardarNuevoVehiculo(VehiculoBO vehiculo) throws Exception;

    boolean actualizarVehiculo(VehiculoBO vehiculo) throws Exception;

    boolean borrarVehiculo(VehiculoBO vehiculo) throws Exception;
}
