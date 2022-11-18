package eclub.com.conticonnec.controller;

import eclub.com.conticonnec.domain.Seguimiento;
import eclub.com.conticonnec.service.SeguimientoService;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Es un controlador REST que expone un conjunto de endpoints para la entidad de Seguimiento.
 */
@RestController
@RequestMapping("/api/v1/conti/seguimiento")
public class SeguimientoController {

    private final SeguimientoService seguimientoService;
    public SeguimientoController(SeguimientoService seguimientoService) {
        this.seguimientoService = seguimientoService;
    }

    // Creación de un objeto registrador que se utilizará para registrar mensajes.
    final static Logger logger = LoggerFactory.getLogger(SeguimientoController.class);


    /**
     * Devuelve una lista de todos los objetos de Seguimiento en la base de datos.
     *
     * @return Una lista de objetos de seguimiento
     */
    @Operation(description = "Devuelve una lista de todos los objetos de Seguimiento en la base de datos.")
    @GetMapping("/")
    public ResponseEntity findAll() {
        try {
            ResponseEntity response;
            List<Seguimiento> list = seguimientoService.findAll();
            return ResponseEntity.ok(list);
        } catch (Exception e) {
            logger.error("SeguimientoController.findAll.Error: {}", e.getMessage());
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Devuelve un objeto de seguimiento por id.
     *
     * @param id El id del Seguimiento que se va a recuperar.
     * @return Seguimiento
     */
    @Operation(description = "Devuelve un objeto de seguimiento por id.")
    @GetMapping("/findById/{id}")
    public ResponseEntity findById(@PathVariable("id") String id) {
        try {
            ResponseEntity response;
            Seguimiento seguimiento = seguimientoService.findById(id);
            return ResponseEntity.ok(seguimiento);
        } catch (Exception e) {
            logger.error("SeguimientoController.findById.Error: {}", e.getMessage());
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Devuelve un objeto de seguimiento por su nroDocumento.
     *
     * @param nroDocumento El número del documento a buscar.
     * @return Seguimiento
     */
    @Operation(description = "Devuelve un objeto de seguimiento por su nroDocumento.")
    @GetMapping("/findByNroDocumentoAndNroSolicitud/{nroDocumento}/{nroSolicitud}")
    public ResponseEntity findByNroDocumentoAndNroSolicitud(@PathVariable("nroDocumento") String nroDocumento, @PathVariable("nroSolicitud") String nroSolicitud) {
        try {
            ResponseEntity response;
            Seguimiento seguimiento = seguimientoService.findByNroDocumentoAndNroSolicitud(nroDocumento, nroSolicitud);
            return ResponseEntity.ok(seguimiento);
        } catch (Exception e) {
            logger.error("SeguimientoController.findByNroDocumento.Error: {}", e.getMessage());
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Devuelve un objeto de seguimiento por su nroTarjeta.
     *
     * @param nroTarjeta El número de la tarjeta que desea buscar.
     * @return Seguimiento
     */
    @Operation(description = "Devuelve un objeto de seguimiento por su nroTarjeta.")
    @GetMapping("/findByNroTarjeta/{nroTarjeta}")
    public ResponseEntity findByNroTarjeta(@PathVariable("nroTarjeta") String nroTarjeta) {
        try {
            ResponseEntity response;
            Seguimiento seguimiento = seguimientoService.findByNroTarjeta(nroTarjeta);
            return ResponseEntity.ok(seguimiento);
        } catch (Exception e) {
            logger.error("SeguimientoController.findByNroTarjeta.Error: {}", e.getMessage());
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}