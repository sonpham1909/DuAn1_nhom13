package com.example.duan1_nhom13.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.duan1_nhom13.R;


public class thong_tin_tai_khoan extends Fragment {



    public thong_tin_tai_khoan() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_thong_tin_tai_khoan, container, false);
        // Inflate the layout for this fragment

        TextView tvten = view.findViewById(R.id.HOTEN);
        TextView tvdn = view.findViewById(R.id.TENDN);
        TextView tvcv = view.findViewById(R.id.CHUCVU);

        SharedPreferences preferences = getContext().getSharedPreferences("role", Context.MODE_PRIVATE);
        String fullName = preferences.getString("fullname","");
        int role = preferences.getInt("role",1);
        SharedPreferences preferences1 = getContext().getSharedPreferences("thongtin", Context.MODE_PRIVATE);
        String user= preferences1.getString("user","");
        tvten.setText(fullName);
        tvdn.setText(user);
        if(role==1){tvcv.setText("Quản lý");
        }else{
            tvcv.setText("Nhân viên");
        }

        return view;
    }
}