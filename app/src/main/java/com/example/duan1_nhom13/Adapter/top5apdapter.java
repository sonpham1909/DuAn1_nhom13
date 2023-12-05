package com.example.duan1_nhom13.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.duan1_nhom13.Fragment.thong_ke;
import com.example.duan1_nhom13.Model.top;
import com.example.duan1_nhom13.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class top5apdapter extends ArrayAdapter<top> {
    Context context;
    thong_ke fragment;
    ArrayList<top> list;

    public top5apdapter(@NonNull @NotNull Context context, thong_ke fragment, ArrayList<top> list) {
        super(context, 0, list);
        this.context = context;
        this.fragment = fragment;
        this.list = list;
    }

    TextView tv_tenstk, tv_sltk;

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_top5, null);
        }
        top top = list.get(position);
        if (top != null) {
            tv_tenstk = view.findViewById(R.id.tenphim);
            tv_tenstk.setText("Phim: " + top.getTenPhim());
            tv_sltk = view.findViewById(R.id.Sove);
            tv_sltk.setText("Số Lượng vé: " + top.getSove());
        }

        return view;
    }
}
