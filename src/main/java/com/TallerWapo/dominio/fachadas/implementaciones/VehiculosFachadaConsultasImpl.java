package com.TallerWapo.dominio.fachadas.implementaciones;


import com.TallerWapo.dominio.BOs.vehiculos.VehiculoBO;
import com.TallerWapo.dominio.Puertos.baseDatos.Daos.vehiculoDao;
import com.TallerWapo.dominio.contexto.ContextoDaos;
import com.TallerWapo.dominio.fachadas.implementaciones.base.FachadaConsultaBase;

public class VehiculosFachadaConsultasImpl extends FachadaConsultaBase {
    private final vehiculoDao  vehiculoDao = ContextoDaos.getVehiculoDao();

    public VehiculoBO buscarVehiculo(String matricula) {
        return ejecutarConsulta(() -> {
            try {
                return vehiculoDao.findByMatricula(matricula);

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

    }
}
