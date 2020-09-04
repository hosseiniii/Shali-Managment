package ir.hosseini.shalimanagment.ui.ReceivedRices;

import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.view.inputmethod.EditorInfo;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.ybq.android.spinkit.SpinKitView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ir.hosseini.shalimanagment.Adapters.ReceiveAdapter;
import ir.hosseini.shalimanagment.Adapters.RicesAdapter;
import ir.hosseini.shalimanagment.R;
import ir.hosseini.shalimanagment.app.Application;
import ir.hosseini.shalimanagment.app.app;
import ir.hosseini.shalimanagment.model.Farmer;
import ir.hosseini.shalimanagment.model.Tahvil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReceivedRicesFragment extends Fragment implements TextView.OnEditorActionListener, View.OnClickListener {

    RecyclerView recyclerView;
    List<Tahvil> list;

    ReceiveAdapter adapter;
    SpinKitView spinKitView;
    Switch grid_switch;
    EditText search_shali_inp;
    ImageButton search_button;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_submitted_shalis, container, false);

        init(root);

        return root;
    }

    private void init(View root) {

        spinKitView = root.findViewById(R.id.loading);
        grid_switch = root.findViewById(R.id.grid_switch);

        search_button = root.findViewById(R.id.search_button);
        search_button.setOnClickListener(this);

        search_shali_inp = root.findViewById(R.id.search_shali_inp);
        search_shali_inp.setOnEditorActionListener(this);

        recyclerView = root.findViewById(R.id.recyclerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(Application.getContext(), 1){
            @Override
            protected boolean isLayoutRTL() {
                return true;
            }
        };
        recyclerView.setLayoutManager(gridLayoutManager);

        grid_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    gridLayoutManager.setSpanCount(2);
                    recyclerView.setLayoutManager(gridLayoutManager);
                }
                else {
                    gridLayoutManager.setSpanCount(1);
                    recyclerView.setLayoutManager(gridLayoutManager);
                }
            }
        });

        recyclerView.setLayoutAnimation(new LayoutAnimationController(AnimationUtils.loadAnimation(Application.getContext(), android.R.anim.slide_in_left)));

        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                int position = parent.getChildAdapterPosition(view);
                if (position == RecyclerView.NO_POSITION) {
                    return;
                }

                if (position == parent.getAdapter().getItemCount() -1) {
                    outRect.bottom = 100;
                }
            }
        });

        list = new ArrayList<>();
        getAllData();

    }

    private void getAllData() {

        Application.getShaliAPI().getAllFarmers().enqueue(new Callback<List<Farmer>>() {
            @Override
            public void onResponse(Call<List<Farmer>> call, Response<List<Farmer>> response) {

                if (response.body() != null) {

                    list.clear();

                    for (Farmer res : response.body()) {
                        try {
                            if (res.getBerenj().getTahvilha() != null) {
                                if (res.getBerenj().getTahvilha().length != 0) {
                                    list.addAll(Arrays.asList(res.getBerenj().getTahvilha()));
                                }
                            }
                        } catch (NullPointerException e) {
                            app.log(e.getMessage());
                        }
                    }

                    adapter = new ReceiveAdapter(ReceivedRicesFragment.this, list);
                    recyclerView.setAdapter(adapter);
                }

                spinKitView.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<List<Farmer>> call, Throwable t) {
                app.toast("error", "دریافت اطلاعات موفق نبود");
                spinKitView.setVisibility(View.GONE);
            }

        });

    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (v.getId() == R.id.search_shali_inp) {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                spinKitView.setVisibility(View.VISIBLE);
                performSearch(v.getText().toString());
                return true;
            }
        }

        return false;
    }

    private void performSearch(String text) {

        if (text.equals("")) {
            getAllData();
            return;
        }

        Application.getShaliAPI().searchFarmer(text).enqueue(new Callback<List<Farmer>>() {
            @Override
            public void onResponse(Call<List<Farmer>> call, Response<List<Farmer>> response) {
                if (response.body() != null) {

                    list.clear();

                    for (Farmer res : response.body()) {
                        if (res.getShaliha() != null) {
                            list.addAll(Arrays.asList(res.getBerenj().getTahvilha()));
                        }
                    }

                }
                else {
                    app.toast("warning", "نتیجه‌ای یافت نشد");

                    list.clear();

                }
                adapter = new ReceiveAdapter(ReceivedRicesFragment.this, list);
                recyclerView.setAdapter(adapter);
                spinKitView.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<List<Farmer>> call, Throwable t) {
                app.toast("error", "دریافت اطلاعات موفق نبود");
                spinKitView.setVisibility(View.GONE);
            }
        });

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.search_button) {
            performSearch(search_shali_inp.getText().toString());
        }
    }
}
