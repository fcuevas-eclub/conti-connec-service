package eclub.com.conticonnec.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.builder.ToStringExclude;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@ToString
public class SeguimientoRequestDTO {
    @NotNull
    private String numeroTarjeta;

    @NotNull
    private String numeroDocumento;

    @NotNull
    private String estadoTarjeta;

    @NotNull
    @Size(max = 160)
    private String observacion;

    private String usuarioId="TAR";

    @NotNull
    private String tipoTarjeta;

    @ToStringExclude
    private Collection<SeguimientoAdjuntoDTO> adjunto = new ArrayList<SeguimientoAdjuntoDTO>();

}
