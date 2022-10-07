package eclub.com.conticonnec.service.impl;

import com.eclub.lib.common.models.enums.Status;
import eclub.com.conticonnec.domain.Seguimiento;
import eclub.com.conticonnec.domain.SeguimientoAdjunto;
import eclub.com.conticonnec.dto.SeguimientoAdjuntoDTO;
import eclub.com.conticonnec.dto.SeguimientoRequestDTO;
import eclub.com.conticonnec.dto.SolicitudContiDTO;
import eclub.com.conticonnec.dto.SolicitudResumenContiDTO;
import eclub.com.conticonnec.service.ContiCallHelper;
import eclub.com.conticonnec.service.SeguimientoService;
import eclub.com.conticonnec.service.SolicitudManagerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Transactional
@Service
public class SolicitudManagerImplService implements SolicitudManagerService {

    private final ContiCallHelper contiCallHelper;
    private final SeguimientoService seguimientoService;

    public SolicitudManagerImplService(ContiCallHelper contiCallHelper, SeguimientoService seguimientoService) {
        this.contiCallHelper = contiCallHelper;
        this.seguimientoService = seguimientoService;
    }


    // Creación de un objeto registrador.
    private static Logger logger = LoggerFactory.getLogger(SolicitudManagerImplService.class);

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
        logger.info("SolicitudManagerImplService.registrarSeguimiento.mask: {}", mask);
        dto.setNumeroTarjeta(mask);

        ResponseEntity response = contiCallHelper.registrarSeguimiento(dto);
        //ResponseEntity response;

        if (response.getBody() == null) {
            throw new Exception("Error al procesar la peticion realizada.");
        }

        List<SeguimientoAdjunto> adjuntos = new ArrayList<>();

        Seguimiento seguimiento = Seguimiento.builder()
                                            .fechaAlta(new Date())
                                            .usuarioAlta(dto.getUsuarioId())
                                            .nroDocumento(dto.getNumeroDocumento())
                                            .nroTarjeta(dto.getNumeroTarjeta())
                                            .nroSolicitud("123") //PREGUNTAR QUE VALOR SE DEBE SETEAR...
                                        .build();

        //Setear datos para el seguimiento_adjunto.
        for (SeguimientoAdjuntoDTO s_a:dto.getAdjunto()) {
            SeguimientoAdjunto a = new SeguimientoAdjunto();
            a.setStatus(Status.ACTIVE);
            a.setTipoDocumento(s_a.getTipoDocumento());
            a.setArchivo(s_a.getArchivo());
            a.setSeguimiento(seguimiento);
            adjuntos.add(a);
        }

        seguimiento.setAdjunto(adjuntos);

        Seguimiento result = seguimientoService.create(seguimiento);

        return new ResponseEntity(result, HttpStatus.OK);
    }

    private String applyMask(String cardNumberOriginal) throws Exception {
        //validar que sea un nro de tarjeta valido
        if (cardNumberOriginal.length() == 16) {
            String cardNumberMask;
            System.out.println(cardNumberOriginal);
            String maskedCard = cardNumberOriginal.substring(0, 4) + "-XXXX-XXXX-" + cardNumberOriginal.substring(12, 16);
            System.out.println(maskedCard);
            return maskedCard;
        } else {
            throw new Exception("El nro de tarjeta no es valido, largo no es 16 caracteres "+ cardNumberOriginal);
        }
    }

}
