package eclub.com.conticonnec.repo;

import eclub.com.conticonnec.domain.SeguimientoAdjunto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeguimientoAdjuntoRepository extends JpaRepository<SeguimientoAdjunto, Long> {
}
