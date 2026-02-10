package com.TallerWapo.dominio.fachadas.sistema;

import com.TallerWapo.dominio.bo.Clientes.ClienteBO;
import com.TallerWapo.dominio.bo.vehiculos.VehiculoBO;
import com.TallerWapo.dominio.dto.gestion.ResumenDatosAppDTO;
import com.TallerWapo.dominio.fachadas.base.FachadaConsultaBase;
import com.TallerWapo.dominio.factorias.ContextoDaos;
import com.TallerWapo.dominio.interfaces.Daos.ClientesDao;
import com.TallerWapo.dominio.interfaces.Daos.GastoDao;
import com.TallerWapo.dominio.interfaces.Daos.IngresosDao;
import com.TallerWapo.dominio.interfaces.Daos.VehiculosDao;
import com.TallerWapo.dominio.utiles.MainUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class GestionSistemaFachadaConsultasImpl extends FachadaConsultaBase {
    static final Logger logger = LoggerFactory.getLogger(GestionSistemaFachadaConsultasImpl.class);

    private final ClientesDao clientesDao = ContextoDaos.getClienteDao(SESION);
    private final VehiculosDao vehiculoDao = ContextoDaos.getVehiculoDao(SESION);
    private final IngresosDao ingresosDao = ContextoDaos.getIngresoDao(SESION);

    private final GastoDao gastoDao = ContextoDaos.getGastoDao(SESION);

    public ResumenDatosAppDTO estadoDelSistema() throws Exception {

        ResumenDatosAppDTO  resumenDatosAppDTO = new ResumenDatosAppDTO();

        resumenDatosAppDTO.setCantidadArranques(MainUtil.getCantidadArranques());
        resumenDatosAppDTO.setVersion(MainUtil.getVersion());

        List<ClienteBO>  listaClientes = clientesDao.buscarTodos();
        resumenDatosAppDTO.setTotalClientes(listaClientes.size());

        List<VehiculoBO>  listaVehiculos = vehiculoDao.buscarTodos();
        resumenDatosAppDTO.setTotalVehiculos(listaVehiculos.size());

        resumenDatosAppDTO.setTotalIngresos(ingresosDao.togalIngresado());
        resumenDatosAppDTO.setTotalGastos(gastoDao.togalGastado());

        return  resumenDatosAppDTO;
    }


}
