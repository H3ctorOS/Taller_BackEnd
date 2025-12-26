package com.TallerWapo.Adaptadores.BaseDatossql.daosImpl.base;


import com.TallerWapo.dominio.contexto.Sesion;

import java.sql.Connection;

public class DaoSQLBase {

    protected Connection conexion;

    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }

    public DaoSQLBase (Sesion sesion){
        this.conexion = sesion.getConexion();
    }

}
