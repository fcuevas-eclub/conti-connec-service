package eclub.com.conticonnec.dto;

import lombok.*;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SolicitudResumenContiDTO {

    private String idSolicitud;

    @NotBlank
    @NotEmpty
    @Size(max = 15)
    private String documento;

    private int idEstado;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCarga;
}
