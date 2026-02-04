package com.TallerWapo.dominio.bo.vehiculos;

import com.TallerWapo.dominio.interfaces.base.BoBase;

import java.util.Date;

public class CitaBO implements BoBase {
    int uuid;
    int vehiculoUuid;
    String concepto;
    Date fechaInicio;
    Date fechaFinalizada;
    String codigoEstado;
    String observaciones;

    @Override
    public int getUuid() {return uuid;}

    @Override
    public void setUuid(int uuid) {this.uuid = uuid;}

    public int getVehiculoUuid() {return vehiculoUuid;}
    public void setVehiculoUuid(int vehiculoUuid) {this.vehiculoUuid = vehiculoUuid;}

    public String getConcepto() {return concepto;}
    public void setConcepto(String concepto) {this.concepto = concepto;}

    public Date getFechaInicio() {return fechaInicio;}
    public void setFechaInicio(Date fecha) {this.fechaInicio = fecha;}

    public Date getFechaFinalizada() {return fechaFinalizada;}
    public void setFechaFinalizada(Date fecha) {this.fechaFinalizada = fecha;}

    public String getCodigoEstado() {return codigoEstado;}
    public void setCodigoEstado(String codigoEstado) {this.codigoEstado = codigoEstado;}

    public String getObservaciones() {return observaciones;}
    public void setObservaciones(String observaciones) {this.observaciones = observaciones;}

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("CitaBO{");
        sb.append("uuid=").append(uuid);
        sb.append(", vehiculo Uuid=").append(vehiculoUuid);
        sb.append(", concepto=").append(concepto);
        sb.append(", fecha inicio=").append(fechaInicio);
        sb.append(", fecha fin =").append(fechaFinalizada);
        sb.append(", codigo Estado=").append(codigoEstado);
        sb.append("}");
        return sb.toString();
    }
}
