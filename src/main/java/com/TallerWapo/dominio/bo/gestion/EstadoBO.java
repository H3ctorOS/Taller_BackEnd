package com.TallerWapo.dominio.bo.gestion;

public enum EstadoBO {

    ACTI("ACTI", "Activo"),
    BAJA("BAJA", "De baja"),
    NULO("", "");

    private final String codigo;
    private final String descripcion;

    EstadoBO(String codigo, String descripcion) {
        this.codigo = codigo;
        this.descripcion = descripcion;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Convierte un código de BD a enum
     */
    public static EstadoBO fromCodigo(String codigo) {
        for (EstadoBO estado : values()) {
            if (estado.codigo.equalsIgnoreCase(codigo)) {
                return estado;
            }
        }
        throw new IllegalArgumentException("Estado no válido: " + codigo);
    }
}
