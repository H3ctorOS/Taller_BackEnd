package com.TallerWapo.dominio.utiles.calendario;

import com.TallerWapo.dominio.dto.calendario.SemanaSelectorDTO;
import com.TallerWapo.dominio.dto.calendario.SemanasDelAnioDTO;
import com.TallerWapo.dominio.utiles.calendario.complementos.SemanaISO;
import com.TallerWapo.dominio.utiles.calendario.complementos.DiasNoLaborablesProvider;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.temporal.WeekFields;
import java.util.*;

/**
 * Utilidad de calendario profesional.
 *
 * ✅ Cumple norma ISO-8601 (España y UE)
 * ✅ Semana lunes-domingo
 * ✅ Número de semana y año ISO correctos
 * ✅ Preparada para festivos y vacaciones
 */
public final class CalendarioUtil {

    private static final WeekFields WEEK_FIELDS_ISO = WeekFields.ISO;

    // Provider para festivos y vacaciones
    private static DiasNoLaborablesProvider provider;

    // Evita instanciación
    private CalendarioUtil() {
        throw new UnsupportedOperationException("Clase utilitaria - no instanciable");
    }

    /** Asigna el provider de días no laborables */
    public static void setDiasNoLaborablesProvider(DiasNoLaborablesProvider p) {
        provider = p;
    }

    // ── Métodos para LocalDate ──
    public static boolean esFinDeSemana(LocalDate fecha) {
        DayOfWeek dia = fecha.getDayOfWeek();
        return dia == DayOfWeek.SATURDAY || dia == DayOfWeek.SUNDAY;
    }

    public static boolean esFestivo(LocalDate fecha) {
        return provider != null && provider.esFestivo(fecha);
    }

    public static boolean esVacaciones(LocalDate fecha) {
        return provider != null && provider.esVacaciones(fecha);
    }

    public static boolean esLaborable(LocalDate fecha) {
        return !esFinDeSemana(fecha) && !esFestivo(fecha) && !esVacaciones(fecha);
    }

    public static int obtenerNumeroSemanaISO(LocalDate fecha) {
        if (fecha == null) throw new IllegalArgumentException("La fecha no puede ser null");
        return fecha.get(WEEK_FIELDS_ISO.weekOfWeekBasedYear());
    }

    public static int obtenerAnioSemanaISO(LocalDate fecha) {
        if (fecha == null) throw new IllegalArgumentException("La fecha no puede ser null");
        return fecha.get(WEEK_FIELDS_ISO.weekBasedYear());
    }

    public static LocalDate obtenerLunesSemanaISO(LocalDate fecha) {
        return fecha.with(DayOfWeek.MONDAY);
    }

    public static LocalDate obtenerDomingoSemanaISO(LocalDate fecha) {
        return fecha.with(DayOfWeek.SUNDAY);
    }

    // ── Sobrecargas para java.util.Date ──
    public static boolean esFinDeSemana(Date fecha) {
        return esFinDeSemana(toLocalDate(fecha));
    }

    public static boolean esFestivo(Date fecha) {
        return esFestivo(toLocalDate(fecha));
    }

    public static boolean esVacaciones(Date fecha) {
        return esVacaciones(toLocalDate(fecha));
    }

    public static boolean esLaborable(Date fecha) {
        return esLaborable(toLocalDate(fecha));
    }

    public static int obtenerNumeroSemanaISO(Date fecha) {
        return obtenerNumeroSemanaISO(toLocalDate(fecha));
    }

    public static int obtenerAnioSemanaISO(Date fecha) {
        return obtenerAnioSemanaISO(toLocalDate(fecha));
    }

    public static Date obtenerLunesSemanaISO(Date fecha) {
        return toDate(obtenerLunesSemanaISO(toLocalDate(fecha)));
    }

    public static Date obtenerDomingoSemanaISO(Date fecha) {
        return toDate(obtenerDomingoSemanaISO(toLocalDate(fecha)));
    }

    /** Conversión interna entre Date y LocalDate */
    private static LocalDate toLocalDate(Date date) {
        if (date == null) return null;
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    private static Date toDate(LocalDate localDate) {
        if (localDate == null) return null;
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    // ── Métodos de semana ISO con año y número de semana ──
    public static LocalDate obtenerLunesDeSemanaISO(int numeroSemana, int anio) {
        LocalDate fechaReferencia = LocalDate.of(anio, 1, 4); // semana 1 siempre
        LocalDate fechaSemana = fechaReferencia.with(WEEK_FIELDS_ISO.weekOfWeekBasedYear(), numeroSemana);
        return obtenerLunesSemanaISO(fechaSemana);
    }

    public static LocalDate obtenerDomingoDeSemanaISO(int numeroSemana, int anio) {
        LocalDate lunes = obtenerLunesDeSemanaISO(numeroSemana, anio);
        return lunes.plusDays(6);
    }

    // ── Otros métodos útiles ──
    public static List<LocalDate> obtenerDiasDelMes(YearMonth yearMonth) {
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

    public static List<SemanaISO> generarCalendarioMensual(int anio, int mes) {
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

    public static int obtenerNumeroSemanasDelAnioISO(int anio) {
        LocalDate fecha = LocalDate.of(anio, 12, 28);
        return fecha.get(WEEK_FIELDS_ISO.weekOfWeekBasedYear());
    }

    public static boolean perteneceAlMes(LocalDate fecha, int anio, int mes) {
        return fecha.getYear() == anio && fecha.getMonthValue() == mes;
    }

    /** Devuelve el número de semana ISO actual */
    public static int obtenerSemanaActualISO() {
        return obtenerNumeroSemanaISO(LocalDate.now());
    }

    /** Devuelve el año ISO de la semana actual */
    public static int obtenerAnioActualISO() {
        return obtenerAnioSemanaISO(LocalDate.now());
    }


    /**
     * Devuelve un DTO con todas las semanas ISO de un año, organizadas por mes.
     * Usa un cache interno para no recalcular las semanas repetidamente.
     *
     * @param anio Año ISO para generar las semanas
     * @return SemanasDelAnioDTO con semanas por mes
     */
    private static final Map<Integer, SemanasDelAnioDTO> CACHE_SEMANAS_ANIO = new HashMap<>();
    public static SemanasDelAnioDTO obtenerSemanasDelAnio(int anio) {
        // ── Revisar cache
        if (CACHE_SEMANAS_ANIO.containsKey(anio)) {
            SemanasDelAnioDTO cached = CACHE_SEMANAS_ANIO.get(anio);

            // ── Actualizamos solo la marca de la semana actual al vuelo
            int semanaActual = CalendarioUtil.obtenerSemanaActualISO();
            Map<Integer, List<SemanaSelectorDTO>> semanasActualizadas = new TreeMap<>();

            cached.semanasPorMes().forEach((mes, lista) -> {
                List<SemanaSelectorDTO> nuevaLista = new ArrayList<>();
                for (SemanaSelectorDTO s : lista) {
                    nuevaLista.add(new SemanaSelectorDTO(
                            s.numeroSemana(),
                            s.anio(),
                            s.numeroSemana() == semanaActual
                    ));
                }
                semanasActualizadas.put(mes, nuevaLista);
            });

            return new SemanasDelAnioDTO(semanasActualizadas);
        }

        // ── Generar nuevo DTO si no está en cache
        Map<Integer, List<SemanaSelectorDTO>> semanasPorMes = new TreeMap<>();
        int totalSemanas = obtenerNumeroSemanasDelAnioISO(anio);
        int semanaActual = CalendarioUtil.obtenerSemanaActualISO();

        for (int semana = 1; semana <= totalSemanas; semana++) {
            LocalDate lunes = obtenerLunesDeSemanaISO(semana, anio);
            LocalDate jueves = lunes.plusDays(3); // referencia ISO correcta
            int mes = jueves.getMonthValue();

            semanasPorMes.computeIfAbsent(mes, k -> new ArrayList<>())
                    .add(new SemanaSelectorDTO(semana, lunes.getYear(), semana == semanaActual));
        }

        SemanasDelAnioDTO dto = new SemanasDelAnioDTO(semanasPorMes);
        CACHE_SEMANAS_ANIO.put(anio, dto); // Guardar en cache
        return dto;
    }





}
