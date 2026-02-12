package com.TallerWapo.dominio.dto.calendario;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class CitaSemanaDTO {

    private final int numeroSemana;

    /** Mapa de día de la semana (nombre en español) -> fecha (epochDay) */
    private final Map<String, Long> fechas;

    /** Mapa de día de la semana (nombre en español) -> lista de citas */
    private final Map<String, List<CitaDTO>> citas;

    public CitaSemanaDTO(int numeroSemana,
                         Map<String, Long> fechas,
                         Map<String, List<CitaDTO>> citas) {
        this.numeroSemana = numeroSemana;
        this.fechas = fechas != null ? fechas : Collections.emptyMap();
        this.citas = citas != null ? citas : Collections.emptyMap();
    }

    public int getNumeroSemana() {
        return numeroSemana;
    }

    public Map<String, Long> getFechas() {
        return fechas;
    }

    public Map<String, List<CitaDTO>> getCitas() {
        return citas;
    }

    /** Devuelve las citas de un día concreto, o vacío si no hay */
    public List<CitaDTO> getCitasDia(String dia) {
        return citas.getOrDefault(dia, Collections.emptyList());
    }

    /** Devuelve la fecha de un día concreto */
    public long getFechaDia(String dia) {
        return fechas.getOrDefault(dia, -1L);
    }
}
