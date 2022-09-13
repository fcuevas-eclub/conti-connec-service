package eclub.com.conticonnec.service;

import com.eclub.lib.common.services.IServiceBase;
import eclub.com.conticonnec.domain.EstadosTarjeta;
import eclub.com.conticonnec.dto.EstadosTarjetaDto;

import java.util.List;

// Una interfaz de servicio.
public interface EstadosTarjetaService extends IServiceBase<EstadosTarjeta, EstadosTarjetaDto> {
    List<EstadosTarjeta> findAll();

    EstadosTarjeta findById(String id) throws Exception;

    EstadosTarjeta findByCodigo(String codigo) throws Exception;

}
