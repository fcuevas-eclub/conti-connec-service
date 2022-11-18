package eclub.com.conticonnec.repo;

import eclub.com.conticonnec.domain.EstadoTarjeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EstadosTarjetaRepository extends JpaRepository<EstadoTarjeta, Long> {

    Optional<EstadoTarjeta> findByCodigo(String codigo);
}
