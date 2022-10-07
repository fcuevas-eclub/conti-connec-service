package eclub.com.conticonnec.dto;

import com.eclub.lib.common.models.dto.BaseDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
//@ToString
public class SeguimientoAdjuntoDTO extends BaseDto {

    /*
    Tipo de documento(1,2).
    1 = digitaliza el Documento de Identidad.
    2 = digitaliza el acuse de recibo
    */
    private Integer tipoDocumento;

    //@ToStringExclude
    //private byte[] Archivo;//Archivo a digitalizar
    //private String Archivo;//Archivo a digitalizar
    private String archivo;//Archivo a digitalizar

    private SeguimientoDto seguimiento;
}
