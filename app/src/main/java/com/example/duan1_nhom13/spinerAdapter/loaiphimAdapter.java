package com.example.duan1_nhom13.spinerAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.duan1_nhom13.Model.loaisach;
import com.example.duan1_nhom13.R;

import java.util.ArrayList;

public class loaiphimAdapter extends ArrayAdapter<loaisach> {
    Context context;
    ArrayList<loaisach> list;

    public loaiphimAdapter(@NonNull Context context, ArrayList<loaisach> list) {
        super(context, 0,list);
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if(view == null){
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.itemphim_spn,null);

        }
        loaisach loaisach = list.get(position);
        if (loaisach != null) {
            TextView tv_ls = view.findViewById(R.id.txtSPNtheloai);
            tv_ls.setText(String.valueOf(loaisach.getMaloai())+". "+loaisach.getTenloai());
        }
        return view;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.itemphim_spn, null);
        }
        loaisach loaiSach = list.get(position);
        if (loaiSach != null) {
            TextView tv_ls = view.findViewById(R.id.txtSPNtheloai);
            tv_ls.setText(String.valueOf(loaiSach.getMaloai())+". "+loaiSach.getTenloai());
        }
        return view;    }
}
