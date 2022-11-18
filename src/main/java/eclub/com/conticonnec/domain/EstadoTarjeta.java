package eclub.com.conticonnec.domain;

import com.eclub.lib.common.models.entities.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * EstadosTarjeta es una entidad JPA que representa la tabla estadostarjeta en la base de datos.
 */
@Entity
@Table(name = "estadostarjeta")
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EstadoTarjeta extends BaseEntity {

    @NotNull(message = "El campo codigo es obligatorio.")
    @Column(nullable = false)
    private String codigo;

    @NotNull(message = "El campo descripcion es obligatorio.")
    @Column( nullable = false, length = 255)
    private String descripcion;

}
