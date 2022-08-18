package eclub.com.conticonnec.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class SolAltaRequest {
    @NotNull
    private SolicitudContiDTO dto;

    @NotBlank
    @NotEmpty
    @Size(min = 3)
    private String usuarioAlta;
}
