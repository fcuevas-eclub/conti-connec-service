package eclub.com.conticonnec.service.impl;


import com.eclub.lib.common.services.ServiceBaseGeneric;
import eclub.com.conticonnec.domain.Seguimiento;
import eclub.com.conticonnec.dto.SeguimientoDto;
import eclub.com.conticonnec.repo.SeguimientoRepository;
import eclub.com.conticonnec.service.SeguimientoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class SeguimientoImplService extends ServiceBaseGeneric<Seguimiento, SeguimientoDto> implements SeguimientoService {

    private final SeguimientoRepository repository;
    public SeguimientoImplService(SeguimientoRepository repository) {
        this.repository = repository;
    }

    // Creación de un objeto registrador para registrar mensajes.
    private static Logger logger = LoggerFactory.getLogger(SeguimientoImplService.class);

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
    public void postConstruct(Seguimiento result) {
       // adjuntoRepository.saveAllAndFlush(result.getAdjuntos());
    }

    /**
     * > Esta función se llama antes de que se construya la entidad
     *
     * @param entity La entidad que se va a conservar.
     */
    @Override
    public void preConstruct(Seguimiento entity) {}
    /**
     * Convierte una entidad en un DTO.
     *
     * @param entity La entidad para convertir a un DTO.
     * @return A SeguimientoDto object
     */
    @Override
    public SeguimientoDto convertToDto(Seguimiento entity) {
        logger.info("convertToDto->Entity::Seguimiento {}:", entity);
        SeguimientoDto dto = modelMapper.map(entity, SeguimientoDto.class);
        logger.info("convertToDto-DTO::SeguimientoDto {}:", dto);
        return dto;
    }


    /**
     * Convierte un DTO en una Entidad.
     *
     * @param dto El objeto DTO para convertir en una entidad.
     * @return A Seguimiento entity
     */
    @Override
    public Seguimiento convertToEntity(SeguimientoDto dto) {
        logger.info("convertToEntity->DTO::SeguimientoDto {}:", dto);
        Seguimiento entity = modelMapper.map(dto, Seguimiento.class);
        logger.info("convertToEntity->Entity::Seguimiento {}:", entity);
        return entity;
    }

    @Override
    public List<Seguimiento> findAll() {
        return repository.findAll();
    }

    @Override
    public Seguimiento findById(String id) throws Exception {
        String msg;
        Optional<Seguimiento> optional = repository.findById(Long.parseLong(id));
        if (optional.isPresent()) {
            msg = "Registro encontrado {} ";
            logger.info(msg, optional.get());
            return optional.get();
        }
        msg = "No se encontro ningun seguimiento!";
        logger.error(msg);
        throw new Exception(msg);
    }



    @Override
    public Seguimiento findByNroDocumentoAndNroSolicitud(String nroDocumento, String nroSolicitud) throws Exception {
        String msg;
        Optional<Seguimiento> optional = repository.findByNroDocumentoAndNroSolicitud(nroDocumento, nroSolicitud);
        if (optional.isPresent()){
            msg = "Registro encontrado {} ";
            logger.info(msg, optional.get());
            return optional.get();
        }
        msg  = "No se encontro ningun seguimiento!";
        logger.error(msg);
        throw new Exception(msg);
    }

    @Override
    public Seguimiento findByNroTarjeta(String nroTarjeta) throws Exception {
        String msg;
        Optional<Seguimiento> optional = repository.findByNroTarjeta(nroTarjeta);
        if (optional.isPresent()) {
            msg = "Registro encontrado {} ";
            logger.info(msg, optional.get());
            return optional.get();
        }
        msg = "No se encontro ningun seguimiento!";
        logger.error(msg);
        throw new Exception(msg);
    }

}
