package eclub.com.conticonnec.scheduler;

import eclub.com.conticonnec.service.ContiCallHelper;
import eclub.com.conticonnec.service.SolicitudManagerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.SimpleDateFormat;

/**
 * Es una aplicación Spring Boot que ejecuta una tarea programada cada minuto
 */
@SpringBootApplication
@EnableScheduling
@PropertySource(value = { "classpath:application.yml" })
@EntityScan(basePackages = { "eclub.com.conticonnec.domain" })
public class RegistrarSeguimientoTask {

    private static final Logger log = LoggerFactory.getLogger(RegistrarSeguimientoTask.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Autowired
    private ContiCallHelper contiCallHelper;

    @Autowired
    private SolicitudManagerService solicitudManagerService;

    @Value("${scheduled.task.registrarSeguimientoPorLotes}")
    private String taskSeguimientoPorLotes;


    /**
     * Ejecuta la función taskRegistrarSeguimientoPorLotes() cada minuto.
     */
    @Scheduled(cron = "${scheduled.task.registrarSeguimientoPorLotes}")
    public void runTaskRegistrarSeguimientoPorLotes() throws Exception {
        taskRegistrarSeguimientoPorLotes();
    }

    /**
     * Es una tarea programada que se ejecuta cada 1 minuto y verifica si hay archivos en una carpeta. Si los hay, los
     * procesa y los elimina. Si no hay, no hace nada.
     */
    private void taskRegistrarSeguimientoPorLotes() {
        log.info("Inicia la tarea programada taskRegistrarSeguimientoPorLotes");

        try {
            ResponseEntity<String> response = solicitudManagerService.registrarSeguimientoPorLotes();

            if ("success".equals(response.getBody())) {
                log.info("SE VACIO CORRECTAMENTE LA CARPETA DE SEGUIMIENTOS PENDIENTES POR LOTE!");
            } else {
                log.info("LA CARPETA DE SEGUIMIENTOS PENDIENTES POR LOTE NO TIENE ARCHIVOS PARA PROCESAR.");
            }
        } catch(Exception e) {
            log.error("registrarSeguimientoPorLotes() -> Ha ocurrido un error inesperado e: " + e);
        }
    }

}
