package eclub.com.conticonnec.repo;

import eclub.com.conticonnec.domain.Envio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EnvioRepository extends JpaRepository<Envio, Long> {

    Optional<Envio> findByNroDocumento(String nroDocumento);
}
