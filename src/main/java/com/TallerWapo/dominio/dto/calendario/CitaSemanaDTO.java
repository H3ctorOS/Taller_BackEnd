package com.TallerWapo.dominio.dto.calendario;


import java.time.DayOfWeek;
import java.util.Collections;
import java.util.List;
import java.util.Map;


public class CitaSemanaDTO {

    private final int numeroSemana;

    /** Mapa de día de la semana -> fecha (epochDay) */
    private final Map<DayOfWeek, Long> fechas;

    /** Mapa de día de la semana -> lista de citas */
    private final Map<DayOfWeek, List<CitaDTO>> citas;

    public CitaSemanaDTO(int numeroSemana,
                         Map<DayOfWeek, Long> fechas,
                         Map<DayOfWeek, List<CitaDTO>> citas) {
        this.numeroSemana = numeroSemana;
        this.fechas = fechas != null ? fechas : Collections.emptyMap();
        this.citas = citas != null ? citas : Collections.emptyMap();
    }

    public int getNumeroSemana() {
        return numeroSemana;
    }

    public Map<DayOfWeek, Long> getFechas() {
        return fechas;
    }

    public Map<DayOfWeek, List<CitaDTO>> getCitas() {
        return citas;
    }

    /** Devuelve las citas de un día concreto, o vacío si no hay */
    public List<CitaDTO> getCitasDia(DayOfWeek dia) {
        return citas.getOrDefault(dia, Collections.emptyList());
    }

    /** Devuelve la fecha de un día concreto */
    public long getFechaDia(DayOfWeek dia) {
        return fechas.getOrDefault(dia, -1L);
    }
}
