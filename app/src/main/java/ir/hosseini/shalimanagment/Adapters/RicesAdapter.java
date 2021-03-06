package ir.hosseini.shalimanagment.Adapters;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ir.hosseini.shalimanagment.AddRiceActivity;
import ir.hosseini.shalimanagment.R;
import ir.hosseini.shalimanagment.app.Application;
import ir.hosseini.shalimanagment.app.app;
import ir.hosseini.shalimanagment.model.Berenj;
import ir.hosseini.shalimanagment.model.Farmer;
import ir.hosseini.shalimanagment.model.Tahvil;
import ir.hosseini.shalimanagment.ui.ReceivedRices.ReceivedRicesFragment;
import ir.hosseini.shalimanagment.ui.SubmittedRices.SubmittedRicesFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RicesAdapter extends RecyclerView.Adapter<RicesAdapter.MyViewHolder> {

    private SubmittedRicesFragment fragment;
    private static List<Berenj> list;
    private Farmer farmer;

    public RicesAdapter(SubmittedRicesFragment fragment, List<Berenj> list) {
        this.fragment = fragment;
        RicesAdapter.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(fragment.getContext()).inflate(R.layout.rice_row, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Berenj berenj = list.get(position);

        Application.getShaliAPI().searchFarmer(String.valueOf(berenj.getKeshavarz())).enqueue(new Callback<List<Farmer>>() {
            @Override
            public void onResponse(Call<List<Farmer>> call, Response<List<Farmer>> response) {
                holder.name_keshavarz.setText(response.body().get(0).getNam());
            }

            @Override
            public void onFailure(Call<List<Farmer>> call, Throwable t) {

            }
        });

        String date_fixed = fix_date(berenj.getTarikh());

        holder.code.setText("کد " + berenj.getKeshavarz());
        holder.date.setText(date_fixed);
        holder.time.setText(berenj.getSaat());
        holder.majmoe_vazn_berenj.setText("محموع وزن برنج: " + berenj.getVazn_kol());
        holder.tedad_kise_berenj.setText("تعداد کیسه برنج: " + berenj.getTedad_kise_berenj());
        holder.vazn_kise_berenj.setText("وزن کیسه برنج: " + berenj.getVazn_kise_berenj() + " کیلوگرم");
        holder.nimdane_weight.setText("وزن نیم دانه: " + berenj.getVazn_nimdane() + " کیلوگرم");
        holder.tedad_kise_saboos.setText("تعداد کیسه سبوس: " + berenj.getVazn_kise_berenj());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    private String fix_date(String tarikh) {
        String[] d = tarikh.split("-");

        String result = "";
        result += d[2];
        result += " ";

        switch (d[1]) {
            case "01":
                result += "فروردین";
                break;
            case "02":
                result += "اردیبهشت";
                break;
            case "03":
                result += "خرداد";
                break;
            case "04":
                result += "تیر";
                break;
            case "05":
                result += "مرداد";
                break;
            case "06":
                result += "شهریور";
                break;
            case "07":
                result += "مهر";
                break;
            case "08":
                result += "آبان";
                break;
            case "09":
                result += "آذر";
                break;
            case "10":
                result += "دی";
                break;
            case "11":
                result += "بهمن";
                break;
            case "12":
                result += "اسفند";
                break;
        }

        result += " ";
        result += d[0];

        return result;
    }

    @SuppressWarnings("NullableProblems")
    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView code, date, time, majmoe_vazn_berenj, tedad_kise_berenj, vazn_kise_berenj, name_keshavarz, nimdane_weight, tedad_kise_saboos;
        ImageButton edit, delete, info;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            code = itemView.findViewById(R.id.code);
            date = itemView.findViewById(R.id.date);
            time = itemView.findViewById(R.id.time);
            majmoe_vazn_berenj = itemView.findViewById(R.id.majmoe_vazn_berenj);
            tedad_kise_berenj = itemView.findViewById(R.id.tedad_kise_berenj);
            vazn_kise_berenj = itemView.findViewById(R.id.vazn_kise_berenj);
            name_keshavarz = itemView.findViewById(R.id.name_keshavarz);
            nimdane_weight = itemView.findViewById(R.id.nimdane_weight);
            tedad_kise_saboos = itemView.findViewById(R.id.tedad_kise_saboos);

            edit = itemView.findViewById(R.id.editButton);
            delete = itemView.findViewById(R.id.deleteButton);
            info = itemView.findViewById(R.id.infoButton);
            edit.setVisibility(View.GONE);
            info.setVisibility(View.GONE);

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String berenj_code = String.valueOf(list.get(getAdapterPosition()).getId());

                    new AlertDialog.Builder(v.getContext())
                            .setTitle("حذف برنج")
                            .setMessage("آیا مطمئنید که می‌خواهید این برنج را حذف کنید؟")
                            .setPositiveButton("بله", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    deleteItem(berenj_code, list, getAdapterPosition());
                                }
                            })
                            .setNegativeButton("خیر", null)
                            .show();
                }
            });

            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    editItem(v.getContext(), list, getAdapterPosition());
                }
            });

        }

        private void deleteItem(String berenj_code, List<Berenj> list, int adapter_position) {

            Application.getShaliAPI().deleteBerenj(berenj_code).enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    app.toast("info", String.valueOf(response.body()));
                    app.toast("success", "برنج با موفقیت حذف شد");
                    list.remove(adapter_position);

                    notifyDataSetChanged();
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    app.toast("error", "حذف ناموفق بود");
                }
            });

        }
    }

    private void editItem(Context c, List<Berenj> list, int adapterPosition) {
        Application.getShaliAPI().searchFarmer(String.valueOf(list.get(adapterPosition).getKeshavarz())).enqueue(new Callback<List<Farmer>>() {
            @Override
            public void onResponse(Call<List<Farmer>> call, Response<List<Farmer>> response) {
                startEditActivity(c, list, response, adapterPosition);
                notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Farmer>> call, Throwable t) {
                app.toast("error", "دریافت اطلاعات ناموفق بود. لطفا دوباره تلاش کنید");
            }
        });

        notifyDataSetChanged();
    }

    private void startEditActivity(Context c, List<Berenj> list, Response<List<Farmer>> response, int adapterPosition) {

        Intent intent = new Intent(c, AddRiceActivity.class);

//        intent.putExtra("farmer_name", response.body().get(0).getNam());
//        intent.putExtra("phone", response.body().get(0).getTelefon());
//        intent.putExtra("driver", response.body().get(0).getRanande());
//        intent.putExtra("place", response.body().get(0).getMakan());
//        intent.putExtra("count", list.get(adapterPosition).getTedad_shali());
//        intent.putExtra("weight", list.get(adapterPosition).getVazn_shali());
//        intent.putExtra("type", list.get(adapterPosition).getNoe_shali());
//        intent.putExtra("keshavarz_id", list.get(adapterPosition).getKeshavarz());
//        intent.putExtra("shali_id", list.get(adapterPosition).getId());
//        intent.putExtra("shali_nam_ijad_konande", list.get(adapterPosition).getNam_ijad_konande());
//        intent.putExtra("shali_saat", list.get(adapterPosition).getSaat());
//        intent.putExtra("shali_tarikh", list.get(adapterPosition).getTarikh());

        c.startActivity(intent);
    }



}
