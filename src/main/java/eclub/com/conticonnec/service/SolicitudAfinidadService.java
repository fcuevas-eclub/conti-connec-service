package eclub.com.conticonnec.service;

import com.eclub.lib.common.services.IServiceBase;
import eclub.com.conticonnec.domain.SolicitudAfinidad;

public interface SolicitudAfinidadService extends IServiceBase<SolicitudAfinidad> {

    void AprobarSolicitud(Long idSolicitud) throws Exception;
    void RechazarSolicitud(Long idSolicitud) throws Exception;
    SolicitudAfinidad findByNroDocumento(String nroDocumento);
    SolicitudAfinidad findByNroSolicitud(String numero);
}
