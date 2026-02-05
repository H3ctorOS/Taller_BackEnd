package com.TallerWapo.dominio.bo.vehiculos;

import com.TallerWapo.dominio.interfaces.base.BoBase;

import java.util.Date;

public class IngresoBO implements BoBase {
    private int uuid;
    private String concepto;
    private double importe;
    private Date fecha;
    private String codEstado;
    private String observaciones;

    // Constructor vacío
    public IngresoBO() {
    }

    // Constructor completo
    public IngresoBO(int uuid, String concepto, double importe, Date fecha, String codEstado, String observaciones) {
        this.uuid = uuid;
        this.concepto = concepto;
        this.importe = importe;
        this.fecha = fecha;
        this.codEstado = codEstado;
        this.observaciones = observaciones;
    }

    @Override
    public void setUuid(int uuid) {
        this.uuid = uuid;
    }

    @Override
    public int getUuid() {
        return this.uuid;
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
}
