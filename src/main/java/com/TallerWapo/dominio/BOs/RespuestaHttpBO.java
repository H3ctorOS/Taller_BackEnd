package com.TallerWapo.dominio.BOs;

import com.TallerWapo.dominio.interfaces.puertos.ApiRest.EstadoRespuestaHTTP;

public class RespuestaHttpBO {
    private int status = EstadoRespuestaHTTP.INTERNAL_SERVER_ERROR.getCodigo();
    private String mensaje;
    private Object BoRespuesta;
    private boolean isOk = false;


    public RespuestaHttpBO(int status, String mensaje, Object BoRespuesta) {
        this.status = status;
        this.mensaje = mensaje;
        this.BoRespuesta = BoRespuesta;
    }

    public RespuestaHttpBO(int status, String mensaje) {
        this.status = status;
        this.mensaje = mensaje;
    }

    public RespuestaHttpBO() {}

    public void setObjeto(Object boRespuesta) {
        BoRespuesta = boRespuesta;
    }

    public Object getObjeto() {
        return BoRespuesta;
    }

    public int getStatus() {
        return status;
    }
    public String getMensaje() {
        return mensaje;
    }

    public boolean isOk() {
        return isOk;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public void setIsOk(boolean isOk) {
        this.isOk = isOk;
    }
}
