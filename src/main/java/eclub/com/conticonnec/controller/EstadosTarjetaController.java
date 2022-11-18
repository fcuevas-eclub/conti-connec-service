package eclub.com.conticonnec.controller;

import eclub.com.conticonnec.domain.EstadoTarjeta;
import eclub.com.conticonnec.service.EstadosTarjetaService;
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
 * Es un controlador REST que proporciona un conjunto de enpoints
 * para la entidad EstadosTarjeta
 */
@RestController
@RequestMapping("/api/v1/conti/estadosTarjeta")
public class EstadosTarjetaController {

    // Una variable privada que se está utilizando para almacenar el objeto EstadosTarjetaService.
    private final EstadosTarjetaService estadosTarjetaService;

    public EstadosTarjetaController(EstadosTarjetaService estadosTarjetaService) {
        this.estadosTarjetaService = estadosTarjetaService;
    }

    // Creación de un objeto registrador que se utilizará para registrar mensajes.
    final static Logger logger = LoggerFactory.getLogger(EstadosTarjetaController.class);

    @Operation(description = "Un método que devuelve una lista de objetos EstadoTarjeta.")
    @GetMapping("/")
    public ResponseEntity findAll() {
        try {
            ResponseEntity response;
            List<EstadoTarjeta> list = estadosTarjetaService.findAll();
            return ResponseEntity.ok(list);
        } catch (Exception e) {
            logger.error("EstadosTarjetaController.findAll.Error: {}", e.getMessage());
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Devuelve el objeto EstadosTarjeta con el id dado.
     *
     * @param id El id de los EstadosTarjeta a recuperar.
     * @return ResponseEntity
     */
    @Operation(description = "Devuelve el objeto EstadosTarjeta con el id dado.")
    @GetMapping("/findById/{id}")
    public ResponseEntity findById(@PathVariable("id") String id) {
        try {
            ResponseEntity response;
            EstadoTarjeta estadosTarjeta = estadosTarjetaService.findById(id);
            return ResponseEntity.ok(estadosTarjeta);
        } catch (Exception e) {
            logger.error("EstadosTarjetaController.findById.Error: {}", e.getMessage());
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Devuelve el objeto EstadosTarjeta con el código dado.
     *
     * @param codigo String
     * @return ResponseEntity
     */
    @Operation(description = "Devuelve el objeto EstadosTarjeta con el código dado.")
    @GetMapping("/findByCodigo/{codigo}")
    public ResponseEntity findByCodigo(@PathVariable("codigo") String codigo) {
        try {
            ResponseEntity response;
            EstadoTarjeta estadosTarjeta = estadosTarjetaService.findByCodigo(codigo);
            return ResponseEntity.ok(estadosTarjeta);
        } catch (Exception e) {
            logger.error("EstadosTarjetaController.findByCodigo.Error: {}", e.getMessage());
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}