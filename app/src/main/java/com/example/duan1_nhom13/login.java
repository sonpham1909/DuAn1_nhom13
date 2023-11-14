package com.example.duan1_nhom13;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duan1_nhom13.DAO.adminDAO;
import com.example.duan1_nhom13.Model.user;

import java.util.ArrayList;

public class login extends AppCompatActivity {
    EditText edtuser,edtpass;
    CheckBox chkremember;
    Button btnlogin;
    adminDAO dao;
    user us;
    ArrayList<user> list = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edtuser = findViewById(R.id.user);
        edtpass= findViewById(R.id.pass);
        chkremember = findViewById(R.id.remember);
        btnlogin = findViewById(R.id.btnlogin);
        dao =new adminDAO(this);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = edtuser.getText().toString();
                String pass = edtpass.getText().toString();


                boolean check = dao.Kiemtradangnhap(user,pass);

               if(TextUtils.isEmpty(edtuser.getText().toString())||TextUtils.isEmpty(edtpass.getText().toString())){
                   Toast.makeText(login.this, "Không để trống", Toast.LENGTH_SHORT).show();
               }else{
                   if(check){
                       //lưu sharedpreferrences
                       SharedPreferences sharedPreferences = getSharedPreferences("thongtin",MODE_PRIVATE);
                       SharedPreferences.Editor editor = sharedPreferences.edit();
                       editor.putString("user",user);
                       editor.putString("pass",pass);
                       editor.putBoolean("success",true);




                       editor.putBoolean("save",chkremember.isChecked());
                       editor.commit();











Intent intent = new Intent(login.this,home.class);


                       intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

                       startActivity(intent.putExtra("username",user));



                   }else{
                       Toast.makeText(login.this, "Sai tài khoản hoặc mật khẩu", Toast.LENGTH_SHORT).show();
                   }
               }
            }
        });



    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = getSharedPreferences("thongtin",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String user = sharedPreferences.getString("user","");
        String pass = sharedPreferences.getString("pass","");
        boolean save = sharedPreferences.getBoolean("save",false);
        if(save==true){
            edtuser.setText(user);
            edtpass.setText(pass);
            chkremember.setChecked(true);
        }else{
            editor.clear();
        }
    }


}