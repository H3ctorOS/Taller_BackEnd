package com.TallerWapo.dominio.BOs.vehiculos;

import com.TallerWapo.dominio.interfaces.base.BoBase;

import java.util.Date;

public class CitaBO implements BoBase {
    int uuid;
    int vehiculoUuid;
    int clienteUuid;
    String concepto;
    Date fecha;


    @Override
    public int getUuid() {return uuid;}

    @Override
    public void setUuid(int uuid) {this.uuid = uuid;}

    public int getVehiculoUuid() {return vehiculoUuid;}
    public void setVehiculoUuid(int vehiculoUuid) {this.vehiculoUuid = vehiculoUuid;}

    public String getConcepto() {return concepto;}
    public void setConcepto(String concepto) {this.concepto = concepto;}

    public Date getFecha() {return fecha;}
    public void setFecha(Date fecha) {this.fecha = fecha;}

    public int getClienteUuid() {return clienteUuid;}
    public void setClienteUuid(int clienteUuid) {this.clienteUuid = clienteUuid;}

}
