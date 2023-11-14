package com.example.duan1_nhom13;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.duan1_nhom13.DAO.adminDAO;
import com.example.duan1_nhom13.Model.user;

import java.util.ArrayList;

public class header_layout extends AppCompatActivity {
    user us;
    adminDAO dao;
    ArrayList<user> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_header_layout);

        SharedPreferences sharedPreferences = getSharedPreferences("thongtin",MODE_PRIVATE);
        String user = sharedPreferences.getString("user","");

        TextView txtuser = findViewById(R.id.user_name);
        TextView txtfullname = findViewById(R.id.user_full_name);
        TextView txtrole = findViewById(R.id.txt_role);
        us = new user();

        dao = new adminDAO(this);
        us = dao.getUser(user);






        txtuser.setText(user);
        txtfullname.setText(us.getTen());
        if(us.getRole()==1){
            txtrole.setText("Quản lý");
        }else{
            txtrole.setText("Nhân viên");
        }
//        Intent resultIntent = new Intent();
//        resultIntent.putExtra("dataChanged", true); // Truyền thông báo rằng dữ liệu đã thay đổi
//        setResult(Activity.RESULT_OK, resultIntent);
//        finish();


//        txtfullname.setText();
//
//        txtfullname.setText(us.getTen());
//        if(us.getRole()==1){
//            txtrole.setText("Quản lý");
//        }else{
//            txtrole.setText("Nhân viên");
//        }

    }

}