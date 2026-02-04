package com.TallerWapo.dominio.interfaces.Daos;

import com.TallerWapo.dominio.bo.Clientes.ClienteBO;

import java.util.List;

public interface ClientesDao {

    List<ClienteBO> buscarTodos() throws Exception;
    ClienteBO buscarPorDni(String matricula) throws Exception;
    List<ClienteBO> buscarPorNombre(String nombre) throws Exception;
    ClienteBO buscarPorId(int id) throws Exception;
    boolean guardarNuevo(ClienteBO vehiculo) throws Exception;
    boolean actualizar(ClienteBO vehiculo) throws Exception;
    boolean borrar(ClienteBO vehiculo) throws Exception;
}
