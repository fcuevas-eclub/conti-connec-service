package eclub.com.conticonnec.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class AccountInfoResponseDTO implements Serializable {
    public static final long serialVersioUID = 1L;

    private String nroDocumento;

    private String nroTarjeta;

    private String nroCuenta;

    private String codigoMarca;

    private String nombreTarjeta;

    private String mesVencimiento;

    private String anioVencimiento;

    private String estadoTarjeta;
}
