package eclub.com.conticonnec.service;

import com.eclub.lib.common.services.IServiceBase;
import eclub.com.conticonnec.domain.Seguimiento;

import java.util.List;

// Una interfaz de servicio.
public interface SeguimientoService extends IServiceBase<Seguimiento> {
    List<Seguimiento> findAll();

    Seguimiento findById(String id) throws Exception;

    Seguimiento findByNroDocumentoAndNroSolicitud(String nroDocumento, String nroSolicitud) throws Exception;

    Seguimiento findByNroTarjeta(String nroTarjeta) throws Exception;

    //Seguimiento createWithAdjunts(Seguimiento seguimiento, List<SeguimientoAdjunto> adjuntos);
}
