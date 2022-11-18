package eclub.com.conticonnec.domain;

import com.eclub.lib.common.models.entities.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "envio")
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Envio extends BaseEntity {

    @NotNull
    @Column(nullable = false, length = 255)
    private String archivo;

    @NotNull
    @Column(nullable = false, length = 255)
    private String estado;

    @NotNull
    @Column(name = "nro_documento", nullable = false, length = 255)
    private String nroDocumento;

    @Column(name = "fecha_creacion", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;

}
