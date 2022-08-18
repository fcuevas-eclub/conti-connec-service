package eclub.com.conticonnec.repo;

import eclub.com.conticonnec.domain.Seguimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface SeguimientoRepository extends JpaRepository<Seguimiento, Long> {

    Optional<Seguimiento> findByNroDocumentoAndNroSolicitud(String nroDocumento, String nroSolicitud);

    Optional<Seguimiento> findByNroTarjeta(String nroTarjeta);
}
