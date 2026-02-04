package com.TallerWapo.dominio.fachadas.negocio.clientes;


import com.TallerWapo.dominio.bo.Clientes.ClienteBO;
import com.TallerWapo.dominio.factorias.ContextoDaos;
import com.TallerWapo.dominio.fachadas.base.FachadaConsultaBase;
import com.TallerWapo.dominio.interfaces.Daos.ClientesDao;

import java.util.List;

public class ClientesFachadaConsultasImpl extends FachadaConsultaBase {
    private final ClientesDao clientesDao = ContextoDaos.getClienteDao(SESION);

    public ClienteBO buscarClienteDni(String dni) {
        try {
            return clientesDao.buscarPorDni(dni);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public List<ClienteBO> buscarTodosLosClientes() {
        try {
            return clientesDao.buscarTodos();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
