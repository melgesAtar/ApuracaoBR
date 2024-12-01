package br.com.atardigital.apuracaoBr.model;

import jakarta.persistence.*;

@Entity
@Table(name = "votos_candidato")
public class VotosCandidato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_voto")
    private Integer idVoto;
    @ManyToOne
    @JoinColumn(name = "id_boletim", nullable = false)
    private BoletimUrna boletimUrna;

    @Column(name = "candidato_numero")
    private Integer candidatoNumero;

    @Column(name = "votos")
    private Integer votos;

    @ManyToOne
    @JoinColumn(name = "id_qr_code", nullable = false)
    private QRCode qrCode;;

    // Getters and Setters

    public Integer getIdVoto() {
        return idVoto;
    }

    public void setIdVoto(Integer idVoto) {
        this.idVoto = idVoto;
    }

    public Integer getCandidatoNumero() {
        return candidatoNumero;
    }

    public void setCandidatoNumero(Integer candidatoNumero) {
        this.candidatoNumero = candidatoNumero;
    }

    public Integer getVotos() {
        return votos;
    }

    public void setVotos(Integer votos) {
        this.votos = votos;
    }

    public BoletimUrna getBoletimUrna() {
        return boletimUrna;
    }

    public void setBoletimUrna(BoletimUrna boletimUrna) {
        this.boletimUrna = boletimUrna;
    }

    public QRCode getQrCode() {
        return qrCode;
    }

    public void setQrCode(QRCode qrCode) {
        this.qrCode = qrCode;
    }
}
