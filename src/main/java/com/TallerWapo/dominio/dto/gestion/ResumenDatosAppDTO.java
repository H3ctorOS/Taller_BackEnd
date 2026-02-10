package com.TallerWapo.dominio.dto.gestion;

import com.TallerWapo.dominio.interfaces.base.DTOBase;

public class ResumenDatosAppDTO implements DTOBase {

    private String version;
    private int cantidadArranques;
    private double totalGastos;
    private double totalIngresos;
    private int totalVehiculos;
    private int totalClientes;

    // Getter y Setter de cantidadArranques
    public int getCantidadArranques() {
        return cantidadArranques;
    }

    public void setCantidadArranques(int cantidadArranques) {
        this.cantidadArranques = cantidadArranques;
    }

    // Getter y Setter de totalGastos
    public double getTotalGastos() {
        return totalGastos;
    }

    public void setTotalGastos(double totalGastos) {
        this.totalGastos = totalGastos;
    }

    // Getter y Setter de totalIngresos
    public double getTotalIngresos() {
        return totalIngresos;
    }

    public void setTotalIngresos(double totalIngresos) {
        this.totalIngresos = totalIngresos;
    }

    // Getter y Setter de totalVehiculos
    public int getTotalVehiculos() {
        return totalVehiculos;
    }

    public void setTotalVehiculos(int totalVehiculos) {
        this.totalVehiculos = totalVehiculos;
    }

    // Getter y Setter de totalClientes
    public int getTotalClientes() {
        return totalClientes;
    }

    public void setTotalClientes(int totalClientes) {
        this.totalClientes = totalClientes;
    }

    public String getVersion() {
        return version;
    }
    public void setVersion(String version) {}
}
