package com.TallerWapo.dominio.BOs.vehiculos;

import com.TallerWapo.dominio.interfaces.base.BoBase;

import java.util.Date;

public class CitaBO implements BoBase {
    int uuid;
    int vehiculoUuid;
    int clienteUuid;
    int reparacionUuid;
    String concepto;
    Date fecha;
    String codigoEstado;


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

    public int getReparacionUuid() {return reparacionUuid;}
    public void setReparacionUuid(int reparacionUuid) {this.reparacionUuid = reparacionUuid;}

    public String getCodigoEstado() {return codigoEstado;}
    public void setCodigoEstado(String codigoEstado) {this.codigoEstado = codigoEstado;}

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("CitaBO{");
        sb.append("uuid=").append(uuid);
        sb.append(", vehiculoUuid=").append(vehiculoUuid);
        sb.append(", clienteUuid=").append(clienteUuid);
        sb.append(", reparacionUuid=").append(reparacionUuid);
        sb.append(", concepto=").append(concepto);
        sb.append(", fecha=").append(fecha);
        sb.append(", codigoEstado=").append(codigoEstado);
        sb.append("}");
        return sb.toString();
    }
}
