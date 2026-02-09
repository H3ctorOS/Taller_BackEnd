package com.TallerWapo.dominio.fachadas.sistema;

import com.TallerWapo.dominio.fachadas.base.FachadaEjecutarBase;
import com.TallerWapo.dominio.utiles.SistemaUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GestionSistemaFachadaConsultasImpl extends FachadaEjecutarBase {
    static final Logger logger = LoggerFactory.getLogger(GestionSistemaFachadaConsultasImpl.class);

    public int vecesArrancado(){
       return 22;
    }

    public String versionApp(){
        return "0.0.00";
    }
}
