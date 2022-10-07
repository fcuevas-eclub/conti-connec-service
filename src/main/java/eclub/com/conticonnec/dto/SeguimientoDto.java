package eclub.com.conticonnec.dto;

import com.eclub.lib.common.models.dto.BaseDto;
import lombok.*;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SeguimientoDto extends BaseDto {

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAlta;

    private String usuarioAlta;

    @NotBlank
    @NotEmpty
    private String nroSolicitud;

    @NotBlank
    @NotEmpty
    @Size(max = 15)
    private String nroDocumento;

    private String nroTarjeta;

    private String tipoDocumento;

    //private Collection<SeguimientoAdjuntoDTO> adjuntos = new ArrayList<>();
    //private Collection<SeguimientoAdjuntoDTO> adjunto = new ArrayList<>();
    private List<SeguimientoAdjuntoDTO> adjunto = new ArrayList<>();
}
