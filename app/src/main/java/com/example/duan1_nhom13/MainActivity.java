package com.example.duan1_nhom13;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnget = findViewById(R.id.btngetst);
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