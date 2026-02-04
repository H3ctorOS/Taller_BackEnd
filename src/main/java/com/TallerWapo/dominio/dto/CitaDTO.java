package com.TallerWapo.dominio.dto;

import com.TallerWapo.dominio.bo.vehiculos.CitaBO;

import java.util.Date;

public class CitaDTO {

    int uuid;
    int vehiculoUuid;
    String concepto;
    long fechaInicio;
    Long fechaFinalizada; // nullable
    String codigoEstado;
    String observaciones;

    public CitaDTO(CitaBO citaBO) {
        this.uuid = citaBO.getUuid();
        this.vehiculoUuid = citaBO.getVehiculoUuid();
        this.concepto = citaBO.getConcepto();
        this.fechaInicio = citaBO.getFechaInicio() != null
                ? citaBO.getFechaInicio().getTime()
                : 0L;
        this.fechaFinalizada = citaBO.getFechaFinalizada() != null
                ? citaBO.getFechaFinalizada().getTime()
                : null;
        this.codigoEstado = citaBO.getCodigoEstado();
        this.observaciones = citaBO.getObservaciones();
    }

    public int getUuid() {
        return uuid;
    }

    public void setUuid(int uuid) {
        this.uuid = uuid;
    }

    public int getVehiculoUuid() {
        return vehiculoUuid;
    }

    public void setVehiculoUuid(int vehiculoUuid) {
        this.vehiculoUuid = vehiculoUuid;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public long getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(long fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Long getFechaFinalizada() {
        return fechaFinalizada;
    }

    public void setFechaFinalizada(Long fechaFinalizada) {
        this.fechaFinalizada = fechaFinalizada;
    }

    public String getCodigoEstado() {
        return codigoEstado;
    }

    public void setCodigoEstado(String codigoEstado) {
        this.codigoEstado = codigoEstado;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}
