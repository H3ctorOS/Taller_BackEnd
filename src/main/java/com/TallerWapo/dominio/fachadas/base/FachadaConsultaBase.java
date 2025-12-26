package com.TallerWapo.dominio.fachadas.base;

import com.TallerWapo.dominio.contexto.ContextoGeneral;
import com.TallerWapo.dominio.interfaces.fachadas.FachadasBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;

public class FachadaConsultaBase implements FachadasBase {
    static final Logger logger = LoggerFactory.getLogger(FachadaConsultaBase.class);
    protected Connection CONEXION = ContextoGeneral.baseDatosSQL.getConexionLectura();
}
