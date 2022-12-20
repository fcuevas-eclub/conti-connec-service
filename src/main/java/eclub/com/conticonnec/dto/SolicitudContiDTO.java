package eclub.com.conticonnec.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class SolicitudContiDTO {

    private int idSolicitud;

    @NotBlank
    @NotEmpty
    private String tipoDocumento;

    @NotBlank
    @NotEmpty
    private String documento;

    @NotBlank
    @NotEmpty
    private String correo;

    @NotBlank
    @NotEmpty
    private String telefono;

    @NotBlank
    @NotEmpty
    @Size(max = 40)
    private String direccion;

    private String estadoCivil;

    private String actividadEco;

    private Integer estadoSolicitud;

    private String fechaCarga;

    private String usuarioAlta;

    private SolicitudContiDatosAdicionalesDTO datosAdicionales = new SolicitudContiDatosAdicionalesDTO();

    private List<SolicitudContiAdjuntoDTO> adjunto = new ArrayList<SolicitudContiAdjuntoDTO>();

}
