package ir.hosseini.shalimanagment.model;

public class Farmer {
    private int code;
    private String makan;
    private String nam;
    private String ranande;
    private String telefon;
    private String nam_ijad_konande;
    private String tarikh;
    private String saat;
    private Berenj berenj;
    private Shali[] shaliha;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMakan() {
        return makan;
    }

    public void setMakan(String makan) {
        this.makan = makan;
    }

    public String getNam() {
        return nam;
    }

    public void setNam(String nam) {
        this.nam = nam;
    }

    public String getRanande() {
        return ranande;
    }

    public void setRanande(String ranande) {
        this.ranande = ranande;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getNam_ijad_konande() {
        return nam_ijad_konande;
    }

    public void setNam_ijad_konande(String nam_ijad_konande) {
        this.nam_ijad_konande = nam_ijad_konande;
    }

    public String getTarikh() {
        return tarikh;
    }

    public void setTarikh(String tarikh) {
        this.tarikh = tarikh;
    }

    public String getSaat() {
        return saat;
    }

    public void setSaat(String saat) {
        this.saat = saat;
    }

    public Berenj getBerenj() {
        return berenj;
    }

    public void setBerenj(Berenj berenj) {
        this.berenj = berenj;
    }

    public Shali[] getShaliha() {
        return shaliha;
    }

    public void setShaliha(Shali[] shaliha) {
        this.shaliha = shaliha;
    }
}
