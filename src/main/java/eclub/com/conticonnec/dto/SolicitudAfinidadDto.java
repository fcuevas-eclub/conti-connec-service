package eclub.com.conticonnec.dto;

import com.eclub.lib.common.models.dto.BaseDto;
import eclub.com.conticonnec.enums.ETypeSolicitudAfinidadState;
import lombok.*;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SolicitudAfinidadDto extends BaseDto {

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAlta;
    private String nroSolicitud;
    private int partnerId;
    private ETypeSolicitudAfinidadState stateSolicitud;

    @NotBlank
    @NotEmpty
    @Size(max = 15)
    private String nroDocumento;
    private String usuarioAlta;
}
