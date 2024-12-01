package br.com.atardigital.apuracaoBr.model;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "qrcode")
public class QRCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "hash", length = 255)
    private String hash;

    private String qrbu;

    @OneToMany(mappedBy = "qrCode", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VotosCandidato> relacaoVotos;

    @ManyToOne
    @JoinColumn(name = "boletim_urna_id", nullable = false)
    private BoletimUrna boletimUrna;

    public List<VotosCandidato> getRelacaoVotos() {
        return relacaoVotos;
    }

    public void setRelacaoVotos(List<VotosCandidato> relacaoVotos) {
        this.relacaoVotos = relacaoVotos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getQrbu() {
        return qrbu;
    }

    public void setQrbu(String qrbu) {
        this.qrbu = qrbu;
    }

    public BoletimUrna getBoletimUrna() {
        return boletimUrna;
    }

    public void setBoletimUrna(BoletimUrna boletimUrna) {
        this.boletimUrna = boletimUrna;
    }
}
