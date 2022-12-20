package eclub.com.conticonnec.service.impl;

import com.eclub.lib.common.models.enums.Status;
import eclub.com.conticonnec.domain.Envio;
import eclub.com.conticonnec.domain.Seguimiento;
import eclub.com.conticonnec.domain.SeguimientoAdjunto;
import eclub.com.conticonnec.dto.*;
import eclub.com.conticonnec.service.ContiCallHelper;
import eclub.com.conticonnec.service.EnvioService;
import eclub.com.conticonnec.service.SeguimientoService;
import eclub.com.conticonnec.service.SolicitudManagerService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Transactional
@Service
@Slf4j
public class SolicitudManagerImplService implements SolicitudManagerService {

    private final ContiCallHelper contiCallHelper;
    private final SeguimientoService seguimientoService;
    private final EnvioService envioService;

    @Autowired
    public SolicitudManagerImplService(ContiCallHelper contiCallHelper,
                                       SeguimientoService seguimientoService,
                                       EnvioService envioService) {
        this.contiCallHelper = contiCallHelper;
        this.seguimientoService = seguimientoService;
        this.envioService = envioService;
    }

    @Value("${resources.files.pendientes}")
    private String directorioPendientes;

    @Value("${resources.files.procesados}")
    private String directorioProcesados;


    @Override
    public String extraeNombres(String nombres, boolean extraePrimerNombre) {
        String retorno = "";
        String[] names = nombres.split(" ");

        if (!(nombres.length() > 0))
            return retorno;

        if (extraePrimerNombre) {
            retorno = names[0];
        } else {
            for (int i = 0; i < names.length; i++) {
                if (i > 0) {
                    if (i == names.length) {
                        retorno += names[i];
                    } else {
                        retorno += names[i] + " ";
                    }
                }
            }
        }

        return retorno;
    }

    private String applyMask(String cardNumberOriginal) throws Exception {
        //validar que sea un numero de tarjeta valido.
        if (cardNumberOriginal.length() == 16) {
            String cardNumberMask;
            String maskedCard = cardNumberOriginal.substring(0, 4) + "-XXXX-XXXX-" + cardNumberOriginal.substring(12, 16);

            return maskedCard;
        } else {
            throw new Exception("El nro de tarjeta no es valido, largo no es 16 caracteres " + cardNumberOriginal);
        }
    }


    public String ImageToBase64Convert(String filePath) throws Exception {
        File f = new File(filePath);
        FileInputStream fis = new FileInputStream(f);
        byte byteArray[] = new byte[(int) f.length()];
        fis.read(byteArray);
        String imageString = Base64.encodeBase64String(byteArray);
        fis.close();

        return imageString;
    }


    /**
     * Envía una solicitud a la clase ContiCallHelper.
     *
     * @param solicitudContiDTO Este es el objeto que contiene los datos que se enviarán a la API.
     * @return Entidad de respuesta
     */
    @Override
    public ResponseEntity sendSolicitud(SolicitudContiDTO solicitudContiDTO) throws Exception {
        ResponseEntity response = contiCallHelper.sendSolicitud(solicitudContiDTO);
        return response;
    }

    @Override
    public SolicitudResumenContiDTO GetSolicitudByNroSolicitud(String nrosolicitud) throws Exception {
        SolicitudResumenContiDTO response = contiCallHelper.GetSolicitudByNroSolicitud(nrosolicitud);
        return response;
    }

    @Override
    public ResponseEntity GetSolicitudByEstado(int estado) throws Exception {
        ResponseEntity response = contiCallHelper.GetSolicitudByEstado(estado);
        return response;
    }

    @Override
    public ResponseEntity registrarSeguimiento(SeguimientoRequestDTO dto) throws Exception {
        String mask = applyMask(dto.getNumeroTarjeta());//aplica formato valido al numero de tarjeta.
        dto.setNumeroTarjeta(mask);

        ResponseEntity response = contiCallHelper.registrarSeguimiento(dto);

        if (response.getBody() == null) {
            throw new Exception("Error al procesar la peticion realizada.");
        }

        List<SeguimientoAdjunto> adjuntos = new ArrayList<>();

        Seguimiento seguimiento = Seguimiento.builder()
                .fechaAlta(new Date())
                .usuarioAlta(dto.getUsuarioId())
                .nroDocumento(dto.getNumeroDocumento())
                .nroTarjeta(dto.getNumeroTarjeta())
                .nroSolicitud("123") //DATO DE EJEMPLO, SE DEBE PREGUNTAR QUE VALOR SE DEBE SETEAR AQUI...
                .build();

        //Setear datos para el seguimiento_adjunto.
        for (SeguimientoAdjuntoDTO s_a : dto.getAdjunto()) {
            SeguimientoAdjunto a = new SeguimientoAdjunto();
            a.setStatus(Status.ACTIVE);
            a.setTipoDocumento(s_a.getTipoDocumento());
            a.setArchivo(s_a.getArchivo());
            a.setSeguimiento(seguimiento);
            adjuntos.add(a);
        }

        seguimiento.setAdjuntos(adjuntos);

        Seguimiento result = seguimientoService.create(seguimiento);

        return new ResponseEntity(result, HttpStatus.OK);
    }


    @Override
    public ResponseEntity<String> registrarSeguimientoPorLotes() throws Exception {
        log.info("Ejecutando el Service registrarSeguimientoPorLotes");

        String carpetaPendientes = directorioPendientes;
        String carpetaProcesados = directorioProcesados;

        File pendientes = new File(carpetaPendientes);
        File[] archivos = pendientes.listFiles();
        ResponseEntity<SeguimientoPorLotesResponseDTO> response = null;
        int totalArchivos = archivos.length;
        int contadorArchivosProcesados = 0;

        //PASO 1
        //Lectura de archivos de la carpeta "Pendientes".
        if (archivos != null && totalArchivos > 0) { //Se inicia el proceso solo si hay archivos dentro de la carpeta Pendientes...
            for (int i = 0; i < totalArchivos; i++) {
                File archivoActual = archivos[i];

                //PASO 2
                //Obtener Numero de Cedula por cada iteraccion de Clientes.
                //Ejemplo de Nombre de Archivos: [2386592 - JUAN PEREZ.pdf]
                String fileName = archivoActual.getName(); //Nombre original del archivo+extension.
                String nroDocumento = fileName.substring(0, fileName.lastIndexOf('.')); //Cortar la extension del Nombre original.
                nroDocumento = nroDocumento.substring(0, nroDocumento.indexOf(' ')); //Cortar la primera parte de la cadena o String que corresponde al nroCedula.

                //PASO 3
                //Buscar en el MS-QUERYUSER al Cliente por Numero de cedula.
                AccountInfoResponseDTO dto = contiCallHelper.getAccountData(nroDocumento);

                if (dto == null) {
                    throw new Exception("No existe el cliente con número de cedula: " + nroDocumento);
                } else {
                    //PASO 4
                    //Buscar Cliente en la BD "eclub_conti".
                    Envio envioActual = envioService.findByNroDocumento(nroDocumento);
                    Envio envio;
                    Envio result;
                    if (envioActual == null) {
                        //Insertar en la tabla Envio con estado='Pendiente'.
                        envio = Envio.builder()
                                .archivo(fileName)
                                .estado("Pendiente")
                                .nroDocumento(nroDocumento)
                                .fechaCreacion(new Date())
                                .build();
                        result = envioService.create(envio);
                    } else {
                        //Ya existe el cliente entonces asignar a la variable "result"
                        //para evitar duplicidad de registros en la BD.
                        result = envioActual;
                    }

                    if (result == null) {
                        throw new Exception("No se pudo insertar/actualizar el Envio en la Base de Datos.");
                    } else {
                        //PASO 5
                        //Envio al API del Banco.

                        //Setear DTO
                        SeguimientoPorLotesRequestDTO seguimientoPorLotesRequestDTO = new SeguimientoPorLotesRequestDTO();
                        String mask = applyMask(dto.getNroTarjeta());//aplica formato valido al numero de tarjeta.
                        seguimientoPorLotesRequestDTO.setNumeroTarjeta(mask);
                        seguimientoPorLotesRequestDTO.setNumeroDocumento(nroDocumento);
                        seguimientoPorLotesRequestDTO.setEstadoTarjeta("06");
                        seguimientoPorLotesRequestDTO.setObservacion("");
                        seguimientoPorLotesRequestDTO.setTipoTarjeta("C");
                        //Setear DTO Adjunto.
                        List<SeguimientoPorLotesAdjuntoDTO> adjunto = new ArrayList<SeguimientoPorLotesAdjuntoDTO>();
                        SeguimientoPorLotesAdjuntoDTO seguimientoPorLotesAdjuntoDTO = new SeguimientoPorLotesAdjuntoDTO();
                        seguimientoPorLotesAdjuntoDTO.setStatus(Status.ACTIVE);
                        seguimientoPorLotesAdjuntoDTO.setTipoDocumento(2);
                        seguimientoPorLotesAdjuntoDTO.setArchivo(ImageToBase64Convert(archivoActual.getAbsolutePath()));
                        adjunto.add(seguimientoPorLotesAdjuntoDTO);

                        //Setear el DTO Adjunto al SeguimientoPorLotesDTO
                        seguimientoPorLotesRequestDTO.setAdjunto(adjunto);

                        //Invocar al "Service" que realiza la peticion al Banco, pasando como parametro el DTO seteado.
                        response = contiCallHelper.registrarSeguimientoPorLote(seguimientoPorLotesRequestDTO);
                        if (response.getBody() == null) {
                            throw new Exception("Error en el Service-SolicituManagerImplService al enviar la " +
                                    "peticion al Banco Continental, Cliente con cédula número: " + nroDocumento);
                        } else {
                            //PASO 6
                            //Mover archivo a la carpeta de "Procesados".
                            //Cambiar el "estado" del registro en la tabla Envio, [estado='Procesado'].
                            contadorArchivosProcesados++;
                            archivoActual.renameTo(new File(carpetaProcesados + "\\" + fileName));
                            archivoActual.delete();

                            result.setEstado("Procesado");
                            envioService.update(result);
                        }
                    }
                }
            }

            //Sino hubo ningun error durante el proceso, retornar: ['success', ok].
            //Mostrar en el 'log' a modo de que quede un registro del proceso.
            log.info("tarea programada en fecha: {}, total de archivos: {}, conti respondio: {}, archivos movidos " +
                            "a la carpeta 'procesados': {}", new Date(),
                                                                totalArchivos,
                                                                response.getBody().getMensaje(),
                                                                contadorArchivosProcesados);
            return new ResponseEntity<>("success", HttpStatus.OK);
        } else {
            //Como no hay errores se retorna igual una "informacion" para
            // logguear de que no existen archivos para procesar en la carpeta.
            return new ResponseEntity<>("info", HttpStatus.OK);
        }
    }

}
