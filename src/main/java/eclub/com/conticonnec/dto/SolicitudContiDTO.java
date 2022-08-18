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
    private String TipoDocumento;

    @NotBlank
    @NotEmpty
    private String Documento;

    @NotBlank
    @NotEmpty
    private String Correo;

    @NotBlank
    @NotEmpty
    private String Telefono;

    @NotBlank
    @NotEmpty
    @Size(max = 40)
    private String Direccion;

    private String EstadoCivil;

    private String ActividadEco;

    private Integer EstadoSolicitud;

    private String FechaCarga;

    private String usuarioAlta;

    private SolicitudContiDatosAdicionalesDTO DatosAdicionales = new SolicitudContiDatosAdicionalesDTO();

    private List<SolicitudContiAdjuntoDTO> Adjunto = new ArrayList<SolicitudContiAdjuntoDTO>();

}
