package eclub.com.conticonnec.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SeguimientoPorLotesResponseDTO {
    @Schema(name = "codigo",description = "Código de retorno de esta solicitud (0 = OK)")
    private String codigo;
    private String mensaje;//Mensaje descriptivo del código de retorno
    private String errorType;
    private String errorDescription;
}
