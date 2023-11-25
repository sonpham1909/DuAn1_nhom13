package com.example.duan1_nhom13.Fragment;

import android.app.ActionBar;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.duan1_nhom13.R;
import com.example.duan1_nhom13.home;


public class man_hinh_chinh extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_man_hinh_chinh, container, false);
        // Inflate the layout for this fragment
        SharedPreferences preferences = getContext().getSharedPreferences("role", Context.MODE_PRIVATE);
String fullName = preferences.getString("fullname","");
        TextView txt = view.findViewById(R.id.hotenUser);
        ImageView imgTT = view.findViewById(R.id.imgttin);
        ImageView imHD = view.findViewById(R.id.hoadon);
        ImageView imPhim = view.findViewById(R.id.dsphim);
        Button themKH = view.findViewById(R.id.btntaohoadon);

        txt.setText(fullName);
        imgTT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                Fragment fragment = new thong_tin_tai_khoan();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(((home) getActivity()).getFragmentContainerId(), fragment);
                // Trong Activity

                fragmentTransaction.commit();
            }
        });
        imHD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                Fragment fragment = new Quan_ly_hoa_don();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(((home) getActivity()).getFragmentContainerId(), fragment);
                // Trong Activity

                fragmentTransaction.commit();
            }
        });
        imPhim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                Fragment fragment = new quan_ly_phim();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(((home) getActivity()).getFragmentContainerId(), fragment);
                // Trong Activity

                fragmentTransaction.commit();
            }
        });
        themKH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                Fragment fragment = new Quan_ly_khach_hang();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(((home) getActivity()).getFragmentContainerId(), fragment);
                // Trong Activity

                fragmentTransaction.commit();
            }
        });
        return view;
    }
}