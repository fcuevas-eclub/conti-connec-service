package eclub.com.conticonnec.dto;

public class AccountInfoResponseDTO {

    private String nroDocumento;

    private String nroTarjeta;

    private String nroCuenta;

    private String codigoMarca;

    private String nombreTarjeta;

    private String mesVencimiento;

    private String anioVencimiento;

    private String estadoTarjeta;

    public String getNroDocumento() {
        return nroDocumento;
    }

    public void setNroDocumento(String nroDocumento) {
        this.nroDocumento = nroDocumento;
    }

    public String getNroTarjeta() {
        return nroTarjeta;
    }

    public void setNroTarjeta(String nroTarjeta) {
        this.nroTarjeta = nroTarjeta;
    }

    public String getNroCuenta() {
        return nroCuenta;
    }

    public void setNroCuenta(String nroCuenta) {
        this.nroCuenta = nroCuenta;
    }

    public String getCodigoMarca() {
        return codigoMarca;
    }

    public void setCodigoMarca(String codigoMarca) {
        this.codigoMarca = codigoMarca;
    }

    public String getNombreTarjeta() {
        return nombreTarjeta;
    }

    public void setNombreTarjeta(String nombreTarjeta) {
        this.nombreTarjeta = nombreTarjeta;
    }

    public String getMesVencimiento() {
        return mesVencimiento;
    }

    public void setMesVencimiento(String mesVencimiento) {
        this.mesVencimiento = mesVencimiento;
    }

    public String getAnioVencimiento() {
        return anioVencimiento;
    }

    public void setAnioVencimiento(String anioVencimiento) {
        this.anioVencimiento = anioVencimiento;
    }

    public String getEstadoTarjeta() {
        return estadoTarjeta;
    }

    public void setEstadoTarjeta(String estadoTarjeta) {
        this.estadoTarjeta = estadoTarjeta;
    }

    @Override
    public String toString() {
        return "AccountInfoResponseDTO{" +
                "nroDocumento='" + nroDocumento + '\'' +
                ", nroTarjeta='" + nroTarjeta + '\'' +
                ", nroCuenta='" + nroCuenta + '\'' +
                ", codigoMarca='" + codigoMarca + '\'' +
                ", nombreTarjeta='" + nombreTarjeta + '\'' +
                ", mesVencimiento='" + mesVencimiento + '\'' +
                ", anioVencimiento='" + anioVencimiento + '\'' +
                ", estadoTarjeta='" + estadoTarjeta + '\'' +
                '}';
    }

}
