package eclub.com.conticonnec.domain;

import com.eclub.lib.common.models.entities.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import eclub.com.conticonnec.enums.EstadoSolicitudAfinidad;
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
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SolicitudAfinidad extends BaseEntity {

    @NotNull(message = "El campo fecha_alta es obligatorio.")
    @Column(name = "fecha_alta", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAlta;

    @NotNull(message = "El campo nro_solicitud es obligatorio.")
    @Column(name = "nro_solicitud", nullable = false, length = 255)
    private String nroSolicitud;

    @NotNull(message = "El campo partner_id es obligatorio.")
    @Column(name = "partner_id", nullable = false)
    private int partnerId;

    @NotNull(message = "El campo estado es obligatorio.")
    @Column(name = "estado", nullable = false)
    private EstadoSolicitudAfinidad stateSolicitud;

    @NotNull(message = "El campo nro_documento es obligatorio.")
    @Column(name = "nro_documento", nullable = false)
    private String nroDocumento;

    @NotNull(message = "El campo usuario_alta es obligatorio.")
    @Column(name = "usuario_alta", nullable = false)
    private String usuarioAlta;
}
