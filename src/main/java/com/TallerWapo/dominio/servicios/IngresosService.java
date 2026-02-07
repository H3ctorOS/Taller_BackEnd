package com.TallerWapo.dominio.servicios;

import com.TallerWapo.dominio.bo.contabilidad.IngresoBO;
import com.TallerWapo.dominio.contexto.Sesion;
import com.TallerWapo.dominio.dto.contabilidad.IngresoDTO;

import java.util.ArrayList;
import java.util.List;

public class IngresosService {

    /**
     * Validar un ingreso antes de guardarlo o actualizarlo
     */
    public static void validarIngreso(Sesion sesion, IngresoBO ingreso) throws Exception {

        // El ingreso no puede ser nulo
        if (ingreso == null) {
            throw new IllegalArgumentException("El ingreso no puede ser nulo.");
        }

        // El ingreso debe tener importe informado y positivo
        if (ingreso.getImporte() <= 0) {
            throw new IllegalArgumentException("El importe del ingreso debe ser mayor que cero.");
        }

        // El ingreso debe tener fecha informada
        if (ingreso.getFecha() == null) {
            throw new IllegalArgumentException("La fecha del ingreso debe venir informada.");
        }

        // El ingreso debe tener un estado
        if (ingreso.getCodEstado() == null || ingreso.getCodEstado().isEmpty()) {
            throw new IllegalArgumentException("El estado del ingreso debe venir informado.");
        }

        // Aquí se podrían añadir más validaciones si se asocia con otras entidades
    }

    /**
     * Convertir lista de BO a DTO
     */
    public static List<IngresoDTO> toDTOList(List<IngresoBO> ingresosBO) {
        List<IngresoDTO> resultado = new ArrayList<>();

        if (ingresosBO == null) {
            return resultado;
        }

        for (IngresoBO ingresoBO : ingresosBO) {
            resultado.add(new IngresoDTO(ingresoBO));
        }

        return resultado;
    }
}
