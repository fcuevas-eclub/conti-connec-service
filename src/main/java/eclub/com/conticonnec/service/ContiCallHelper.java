package eclub.com.conticonnec.service;

import eclub.com.conticonnec.dto.SeguimientoRequestDTO;
import eclub.com.conticonnec.dto.SolicitudContiDTO;
import eclub.com.conticonnec.dto.SolicitudResumenContiDTO;
import org.springframework.http.ResponseEntity;

public interface ContiCallHelper {

    ResponseEntity sendSolicitud(SolicitudContiDTO dto) throws Exception;

    SolicitudResumenContiDTO GetSolicitudByNroSolicitud(String nrosolicitud) throws Exception;

    ResponseEntity GetSolicitudByEstado(int estado) throws Exception;

    //ResponseEntity<?> registrarSeguimiento(SeguimientoRequestDTO dto) throws Exception;
    ResponseEntity registrarSeguimiento(SeguimientoRequestDTO dto) throws Exception;
}
