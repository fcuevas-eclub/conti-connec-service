package eclub.com.conticonnec.domain;

import com.eclub.lib.common.models.entities.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import eclub.com.conticonnec.enums.ETypeSolicitudAfinidadState;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * SolicitudAfinidad es una entidad JPA que representa la tabla solicitudafinidad en la base de datos.
 */
@Entity
@Table(name = "solicitudafinidad")
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SolicitudAfinidad extends BaseEntity {

    @NotNull
    @Column(name = "fecha_alta", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAlta;

    @NotNull
    @Column(name = "nro_solicitud", nullable = false, length = 255)
    private String nroSolicitud;

    @NotNull
    @Column(name = "partner_id", nullable = false)
    private int partnerId;

    @NotNull
    @Column(name = "estado", nullable = false)
    private ETypeSolicitudAfinidadState stateSolicitud;

    @NotNull
    @Column(name = "nro_documento", nullable = false)
    private String nroDocumento;

    @NotNull
    @Column(name = "usuario_alta", nullable = false)
    private String usuarioAlta;
}
