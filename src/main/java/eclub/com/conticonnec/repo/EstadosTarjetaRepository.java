package eclub.com.conticonnec.repo;

import eclub.com.conticonnec.domain.EstadosTarjeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// Repositorio para la clase EstadosTarjeta.
@Repository
public interface EstadosTarjetaRepository extends JpaRepository<EstadosTarjeta, Long> {

    Optional<EstadosTarjeta> findByCodigo(String codigo);
}
