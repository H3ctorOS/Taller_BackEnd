package com.TallerWapo.dominio.Puertos.ApiRest;

import com.TallerWapo.dominio.interfacez.base.PuertosBase;

public interface ApiRestPort extends PuertosBase {
    void setPuerto(int puerto);
    int getPuerto();

    void iniciarControllers();

}
