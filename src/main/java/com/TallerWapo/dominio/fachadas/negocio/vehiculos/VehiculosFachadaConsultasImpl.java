package com.TallerWapo.dominio.fachadas.negocio.vehiculos;


import com.TallerWapo.dominio.BOs.vehiculos.VehiculoBO;
import com.TallerWapo.dominio.interfaces.Daos.VehiculosDao;
import com.TallerWapo.dominio.contexto.FactoriaDaos;
import com.TallerWapo.dominio.fachadas.base.FachadaConsultaBase;

import java.util.List;

public class VehiculosFachadaConsultasImpl extends FachadaConsultaBase {
    private final VehiculosDao vehiculoDao = FactoriaDaos.getVehiculoDao(CONEXION);

    public VehiculoBO buscarVehiculo(String matricula) {
        try {
            return vehiculoDao.buscarPorMatricula(matricula);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<VehiculoBO> buscarTodosLosVehiculos() {
        try {
            return vehiculoDao.buscarTodos();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
