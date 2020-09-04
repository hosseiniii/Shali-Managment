package ir.hosseini.shalimanagment.Dialogs;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import ir.hosseini.shalimanagment.AddRiceActivity;
import ir.hosseini.shalimanagment.R;
import ir.hosseini.shalimanagment.model.DialogObject;

public class MyDialog extends AlertDialog implements View.OnClickListener {

    DialogObject object;

    TextView title, message;
    EditText number;
    Button ok, cancel;

    public MyDialog(Context context, DialogObject dialogObject) {
        super(context);
        this.object = dialogObject;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_dialog_object);

        initViews();
        init();
    }

    private void initViews() {
        title = findViewById(R.id.title);
        message = findViewById(R.id.message);

        number = findViewById(R.id.number);

        ok = findViewById(R.id.ok);
        cancel = findViewById(R.id.cancel);

        ok.setOnClickListener(this);
        cancel.setOnClickListener(this);

    }

    private void init() {
        title.setText(object.getTitle());
        message.setText(object.getMessage());
        ok.setText(object.getPositive());
        cancel.setText(object.getNegative());
    }

    @Override
    public void onClick(View v) {
        if (v == ok) {
            Intent intent = new Intent(getContext(), AddRiceActivity.class);
            intent.putExtra("keshavarzId", Integer.valueOf(number.getText().toString()));
            getContext().startActivity(intent);
        }
        if (v == cancel) {
            dismiss();
        }
    }
}
