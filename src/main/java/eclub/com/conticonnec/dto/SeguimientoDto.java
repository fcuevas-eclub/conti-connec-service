package eclub.com.conticonnec.dto;

import com.eclub.lib.common.models.dto.BaseDto;
import lombok.*;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SeguimientoDto extends BaseDto {

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAlta;

    private String usuarioAlta;

    @NotBlank(message = "El campo nroSolicitud es obligatorio.")
    @NotEmpty(message = "El campo nroSolicitud es obligatorio.")
    @NotNull(message = "El campo nroSolicitud es obligatorio.")
    private String nroSolicitud;

    @NotBlank(message = "El campo nroDocumento es obligatorio.")
    @NotEmpty(message = "El campo nroDocumento es obligatorio.")
    @NotNull(message = "El campo nroDocumento es obligatorio.")
    @Size(max = 15)
    private String nroDocumento;

    private String nroTarjeta;

    private String tipoDocumento;

    private String estadoTarjeta;

    private String observacion;

    private String usuarioId = "TAR";

    private String tipoTarjeta;

    private List<SeguimientoAdjuntoDTO> adjunto = new ArrayList<>();
}
