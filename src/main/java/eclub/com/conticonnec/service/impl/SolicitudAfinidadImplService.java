package eclub.com.conticonnec.service.impl;


import com.eclub.lib.common.services.ServiceBaseGeneric;
import eclub.com.conticonnec.domain.SolicitudAfinidad;
import eclub.com.conticonnec.dto.SolicitudAfinidadDto;
import eclub.com.conticonnec.repo.SolicitudAfinidadRepository;
import eclub.com.conticonnec.service.SolicitudAfinidadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SolicitudAfinidadImplService extends ServiceBaseGeneric<SolicitudAfinidad, SolicitudAfinidadDto> implements SolicitudAfinidadService {

    private final SolicitudAfinidadRepository repository;
    private static Logger logger = LoggerFactory.getLogger(SolicitudAfinidadImplService.class);

    public SolicitudAfinidadImplService(SolicitudAfinidadRepository repository) {
        this.repository = repository;
    }

    @Override
    public JpaRepository getRepository() { return repository; }

    @Override
    public void postConstruct(SolicitudAfinidad result) { }

    @Override
    public void preConstruct(SolicitudAfinidad entity) { }

    @Override
    public SolicitudAfinidadDto convertToDto(SolicitudAfinidad entity) {
        logger.info("convertToDto->Entity::SolicitudAfinidad {}:", entity);
        SolicitudAfinidadDto dto = modelMapper.map(entity,SolicitudAfinidadDto.class);
        logger.info("convertToDto-DTO::SolicitudAfinidadDto {}:", dto);
        return dto;
    }

    @Override
    public SolicitudAfinidad convertToEntity(SolicitudAfinidadDto dto) {
        logger.info("convertToEntity->DTO::SolicitudAfinidadDto {}:", dto);
        SolicitudAfinidad entity = modelMapper.map(dto, SolicitudAfinidad.class);
        logger.info("convertToEntity->Entity::SolicitudAfinidad {}:", entity);
        return entity;
    }

    @Override
    public void AprobarSolicitud(Long idSolicitud) throws Exception {
        Optional<SolicitudAfinidad> optional = repository.findById(idSolicitud);

        if (optional.isPresent()) {
            // solo puedo aprobar si esta en estado PENDIENTE
            if (optional.get().getStateSolicitud().ordinal() == 0) {
                repository.aprobar(optional.get().getId());
            } else {
                throw new Exception("Estado debe ser Pendiente: ");
            }
        } else {
            throw new Exception("No existe solicitud con id: " + idSolicitud);
        }
    }

    @Override
    public void RechazarSolicitud(Long idSolicitud) throws Exception {
        Optional<SolicitudAfinidad> optional = repository.findById(idSolicitud);

        if (optional.isPresent()) {
            // solo puedo rechazar si esta en estado PENDIENTE
            if (optional.get().getStateSolicitud().ordinal() == 0) {
                repository.rechazar(optional.get().getId());
            } else {
                throw new Exception("Estado debe ser Pendiente");
            }
        } else {
            throw new Exception("No existe solicitud con id: " + idSolicitud);
        }
    }

    @Override
    public SolicitudAfinidad findByNroDocumento(String NroDocumento) {
        return repository.findByNroDocumento(NroDocumento);
    }

    @Override
    public SolicitudAfinidad findByNroSolicitud(String numero) {
        return repository.findByNroSolicitud(numero);
    }

}
