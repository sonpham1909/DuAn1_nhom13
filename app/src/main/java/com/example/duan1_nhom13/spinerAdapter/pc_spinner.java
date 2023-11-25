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
import com.example.duan1_nhom13.Model.phonchieu;
import com.example.duan1_nhom13.Model.suatchieu;
import com.example.duan1_nhom13.R;

import java.util.ArrayList;

public class pc_spinner extends ArrayAdapter<phonchieu> {
    Context context;
    ArrayList<phonchieu> list;


    public pc_spinner(@NonNull Context context,ArrayList<phonchieu> list) {
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
            view = layoutInflater.inflate(R.layout.item_spnpc, null);

        }
        phonchieu phonchieu = list.get(position);
        TextView tv_pc = null;
        if (phonchieu != null) {
            tv_pc = view.findViewById(R.id.tv_spntenSC);
            TextView tv_sg = view.findViewById(R.id.tv_spnsoghe);
            tv_pc.setText(String.valueOf(phonchieu.getMaPC() + ". " + phonchieu.getTenPC()));
            tv_sg.setText("số ghế: " + phonchieu.getSoGhe() + "");
        }
        return view;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.item_spnpc, null);

        }
        phonchieu phonchieu = list.get(position);
        TextView tv_pc = null;
        if (phonchieu != null) {
            tv_pc = view.findViewById(R.id.tv_spntenSC);
            TextView tv_sg = view.findViewById(R.id.tv_spnsoghe);
            tv_pc.setText(String.valueOf(phonchieu.getMaPC() + ". " + phonchieu.getTenPC()));
            tv_sg.setText("số ghế: " + phonchieu.getSoGhe() + "");
        }
        return view;
    }
}
