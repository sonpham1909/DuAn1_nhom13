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
import com.example.duan1_nhom13.Model.suatchieu;
import com.example.duan1_nhom13.R;

import java.util.ArrayList;

public class sc_spinner extends ArrayAdapter<suatchieu> {
    Context context;
    ArrayList<suatchieu> list;


    public sc_spinner(@NonNull Context context, ArrayList<suatchieu> list) {
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
            view = layoutInflater.inflate(R.layout.item_spinner_suat_chieu, null);

        }
       suatchieu suatchieu =list.get(position);
        TextView tv_pc = null;
        if (suatchieu!= null) {
            tv_pc = view.findViewById(R.id.tvTenSCSPinner);

            tv_pc.setText(String.valueOf(suatchieu.getMaSC() + ". " + suatchieu.getTenSC()));

        }
        return view;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.item_spinner_suat_chieu, null);

        }
        suatchieu suatchieu =list.get(position);
        TextView tv_pc = null;
        if (suatchieu!= null) {
            tv_pc = view.findViewById(R.id.tvTenSCSPinner);

            tv_pc.setText(String.valueOf(suatchieu.getMaSC() + ". " + suatchieu.getTenSC()));

        }
        return view;
    }
}
