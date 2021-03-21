package com.example.Cmap;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;

import static com.example.Cmap.NewMain.IsLogined2;
import static com.example.Cmap.NewMain.kind;


public class MainActivity<locationMgr> extends AppCompatActivity {

    public static Boolean IsLogined = Boolean.FALSE;
    private String userId;
    private String userName;
    private WebView webView;
    private String url = "http://115.85.180.33:3000";



    @Override
    protected void onCreate(Bundle savedInstanceState) {


       super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (kind == 1){
            url = "http://115.85.180.33:3000";
        }
        else if (kind == 2) {
            url = "http://115.85.180.33:3000/gym";
        }
        else {
            url = "http://115.85.180.33:3000/restaurant";
        }
        super.onResume();



        webView = (WebView) findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClientClass());

        if (AppHelper.requestQueue == null) {
            AppHelper.requestQueue = Volley.newRequestQueue(getApplicationContext());
        }


        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(IsLogined || IsLogined2) {
                    Intent intent2 = new Intent(getApplicationContext(), Scaner.class);
                    MainActivity.IsLogined = Boolean.TRUE;
                    startActivity(intent2);
                }
                else{
                    Intent intent5 = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivityForResult(intent5, 101);
                }

            }
        });


        //reload 버튼을 누르면 위경도 정보랑 웹 새로고침



        Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //웹 새로고침
                webView.reload();
                // 위경도 구해서 보내기

                String url = "http://49.50.173.234:3000/post";
                StringRequest request = new StringRequest(
                        Request.Method.POST,
                        url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                            }
                        }
                ) {
                    // post 방식일때 파라미터 전달 하는 법
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<String, String>();


                          params.put("lng", "127.127272");
                          params.put("lat", "37.322964");
//                          params.put("item", "diddididiididii");



                        return params;
                    }
                };
                request.setShouldCache(false);
                AppHelper.requestQueue.add(request);



            }
        });

        Button Button3 = (Button) findViewById(R.id.button3);
        Button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView.loadUrl("http://115.85.180.33:3000/gym");
                // 위경도 구해서 보내기

                String url = "http://115.85.180.33:3000/gym";
                StringRequest request = new StringRequest(
                        Request.Method.POST,
                        url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                            }
                        }
                ) {
                    // post 방식일때 파라미터 전달 하는 법
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<String, String>();


                        params.put("lng", "127.127272");
                        params.put("lat", "37.322964");
//                          params.put("item", "diddididiididii");



                        return params;
                    }
                };
                request.setShouldCache(false);
                AppHelper.requestQueue.add(request);


            }
        });

        Button button5 = (Button) findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView.loadUrl("http://115.85.180.33:3000/restaurant");
                // 위경도 구해서 보내기

                String url = "http://115.85.180.33:3000/restaurant";
                StringRequest request = new StringRequest(
                        Request.Method.POST,
                        url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                            }
                        }
                ) {
                    // post 방식일때 파라미터 전달 하는 법
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<String, String>();


                        params.put("lng", "127.127272");
                        params.put("lat", "37.322964");
//                          params.put("item", "diddididiididii");



                        return params;
                    }
                };
                request.setShouldCache(false);
                AppHelper.requestQueue.add(request);
            }
        });

        Button button10 = (Button) findViewById(R.id.button10);
        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView.loadUrl("http://115.85.180.33:3000");
                // 위경도 구해서 보내기

                String url = "http://115.85.180.33:3000";
                StringRequest request = new StringRequest(
                        Request.Method.POST,
                        url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                            }
                        }
                ) {
                    // post 방식일때 파라미터 전달 하는 법
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<String, String>();


                        params.put("lng", "127.127272");
                        params.put("lat", "37.322964");
//                          params.put("item", "diddididiididii");



                        return params;
                    }
                };
                request.setShouldCache(false);
                AppHelper.requestQueue.add(request);
            }
        });

        Button QrButton = (Button) findViewById(R.id.QrButton);
        QrButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent(getApplicationContext(), MyQr.class);
                startActivity(intent4);



            }
        });




    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        /*if (requestCode == 101){
           *//* data.getStringExtra("name");*//*
        }*/
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()){
            webView.goBack();
            finish();
            return true;
        }


        return super.onKeyDown(keyCode, event);
    }

    private class WebViewClientClass extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            view.loadUrl(url);
            return true;
        }
    }



}