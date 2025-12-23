package com.TallerWapo.dominio.Puertos.baseDatos;

import java.sql.Connection;

public interface BaseDatosSQLPort extends BaseDatosPort {
    Connection getConexion();
    void generarConexionEscritura();
    void generarConexionLectura();
    void finalizarConexion();
}
