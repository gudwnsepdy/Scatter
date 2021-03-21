package com.example.Cmap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    public static String ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText editText1 = findViewById(R.id.editText1);
        final EditText editText2 = findViewById(R.id.editText2);


        Button button4 = (Button) findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ID = editText1.getText().toString();

                String PASS = editText2.getText().toString();


                if ((ID.equals("starbucks") && PASS.equals("aa")) || (ID.equals("ediya") && PASS.equals("bb"))|| (ID.equals("azit") && PASS.equals("cc")) || (ID.equals("gym1") && PASS.equals("aa"))|| (ID.equals("gym2") && PASS.equals("bb"))|| (ID.equals("gym3") && PASS.equals("cc"))|| (ID.equals("r1") && PASS.equals("aa"))|| (ID.equals("r2") && PASS.equals("bb"))|| (ID.equals("r3") && PASS.equals("cc"))) {
                    Intent intent2 = new Intent(getApplicationContext(), Scaner.class);
                    NewMain.IsLogined2 = Boolean.TRUE;
                    startActivity(intent2);
                }
                else {
                    String worng = "아이디나 비밀번호를 확인해 주세요.";
                    Toast.makeText(getApplicationContext(), worng, Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}