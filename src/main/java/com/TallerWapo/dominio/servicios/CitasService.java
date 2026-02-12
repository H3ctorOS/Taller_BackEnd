package com.TallerWapo.dominio.servicios;

import com.TallerWapo.dominio.bo.vehiculos.CitaBO;
import com.TallerWapo.dominio.bo.vehiculos.VehiculoBO;
import com.TallerWapo.dominio.contexto.Sesion;
import com.TallerWapo.dominio.dto.calendario.CitaDTO;
import com.TallerWapo.dominio.dto.calendario.CitaSemanaDTO;
import com.TallerWapo.dominio.factorias.ContextoDaos;
import com.TallerWapo.dominio.interfaces.Daos.CitasDao;
import com.TallerWapo.dominio.interfaces.Daos.VehiculosDao;
import com.TallerWapo.dominio.utiles.calendario.CalendarioUtil;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CitasService {

    public static void validarCita(Sesion sesion, CitaBO cita) throws Exception {
        VehiculosDao vehiculosDao = ContextoDaos.getVehiculoDao(sesion);

        //la cita no puede ser nulo
        if(cita == null){
            throw  new IllegalArgumentException("El cita no puede ser nulo.");
        }

        //la cita debe tener informada la fecha inicio
        if(cita.getFechaInicio() == null){
            throw  new IllegalArgumentException("El fecha debe de venir informada");
        }


        //Validar vehiculo
        VehiculoBO vehiculo = vehiculosDao.buscarPorId(cita.getVehiculoUuid());
        if(vehiculo == null){
            throw  new IllegalArgumentException("El vehiculo no existe");
        }

        //Validar que la cita no caiga en un día no laborable
        if (!CalendarioUtil.esLaborable(cita.getFechaInicio())) {
            throw new IllegalArgumentException("No se puede crear la cita en un día no laborable (fin de semana, festivo o vacaciones).");
        }

    }


    public static List<CitaDTO> toDTOList(List<CitaBO> citasBO) {
        List<CitaDTO> resultado = new ArrayList<>();

        if (citasBO == null) {
            return resultado;
        }

        for (CitaBO citaBO : citasBO) {
            resultado.add(new CitaDTO(citaBO));
        }

        return resultado;
    }

    /**
     * Busca todas las citas de una semana concreta (lunes a viernes)
     * @param numeroSemana semana ISO
     * @param anio año ISO
     * @param sesion sesión actual
     * @return DTO de la semana con fechas y citas
     */
    public static CitaSemanaDTO buscarCitaSemana(Sesion sesion, int numeroSemana, int anio) throws Exception {

        CitasDao citasDao = ContextoDaos.getCitaDao(sesion);

        // ── 1. Obtenemos el lunes de la semana
        LocalDate lunesSemana = CalendarioUtil.obtenerLunesDeSemanaISO(numeroSemana, anio);

        Map<String, Long> fechas = new HashMap<>();
        Map<String, List<CitaDTO>> citasPorDia = new HashMap<>();

        // ── 2. Recorremos lunes a viernes
        for (int i = 0; i < 5; i++) {
            LocalDate dia = lunesSemana.plusDays(i);
            String nombreDia = CalendarioUtil.diaSemanaEsp(dia.getDayOfWeek());

            // ✅ Convertir a milisegundos
            long milis = CalendarioUtil.toMillis(dia);

            fechas.put(nombreDia, milis);

            // ── 3. Buscamos todas las citas activas de ese día
            List<CitaBO> citasBO = citasDao.buscarAcivasDia(milis);
            citasPorDia.put(nombreDia, toDTOList(citasBO));
        }

        // ── 4. Construimos el DTO
        return new CitaSemanaDTO(numeroSemana, fechas, citasPorDia);
    }


}
