package eclub.com.conticonnec.repo;

import eclub.com.conticonnec.domain.SolicitudAfinidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface SolicitudAfinidadRepository extends JpaRepository<SolicitudAfinidad, Long> {

    @Modifying
    @Query(value = "UPDATE SolicitudAfinidad "
            + "set estado = 1 "
            + "where id = :id"
            , nativeQuery = true)
    void aprobar(@Param("id") Long id);

    @Modifying
    @Query(value = "UPDATE SolicitudAfinidad "
            + "set estado = 2 "
            + "where id = :id"
            , nativeQuery = true)
    void rechazar(@Param("id") Long id);

    SolicitudAfinidad findByNroDocumento(String nroDocumento);

    SolicitudAfinidad findByNroSolicitud(String nrosolicitud);
}
