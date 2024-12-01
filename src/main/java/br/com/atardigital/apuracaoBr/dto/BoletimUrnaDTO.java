package br.com.atardigital.apuracaoBr.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


import java.util.List;


public class BoletimUrnaDTO {
    @NotNull(message = "A lista de QRCodes não pode ser nula")
    @Size(min = 1, message = "A lista de QRCodes deve conter pelo menos um elemento")
    @JsonProperty("QRCode")
    private List<QRCodeDTO> listaQRCodes;

    public List<QRCodeDTO> getListaQRCodes() {
        return listaQRCodes;
    }

    public void setListaQRCodes(List<QRCodeDTO> listaQRCodes) {
        this.listaQRCodes = listaQRCodes;
    }

    @Override
    public String toString() {
        return "BoletimUrnaDTO{" +
                "listaQRCodes=" + listaQRCodes +
                '}';
    }
}

