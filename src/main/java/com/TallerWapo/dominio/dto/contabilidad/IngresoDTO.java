package com.TallerWapo.dominio.dto.contabilidad;

import com.TallerWapo.dominio.bo.contabilidad.IngresoBO;
import com.TallerWapo.dominio.interfaces.base.DTOBase;


public class IngresoDTO implements DTOBase {

    int uuid;
    String concepto;
    double importe;
    long fecha;
    String codEstado;
    String observaciones;


    // Constructor a partir de IngresoBO
    public IngresoDTO(IngresoBO ingresoBO) {
        this.uuid = ingresoBO.getUuid();
        this.concepto = ingresoBO.getConcepto();
        this.importe = ingresoBO.getImporte();
        this.fecha = ingresoBO.getFecha() != null
                ? ingresoBO.getFecha().getTime()
                : 0L;
        this.codEstado = ingresoBO.getCodEstado();
        this.observaciones = ingresoBO.getObservaciones();
    }

    // Getters y Setters
    public int getUuid() {
        return uuid;
    }

    public void setUuid(int uuid) {
        this.uuid = uuid;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public long getFecha() {
        return fecha;
    }

    public void setFecha(long fecha) {
        this.fecha = fecha;
    }

    public String getCodEstado() {
        return codEstado;
    }

    public void setCodEstado(String codEstado) {
        this.codEstado = codEstado;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}
