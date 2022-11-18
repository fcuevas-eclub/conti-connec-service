package eclub.com.conticonnec.service;

import com.eclub.lib.common.services.IServiceBase;
import eclub.com.conticonnec.domain.EstadoTarjeta;
import eclub.com.conticonnec.dto.EstadosTarjetaDto;

import java.util.List;

// Una interfaz de servicio.
public interface EstadosTarjetaService extends IServiceBase<EstadoTarjeta, EstadosTarjetaDto> {
    List<EstadoTarjeta> findAll();

    EstadoTarjeta findById(String id) throws Exception;

    EstadoTarjeta findByCodigo(String codigo) throws Exception;

}
