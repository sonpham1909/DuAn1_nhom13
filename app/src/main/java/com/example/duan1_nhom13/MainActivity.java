package com.example.duan1_nhom13;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnget = findViewById(R.id.btngetst);
        SharedPreferences sharedPreferences = getSharedPreferences("thongtin", MODE_PRIVATE);
        String loginsc = sharedPreferences.getString("user","");


        if (loginsc.equals("")) {




        }else{
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Đang load...!");
            builder.setMessage("Đang đăng nhập");
            builder.create().show();

            try {

                Thread.sleep(1000); // Chờ đợi 1 giây (1000 mili giây)


                String user = sharedPreferences.getString("user", "");

                Intent intent1 = new Intent(MainActivity.this, home.class);

                startActivity(intent1.putExtra("username", user));
                finish();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
            btnget.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(MainActivity.this, login.class);

                    startActivity(intent);


                    ObjectAnimator animator = ObjectAnimator.ofFloat(btnget, "translationY", btnget.getHeight(), 0);
                    animator.setDuration(500);
                    animator.start();
                }
            });
        }

}