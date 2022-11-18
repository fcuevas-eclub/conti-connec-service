package eclub.com.conticonnec.service.impl;


import com.eclub.lib.common.services.ServiceBaseGeneric;
import eclub.com.conticonnec.domain.Envio;
import eclub.com.conticonnec.dto.EnvioDto;
import eclub.com.conticonnec.repo.EnvioRepository;
import eclub.com.conticonnec.service.EnvioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Esta clase es una clase de servicio que implementa la interfaz
 * EnvioService. Utiliza el Repositorio de Envio para
 * acceder a la base de datos.
 */
@Service
public class EnvioImplService extends ServiceBaseGeneric<Envio, EnvioDto> implements EnvioService {

    // Un constructor que se utiliza para inyectar el repositorio en el servicio.
    private final EnvioRepository repository;
    public EnvioImplService(EnvioRepository repository) {
        this.repository = repository;
    }

    // Creación de un objeto registrador que se utilizará para registrar mensajes.
    private static Logger logger = LoggerFactory.getLogger(EnvioImplService.class);


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
     * Esta función se llama después de que se crea el objeto.
     *
     * @param result El resultado de la consulta.
     */
    @Override
    public void postConstruct(Envio result) { }


    /**
     * > Esta función se llama antes de crear la entidad
     *
     * @param entity La entidad que se va a conservar.
     */
    @Override
    public void preConstruct(Envio entity) { }

    /**
     * Convierte una entidad en un DTO.
     *
     * @param entity La entidad para convertir a un DTO.
     * @return Un objeto DTO
     */
    @Override
    public EnvioDto convertToDto(Envio entity) {
        logger.info("convertToDto->Entity::Envio {}:", entity);
        EnvioDto dto = modelMapper.map(entity, EnvioDto.class);
        logger.info("convertToDto-DTO::EnvioDto {}:", dto);
        return dto;
    }


    /**
     * Convierte un DTO en una Entidad.
     *
     * @param dto El objeto DTO para convertir en una entidad.
     * @return A EstadosTarjeta entity
     */
    @Override
    public Envio convertToEntity(EnvioDto dto) {
        logger.info("convertToEntity->DTO::EnvioDto {}:", dto);
        Envio entity = modelMapper.map(dto, Envio.class);
        logger.info("convertToEntity->Entity::Envio {}:", entity);
        return entity;
    }


    @Override
    public List<Envio> findAll() {
        return repository.findAll();
    }


    @Override
    public Envio findById(String id) throws Exception {
        String msg;
        Optional<Envio> optional = repository.findById(Long.parseLong(id));
        if (optional.isPresent()) {
            msg = "Registro encontrado {} ";
            logger.info(msg, optional.get());
            return optional.get();
        }
        msg = "No se encontro ningun Envio!";
        logger.error(msg);
        throw new Exception(msg);
    }

    @Override
    public Envio findByNroDocumento(String nroDocumento) throws Exception {
        String msg;
        Optional<Envio> optional = repository.findByNroDocumento(nroDocumento);
        if (optional.isPresent()) {
            msg = "Registro encontrado {} ";
            logger.info(msg, optional.get());
            return optional.get();
        } else {
            return null;
        }
        //msg = "No se encontro ningun Envio!";
        //logger.error(msg);
        //throw new Exception(msg);
    }

}
