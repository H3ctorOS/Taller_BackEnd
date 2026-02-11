package com.TallerWapo.dominio.dto.calendario;

import com.TallerWapo.dominio.bo.vehiculos.CitaBO;
import com.TallerWapo.dominio.dto.contabilidad.GastoDTO;
import com.TallerWapo.dominio.dto.contabilidad.IngresoDTO;

import java.util.List;
import java.util.stream.Collectors;

public class CitaDTO {

    private int uuid;
    private int vehiculoUuid;
    private String concepto;
    private long fechaInicio;
    private Long fechaFinalizada; // nullable
    private String codigoEstado;
    private String observaciones;
    private List<IngresoDTO> ingresos; // nullable
    private List<GastoDTO> gastos;     // nullable

    public CitaDTO(CitaBO citaBO) {
        this.uuid = citaBO.getUuid();
        this.vehiculoUuid = citaBO.getVehiculoUuid();
        this.concepto = citaBO.getConcepto();
        this.fechaInicio = citaBO.getFechaInicio() != null
                ? citaBO.getFechaInicio().getTime()
                : 0L;
        this.fechaFinalizada = citaBO.getFechaFinalizada() != null
                ? citaBO.getFechaFinalizada().getTime()
                : null;
        this.codigoEstado = citaBO.getCodigoEstado();
        this.observaciones = citaBO.getObservaciones();

        // Mapear ingresos si existen
        if (citaBO.getIngresos() != null) {
            this.ingresos = citaBO.getIngresos().stream()
                    .map(IngresoDTO::new)
                    .collect(Collectors.toList());
        }

        // Mapear gastos si existen
        if (citaBO.getGastos() != null) {
            this.gastos = citaBO.getGastos().stream()
                    .map(GastoDTO::new)
                    .collect(Collectors.toList());
        }
    }

    // Getters y setters
    public int getUuid() { return uuid; }
    public void setUuid(int uuid) { this.uuid = uuid; }

    public int getVehiculoUuid() { return vehiculoUuid; }
    public void setVehiculoUuid(int vehiculoUuid) { this.vehiculoUuid = vehiculoUuid; }

    public String getConcepto() { return concepto; }
    public void setConcepto(String concepto) { this.concepto = concepto; }

    public long getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(long fechaInicio) { this.fechaInicio = fechaInicio; }

    public Long getFechaFinalizada() { return fechaFinalizada; }
    public void setFechaFinalizada(Long fechaFinalizada) { this.fechaFinalizada = fechaFinalizada; }

    public String getCodigoEstado() { return codigoEstado; }
    public void setCodigoEstado(String codigoEstado) { this.codigoEstado = codigoEstado; }

    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }

    public List<IngresoDTO> getIngresos() { return ingresos; }
    public void setIngresos(List<IngresoDTO> ingresos) { this.ingresos = ingresos; }

    public List<GastoDTO> getGastos() { return gastos; }
    public void setGastos(List<GastoDTO> gastos) { this.gastos = gastos; }
}
