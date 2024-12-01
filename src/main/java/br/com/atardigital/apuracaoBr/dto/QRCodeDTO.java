package br.com.atardigital.apuracaoBr.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class QRCodeDTO {
    private String vrqr;
    private String vrch;
    private String orig;
    private String orlc;
    private String proc;
    private String dtpl;
    private String plei;
    private int turn;
    private String fase;
    private String unfe;
    private int zona;
    private int seca;
    private long idue;
    private String idca;
    private int hiqt;
    private String hica;
    private String vers;
    private int loca;
    private int apto;
    private int apts;
    private int aptt;
    private int comp;
    private int falt;
    private String dtab;
    private String hrab;
    private String dtfc;
    private String hrfc;
    private int idel;
    private int carg;
    private int tipo;
    private Long verc;
    @JsonProperty("bran")
    private Long bran;
    @JsonProperty("nulo")
    private Long nulo;


    @JsonProperty("relacaoVotos")
    private List<VotosCandidatoDTO> relacaoVotos;
    private String hash;
    private int muni;
    private String versao;
    private Integer prop;
    @JsonProperty("ASSI")
    private String assi;
    @NotNull(message = "O valor de QRBU não pode ser nulo")
    @JsonProperty("qrbu")
    private String qrbu;


    public String getQrbu() {
        return qrbu;
    }
    public void setQrbu(String qrbu) {
        this.qrbu = qrbu;
    }


    public String getAssi() {
        return assi;
    }

    public Integer getProp() {
        return prop;
    }

    public String getVers() {
        return vers;
    }

    public int getMuni() {
        return muni;
    }
    public void setMuni(int muni) {
        this.muni = muni;
    }


    public String getVrqr() {
        return vrqr;
    }

    public String getVrch() {
        return vrch;
    }

    public String getOrig() {
        return orig;
    }

    public String getOrlc() {
        return orlc;
    }

    public String getProc() {
        return proc;
    }

    public String getDtpl() {
        return dtpl;
    }

    public String getPlei() {
        return plei;
    }

    public int getTurn() {
        return turn;
    }

    public String getFase() {
        return fase;
    }

    public String getUnfe() {
        return unfe;
    }

    public int getZona() {
        return zona;
    }

    public int getSeca() {
        return seca;
    }

    public long getIdue() {
        return idue;
    }

    public void setIdue(long idue) {
        this.idue = idue;
    }

    public String getIdca() {
        return idca;
    }

    public int getHiqt() {
        return hiqt;
    }

    public String getHica() {
        return hica;
    }

    public String getVersao() {
        return versao;
    }

    public int getLoca() {
        return loca;
    }

    public int getApto() {
        return apto;
    }

    public void setApto(int apto) {
        this.apto = apto;
    }

    public int getApts() {
        return apts;
    }

    public void setApts(int apts) {
        this.apts = apts;
    }

    public int getAptt() {
        return aptt;
    }

    public void setAptt(int aptt) {
        this.aptt = aptt;
    }

    public int getComp() {
        return comp;
    }

    public void setComp(int comp) {
        this.comp = comp;
    }

    public int getFalt() {
        return falt;
    }

    public void setFalt(int falt) {
        this.falt = falt;
    }

    public String getDtab() {
        return dtab;
    }

    public void setDtab(String dtab) {
        this.dtab = dtab;
    }

    public String getHrab() {
        return hrab;
    }

    public void setHrab(String hrab) {
        this.hrab = hrab;
    }

    public String getDtfc() {
        return dtfc;
    }

    public void setDtfc(String dtfc) {
        this.dtfc = dtfc;
    }

    public String getHrfc() {
        return hrfc;
    }

    public void setHrfc(String hrfc) {
        this.hrfc = hrfc;
    }

    public int getIdel() {
        return idel;
    }

    public void setIdel(int idel) {
        this.idel = idel;
    }

    public int getCarg() {
        return carg;
    }

    public void setCarg(int carg) {
        this.carg = carg;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public Long getVerc() {
        return verc;
    }

    public void setVerc(Long verc) {
        this.verc = verc;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public List<VotosCandidatoDTO> getRelacaoVotos() {
        return relacaoVotos;
    }

    public void setRelacaoVotos(List<VotosCandidatoDTO> relacaoVotos) {
        this.relacaoVotos = relacaoVotos;
    }

    public long getBran() {
        return bran;
    }

    public void setBran(long bran) {
        this.bran = bran;
    }

    public long getNulo() {
        return nulo;
    }

    public void setNulo(long nulo) {
        this.nulo = nulo;
    }

    @Override
    public String toString() {
        return "QRCodeDTO{" +
                "codigo='" + qrbu + '\'' +
                ", vrqr='" + vrqr + '\'' +
                ", vrch='" + vrch + '\'' +
                ", orig='" + orig + '\'' +
                ", orlc='" + orlc + '\'' +
                ", proc='" + proc + '\'' +
                ", dtpl='" + dtpl + '\'' +
                ", plei='" + plei + '\'' +
                ", turn=" + turn +
                ", fase='" + fase + '\'' +
                ", unfe='" + unfe + '\'' +
                ", zona=" + zona +
                ", seca=" + seca +
                ", idue=" + idue +
                ", idca='" + idca + '\'' +
                ", hiqt=" + hiqt +
                ", hica='" + hica + '\'' +
                ", versao='" + versao + '\'' +
                ", loca=" + loca +
                ", apto=" + apto +
                ", apts=" + apts +
                ", aptt=" + aptt +
                ", comp=" + comp +
                ", falt=" + falt +
                ", dtab='" + dtab + '\'' +
                ", hrab='" + hrab + '\'' +
                ", dtfc='" + dtfc + '\'' +
                ", hrfc='" + hrfc + '\'' +
                ", idel=" + idel +
                ", carg=" + carg +
                ", tipo=" + tipo +
                ", verc=" + verc +
                ", hash='" + hash + '\'' +
                ", relacaoVotos=" + relacaoVotos +
                '}';
    }
}
