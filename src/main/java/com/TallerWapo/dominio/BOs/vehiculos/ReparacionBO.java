package com.TallerWapo.dominio.BOs.vehiculos;

import com.TallerWapo.dominio.interfaces.base.BoBase;

import java.util.Date;

public class ReparacionBO implements BoBase {
    int uuid;
    int vehiculoUuid;
    int mecanicoUuid;
    String codigoEstado;
    String concepto;
    Date fechaInicio;
    Date fechaFin;


    @Override
    public int getUuid() {return uuid;}

    @Override
    public void setUuid(int uuid) {this.uuid = uuid;}

    public int getVehiculoUuid() {return vehiculoUuid;}
    public void setVehiculoUuid(int vehiculoUuid) {this.vehiculoUuid = vehiculoUuid;}

    public int getMecanicoUuid() {return mecanicoUuid;}
    public void setMecanicoUuid(int mecanicoUuid) {this.mecanicoUuid = mecanicoUuid;}

    public String getConcepto() {return concepto;}
    public void setConcepto(String concepto) {this.concepto = concepto;}

    public Date getFechaInicio() {return fechaInicio;}
    public void setFechaInicio(Date fechaInicio) {this.fechaInicio = fechaInicio;}

    public Date getFechaFin() {return fechaFin;}
    public void setFechaFin(Date fechaFin) {this.fechaFin = fechaFin;}

    public void setCodigoEstado(String codigoEstado) {this.codigoEstado = codigoEstado;}
    public String getCodigoEstado() {return codigoEstado;}
}
