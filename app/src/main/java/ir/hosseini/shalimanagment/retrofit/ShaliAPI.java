package ir.hosseini.shalimanagment.retrofit;

import java.util.List;

import ir.hosseini.shalimanagment.model.Berenj;
import ir.hosseini.shalimanagment.model.Farmer;
import ir.hosseini.shalimanagment.model.Shali;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ShaliAPI {

    @GET("keshavarz/list/")
    Call<List<Farmer>> getAllFarmers();

    @FormUrlEncoded
    @POST("keshavarz/new/")
    Call<Farmer> addFarmer(
            @Field("makan") String makan,
            @Field("nam") String nam,
            @Field("ranande") String ranande,
            @Field("telefon") String telefon,
            @Field("nam_ijad_konande") String nam_ijad_konande);

    @GET("keshavarz/search/{search_item}")
    Call<List<Farmer>> searchFarmer(@Path("search_item") String searchString);

    @FormUrlEncoded
    @POST("shali/new/")
    Call<Shali> addShali(
            @Field("noe_shali") String noe_shali,
            @Field("tedad_shali") int tedad_shali,
            @Field("vazn_shali") int vazn_shali,
            @Field("keshavarz") int keshavarz,
            @Field("nam_ijad_konande") String nam_ijad_konande
    );

    @DELETE("berenj/{berenj_code}/")
    Call<Void> deleteBerenj(
            @Path("berenj_code") String berenj_code
    );

    @Headers({"Content-Type: application/json"})
    @PUT("berenj/{berenj_code}/")
    Call<Berenj> editBerenj(
            @Path("berenj_code") String berenj_code,
            @Body Berenj berenj
    );

    @Headers({"Content-Type: application/json"})
    @PUT("shali/{shali_code}/")
    Call<Shali> editShali(
            @Path("shali_code") String shali_code,
            @Body Shali shali
    );

    @DELETE("shali/{shali_code}/")
    Call<Void> deleteShali(
            @Path("shali_code") String shali_code
    );

    @FormUrlEncoded
    @POST("berenj/new/")
    Call<Berenj> addBerenj(
            @Field("makan_berenj") String makan_berenj,
            @Field("tedad_kise_berenj") int tedad_kise_berenj,
            @Field("tedad_kise_saboos") int tedad_kise_saboos,
            @Field("vazn_ezafe") int vazn_ezafe,
            @Field("vazn_kise_berenj") int vazn_kise_berenj,
            @Field("vazn_nimdane") int vazn_nimdane,
            @Field("karmozd") int karmozd,
            @Field("tozihat") String tozihat,
            @Field("keshavarz") int keshavarz,
            @Field("nam_ijad_konande") String nam_ijad_konande
    );


}
