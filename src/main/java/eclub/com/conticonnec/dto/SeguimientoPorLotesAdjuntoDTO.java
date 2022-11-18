package eclub.com.conticonnec.dto;

import com.eclub.lib.common.models.dto.BaseDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class SeguimientoPorLotesAdjuntoDTO extends BaseDto {

    /*
    Tipo de documento(1,2).
    1 = digitaliza el Documento de Identidad.
    2 = digitaliza el acuse de recibo
    */
    @NotNull(message = "El Tipo de Documento es obligatorio.")
    private Integer tipoDocumento;

    @NotNull(message = "El Archivo es obligatorio.")
    @NotBlank
    private String Archivo;//Archivo a digitalizar

}
