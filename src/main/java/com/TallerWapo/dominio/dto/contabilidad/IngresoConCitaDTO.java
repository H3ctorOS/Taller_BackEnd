package com.TallerWapo.dominio.dto.contabilidad;

import com.TallerWapo.dominio.dto.CitaDTO;
import com.TallerWapo.dominio.interfaces.base.DTOBase;

public class IngresoConCitaDTO implements DTOBase {
    IngresoDTO ingreso;
    CitaDTO cita;

    public IngresoConCitaDTO() {}

    public IngresoConCitaDTO(CitaDTO cita, IngresoDTO ingreso) {
        this.cita = cita;
        this.ingreso = ingreso;
    }

    public  IngresoDTO getIngreso() {
        return ingreso;
    }

    public void setIngreso(IngresoDTO ingreso) {
        this.ingreso = ingreso;
    }

    public CitaDTO getCita() {
        return cita;
    }

    public void setCita(CitaDTO cita) {
        this.cita = cita;
    }

}
