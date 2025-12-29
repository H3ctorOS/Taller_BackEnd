package com.TallerWapo.dominio.interfaces.puertos.ApiRest;

import com.TallerWapo.dominio.interfaces.base.PuertosBase;

public interface ApiRestPort extends PuertosBase {
    void setPuerto(int puerto);
    int getPuerto();

}
