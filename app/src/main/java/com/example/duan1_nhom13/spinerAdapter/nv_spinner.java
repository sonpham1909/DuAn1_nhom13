package com.example.duan1_nhom13.spinerAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.duan1_nhom13.Model.khachhang;
import com.example.duan1_nhom13.Model.user;
import com.example.duan1_nhom13.R;

import java.util.ArrayList;

public class nv_spinner extends ArrayAdapter<user> {
    Context context;
    ArrayList<user> list;


    public nv_spinner(@NonNull Context context, ArrayList<user> list) {
        super(context, 0,list);
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.item_nhan_vien_spinner, null);

        }
        user us = list.get(position);
        TextView tv_pc = null;
        TextView tv_ma = null;
        if (us != null) {
            tv_pc = view.findViewById(R.id.tennhanvien);
            tv_ma = view.findViewById(R.id.manhanvien);

            tv_pc.setText(us.getTen());
            tv_ma.setText(us.getUser());

        }
        return view;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.item_nhan_vien_spinner, null);

        }
        user us = list.get(position);
        TextView tv_pc = null;
        TextView tv_ma = null;
        if (us != null) {
            tv_pc = view.findViewById(R.id.tennhanvien);
            tv_ma = view.findViewById(R.id.manhanvien);

            tv_pc.setText(us.getTen());
            tv_ma.setText(us.getUser());

        }
        return view;
    }
}
