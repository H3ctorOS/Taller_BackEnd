package com.TallerWapo.dominio.utiles.calendario.complementos;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class DiasNoLaborablesProvider {

    private final Set<LocalDate> festivos = new HashSet<>();
    private final Set<LocalDate> vacaciones = new HashSet<>();

    /** Agrega un festivo */
    public void agregarFestivo(LocalDate fecha) {
        festivos.add(fecha);
    }

    /** Agrega una vacación (de un mecánico) */
    public void agregarVacaciones(LocalDate inicio, LocalDate fin) {
        LocalDate actual = inicio;
        while (!actual.isAfter(fin)) {
            vacaciones.add(actual);
            actual = actual.plusDays(1);
        }
    }

    /** Devuelve true si la fecha es un festivo */
    public boolean esFestivo(LocalDate fecha) {
        return festivos.contains(fecha);
    }

    /** Devuelve true si la fecha es vacaciones */
    public boolean esVacaciones(LocalDate fecha) {
        return vacaciones.contains(fecha);
    }

    /** Devuelve true si la fecha es no laborable (festivo o vacaciones) */
    public boolean esNoLaborable(LocalDate fecha) {
        return esFestivo(fecha) || esVacaciones(fecha);
    }

    /** Limpia todos los días cargados */
    public void limpiar() {
        festivos.clear();
        vacaciones.clear();
    }
}
