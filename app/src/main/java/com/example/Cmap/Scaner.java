package com.example.Cmap;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.HashMap;
import java.util.Map;

import static java.sql.DriverManager.println;

public class Scaner extends AppCompatActivity implements View.OnClickListener {


    Button scanBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scaner);

        scanBtn = findViewById(R.id.scanBtn);
        scanBtn.setOnClickListener(this);

        if (AppHelper.requestQueue == null) {
            AppHelper.requestQueue = Volley.newRequestQueue(getApplicationContext());
        }
    }

    @Override
    public void onClick(View v) {
        scanCode();
    }


    private void scanCode() {
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setCaptureActivity(CaptureAct.class);
        integrator.setOrientationLocked(false);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        integrator.setPrompt("Scanning Code");
        integrator.initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() != null) {
                sendData();
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
//                builder.setMessage(result.getContents());
                builder.setMessage("????????? ?????? ???????????????.");
                builder.setTitle("Scanning Result");
                builder.setPositiveButton("?????? ??????", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        scanCode();
                    }
                }).setNegativeButton("????????? ??????", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();



            } else {
                Toast.makeText(this, "No result", Toast.LENGTH_LONG).show();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }


    //-----------------?????? ???????????????
    public void sendData() {
        String url = "http://115.85.180.33:3000/post";
        if(LoginActivity.ID.equals("starbucks")||LoginActivity.ID.equals("ediya")||LoginActivity.ID.equals("azit")){
            url = "http://115.85.180.33:3000/post";
        }
        else if(LoginActivity.ID.equals("gym1")||LoginActivity.ID.equals("gym2")||LoginActivity.ID.equals("gym3")){
            url = "http://115.85.180.33:3000/post2";
        }else{
            url = "http://115.85.180.33:3000/post3";
        }

        StringRequest request = new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                        processResponse(response);
//                        println("?????? ->" + response);
                        Log.i("onResponse", "in response Write.");
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // println("?????? -> " + error.getMessage());
                    }
                }
        ) {
            // post ???????????? ???????????? ?????? ?????? ???
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();


                params.put("item", LoginActivity.ID);
                return params;
            }
        };
        //-----?????????
        request.setShouldCache(false);
        AppHelper.requestQueue.add(request);
        println("????????? ??????.");
    }
}