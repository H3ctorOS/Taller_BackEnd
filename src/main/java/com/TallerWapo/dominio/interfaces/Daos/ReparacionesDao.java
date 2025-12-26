package com.TallerWapo.dominio.interfaces.Daos;

import com.TallerWapo.dominio.BOs.vehiculos.ReparacionBO;
import com.TallerWapo.dominio.BOs.vehiculos.VehiculoBO;

import java.util.List;

public interface ReparacionesDao {
    List<ReparacionBO> buscarTodas() throws Exception;
    List<ReparacionBO> buscarPorVehiculo(VehiculoBO vehiculo) throws Exception;
    boolean guardarNueva(ReparacionBO reparacion) throws Exception;
    boolean actualizar(ReparacionBO reparacion) throws Exception;
    boolean borrar(ReparacionBO reparacion) throws Exception;
}
