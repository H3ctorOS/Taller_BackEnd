package com.TallerWapo.dominio.interfaces.Daos;

import com.TallerWapo.dominio.bo.vehiculos.CitaBO;
import com.TallerWapo.dominio.bo.vehiculos.VehiculoBO;

import java.util.List;

public interface CitasDao {
    List<CitaBO> buscarTodas() throws Exception;
    List<CitaBO> buscarPorVehiculo(VehiculoBO vehiculo) throws Exception;
    boolean guardarNueva(CitaBO cita) throws Exception;
    boolean actualizar(CitaBO cita) throws Exception;
    boolean borrar(CitaBO cita) throws Exception;
    List<CitaBO> buscarAcivasDia(Long dia) throws Exception;
}
