package br.com.atardigital.apuracaoBr.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class VotosCandidatoDTO {

    @JsonProperty("idPartido")
    private int idPartido;

    @JsonProperty("votos")
    private Map<String, Integer> votos;

    public int getIdPartido() {
        return idPartido;
    }

    public void setIdPartido(int idPartido) {
        this.idPartido = idPartido;
    }

    public Map<String, Integer> getVotos() {
        return votos;
    }

    public void setVotos(Map<String, Integer> votos) {
        this.votos = votos;
    }
}
