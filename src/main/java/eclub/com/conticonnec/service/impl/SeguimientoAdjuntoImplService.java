package eclub.com.conticonnec.service.impl;


import com.eclub.lib.common.services.ServiceBaseGeneric;
import eclub.com.conticonnec.domain.SeguimientoAdjunto;
import eclub.com.conticonnec.dto.SeguimientoAdjuntoDTO;
import eclub.com.conticonnec.repo.SeguimientoAdjuntoRepository;
import eclub.com.conticonnec.repo.SeguimientoRepository;
import eclub.com.conticonnec.service.SeguimientoAdjuntoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;


@Service
public class SeguimientoAdjuntoImplService extends ServiceBaseGeneric<SeguimientoAdjunto> implements SeguimientoAdjuntoService {

    private final SeguimientoRepository repository;
    private final SeguimientoAdjuntoRepository adjuntoRepository;
    public SeguimientoAdjuntoImplService(SeguimientoRepository repository, SeguimientoAdjuntoRepository adjuntoRepository) {
        this.repository = repository;
        this.adjuntoRepository = adjuntoRepository;
    }

    // Creación de un objeto registrador para registrar mensajes.
    private static Logger logger = LoggerFactory.getLogger(SeguimientoAdjuntoImplService.class);


    /**
     * > Esta función devuelve el repositorio que se utiliza para acceder a la base de datos
     *
     * @return el repositorio
     */
    @Override
    public JpaRepository getRepository() {
        return repository;
    }


    /**
     * > La función `postConstruct` se llama después de construir la entidad y guardarla en la base de datos
     *
     * @param result La entidad que se acaba de crear.
     */
    @Override
    public void postConstruct(SeguimientoAdjunto result) { }

    /**
     * > Esta función se llama antes de que se construya la entidad
     *
     * @param entity La entidad que se va a conservar.
     */
    @Override
    public void preConstruct(SeguimientoAdjunto entity) { }

    /**
     * Convierte una entidad en un DTO.
     *
     * @param entity La entidad para convertir a un DTO.
     * @return A SeguimientoDto object
     */
    @Override
    public SeguimientoAdjuntoDTO convertToDto(SeguimientoAdjunto entity) {
        logger.info("convertToDto->Entity::SeguimientoAdjunto {}:", entity);
        SeguimientoAdjuntoDTO dto = modelMapper.map(entity, SeguimientoAdjuntoDTO.class);
        logger.info("convertToDto-DTO::SeguimientoAdjuntoDto {}:", dto);
        return dto;
    }


    /**
     * Convierte un DTO en una Entidad.
     *
     * @param dto El objeto DTO para convertir en una entidad.
     * @return A Seguimiento entity
     */
    @Override
    public SeguimientoAdjunto convertToEntity(Object dto) {
        logger.info("convertToEntity->DTO::SeguimientoAdjuntoDto {}:", (SeguimientoAdjuntoDTO)dto);
        SeguimientoAdjunto entity = modelMapper.map((SeguimientoAdjuntoDTO)dto, SeguimientoAdjunto.class);
        logger.info("convertToEntity->Entity::SeguimientoAdjunto {}:", entity);
        return entity;
    }

}
