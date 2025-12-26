package com.TallerWapo.dominio.contexto;

import java.sql.Connection;

public class Sesion {
    Connection conexion;

    public Sesion(Connection conexion) {
        this.conexion = conexion;
    }

    public Connection getConexion() {
        return conexion;
    }
    public void setConexion(Connection conexion) {}

}
