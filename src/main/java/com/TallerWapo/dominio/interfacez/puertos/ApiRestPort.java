package com.TallerWapo.dominio.interfacez.puertos;

import com.TallerWapo.dominio.interfacez.PuertosBase;

public interface ApiRestPort extends PuertosBase {
    void setPuerto(int puerto);
    int getPuerto();

    void iniciarControllers();
}
