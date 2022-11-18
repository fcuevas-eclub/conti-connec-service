package eclub.com.conticonnec.domain;

import com.eclub.lib.common.models.entities.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "seguimiento")
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Seguimiento extends BaseEntity {

    @NotNull(message = "El campo fecha_alta es obligatorio.")
    @Column(name = "fecha_alta", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAlta;

    @NotNull(message = "El campo usuario_alta es obligatorio.")
    @Column(name = "usuario_alta", nullable = false)
    private String usuarioAlta;

    @NotNull(message = "El campo nro_solicitud es obligatorio.")
    @Column(name = "nro_solicitud", nullable = false)
    private String nroSolicitud;

    @NotNull(message = "El campo nro_documento es obligatorio.")
    @Column(name = "nro_documento", nullable = false)
    private String nroDocumento;

    @NotNull(message = "El campo nro_tarjeta es obligatorio.")
    @Column(name = "nro_tarjeta", nullable = false, length = 255)
    private String nroTarjeta;

    @OneToMany(mappedBy = "seguimiento", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<SeguimientoAdjunto> adjuntos = new ArrayList<>();

}
