package ir.hosseini.shalimanagment.model;

public class Tahvil {
    private int berenj;
    private int id;
    private int tedad_kise_berenj;
    private int tedad_kise_saboos;
    private int vazn_ezafe;
    private int vazn_nimdane;
    private String nam_ijad_konande;
    private String tarikh;
    private String saat;

    public int getBerenj() {
        return berenj;
    }

    public void setBerenj(int berenj) {
        this.berenj = berenj;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTedad_kise_berenj() {
        return tedad_kise_berenj;
    }

    public void setTedad_kise_berenj(int tedad_kise_berenj) {
        this.tedad_kise_berenj = tedad_kise_berenj;
    }

    public int getTedad_kise_saboos() {
        return tedad_kise_saboos;
    }

    public void setTedad_kise_saboos(int tedad_kise_saboos) {
        this.tedad_kise_saboos = tedad_kise_saboos;
    }

    public int getVazn_ezafe() {
        return vazn_ezafe;
    }

    public void setVazn_ezafe(int vazn_ezafe) {
        this.vazn_ezafe = vazn_ezafe;
    }

    public int getVazn_nimdane() {
        return vazn_nimdane;
    }

    public void setVazn_nimdane(int vazn_nimdane) {
        this.vazn_nimdane = vazn_nimdane;
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
}
