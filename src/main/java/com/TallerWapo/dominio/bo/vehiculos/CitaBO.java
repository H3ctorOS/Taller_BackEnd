package com.TallerWapo.dominio.bo.vehiculos;

import com.TallerWapo.dominio.bo.contabilidad.GastoBO;
import com.TallerWapo.dominio.bo.contabilidad.IngresoBO;
import com.TallerWapo.dominio.dto.calendario.CitaDTO;
import com.TallerWapo.dominio.interfaces.base.BoBase;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class CitaBO implements BoBase {

    private int uuid;
    private int vehiculoUuid;
    private String concepto;
    private Date fechaInicio;
    private Date fechaFinalizada;
    private String codigoEstado;
    private String observaciones;

    private List<IngresoBO> ingresos; // nullable
    private List<GastoBO> gastos;     // nullable

    public CitaBO() {}

    // Constructor desde DTO
    public CitaBO(CitaDTO dto) {
        this.uuid = dto.getUuid();
        this.vehiculoUuid = dto.getVehiculoUuid();
        this.concepto = dto.getConcepto();
        this.fechaInicio = dto.getFechaInicio() > 0 ? new Date(dto.getFechaInicio()) : null;
        this.fechaFinalizada = dto.getFechaFinalizada() != null ? new Date(dto.getFechaFinalizada()) : null;
        this.codigoEstado = dto.getCodigoEstado();
        this.observaciones = dto.getObservaciones();

        // Mapear ingresos si existen
        if (dto.getIngresos() != null) {
            this.ingresos = dto.getIngresos().stream()
                    .map(IngresoBO::new)
                    .collect(Collectors.toList());
        }

        // Mapear gastos si existen
        if (dto.getGastos() != null) {
            this.gastos = dto.getGastos().stream()
                    .map(GastoBO::new)
                    .collect(Collectors.toList());
        }
    }

    @Override
    public int getUuid() { return uuid; }

    @Override
    public void setUuid(int uuid) { this.uuid = uuid; }

    public int getVehiculoUuid() { return vehiculoUuid; }
    public void setVehiculoUuid(int vehiculoUuid) { this.vehiculoUuid = vehiculoUuid; }

    public String getConcepto() { return concepto; }
    public void setConcepto(String concepto) { this.concepto = concepto; }

    public Date getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(Date fecha) { this.fechaInicio = fecha; }

    public Date getFechaFinalizada() { return fechaFinalizada; }
    public void setFechaFinalizada(Date fecha) { this.fechaFinalizada = fecha; }

    public String getCodigoEstado() { return codigoEstado; }
    public void setCodigoEstado(String codigoEstado) { this.codigoEstado = codigoEstado; }

    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }

    public List<IngresoBO> getIngresos() { return ingresos; }
    public void setIngresos(List<IngresoBO> ingresos) { this.ingresos = ingresos; }

    public List<GastoBO> getGastos() { return gastos; }
    public void setGastos(List<GastoBO> gastos) { this.gastos = gastos; }

    @Override
    public String toString() {
        return "CitaBO{" +
                "uuid=" + uuid +
                ", vehiculoUuid=" + vehiculoUuid +
                ", concepto='" + concepto + '\'' +
                ", fechaInicio=" + fechaInicio +
                ", fechaFinalizada=" + fechaFinalizada +
                ", codigoEstado='" + codigoEstado + '\'' +
                ", ingresos=" + ingresos +
                ", gastos=" + gastos +
                '}';
    }
}
