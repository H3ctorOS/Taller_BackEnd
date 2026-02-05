package com.TallerWapo.dominio.dto;

import com.TallerWapo.dominio.bo.vehiculos.GastoBO;

import java.util.Date;

public class GastoDTO {

    int uuid;
    String descripcion;
    double importe;
    long fecha;
    String observaciones;

    // Constructor a partir de GastoBO
    public GastoDTO(GastoBO gastoBO) {
        this.uuid = gastoBO.getUuid();
        this.descripcion = gastoBO.getDescripcion();
        this.importe = gastoBO.getImporte();
        this.fecha = gastoBO.getFecha() != null
                ? gastoBO.getFecha().getTime()
                : 0L;
        this.observaciones = gastoBO.getObservaciones();
    }

    // Getters y Setters
    public int getUuid() {
        return uuid;
    }

    public void setUuid(int uuid) {
        this.uuid = uuid;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}
