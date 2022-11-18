package eclub.com.conticonnec.service;

import eclub.com.conticonnec.dto.*;
import org.springframework.http.ResponseEntity;

public interface ContiCallHelper {

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
     * Devuelve la información de la cuenta de un cliente.
     *
     * @param nroDocumento El número de documento del usuario.
     * @return AccountInfoResponseDTO
     */
    AccountInfoResponseDTO getAccountData(String nroDocumento) throws Exception;

    /**
     * Registra el seguimiento de un lote de documentos.
     *
     * @param dto SeguimientoPorLotesRequestDTO
     * @return ResponseEntity<SeguimientoPorLotesResponseDTO>
     */
    ResponseEntity<SeguimientoPorLotesResponseDTO> registrarSeguimientoPorLote(SeguimientoPorLotesRequestDTO dto) throws Exception;
}
