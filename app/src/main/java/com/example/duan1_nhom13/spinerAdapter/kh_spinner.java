package com.example.duan1_nhom13.spinerAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.duan1_nhom13.Model.Phim;
import com.example.duan1_nhom13.Model.khachhang;
import com.example.duan1_nhom13.R;

import java.util.ArrayList;

public class kh_spinner extends ArrayAdapter<khachhang> {
    Context context;
    ArrayList<khachhang> list;


    public kh_spinner(@NonNull Context context, ArrayList<khachhang> list) {
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
            view = layoutInflater.inflate(R.layout.item_spinner_khachhang, null);

        }
        khachhang khachhang= list.get(position);
        TextView tv_pc = null;
        if (khachhang != null) {
            tv_pc = view.findViewById(R.id.tvTenKhachHangSPN);

            tv_pc.setText(String.valueOf(khachhang.getMaKH() + ". " + khachhang.getTenKH()));

        }
        return view;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.item_spinner_khachhang, null);

        }
        khachhang khachhang= list.get(position);
        TextView tv_pc = null;
        if (khachhang != null) {
            tv_pc = view.findViewById(R.id.tvTenKhachHangSPN);

            tv_pc.setText(String.valueOf(khachhang.getMaKH() + ". " + khachhang.getTenKH()));

        }
        return view;
    }
}
