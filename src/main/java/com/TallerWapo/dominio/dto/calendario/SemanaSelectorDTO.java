package com.TallerWapo.dominio.dto.calendario;

public record SemanaSelectorDTO(
        int numeroSemana, // Número ISO de la semana
        int anio          // Año ISO
) {}