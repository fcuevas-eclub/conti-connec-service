package eclub.com.conticonnec.dto;

import com.eclub.lib.common.models.dto.BaseDto;
import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EstadosTarjetaDto extends BaseDto {

    @NotNull(message = "El campo codigo es obligatorio.")
    private String codigo;

    @NotNull(message = "El campo descripcion es obligatorio.")
    private String descripcion;

}
