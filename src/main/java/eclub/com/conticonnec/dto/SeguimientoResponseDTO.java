package eclub.com.conticonnec.dto;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SeguimientoResponseDTO {
    private String codigo;//Código de retorno de esta solicitud (0 = OK)

    private String mensaje;//Mensaje descriptivo del código de retorno

    private String errorType;

    private String errorDescription;

}
