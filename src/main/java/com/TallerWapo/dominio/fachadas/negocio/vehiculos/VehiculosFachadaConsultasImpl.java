package com.TallerWapo.dominio.fachadas.negocio.vehiculos;


import com.TallerWapo.dominio.BOs.vehiculos.VehiculoBO;
import com.TallerWapo.dominio.interfaces.Daos.VehiculosDao;
import com.TallerWapo.dominio.contexto.ContextoDaos;
import com.TallerWapo.dominio.fachadas.base.FachadaConsultaBase;

import java.util.List;

public class VehiculosFachadaConsultasImpl extends FachadaConsultaBase {
    private final VehiculosDao vehiculoDao = ContextoDaos.getVehiculoDao();

    public VehiculoBO buscarVehiculo(String matricula) {
        return ejecutarConsulta(() -> {
            try {
                return vehiculoDao.buscarPorMatricula(matricula);

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

    }

    public List<VehiculoBO> buscarTodosLosVehiculos() {
        return ejecutarConsulta(() -> {
            try {
                return vehiculoDao.buscarTodos();

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }
}
