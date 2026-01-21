package com.TallerWapo.dominio.BOs.vehiculos;

import com.TallerWapo.dominio.interfaces.base.BoBase;

public class VehiculoBO implements BoBase {

    private int uuid;
    private int uuidPropietario;
    private String matricula;
    private String marca;
    private String modelo;
    private String codidoEstado;


    @Override
    public void setUuid(int uuid) {this.uuid = uuid;}
    @Override
    public int getUuid() {return this.uuid;}


    public String getMatricula() {return this.matricula;}
    public void setMatricula(String matricula) {this.matricula = matricula;}

    public String getMarca() {return this.marca;}
    public void setMarca(String marca) {this.marca = marca;}

    public String getModelo() {return this.modelo;}
    public void setModelo(String modelo) {this.modelo = modelo;}

    public String getCodidoEstado() {return this.codidoEstado;}
    public void setCodidoEstado(String codidoEstado) {this.codidoEstado = codidoEstado;}

    public  int getUuidPropietario() {return this.uuidPropietario;}
    public void setUuidPropietario(int uuid) {this.uuidPropietario = uuid;}

    @Override
    public String toString() {
        return "VehiculoBO{" +
                "uuid=" + uuid +
                ", uuidPropietario=" + uuidPropietario +
                ", matricula='" + matricula + '\'' +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", codigoEstado='" + codidoEstado + '\'' +
                '}';
    }
}
