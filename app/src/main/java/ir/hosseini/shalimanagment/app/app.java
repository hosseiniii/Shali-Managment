package ir.hosseini.shalimanagment.app;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

import es.dmoral.toasty.Toasty;
import ir.hosseini.shalimanagment.R;

import static ir.hosseini.shalimanagment.app.app.main.TAG;

public class app {



    public static class main {
        public static final String TAG = "ShalikarManagement";
        public static final String URL = "http://shali-firstsite.fandogh.cloud/api/";
    }

    public static void log(String message) {
        Log.e(TAG , message);
    }


    @SuppressLint("ResourceType")
    public static void toast(String type, String message) {

        Typeface typeface = ResourcesCompat.getFont(Application.getContext(), R.font.iran_yekan_regular);

        Context context = Application.getContext();

        Toasty.Config.getInstance()
                .setToastTypeface(typeface)
                .apply();

        switch (type) {
            case "error":
                Toasty.error(context, message, Toast.LENGTH_SHORT, true).show();
                break;
            case "success":
                Toasty.success(context, message, Toast.LENGTH_SHORT, true).show();
                break;
            case "info":
                Toasty.info(context, message, Toast.LENGTH_SHORT, true).show();
                break;
            case "warning":
                Toasty.warning(context, message, Toast.LENGTH_SHORT, true).show();
                break;
            case "normal":
                @SuppressLint("ResourceType") Drawable d = null;
                try {
                    d = Drawable.createFromXml(context.getResources(), context.getResources().getXml(R.drawable.ic_view_headline_black_24dp));
                } catch (IOException | XmlPullParserException e) {
                    e.printStackTrace();
                }
                Toasty.normal(context, message, Toast.LENGTH_SHORT, d).show();
                break;
        }

    }

    /* TODO: Useful Libraries
    *   1. Retrofit
    *   2. GSON or Moshi
    *   3. lottie // animation // https://github.com/airbnb/lottie-android
    *   4. toasty or StyleableToast
    *   toasty: https://github.com/GrenderG/Toasty
    *   StyleableToast: https://github.com/Muddz/StyleableToast
    *   5. chuck // maybe
    *   6. shimmer and shimmer recyclerview
    *   7. mpchart // https://github.com/PhilJay/MPAndroidChart
    *   8. Glide
    *   9. Swipe Layout // https://github.com/daimajia/AndroidSwipeLayout/wiki/usage
    *   10. Android Charts // https://github.com/HackPlan/AndroidCharts
    *   11. Gesture // https://github.com/nisrulz/Sensey
    *   12. Location // https://github.com/mrmans0n/smart-location-lib
    *   13. textDrawable // https://github.com/amulyakhare/TextDrawable
    *   14. spinKit // https://github.com/ybq/Android-SpinKit
    * */

    /*
    *
    <com.github.ybq.android.spinkit.SpinKitView
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:id="@+id/spin_kit"
    style="@style/SpinKitView.Large.Circle"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layout_gravity="center"
    app:SpinKit_Color="@color/colorAccent" />
    *
    * */

}
