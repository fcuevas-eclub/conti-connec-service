package eclub.com.conticonnec.dto;

import com.eclub.lib.common.models.dto.BaseDto;
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
public class EnvioDto extends BaseDto {

    @NotBlank
    @NotEmpty
    @Size(max = 255)
    private String archivo;

    @NotBlank
    @NotEmpty
    @Size(max = 255)
    private String estado;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;

}
