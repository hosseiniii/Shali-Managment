package ir.hosseini.shalimanagment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.pushpole.sdk.PushPole;

import ir.hosseini.shalimanagment.Dialogs.MyDialog;
import ir.hosseini.shalimanagment.Interfaces.AlertDialogListener;
import ir.hosseini.shalimanagment.app.app;
import ir.hosseini.shalimanagment.model.DialogObject;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    CardView add_shali_btn, submitteds_btn, add_rice_btn, deliver_btn, stats_btn;
    MyDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PushPole.initialize(this,true);

        init();

    }

    private void init() {

        add_shali_btn = findViewById(R.id.add_shali_btn);
        add_shali_btn.setOnClickListener(this);

        submitteds_btn = findViewById(R.id.submitteds_btn);
        submitteds_btn.setOnClickListener(this);

        add_rice_btn = findViewById(R.id.add_rice_btn);
        add_rice_btn.setOnClickListener(this);

        deliver_btn = findViewById(R.id.deliver_btn);
        deliver_btn.setOnClickListener(this);

        stats_btn = findViewById(R.id.stats_btn);
        stats_btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.add_shali_btn:
                app.log("add shali");
                Intent intent = new Intent(MainActivity.this, AddShaliActivity.class);
                startActivity(intent);
                break;
            case R.id.submitteds_btn:
                app.log("submitteds");
                Intent intent1 = new Intent(MainActivity.this, SubmittedsActivity.class);
                startActivity(intent1);
                break;
            case R.id.add_rice_btn:
                app.log("add rice");
                makeGetCodeDialog("rice");
//                Intent intent2 = new Intent(MainActivity.this, AddRiceActivity.class);
//                startActivity(intent2);
                break;
            case R.id.deliver_btn:
//                app.toast("info","تحویل");
                app.log("deliver_btn");
                break;
            case R.id.stats_btn:
                makeGetCodeDialog("rice");
//                app.toast("normal","آمار و گزارشات");
                app.log("stats_btn");
                break;


        }
    }

    private void makeGetCodeDialog(String type) {
        String title = "";
        String message = "";

        if (type.equals("rice")) {
            title = "ثبت برنج";
            message = "لطفا شماره کشاورز را وارد کنید";
        }
        else if (type.equals("receive")) {
            title = "ثبت تحویل";
            message = "لطفا شماره کشاورز را وارد کنید";
        }

        DialogObject dialogObject = new DialogObject()
                .setTitle(title)
                .setMessage(message)
                .setPositive("تایید")
                .setNegative("لغو")
                .setNegativeDisplay(true)
                .setPositiveDisplay(true)
                .setNegativeIcon(R.drawable.ic_close)
                .setPositiveIcon(R.drawable.ic_check)
                .setAlertDialogListener(new AlertDialogListener() {
                    @Override
                    public void onPositiveClick() {

                    }

                    @Override
                    public void onNegativeClick() {

                    }
                });

        dialog = new MyDialog(this, dialogObject);
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        dialog.show();
    }
}
