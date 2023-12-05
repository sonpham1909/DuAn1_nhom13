package com.example.duan1_nhom13.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1_nhom13.DAO.adminDAO;
import com.example.duan1_nhom13.Model.user;
import com.example.duan1_nhom13.R;

import java.util.ArrayList;

public class xepCa_Adapter extends RecyclerView.Adapter<xepCa_Adapter.Viewholder>{
    private Context context;
    private ArrayList<user> list;
    adminDAO dao;

    public xepCa_Adapter(Context context, ArrayList<user> list) {
        this.context = context;
        this.list = list;
        dao = new adminDAO(context);
    }



    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_nhan_vien, null);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class Viewholder extends RecyclerView.ViewHolder{
        TextView tvtennv, tvchucvu, tvsdt;
        CardView card;


        public Viewholder(@NonNull View itemView) {
            super(itemView);
            tvtennv = itemView.findViewById(R.id.tvtenNhanvien);
            tvsdt = itemView.findViewById(R.id.tvSdt);
            tvchucvu = itemView.findViewById(R.id.tvrole);
            card = itemView.findViewById(R.id.cardNV);
        }
    }
}
