package eclub.com.conticonnec.dto;

import com.eclub.lib.common.models.dto.BaseDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class SeguimientoPorLotesRequestDTO extends BaseDto {

    @NotNull(message = "El Numero de Tarjeta es obligatorio.")
    @NotBlank
    private String numeroTarjeta;

    @NotNull(message = "El Numero de Documento es obligatorio.")
    @NotBlank
    private String numeroDocumento;

    @NotNull(message = "El Estado tarjeta es obligatorio.")
    private String estadoTarjeta;

    @NotNull
    @Size(max = 160)
    private String observacion;

    @NotNull(message = "El Tipo de tarjeta es obligatorio.")
    private String tipoTarjeta;

    private List<SeguimientoPorLotesAdjuntoDTO> adjunto = new ArrayList<SeguimientoPorLotesAdjuntoDTO>();

}
