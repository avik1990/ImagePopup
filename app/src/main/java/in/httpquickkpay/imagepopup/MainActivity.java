package in.httpquickkpay.imagepopup;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Environment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.DownloadListener;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    List<String> imagelist = new ArrayList<>();
    Button btn_popup;
    Dialog myDialog;
    Context mContext;
    ////////////////////////////////////////////////////
    public final String downloadDirectory = "EduApp";
    SimpleDateFormat dateFormat, dateFormat1;
    Calendar cal;
    Button btn_upload;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH:mm:ss");
        cal = Calendar.getInstance();
        Log.d("time=>", dateFormat.format(cal.getTime()));
       // savepath();
        initview();

    }


    private void initview() {
        myDialog = new Dialog(this);
        imagelist.clear();
        imagelist.add("http://lmet-rfidcloud.croyezdem.com/uploaded/5b6753cc4ab6f_IMG-20180805-WA0004.jpg");
        imagelist.add("http://lmet-rfidcloud.croyezdem.com/uploaded/5b6753cc4a739_IMG-20180805-WA0005.jpg");
        imagelist.add("http://lmet-rfidcloud.croyezdem.com/uploaded/5b6753cc4a2f4_IMG-20180805-WA0000.jpg");
        imagelist.add("http://lmet-rfidcloud.croyezdem.com/uploaded/5b6753cc49ed7_IMG-20180805-WA0009.jpg");
        imagelist.add("http://lmet-rfidcloud.croyezdem.com/uploaded/5b6753cc49ada_IMG-20180805-WA0010.jpg");

        btn_popup = findViewById(R.id.btn_popup);
        btn_upload= findViewById(R.id.btn_upload);
        btn_popup.setOnClickListener(this);
        btn_upload.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == btn_popup) {
            ShowPopup();
        } else if (v == btn_upload) {
            ShowPopup();
        }

    }


    public void ShowPopup() {
        ImageView iv_close, iv_download;
        final ViewPager vpager;
        myDialog.setContentView(R.layout.custom_popup);
        iv_close = myDialog.findViewById(R.id.iv_close);
        iv_download = myDialog.findViewById(R.id.iv_download);
        vpager = myDialog.findViewById(R.id.vpager);
        iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });

        iv_download.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Picasso.get()
                        .load(imagelist.get(vpager.getCurrentItem()))
                        .into(new PhotoLoader(mContext,dateFormat.format(cal.getTime())));
            }
        });

        if (imagelist.size() > 0) {
            ViewPagerAdapter vadapter = new ViewPagerAdapter(mContext, imagelist);
            vpager.setAdapter(vadapter);
        }

        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }

}
