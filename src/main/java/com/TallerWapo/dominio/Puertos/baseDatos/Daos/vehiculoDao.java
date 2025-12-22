package com.TallerWapo.dominio.Puertos.baseDatos.Daos;

import com.TallerWapo.dominio.BOs.vehiculos.VehiculoBO;
import com.TallerWapo.dominio.interfacez.base.DaoBase;

import java.util.List;

public interface vehiculoDao extends DaoBase {
    List<VehiculoBO> findAll() throws Exception;

    VehiculoBO findByMatricula(String matricula) throws Exception;

    void save(VehiculoBO vehiculo) throws Exception;

    void update(VehiculoBO vehiculo) throws Exception;

    void deleteByMatricula(String matricula) throws Exception;
}
