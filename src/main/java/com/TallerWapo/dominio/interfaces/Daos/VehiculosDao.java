package com.TallerWapo.dominio.interfaces.Daos;

import com.TallerWapo.dominio.BOs.Clientes.ClienteBO;
import com.TallerWapo.dominio.BOs.vehiculos.VehiculoBO;

import java.util.List;

public interface VehiculosDao {
    List<VehiculoBO> buscarTodos() throws Exception;

    List<VehiculoBO> buscarPorCliente(ClienteBO cliente) throws Exception;
    VehiculoBO buscarPorMatricula(String matricula) throws Exception;
    VehiculoBO buscarPorId(int id) throws Exception;
    boolean guardarNuevo(VehiculoBO vehiculo) throws Exception;
    boolean actualizar(VehiculoBO vehiculo) throws Exception;
    boolean borrar(VehiculoBO vehiculo) throws Exception;

    boolean altaPropietario(VehiculoBO vehiculo, ClienteBO cliente,String observaciones) throws Exception;
}
