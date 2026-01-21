package com.TallerWapo.dominio.fachadas.negocio.vehiculos;



import com.TallerWapo.dominio.BOs.vehiculos.ReparacionBO;
import com.TallerWapo.dominio.BOs.vehiculos.VehiculoBO;
import com.TallerWapo.dominio.fachadas.base.FachadaConsultaBase;
import com.TallerWapo.dominio.factorias.ContextoDaos;
import com.TallerWapo.dominio.interfaces.Daos.ReparacionesDao;

import java.util.List;

public class ReparacionesFachadaConsultasImpl extends FachadaConsultaBase {
    private final ReparacionesDao reparacionesDao = ContextoDaos.getReparacionesDao(SESION);

    public List<ReparacionBO> buscarPorVehiculo(VehiculoBO vehiculo) {
        try {
            return reparacionesDao.buscarPorVehiculo(vehiculo);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<ReparacionBO> buscarTodas() {
        try {
            return reparacionesDao.buscarTodas();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
