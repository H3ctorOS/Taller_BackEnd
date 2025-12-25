package com.TallerWapo.dominio.fachadas.negocio.clientes;


import com.TallerWapo.dominio.BOs.Clientes.ClienteBO;
import com.TallerWapo.dominio.contexto.ContextoDaos;
import com.TallerWapo.dominio.fachadas.base.FachadaConsultaBase;
import com.TallerWapo.dominio.interfaces.Daos.vehiculosDao;

import java.util.List;

public class ClientesFachadaConsultasImpl extends FachadaConsultaBase {
    private final vehiculosDao vehiculoDao = ContextoDaos.getVehiculoDao();

    public ClienteBO buscarClienteDni(String dni) {
        return ejecutarConsulta(() -> {
            try {
                return null;

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    public List<ClienteBO> buscarTodosLosClientes() {
        return ejecutarConsulta(() -> {
            try {
                return null;

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }
}
