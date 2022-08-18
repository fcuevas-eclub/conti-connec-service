package eclub.com.conticonnec.service.impl;


import com.eclub.lib.common.services.ServiceBaseGeneric;
import eclub.com.conticonnec.domain.EstadosTarjeta;
import eclub.com.conticonnec.dto.EstadosTarjetaDto;
import eclub.com.conticonnec.repo.EstadosTarjetaRepository;
import eclub.com.conticonnec.service.EstadosTarjetaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Esta clase es una clase de servicio que implementa la interfaz
 * EstadosTarjetaService. Utiliza el Repositorio de EstadosTarjeta para
 * acceder a la base de datos.
 */
@Service
public class EstadosTarjetaImplService extends ServiceBaseGeneric<EstadosTarjeta> implements EstadosTarjetaService {

    // Un constructor que se utiliza para inyectar el repositorio en el servicio.
    private final EstadosTarjetaRepository repository;
    public EstadosTarjetaImplService(EstadosTarjetaRepository repository) {
        this.repository = repository;
    }

    // Creación de un objeto registrador que se utilizará para registrar mensajes.
    private static Logger logger = LoggerFactory.getLogger(EstadosTarjetaImplService.class);


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
    public void postConstruct(EstadosTarjeta result) { }


    /**
     * > Esta función se llama antes de crear la entidad
     *
     * @param entity La entidad que se va a conservar.
     */
    @Override
    public void preConstruct(EstadosTarjeta entity) { }

    /**
     * Convierte una entidad en un DTO.
     *
     * @param entity La entidad para convertir a un DTO.
     * @return Un objeto DTO
     */
    @Override
    public EstadosTarjetaDto convertToDto(EstadosTarjeta entity) {
        logger.info("convertToDto->Entity::EstadosTarjeta {}:", entity);
        EstadosTarjetaDto dto = modelMapper.map(entity, EstadosTarjetaDto.class);
        logger.info("convertToDto-DTO::EstadosTarjetaDto {}:", dto);
        return dto;
    }

    /**
     * Convierte un DTO en una Entidad.
     *
     * @param dto El objeto DTO para convertir en una entidad.
     * @return A EstadosTarjeta entity
     */
    @Override
    public EstadosTarjeta convertToEntity(Object dto) {
        logger.info("convertToEntity->DTO::EstadosTarjetaDto {}:", (EstadosTarjetaDto)dto);
        EstadosTarjeta entity = modelMapper.map((EstadosTarjetaDto)dto, EstadosTarjeta.class);
        logger.info("convertToEntity->Entity::EstadosTarjeta {}:", entity);
        return entity;
    }

    @Override
    public List<EstadosTarjeta> findAll() {
        return repository.findAll();
    }

    @Override
    public EstadosTarjeta findById(String id) throws Exception {
        String msg;
        Optional<EstadosTarjeta> optional = repository.findById(Long.parseLong(id));
        if (optional.isPresent()) {
            msg = "Registro encontrado {} ";
            logger.info(msg, optional.get());
            return optional.get();
        }
        msg = "No se encontro ningun EstadoTarjeta!";
        logger.error(msg);
        throw new Exception(msg);
    }

    @Override
    public EstadosTarjeta findByCodigo(String codigo) throws Exception {
        String msg;
        Optional<EstadosTarjeta> optional = repository.findByCodigo(codigo);
        if (optional.isPresent()) {
            msg = "Registro encontrado {} ";
            logger.info(msg, optional.get());
            return optional.get();
        }
        msg = "No se encontro ningun EstadoTarjeta!";
        logger.error(msg);
        throw new Exception(msg);
    }

}
