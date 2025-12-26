package com.TallerWapo.dominio.interfaces.Daos;

import com.TallerWapo.dominio.BOs.Clientes.ClienteBO;

import java.util.List;

public interface ClientesDao {

    List<ClienteBO> buscarTodos() throws Exception;
    ClienteBO buscarPorDni(String matricula) throws Exception;
    ClienteBO buscarPorId(int id) throws Exception;
    boolean guardarNuevo(ClienteBO vehiculo) throws Exception;
    boolean actualizar(ClienteBO vehiculo) throws Exception;
    boolean borrar(ClienteBO vehiculo) throws Exception;
}
