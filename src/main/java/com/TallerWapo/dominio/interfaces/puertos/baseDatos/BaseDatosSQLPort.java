package com.TallerWapo.dominio.interfaces.puertos.baseDatos;

import java.sql.Connection;

public interface BaseDatosSQLPort extends BaseDatosPort {
    Connection getConexion();
    void generarConexionEscritura();
    void generarConexionLectura();
    void finalizarConexion();
}
