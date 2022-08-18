package eclub.com.conticonnec.domain;

import com.eclub.lib.common.models.entities.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;


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

    @NotNull
    @Column(name = "fecha_alta", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAlta;

    @NotNull
    @Column(name = "usuario_alta", nullable = false)
    private String usuarioAlta;

    @NotNull
    @Column(name = "nro_solicitud", nullable = false)
    private String nroSolicitud;

    @NotNull
    @Column(name = "nro_documento", nullable = false)
    private String nroDocumento;

    @NotNull
    @Column(name = "nro_tarjeta", nullable = false, length = 255)
    private String nroTarjeta;

    @OneToMany(mappedBy = "seguimiento", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Collection<SeguimientoAdjunto> adjuntos = new ArrayList<>();

}
