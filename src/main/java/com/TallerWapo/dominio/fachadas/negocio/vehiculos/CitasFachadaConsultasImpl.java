package com.TallerWapo.dominio.fachadas.negocio.vehiculos;


import com.TallerWapo.dominio.BOs.Clientes.ClienteBO;
import com.TallerWapo.dominio.BOs.vehiculos.CitaBO;
import com.TallerWapo.dominio.BOs.vehiculos.VehiculoBO;
import com.TallerWapo.dominio.fachadas.base.FachadaConsultaBase;
import com.TallerWapo.dominio.factorias.FactoriaDaos;
import com.TallerWapo.dominio.interfaces.Daos.CitasDao;

import java.util.List;

public class CitasFachadaConsultasImpl extends FachadaConsultaBase {
    private final CitasDao citasDao = FactoriaDaos.getCitaDao(SESION);

    public List<CitaBO> buscarPorVehiculo(VehiculoBO vehiculo) {
        try {
            return citasDao.buscarPorVehiculo(vehiculo);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<CitaBO> buscarPorCliente(ClienteBO cliente) {
        try {
            return citasDao.buscarPorCliente(cliente);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<CitaBO> buscarTodas() {
        try {
            return citasDao.buscarTodas();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
