package eclub.com.conticonnec.controller;

import com.eclub.lib.common.models.dto.ErrorMessage;
import eclub.com.conticonnec.domain.SolicitudAfinidad;
import eclub.com.conticonnec.dto.SeguimientoRequestDTO;
import eclub.com.conticonnec.dto.SolicitudContiDTO;
import eclub.com.conticonnec.dto.SolicitudResumenContiDTO;
import eclub.com.conticonnec.enums.ETypeSolicitudAfinidadState;
import eclub.com.conticonnec.service.SolicitudAfinidadService;
import eclub.com.conticonnec.service.SolicitudManagerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * Esta clase es un controlador rest que llama al servicio para crear una nueva solicitud
 * de alta en el Banco Continental.
 */
@RestController
@RequestMapping("/api/v1/conti/solicitud")
public class SolicitudAfinidadController {

    @Value("${configuracion.ambiente}")
    private String config;


    private final SolicitudAfinidadService service;
    private final SolicitudManagerService solicitudManagerService;
    public SolicitudAfinidadController( SolicitudAfinidadService service, SolicitudManagerService solicitudManagerService) {
        this.service = service;
        this.solicitudManagerService = solicitudManagerService;
    }

    final static Logger logger = LoggerFactory.getLogger(SolicitudAfinidadController.class);


    @GetMapping("/config")
    public ResponseEntity getConfig() {
        Map<String, String> map = new HashMap<>();
        map.put("config", config);
        return ResponseEntity.ok(map);
    }

    /**
     * Llama a la API conti para crear una nueva solicitud.
     *
     * @param solicitud es el objeto que se envía a la API conti, es un DTO que contiene los datos de la solicitud.
     * @param result El resultado de la validación.
     * @return Una ResponseEntity con el estado de la solicitud y el cuerpo de la respuesta.
     */
    @PostMapping("/")
    public ResponseEntity enviarSolicitud(@Valid @RequestBody SolicitudContiDTO solicitud, BindingResult result) {

        if (result.hasErrors()) {
            logger.error("SolicitudAlta don't create! Error in validated {} ", result.getFieldErrors());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(ErrorMessage
                            .formatMessages("conticonec-service", result.getFieldErrors()));
        }

        ResponseEntity response;
        try {
            response = solicitudManagerService.sendSolicitud(solicitud);

            if (response.getStatusCode() == HttpStatus.OK) {
                String responseConti = (String) response.getBody();
                String[] parts = responseConti.split("OK-");
                logger.info("SolicitudAfinidadController.callPostSolicitud.HttpStatus.OK: {}", parts[1]);

                SolicitudResumenContiDTO solresumeres = solicitudManagerService.GetSolicitudByNroSolicitud(parts[1]);

                if (solresumeres == null)
                    throw new Exception("solresume whit nro: " + parts[1] + " Not found in conti API!");

                SolicitudResumenContiDTO solresume = solresumeres;
                SolicitudAfinidad sol = SolicitudAfinidad.builder()
                                                            .nroSolicitud(solresume.getIdSolicitud())
                                                            .nroDocumento(solicitud.getDocumento())
                                                            .fechaAlta(solresume.getFechaCarga())
                                                            .partnerId(1)
                                                            .usuarioAlta(solicitud.getUsuarioAlta())
                                                            .build();

                ETypeSolicitudAfinidadState eTypeSolicitudAfinidadState;
                if (solresume.getIdEstado() == 1 || solresume.getIdEstado() == 3) {
                    sol.setStateSolicitud(ETypeSolicitudAfinidadState.PENDING);
                } else if (solresume.getIdEstado() == 2) {
                    sol.setStateSolicitud(ETypeSolicitudAfinidadState.APPROVED);
                } else if (solresume.getIdEstado() == 4) {
                    sol.setStateSolicitud(ETypeSolicitudAfinidadState.REJECTED);
                } else {
                    eTypeSolicitudAfinidadState = ETypeSolicitudAfinidadState.ERROR;
                }
                service.create(sol);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>("Error al intentar crear la solicitud ERROR:" + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    /**
     * Llama a la función callGetSolicitudByNroSolicitud desde la clase SolicitudManagerService.
     * es un Servicio para obtener el objeto SolicitudResumenContiDTO por el parámetro nrosolicitud.
     *
     * @param nrosolicitud El número de la solicitud.
     * @return Un objeto SolicitudResumenContiDTO.
     */
    @GetMapping("/byNroSolicitud/{nrosolicitud}")
    public SolicitudResumenContiDTO GetSolicitudByNroSolicitud(@PathVariable("nrosolicitud") String nrosolicitud) {
        SolicitudResumenContiDTO dto = null;
        try {
            dto = solicitudManagerService.GetSolicitudByNroSolicitud(nrosolicitud);
        } catch (Exception e) {
            logger.error("SolicitudManagerController.callGetSolicitudByNroSolicitud.Error: {}", e.getMessage());
        }
        return dto;
    }

    /**
     * Llama al servicio para obtener la solicitud por estado.
     *
     * @param estado int
     * @return Una lista de objetos de Solicitud.
     */
    @GetMapping("/byEstado/{estado}")
    public ResponseEntity GetSolicitudByEstado(@PathVariable("estado") int estado) {
        try {
            ResponseEntity response;
            response = solicitudManagerService.GetSolicitudByEstado(estado);
            return response;
        } catch (Exception e) {
            logger.error("SolicitudManagerController.callGetSolicitudByEstado.Error: {}", e.getMessage());
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    /**
     * Un método POST que recibe un objeto JSON y devuelve un objeto JSON.
     *
     * @param dto Este es el objeto que contiene los parámetros que se envían al servicio.
     * @return Entidad de respuesta<?>
     */
    @PostMapping("/registrarSeguimiento")
    public ResponseEntity registrarSeguimiento(@RequestBody SeguimientoRequestDTO dto) {

        ResponseEntity response;
        try {
            response = solicitudManagerService.registrarSeguimiento(dto);
            return response;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity("Error al intentar registrar el seguimiento ERROR:" + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}