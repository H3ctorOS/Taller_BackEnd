package com.TallerWapo.dominio.dto.contabilidad;

import com.TallerWapo.dominio.dto.calendario.CitaDTO;
import com.TallerWapo.dominio.interfaces.base.DTOBase;

public class GastoConCitaDTO implements DTOBase {

    private GastoDTO gasto;
    private CitaDTO cita;

    public GastoConCitaDTO() {}

    public GastoConCitaDTO(CitaDTO cita, GastoDTO gasto) {
        this.cita = cita;
        this.gasto = gasto;
    }

    public GastoDTO getGasto() {
        return gasto;
    }

    public void setGasto(GastoDTO gasto) {
        this.gasto = gasto;
    }

    public CitaDTO getCita() {
        return cita;
    }

    public void setCita(CitaDTO cita) {
        this.cita = cita;
    }

}
