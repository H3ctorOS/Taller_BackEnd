package com.TallerWapo.dominio.bo.vehiculos;

import com.TallerWapo.dominio.interfaces.base.BoBase;

import java.util.Date;

public class GastoBO implements BoBase {
    private int uuid;
    private String descripcion;
    private double importe;
    private Date fecha;
    private String observaciones;

    // Constructor vacío
    public GastoBO() {
    }

    // Constructor completo
    public GastoBO(int uuid, String descripcion, double importe, Date fecha, String observaciones) {
        this.uuid = uuid;
        this.descripcion = descripcion;
        this.importe = importe;
        this.fecha = fecha;
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}
