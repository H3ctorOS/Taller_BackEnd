package com.TallerWapo.dominio.utiles.calendario;

import com.TallerWapo.dominio.dto.calendario.SemanaSelectorDTO;
import com.TallerWapo.dominio.dto.calendario.SemanasDelAnioDTO;
import com.TallerWapo.dominio.utiles.calendario.complementos.SemanaISO;
import com.TallerWapo.dominio.utiles.calendario.complementos.DiasNoLaborablesProvider;

import java.time.*;
import java.time.temporal.WeekFields;
import java.util.*;

/**
 * Utilidad de calendario profesional centralizada.
 *
 * ✅ Cumple norma ISO-8601 (España y UE)
 * ✅ Semana lunes-domingo
 * ✅ Número de semana y año ISO correctos
 * ✅ Preparada para festivos y vacaciones
 * ✅ Conversión entre LocalDate, Date y milisegundos
 */
public final class CalendarioUtil {

    private static final WeekFields WEEK_FIELDS_ISO = WeekFields.ISO;
    private static DiasNoLaborablesProvider provider;

    private CalendarioUtil() {
        throw new UnsupportedOperationException("Clase utilitaria - no instanciable");
    }

    public static void setDiasNoLaborablesProvider(DiasNoLaborablesProvider p) {
        provider = p;
    }

    // ── Validaciones ──
    private static void validarFecha(LocalDate fecha) {
        if (fecha == null) throw new IllegalArgumentException("La fecha no puede ser null");
    }

    private static void validarAnio(int anio) {
        if (anio <= 0) throw new IllegalArgumentException("El año debe ser mayor que 0. Valor recibido: " + anio);
    }

    private static void validarMes(int mes) {
        if (mes < 1 || mes > 12) throw new IllegalArgumentException("Mes fuera de rango: " + mes);
    }

    private static void validarSemana(int numeroSemana, int anio) {
        validarAnio(anio);
        int totalSemanas = obtenerNumeroSemanasDelAnioISO(anio);
        if (numeroSemana < 1 || numeroSemana > totalSemanas) {
            throw new IllegalArgumentException("Semana fuera de rango ISO para el año: " + anio + ". Valor recibido: " + numeroSemana);
        }
    }

    // ── Métodos con LocalDate ──
    public static boolean esFinDeSemana(LocalDate fecha) {
        validarFecha(fecha);
        DayOfWeek dia = fecha.getDayOfWeek();
        return dia == DayOfWeek.SATURDAY || dia == DayOfWeek.SUNDAY;
    }

    public static boolean esFestivo(LocalDate fecha) {
        validarFecha(fecha);
        return provider != null && provider.esFestivo(fecha);
    }

    public static boolean esVacaciones(LocalDate fecha) {
        validarFecha(fecha);
        return provider != null && provider.esVacaciones(fecha);
    }

    public static boolean esLaborable(LocalDate fecha) {
        return !esFinDeSemana(fecha) && !esFestivo(fecha) && !esVacaciones(fecha);
    }

    public static int obtenerNumeroSemanaISO(LocalDate fecha) {
        validarFecha(fecha);
        return fecha.get(WEEK_FIELDS_ISO.weekOfWeekBasedYear());
    }

    public static int obtenerAnioSemanaISO(LocalDate fecha) {
        validarFecha(fecha);
        return fecha.get(WEEK_FIELDS_ISO.weekBasedYear());
    }

    public static LocalDate obtenerLunesSemanaISO(LocalDate fecha) {
        validarFecha(fecha);
        return fecha.with(DayOfWeek.MONDAY);
    }

    public static LocalDate obtenerDomingoSemanaISO(LocalDate fecha) {
        validarFecha(fecha);
        return fecha.with(DayOfWeek.SUNDAY);
    }

    // ── Métodos con java.util.Date ──
    private static LocalDate toLocalDate(Date date) {
        return date == null ? null : Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    private static Date toDate(LocalDate localDate) {
        return localDate == null ? null : Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public static boolean esFinDeSemana(Date fecha) { return esFinDeSemana(toLocalDate(fecha)); }
    public static boolean esFestivo(Date fecha) { return esFestivo(toLocalDate(fecha)); }
    public static boolean esVacaciones(Date fecha) { return esVacaciones(toLocalDate(fecha)); }
    public static boolean esLaborable(Date fecha) { return esLaborable(toLocalDate(fecha)); }
    public static int obtenerNumeroSemanaISO(Date fecha) { return obtenerNumeroSemanaISO(toLocalDate(fecha)); }
    public static int obtenerAnioSemanaISO(Date fecha) { return obtenerAnioSemanaISO(toLocalDate(fecha)); }
    public static Date obtenerLunesSemanaISO(Date fecha) { return toDate(obtenerLunesSemanaISO(toLocalDate(fecha))); }
    public static Date obtenerDomingoSemanaISO(Date fecha) { return toDate(obtenerDomingoSemanaISO(toLocalDate(fecha))); }

    // ── Métodos con milisegundos ──
    public static long toMillis(LocalDate fecha) {
        return fecha.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    public static LocalDate fromMillis(long millis) {
        return Instant.ofEpochMilli(millis).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    // ── Métodos de semana ISO con año y número de semana ──
    public static LocalDate obtenerLunesDeSemanaISO(int numeroSemana, int anio) {
        validarSemana(numeroSemana, anio);
        LocalDate primerLunes = LocalDate.of(anio, 1, 4).with(WEEK_FIELDS_ISO.dayOfWeek(), 1);
        return primerLunes.plusWeeks(numeroSemana - 1);
    }

    public static LocalDate obtenerDomingoDeSemanaISO(int numeroSemana, int anio) {
        return obtenerLunesDeSemanaISO(numeroSemana, anio).plusDays(6);
    }

    // ── Días del mes ──
    public static List<LocalDate> obtenerDiasDelMes(YearMonth yearMonth) {
        validarMes(yearMonth.getMonthValue());
        List<LocalDate> dias = new ArrayList<>();
        for (int i = 1; i <= yearMonth.lengthOfMonth(); i++) {
            dias.add(yearMonth.atDay(i));
        }
        return dias;
    }

    public static List<LocalDate> obtenerDiasLaborablesDelMes(YearMonth yearMonth) {
        List<LocalDate> laborables = new ArrayList<>();
        for (LocalDate fecha : obtenerDiasDelMes(yearMonth)) {
            if (esLaborable(fecha)) laborables.add(fecha);
        }
        return laborables;
    }

    // ── Generar calendario mensual ──
    public static List<SemanaISO> generarCalendarioMensual(int anio, int mes) {
        validarAnio(anio);
        validarMes(mes);

        YearMonth yearMonth = YearMonth.of(anio, mes);
        LocalDate primerDiaMes = yearMonth.atDay(1);
        LocalDate ultimoDiaMes = yearMonth.atEndOfMonth();

        LocalDate inicioCalendario = primerDiaMes.with(DayOfWeek.MONDAY);
        LocalDate finCalendario = ultimoDiaMes.with(DayOfWeek.SUNDAY);

        List<SemanaISO> semanas = new ArrayList<>();
        LocalDate fechaActual = inicioCalendario;

        while (!fechaActual.isAfter(finCalendario)) {
            semanas.add(new SemanaISO(
                    obtenerNumeroSemanaISO(fechaActual),
                    obtenerAnioSemanaISO(fechaActual),
                    obtenerLunesSemanaISO(fechaActual),
                    obtenerDomingoSemanaISO(fechaActual)
            ));
            fechaActual = fechaActual.plusWeeks(1);
        }

        return semanas;
    }

    // ── Número de semanas del año ISO ──
    public static int obtenerNumeroSemanasDelAnioISO(int anio) {
        validarAnio(anio);
        LocalDate fecha = LocalDate.of(anio, 12, 28);
        return fecha.get(WEEK_FIELDS_ISO.weekOfWeekBasedYear());
    }

    // ── Utilidades ──
    public static boolean perteneceAlMes(LocalDate fecha, int anio, int mes) {
        validarFecha(fecha);
        validarAnio(anio);
        validarMes(mes);
        return fecha.getYear() == anio && fecha.getMonthValue() == mes;
    }

    public static int obtenerSemanaActualISO() {
        return obtenerNumeroSemanaISO(LocalDate.now());
    }

    public static int obtenerAnioActualISO() {
        return obtenerAnioSemanaISO(LocalDate.now());
    }

    // ── Cache de semanas del año ──
    private static final Map<Integer, SemanasDelAnioDTO> CACHE_SEMANAS_ANIO = new HashMap<>();

    public static SemanasDelAnioDTO obtenerSemanasDelAnio(int anio) {
        validarAnio(anio);

        if (CACHE_SEMANAS_ANIO.containsKey(anio)) {
            return CACHE_SEMANAS_ANIO.get(anio);
        }

        Map<Integer, List<SemanaSelectorDTO>> semanasPorMes = new TreeMap<>();
        int totalSemanas = obtenerNumeroSemanasDelAnioISO(anio);

        for (int semana = 1; semana <= totalSemanas; semana++) {
            LocalDate lunes = obtenerLunesDeSemanaISO(semana, anio);
            LocalDate jueves = lunes.plusDays(3);
            int mes = jueves.getMonthValue();

            semanasPorMes.computeIfAbsent(mes, k -> new ArrayList<>())
                    .add(new SemanaSelectorDTO(semana, lunes.getYear(), semana == obtenerSemanaActualISO()));
        }

        SemanasDelAnioDTO dto = new SemanasDelAnioDTO(semanasPorMes);
        CACHE_SEMANAS_ANIO.put(anio, dto);
        return dto;
    }

    // ── Conversión rápida de semana a milisegundos ──
    public static long obtenerLunesMillis(int numeroSemana, int anio) {
        return toMillis(obtenerLunesDeSemanaISO(numeroSemana, anio));
    }

    public static long obtenerDomingoMillis(int numeroSemana, int anio) {
        return toMillis(obtenerDomingoDeSemanaISO(numeroSemana, anio));
    }

    // ── Conversión de DayOfWeek a nombre en español ──
    private static final Locale LOCALE_ES = new Locale("es", "ES");

    /**
     * Devuelve el nombre completo del día de la semana en español.
     * Ejemplo: Lunes, Martes, Miércoles...
     */
    public static String diaSemanaEsp(DayOfWeek dia) {
        if (dia == null) return null;
        return dia.getDisplayName(java.time.format.TextStyle.FULL, LOCALE_ES);
    }

    /**
     * Devuelve el nombre abreviado del día de la semana en español.
     * Ejemplo: Lun, Mar, Mié...
     */
    public static String diaSemanaEspAbreviado(DayOfWeek dia) {
        if (dia == null) return null;
        return dia.getDisplayName(java.time.format.TextStyle.SHORT, LOCALE_ES);
    }

}
