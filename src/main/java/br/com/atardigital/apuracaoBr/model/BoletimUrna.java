package br.com.atardigital.apuracaoBr.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
@Table(name = "boletim_urna")
public class BoletimUrna {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_boletim")
    private Integer idBoletim;

    @Column(name = "vrqr")
    private String vrqr;

    @Column(name = "vrch", length = 255)
    private String vrch;

    @Column(name = "proc")
    private String proc;

    @Column(name = "dtpl")
    private String dtpl;

    @Column(name = "plei")
    private String plei;

    @Column(name = "turn")
    private Integer turn;

    @Column(name = "unfe", length = 255)
    private String unfe;

    @Column(name = "muni")
    private Integer muni;

    @Column(name = "zona")
    private Integer zona;

    @Column(name = "seca")
    private Integer seca;

    @Column(name = "idue", length = 255)
    private Long idue;

    @Column(name = "idca", length = 255)
    private String idca;

    @Column(name = "hiqt")
    private Integer hiqt;

    @Column(name = "vers", length = 255)
    private String vers;

    @Column(name = "loca")
    private Integer loca;

    @Column(name = "dtab")
    private String dtab;

    @Column(name = "hrab")
    private String hrab;

    @Column(name = "dtfc")
    private String dtfc;

    @Column(name = "hrfc")
    private String hrfc;

    @Column(name = "idel")
    private Integer idel;

    @Column(name = "tipo")
    private Integer tipo;

    @Column(name = "assi", length = 255)
    private String assi;

    @Column(name = "qrbu_total")
    private String qrbuTotal;

    @OneToMany(mappedBy = "boletimUrna", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<QRCode> qrCode;
    @Column(name= "usuario")
    private int usuario;
    @Column(name= "bran")
    private Long bran;

    @Column(name = "nulo")
    private Long nulo;

    public Long getBran() {
        return bran;
    }

    public void setBran(Long bran) {
        this.bran = bran;
    }

    public Long getNulo() {
        return nulo;
    }

    public void setNulo(Long nulo) {
        this.nulo = nulo;
    }

    public String getQrbuTotal() {
        return qrbuTotal;
    }

    public void setQrbuTotal(String qrbuTotal) {
        this.qrbuTotal = qrbuTotal;
    }

    public int getUsuario() {
        return usuario;
    }

    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }

    public String getVrqr() {
        return vrqr;
    }

    public void setVrqr(String vrqr) {
        this.vrqr = vrqr;
    }

    public List<QRCode> getQrCode() {
        return qrCode;
    }

    public void setQrCode(List<QRCode> qrCode) {
        this.qrCode = qrCode;
    }

    public Integer getIdBoletim() {
        return idBoletim;
    }

    public void setIdBoletim(Integer idBoletim) {
        this.idBoletim = idBoletim;
    }

    public String getVrch() {
        return vrch;
    }

    public void setVrch(String vrch) {
        this.vrch = vrch;
    }

    public String getProc() {
        return proc;
    }

    public void setProc(String proc) {
        this.proc = proc;
    }

    public String getDtpl() {
        return dtpl;
    }

    public void setDtpl(String dtpl) {
        this.dtpl = dtpl;
    }

    public String getPlei() {
        return plei;
    }

    public void setPlei(String plei) {
        this.plei = plei;
    }

    public Integer getTurn() {
        return turn;
    }

    public void setTurn(Integer turn) {
        this.turn = turn;
    }

    public String getUnfe() {
        return unfe;
    }

    public void setUnfe(String unfe) {
        this.unfe = unfe;
    }

    public Integer getMuni() {
        return muni;
    }

    public void setMuni(Integer muni) {
        this.muni = muni;
    }

    public Integer getZona() {
        return zona;
    }

    public void setZona(Integer zona) {
        this.zona = zona;
    }

    public Integer getSeca() {
        return seca;
    }

    public void setSeca(Integer seca) {
        this.seca = seca;
    }

    public Long getIdue() {
        return idue;
    }

    public void setIdue(Long idue) {
        this.idue = idue;
    }

    public String getIdca() {
        return idca;
    }

    public void setIdca(String idca) {
        this.idca = idca;
    }

    public Integer getHiqt() {
        return hiqt;
    }

    public void setHiqt(Integer hiqt) {
        this.hiqt = hiqt;
    }

    public String getVers() {
        return vers;
    }

    public void setVers(String vers) {
        this.vers = vers;
    }

    public Integer getLoca() {
        return loca;
    }

    public void setLoca(Integer loca) {
        this.loca = loca;
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

    public Integer getIdel() {
        return idel;
    }

    public void setIdel(Integer idel) {
        this.idel = idel;
    }


    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public String getAssi() {
        return assi;
    }

    public void setAssi(String assi) {
        this.assi = assi;
    }

    public String getQrbu() {
        return qrbuTotal;
    }

    public void setQrbu(String qrbu) {
        this.qrbuTotal = qrbu;
    }


    @Override
    public String toString() {
        return "BoletimUrna{" +
                "idBoletim=" + idBoletim +
                ", vrqr='" + vrqr + '\'' +
                ", vrch='" + vrch + '\'' +
                ", proc=" + proc +
                ", dtpl=" + dtpl +
                ", plei=" + plei +
                ", turn=" + turn +
                ", unfe='" + unfe + '\'' +
                ", muni=" + muni +
                ", zona=" + zona +
                ", seca=" + seca +
                ", idue='" + idue + '\'' +
                ", idca='" + idca + '\'' +
                ", hiqt=" + hiqt +
                ", vers='" + vers + '\'' +
                ", loca=" + loca +
                ", dtab=" + dtab +
                ", hrab=" + hrab +
                ", dtfc=" + dtfc +
                ", hrfc=" + hrfc +
                ", idel=" + idel +
                ", tipo=" + tipo +
                ", assi='" + assi + '\'' +
                ", qrbu=" + qrbuTotal +
                ", qrCode=" + qrCode +
                '}';
    }
}
