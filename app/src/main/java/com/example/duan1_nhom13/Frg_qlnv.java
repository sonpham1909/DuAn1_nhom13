package com.example.duan1_nhom13;

import android.app.Dialog;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class Frg_qlnv extends Fragment {
    FloatingActionButton fltbtn;


    public Frg_qlnv() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_frg_qlnv, container, false);
        // Inflate the layout for this fragment

        fltbtn = view.findViewById(R.id.btnaddnv);
        fltbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                them();

            }
        });

        return view;
    }
    public  void  them(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view = getLayoutInflater().inflate(R.layout.add_qlnv,null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();
    }
}