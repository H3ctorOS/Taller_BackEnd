package com.TallerWapo.dominio.interfaces.base;


import java.sql.Connection;

public class DaoSQLBase {

    protected Connection conexion;

    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }

    public DaoSQLBase (Connection conexion){
        this.conexion = conexion;
    }

}
