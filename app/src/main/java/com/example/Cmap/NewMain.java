package com.example.Cmap;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Calendar;

import static com.example.Cmap.MainActivity.IsLogined;

public class NewMain extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
    public static Boolean IsLogined2 = Boolean.FALSE;
    public static int kind = 1;
    public static String daily;

    private TextView result;
    private static int ONE_MINUTE = 5626;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_main);


        new AlarmHATT(getApplicationContext()).Alarm();






        result = (TextView)findViewById(R.id.result);
        getWebsite();

        Button Button7 = (Button) findViewById(R.id.button7);
        Button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(IsLogined2 || IsLogined) {
                    Intent intent2 = new Intent(getApplicationContext(), Scaner.class);
                    IsLogined2 = Boolean.TRUE;
                    startActivity(intent2);
                }
                else{
                    Intent intent5 = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivityForResult(intent5, 101);
                }
            }
        });

        Button Button8 = (Button) findViewById(R.id.button8);
        Button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent(getApplicationContext(), MyQr.class);
                startActivity(intent4);

            }
        });

        Button Button9 = (Button) findViewById(R.id.button9);
        Button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent(getApplicationContext(), News.class);
                startActivity(intent4);

            }
        });



        Intent intent = new Intent(this, Loading.class);
        startActivityForResult(intent, 104);
    }




    public  void showPopup(View v){
        PopupMenu popup = new PopupMenu(this, v);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.popup_menu);
        popup.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
                Toast.makeText(this, "카페", Toast.LENGTH_SHORT).show();
                Intent intent45 = new Intent(getApplicationContext(), MainActivity.class);
                intent45.putExtra("case", 1);
                kind = 1;
                startActivity(intent45);

                return true;
            case R.id.item2:
                Toast.makeText(this, "헬스장", Toast.LENGTH_SHORT).show();
                Intent intent46 = new Intent(getApplicationContext(), MainActivity.class);
                intent46.putExtra("case", 2);
                kind = 2;
                startActivity(intent46);
                return true;
            case R.id.item3:
                Toast.makeText(this, "식", Toast.LENGTH_SHORT).show();
                Intent intent47 = new Intent(getApplicationContext(), MainActivity.class);
                intent47.putExtra("case", 3);
                kind = 3;
                startActivity(intent47);
                return true;
            default:
                return false;
        }
    }

    private void getWebsite(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                final StringBuilder builder = new StringBuilder();
                try{
                    Document doc = Jsoup.connect("https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=1&ie=utf8&query=%EC%BD%94%EB%A1%9C%EB%82%98").get();
                    //String title = doc.title();
                    Elements links = doc.select("div.status_today li.info_02 em.info_num");

                    builder.append("국내 발생 : ").append(links.text());
                    builder.append("명 ").append("\n");
                    links = doc.select("div.status_today li.info_03 em.info_num");
                    builder.append("해외 유입 : ");
                    builder.append(links.text());
                    builder.append("명 ");
                } catch(IOException e){
                    builder.append("Error");
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        result.setText(builder.toString());
                    }
                });
            }
        }).start();
    }

    public class AlarmHATT {
        private Context context;
        public AlarmHATT(Context context) {
            this.context=context;
        }
        public void Alarm() {
            AlarmManager am = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
            Intent intent = new Intent(NewMain.this, BroadcastD.class);

            PendingIntent sender = PendingIntent.getBroadcast(NewMain.this, 0, intent, 0);

            Calendar calendar = Calendar.getInstance();
            //알람시간 calendar에 set해주기

            calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), 13, 42, 0);

            //알람 예약
            am.set(AlarmManager.RTC, calendar.getTimeInMillis(), sender);
        }
    }

}