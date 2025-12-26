package com.TallerWapo.dominio.fachadas.negocio.clientes;


import com.TallerWapo.dominio.BOs.Clientes.ClienteBO;
import com.TallerWapo.dominio.contexto.ContextoDaos;
import com.TallerWapo.dominio.fachadas.base.FachadaConsultaBase;
import com.TallerWapo.dominio.interfaces.Daos.clientesDao;

import java.util.List;

public class ClientesFachadaConsultasImpl extends FachadaConsultaBase {
    private final clientesDao clientesDao = ContextoDaos.getClienteDao();

    public ClienteBO buscarClienteDni(String dni) {
        return ejecutarConsulta(() -> {
            try {
                return clientesDao.buscarPorDni(dni);

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    public List<ClienteBO> buscarTodosLosClientes() {
        return ejecutarConsulta(() -> {
            try {
                return clientesDao.buscarTodos();

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }
}
