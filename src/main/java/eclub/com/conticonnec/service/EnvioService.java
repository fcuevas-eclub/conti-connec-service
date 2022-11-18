package eclub.com.conticonnec.service;

import com.eclub.lib.common.services.IServiceBase;
import eclub.com.conticonnec.domain.Envio;
import eclub.com.conticonnec.dto.EnvioDto;

import java.util.List;

// Una interfaz de servicio.
public interface EnvioService extends IServiceBase<Envio, EnvioDto> {
    List<Envio> findAll();

    Envio findById(String id) throws Exception;

    Envio findByNroDocumento(String nroDocumento) throws Exception;
}
