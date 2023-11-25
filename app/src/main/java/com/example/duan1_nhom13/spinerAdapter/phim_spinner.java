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
import com.example.duan1_nhom13.Model.phonchieu;
import com.example.duan1_nhom13.R;

import java.util.ArrayList;

public class phim_spinner extends ArrayAdapter<Phim> {

    Context context;
    ArrayList<Phim> list;


    public phim_spinner(@NonNull Context context, ArrayList<Phim> list) {
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
            view = layoutInflater.inflate(R.layout.item_spinner_phim, null);

        }
        Phim phim = list.get(position);
        TextView tv_pc = null;
        if (phim != null) {
            tv_pc = view.findViewById(R.id.tvTenPhimSPN);
            TextView tv_sg = view.findViewById(R.id.tvgiaVeSPN);
            tv_pc.setText(String.valueOf(phim.getMaPhim() + ". " + phim.getTen()));
            tv_sg.setText("giá vé: " + phim.getGia() + "");
        }
        return view;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.item_spinner_phim, null);

        }
        Phim phim = list.get(position);
        TextView tv_pc = null;
        if (phim != null) {
            tv_pc = view.findViewById(R.id.tvTenPhimSPN);
            TextView tv_sg = view.findViewById(R.id.tvgiaVeSPN);
            tv_pc.setText(String.valueOf(phim.getMaPhim() + ". " + phim.getTen()));
            tv_sg.setText("giá vé: " + phim.getGia() + "");
        }
        return view;
    }
}
