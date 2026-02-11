package com.TallerWapo.dominio.dto.calendario;

import java.util.List;
import java.util.Map;

public record SemanasDelAnioDTO(
        Map<Integer, List<SemanaSelectorDTO>> semanasPorMes // Clave = mes (1-12), valor = semanas del mes
) {}