package ir.hosseini.shalimanagment;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import ir.hosseini.shalimanagment.app.Application;
import ir.hosseini.shalimanagment.app.app;
import ir.hosseini.shalimanagment.model.Farmer;
import ir.hosseini.shalimanagment.model.Shali;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddShaliActivity extends AppCompatActivity implements View.OnClickListener {

    EditText farmer_name_inp, shali_kise_count_inp, shali_weight_inp, shali_type_inp, shali_place_inp, driver_inp, phone_inp;
    CardView save_btn;
    String farmer_name, keshavarz_id, shali_nam_ijad_konande, shali_saat, shali_tarikh;
    String shali_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_shali);

        init();
    }

    private void init() {

        farmer_name_inp = findViewById(R.id.farmer_name_inp);
        shali_kise_count_inp = findViewById(R.id.shali_kise_count_inp);
        shali_weight_inp = findViewById(R.id.shali_weight_inp);
        shali_type_inp = findViewById(R.id.tedad_kise_saboos_inp);
        phone_inp = findViewById(R.id.phone_inp);
        shali_place_inp = findViewById(R.id.shali_place_inp);
        driver_inp = findViewById(R.id.driver_inp);

        save_btn = findViewById(R.id.save_btn);
        save_btn.setOnClickListener(this);

        Bundle extras = getIntent().getExtras();
        farmer_name = "";

        if (extras != null) {
            farmer_name = String.valueOf(extras.get("farmer_name"));
            farmer_name_inp.setText(String.valueOf(extras.get("farmer_name")));
            phone_inp.setText(String.valueOf(extras.get("phone")));
            driver_inp.setText(String.valueOf(extras.get("driver")));
            shali_place_inp.setText(String.valueOf(extras.get("place")));
            shali_kise_count_inp.setText(String.valueOf(extras.get("count")));
            shali_weight_inp.setText(String.valueOf(extras.get("weight")));
            shali_type_inp.setText(String.valueOf(extras.get("type")));
            shali_id = String.valueOf(extras.get("shali_id"));
            keshavarz_id = String.valueOf(extras.get("keshavarz_id"));
            shali_nam_ijad_konande = String.valueOf(extras.get("shali_nam_ijad_konande"));
            shali_saat = String.valueOf(extras.get("shali_saat"));
            shali_tarikh = String.valueOf(extras.get("shali_tarikh"));

        }


    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.save_btn:
                if (farmer_name.equals(""))
                    add_shali();
                else
                    edit_shali();


            default:
//                app.toast("info", "هیچی");
                break;

        }

    }

    private void add_shali() {

        String farmer_name = farmer_name_inp.getText().toString();

        if (farmer_name.equals("")) {
            app.toast("error", "نام کشاورز نباید خالی باشد");
            return;
        }

        int shali_kise_count = shali_kise_count_inp.getText().toString().equals("") ? 0 : Integer.parseInt(shali_kise_count_inp.getText().toString());
        int shali_weight = shali_weight_inp.getText().toString().equals("") ? 0 : Integer.parseInt(shali_weight_inp.getText().toString());
        String shali_type = shali_type_inp.getText().toString();
        String phone = phone_inp.getText().toString();
        String driver = driver_inp.getText().toString();
        String place = shali_place_inp.getText().toString();


        Application.getShaliAPI().addFarmer(place, farmer_name, driver, phone, "abc").enqueue(new Callback<Farmer>() {
            @Override
            public void onResponse(Call<Farmer> call, Response<Farmer> response) {

                int farmer_code = response.body().getCode();

                Application.getShaliAPI().addShali(shali_type, shali_kise_count, shali_weight, farmer_code, "abc").enqueue(new Callback<Shali>() {
                    @Override
                    public void onResponse(Call<Shali> call, Response<Shali> response) {
                        app.toast("success", "شالی با موفقیت ثبت شد");
                        clear_form();
                    }

                    @Override
                    public void onFailure(Call<Shali> call, Throwable t) {
                        app.toast("error", "خطایی در هنگام اضافه کردن شالی پیش آمد");
                    }
                });

            }

            @Override
            public void onFailure(Call<Farmer> call, Throwable t) {
                app.toast("error", "خطایی در هنگام اضافه کردن شالی پیش آمد");
            }
        });


    }

    private void edit_shali() {

        String farmer_name = farmer_name_inp.getText().toString();

        if (farmer_name.equals("")) {
            app.toast("error", "نام کشاورز نباید خالی باشد");
            return;
        }

        int shali_kise_count = shali_kise_count_inp.getText().toString().equals("") ? 0 : Integer.parseInt(shali_kise_count_inp.getText().toString());
        int shali_weight = shali_weight_inp.getText().toString().equals("") ? 0 : Integer.parseInt(shali_weight_inp.getText().toString());
        String shali_type = shali_type_inp.getText().toString();
        String phone = phone_inp.getText().toString();
        String driver = driver_inp.getText().toString();
        String place = shali_place_inp.getText().toString();

        Shali editedShali = new Shali();
        editedShali.setId(Integer.parseInt(shali_id));
        editedShali.setKeshavarz(Integer.parseInt(keshavarz_id));
        editedShali.setNam_ijad_konande(shali_nam_ijad_konande);
        editedShali.setNoe_shali(shali_type);
        editedShali.setSaat(shali_saat);
        editedShali.setTedad_shali(shali_kise_count);
        editedShali.setTarikh(shali_tarikh);
        editedShali.setVazn_shali(shali_weight);

        Application.getShaliAPI().editShali(shali_id, editedShali)
                .enqueue(new Callback<Shali>() {
                    @Override
                    public void onResponse(Call<Shali> call, Response<Shali> response) {
                        app.toast("success", "ویرایش با موفقیت انجام شد");
                        finish();
                    }

                    @Override
                    public void onFailure(Call<Shali> call, Throwable t) {
                        app.toast("error", "ویرایش ناموفق بود");
                    }
                });
    }

    private void clear_form() {
        farmer_name_inp.setText("");
        shali_kise_count_inp.setText("");
        shali_weight_inp.setText("");
        shali_type_inp.setText("");
        phone_inp.setText("");
        driver_inp.setText("");
        shali_place_inp.setText("");
    }
}
