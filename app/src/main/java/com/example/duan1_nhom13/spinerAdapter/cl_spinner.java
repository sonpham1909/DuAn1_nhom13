package com.example.duan1_nhom13.spinerAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.duan1_nhom13.Model.calam;
import com.example.duan1_nhom13.Model.khachhang;
import com.example.duan1_nhom13.R;

import java.util.ArrayList;

public class cl_spinner extends ArrayAdapter<calam> {
    Context context;
    ArrayList<calam> list;


    public cl_spinner(@NonNull Context context, ArrayList<calam> list) {
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
            view = layoutInflater.inflate(R.layout.item_ca_lam_spinner, null);

        }
        calam cl=  list.get(position);
        TextView tv_pc = null;
        TextView tv_gbd = null;
        TextView tv_gkt = null;

        if (cl != null) {
            tv_pc = view.findViewById(R.id.tvtenca);
            tv_gbd = view.findViewById(R.id.tvgbdspn);
            tv_gkt = view.findViewById(R.id.tvgktspn);

            tv_pc.setText(String.valueOf(cl.getMaca()+ ". " +cl.getTenCa()));
            tv_gbd.setText(cl.getGioBD());
            tv_gkt.setText(cl.getGioKT());

        }
        return view;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.item_ca_lam_spinner, null);

        }
        calam cl=  list.get(position);
        TextView tv_pc = null;
        TextView tv_gbd = null;
        TextView tv_gkt = null;

        if (cl != null) {
            tv_pc = view.findViewById(R.id.tvtenca);
            tv_gbd = view.findViewById(R.id.tvgbdspn);
            tv_gkt = view.findViewById(R.id.tvgktspn);

            tv_pc.setText(String.valueOf(cl.getMaca()+ ". " +cl.getTenCa()));
            tv_gbd.setText(cl.getGioBD());
            tv_gkt.setText(cl.getGioKT());

        }
        return view;
    }
}
