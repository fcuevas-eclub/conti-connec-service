package eclub.com.conticonnec.service;

import com.eclub.lib.common.services.IServiceBase;
import eclub.com.conticonnec.domain.Seguimiento;
import eclub.com.conticonnec.dto.SeguimientoDto;

import java.util.List;

// Una interfaz de servicio.
public interface SeguimientoService extends IServiceBase<Seguimiento, SeguimientoDto> {
    List<Seguimiento> findAll();

    Seguimiento findById(String id) throws Exception;

    Seguimiento findByNroDocumentoAndNroSolicitud(String nroDocumento, String nroSolicitud) throws Exception;

    Seguimiento findByNroTarjeta(String nroTarjeta) throws Exception;

}
