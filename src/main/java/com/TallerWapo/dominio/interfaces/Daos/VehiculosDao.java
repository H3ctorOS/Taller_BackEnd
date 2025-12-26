package com.TallerWapo.dominio.interfaces.Daos;

import com.TallerWapo.dominio.BOs.vehiculos.VehiculoBO;

import java.util.List;

public interface VehiculosDao {
    List<VehiculoBO> buscarTodos() throws Exception;
    VehiculoBO buscarPorMatricula(String matricula) throws Exception;
    boolean guardarNuevo(VehiculoBO vehiculo) throws Exception;
    boolean actualizar(VehiculoBO vehiculo) throws Exception;
    boolean borrar(VehiculoBO vehiculo) throws Exception;
}
