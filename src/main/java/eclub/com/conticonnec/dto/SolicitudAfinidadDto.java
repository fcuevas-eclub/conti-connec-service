package eclub.com.conticonnec.dto;

import com.eclub.lib.common.models.dto.BaseDto;
import eclub.com.conticonnec.enums.EstadoSolicitudAfinidad;
import lombok.*;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SolicitudAfinidadDto extends BaseDto {

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAlta;

    private String nroSolicitud;

    private int partnerId;

    private EstadoSolicitudAfinidad stateSolicitud;

    @NotBlank(message = "El campo nroDocumento es obligatorio.")
    @NotEmpty(message = "El campo nroDocumento es obligatorio.")
    @NotNull(message = "El campo nroDocumento es obligatorio.")
    @Size(max = 15)
    private String nroDocumento;

    private String usuarioAlta;
}
