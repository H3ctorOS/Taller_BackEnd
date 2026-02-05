package com.TallerWapo.dominio.interfaces.Daos;

import com.TallerWapo.dominio.bo.vehiculos.CitaBO;
import com.TallerWapo.dominio.bo.vehiculos.IngresoBO;

import java.util.List;

public interface IngresosDao {
    List<IngresoBO> buscarTodos() throws Exception;
    List<IngresoBO> buscarPorCita(CitaBO cita) throws Exception;
    boolean guardarNuevo(IngresoBO ingreso) throws Exception;
    boolean actualizar(IngresoBO ingreso) throws Exception;
    boolean borrar(IngresoBO ingreso) throws Exception;
}
