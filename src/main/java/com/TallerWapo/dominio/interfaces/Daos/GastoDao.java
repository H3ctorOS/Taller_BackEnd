package com.TallerWapo.dominio.interfaces.Daos;

import com.TallerWapo.dominio.bo.contabilidad.IngresoBO;
import com.TallerWapo.dominio.bo.vehiculos.CitaBO;
import com.TallerWapo.dominio.bo.contabilidad.GastoBO;

import java.util.List;

public interface GastoDao {
    List<GastoBO> buscarTodos() throws Exception;
    List<GastoBO> buscarPorCita(CitaBO cita) throws Exception;
    boolean guardarNuevo(GastoBO gasto) throws Exception;
    boolean guardarRelacionCita(GastoBO gasto, CitaBO cita) throws Exception;
    boolean actualizar(GastoBO gasto) throws Exception;
    boolean borrar(GastoBO gasto) throws Exception;
}
