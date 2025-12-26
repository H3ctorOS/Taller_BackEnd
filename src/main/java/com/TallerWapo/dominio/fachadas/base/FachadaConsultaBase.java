package com.TallerWapo.dominio.fachadas.base;

import com.TallerWapo.dominio.contexto.Contexto;
import com.TallerWapo.dominio.contexto.Sesion;
import com.TallerWapo.dominio.interfaces.fachadas.FachadasBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FachadaConsultaBase implements FachadasBase {
    static final Logger logger = LoggerFactory.getLogger(FachadaConsultaBase.class);
    protected final Sesion SESION = new Sesion(Contexto.baseDatosSQL.getConexionLectura());
}
