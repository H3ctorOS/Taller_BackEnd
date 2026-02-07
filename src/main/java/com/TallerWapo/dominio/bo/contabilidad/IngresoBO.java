package com.TallerWapo.dominio.bo.contabilidad;

import com.TallerWapo.dominio.dto.contabilidad.IngresoDTO;

import java.util.Date;

public class IngresoBO {

    private int uuid;
    private String concepto;
    private double importe;
    private Date fecha;
    private String codEstado;
    private String observaciones;

    // Constructor vacío
    public IngresoBO() {}

    // Constructor a partir de un DTO
    public IngresoBO(IngresoDTO dto) {
        this.uuid = dto.getUuid();
        this.concepto = dto.getConcepto();
        this.importe = dto.getImporte();
        this.fecha = dto.getFecha() > 0 ? new Date(dto.getFecha()) : null;
        this.codEstado = dto.getCodEstado();
        this.observaciones = dto.getObservaciones();
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
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

    @Override
    public String toString() {
        return "IngresoBO{" +
                "uuid=" + uuid +
                ", concepto='" + concepto + '\'' +
                ", importe=" + importe +
                ", fecha=" + fecha +
                ", codEstado='" + codEstado + '\'' +
                ", observaciones='" + observaciones + '\'' +
                '}';
    }
}
