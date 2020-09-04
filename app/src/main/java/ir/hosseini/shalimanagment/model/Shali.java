package ir.hosseini.shalimanagment.model;

import java.io.Serializable;
import java.util.List;

import ir.hosseini.shalimanagment.Adapters.ShalisAdapter;
import ir.hosseini.shalimanagment.app.Application;
import ir.hosseini.shalimanagment.app.app;
import ir.hosseini.shalimanagment.retrofit.ShaliAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Shali {
    private int id;
    private String noe_shali;
    private int tedad_shali;
    private int vazn_shali;
    private int keshavarz;
    private String nam_ijad_konande;
    private String tarikh;
    private String saat;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNoe_shali() {
        return noe_shali;
    }

    public void setNoe_shali(String noe_shali) {
        this.noe_shali = noe_shali;
    }

    public int getTedad_shali() {
        return tedad_shali;
    }

    public void setTedad_shali(int tedade_shali) {
        this.tedad_shali = tedade_shali;
    }

    public int getVazn_shali() {
        return vazn_shali;
    }

    public void setVazn_shali(int vazn_shali) {
        this.vazn_shali = vazn_shali;
    }

    public int getKeshavarz() {
        return keshavarz;
    }

    public void setKeshavarz(int keshavarz) {
        this.keshavarz = keshavarz;
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
