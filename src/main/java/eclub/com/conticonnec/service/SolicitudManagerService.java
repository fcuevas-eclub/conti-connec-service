package eclub.com.conticonnec.service;

import eclub.com.conticonnec.dto.SeguimientoRequestDTO;
import eclub.com.conticonnec.dto.SolicitudContiDTO;
import eclub.com.conticonnec.dto.SolicitudResumenContiDTO;
import org.springframework.http.ResponseEntity;

public interface SolicitudManagerService {

    String extraeNombres(String nombres, boolean extraePrimerNombre);

    /**
     * Envía una petición al servidor.
     *
     * @param dto El objeto que contiene los datos a enviar.
     * @return ResponseEntity<SolicitudContiDTO>
     */
    ResponseEntity sendSolicitud(SolicitudContiDTO dto) throws Exception;

    SolicitudResumenContiDTO GetSolicitudByNroSolicitud(String nrosolicitud) throws Exception;

    ResponseEntity GetSolicitudByEstado(int estado) throws Exception;

    ResponseEntity registrarSeguimiento(SeguimientoRequestDTO dto) throws Exception;

    /**
     * Registra el seguimiento de una lista de envíos.
     *
     * @return Entidad de respuesta<String>
     */
    ResponseEntity<String> registrarSeguimientoPorLotes() throws Exception;

}
