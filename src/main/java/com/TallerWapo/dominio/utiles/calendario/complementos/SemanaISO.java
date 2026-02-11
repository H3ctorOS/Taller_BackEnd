package com.TallerWapo.dominio.utiles.calendario.complementos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SemanaISO {

    private final int numeroSemana;
    private final int anioSemanaISO;
    private final LocalDate fechaInicio; // lunes
    private final LocalDate fechaFin;    // domingo

    public SemanaISO(int numeroSemana, int anioSemanaISO, LocalDate fechaInicio, LocalDate fechaFin) {
        this.numeroSemana = numeroSemana;
        this.anioSemanaISO = anioSemanaISO;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public int getNumeroSemana() {
        return numeroSemana;
    }

    public int getAnioSemanaISO() {
        return anioSemanaISO;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    /** Devuelve todas las fechas de la semana */
    public List<LocalDate> getDiasSemana() {
        List<LocalDate> dias = new ArrayList<>();
        LocalDate actual = fechaInicio;
        while (!actual.isAfter(fechaFin)) {
            dias.add(actual);
            actual = actual.plusDays(1);
        }
        return dias;
    }

    /** Formato profesional tipo ERP: 2026-W05 */
    public String formatoERP() {
        return String.format("%d-W%02d", anioSemanaISO, numeroSemana);
    }

    @Override
    public String toString() {
        return formatoERP() + " (" + fechaInicio + " - " + fechaFin + ")";
    }
}
