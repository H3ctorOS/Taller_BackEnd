package com.TallerWapo.dominio.interfaces.Daos;

import com.TallerWapo.dominio.BOs.vehiculos.CitaBO;
import com.TallerWapo.dominio.BOs.vehiculos.VehiculoBO;

import java.util.List;

public interface CitasDao {
    List<CitaBO> buscarTodas() throws Exception;
    List<CitaBO> buscarPorVehiculo(VehiculoBO vehiculo) throws Exception;
    boolean guardarNueva(CitaBO cita) throws Exception;
    boolean actualizar(CitaBO cita) throws Exception;
    boolean borrar(CitaBO cita) throws Exception;
}
