package eclub.com.conticonnec.domain;

import com.eclub.lib.common.models.entities.BaseEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "seguimiento_adjunto")
@Getter
@Setter
//@ToString
@Builder
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class SeguimientoAdjunto extends BaseEntity {
    @Column(name = "tipo_documento", nullable = false)
    private Integer tipoDocumento;

    @Column(nullable = false)
    private String archivo;

    @ManyToOne
    @JoinColumn(name = "seguimiento_id", nullable = false)
    @JsonBackReference
    private Seguimiento seguimiento;

}
