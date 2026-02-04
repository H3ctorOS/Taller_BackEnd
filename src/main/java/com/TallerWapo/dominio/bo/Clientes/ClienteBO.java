package com.TallerWapo.dominio.bo.Clientes;

import com.TallerWapo.dominio.interfaces.base.BoBase;

public class ClienteBO implements BoBase {
    int uuid;
    String dni;
    String nombre;
    String apellidos;
    String direccion;
    int telefono;
    String email;
    String estado;
    String observaciones;

    @Override
    public void setUuid(int uuid) {this.uuid = uuid;}

    @Override
    public int getUuid() {return uuid;}

    public void setDni(String dni) {this.dni = dni;}
    public String getDni() {return dni;}

    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}

    public String getApellidos() {return apellidos;}
    public void setApellidos(String apellidos) {this.apellidos = apellidos;}

    public String getDireccion() {return direccion;}
    public void setDireccion(String direccion) {this.direccion = direccion;}

    public int getTelefono() {return telefono;}
    public void setTelefono(int telefono) {this.telefono = telefono;}

    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}

    public String getEstado() {return estado;}
    public void setEstado(String estado) {this.estado = estado;}


    public String getObservaciones() {return observaciones;}
    public void setObservaciones(String observaciones) {this.observaciones = observaciones;}
}
