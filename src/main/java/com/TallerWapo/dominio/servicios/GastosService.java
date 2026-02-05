package com.TallerWapo.dominio.servicios;

import com.TallerWapo.dominio.bo.vehiculos.GastoBO;
import com.TallerWapo.dominio.contexto.Sesion;
import com.TallerWapo.dominio.dto.GastoDTO;

import java.util.ArrayList;
import java.util.List;

public class GastosService {

    public static void validarGasto(Sesion sesion, GastoBO gasto) throws Exception {

        // El gasto no puede ser nulo
        if (gasto == null) {
            throw new IllegalArgumentException("El gasto no puede ser nulo.");
        }

        // El gasto debe tener importe informado y positivo
        if (gasto.getImporte() <= 0) {
            throw new IllegalArgumentException("El importe del gasto debe ser mayor que cero.");
        }

        // El gasto debe tener fecha informada
        if (gasto.getFecha() == null) {
            throw new IllegalArgumentException("La fecha del gasto debe venir informada.");
        }

        // Se podrían añadir más validaciones en el futuro (por ejemplo existencia de la entidad asociada)
    }

    public static List<GastoDTO> toDTOList(List<GastoBO> gastosBO) {
        List<GastoDTO> resultado = new ArrayList<>();

        if (gastosBO == null) {
            return resultado;
        }

        for (GastoBO gastoBO : gastosBO) {
            resultado.add(new GastoDTO(gastoBO));
        }

        return resultado;
    }
}
