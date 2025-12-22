package com.TallerWapo.dominio.Puertos.ApiRest;

public enum EstadoRespuestaHTTP {
    OK(200, "OK"),  // Éxito general
    CREATED(201, "Created"),  // Éxito en creación (e.g., POST nuevo vehículo)
    NO_CONTENT(204, "No Content"),  // Éxito sin body (e.g., DELETE)
    BAD_REQUEST(400, "Bad Request"),  // Error cliente: Datos inválidos
    UNAUTHORIZED(401, "Unauthorized"),  // Autenticación requerida
    FORBIDDEN(403, "Forbidden"),  // Acceso denegado
    NOT_FOUND(404, "Not Found"),  // Recurso no encontrado (e.g., vehículo por matrícula)
    INTERNAL_SERVER_ERROR(500, "Internal Server Error");  // Error servidor (e.g., SQLException)

    private final int codigo;
    private final String texto;

    EstadoRespuestaHTTP(int codigo, String texto) {
        this.codigo = codigo;
        this.texto = texto;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getTexto() {
        return texto;
    }

}
