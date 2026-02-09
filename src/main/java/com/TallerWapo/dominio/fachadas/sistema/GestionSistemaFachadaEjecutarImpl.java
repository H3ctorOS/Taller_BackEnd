package com.TallerWapo.dominio.fachadas.sistema;

import com.TallerWapo.dominio.fachadas.base.FachadaEjecutarBase;
import com.TallerWapo.dominio.utiles.SistemaUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GestionSistemaFachadaEjecutarImpl extends FachadaEjecutarBase {
    static final Logger logger = LoggerFactory.getLogger(GestionSistemaFachadaEjecutarImpl.class);

    public void ordenApagar() throws Exception {
        logger.info("Ordenando apagar");

        SistemaUtils.apagarPC();

    }
}
