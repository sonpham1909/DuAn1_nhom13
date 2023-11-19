package com.example.duan1_nhom13.Adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1_nhom13.DAO.loaisachDAO;
import com.example.duan1_nhom13.DAO.phimDAO;
import com.example.duan1_nhom13.Model.Phim;
import com.example.duan1_nhom13.Model.loaisach;
import com.example.duan1_nhom13.R;

import java.util.ArrayList;

public class PhimAdapter extends RecyclerView.Adapter<PhimAdapter.Viewholder> {
Context context;
ArrayList<Phim> list;
phimDAO dao;
    ArrayList<loaisach> loaiSaches;

    public PhimAdapter(Context context, ArrayList<Phim> list) {
        this.context = context;
        this.list = list;
        dao = new phimDAO(context);
        loaiSaches = new ArrayList<>();
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = ((Activity)context).getLayoutInflater();
        View  view = layoutInflater.inflate(R.layout.item_phim,null);
        return  new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {

        Phim phim = list.get(position);
if(phim == null){
    return;
}else {
    String tenLoai;
    try {
        loaisachDAO loaiSachDao;
        loaiSachDao = new loaisachDAO(context);
        loaisach loaiSach = loaiSachDao.getId(String.valueOf(phim.getMaloai()));
        tenLoai = loaiSach.getTenloai();
    } catch (Exception e) {
        tenLoai = "Đã xóa loại sách";

    }


    byte[] hih = phim.getAnh();
    Bitmap bitmap = BitmapFactory.decodeByteArray(hih, 0, hih.length);
    holder.imghinh.setImageBitmap(bitmap);



    holder.tv_mp.setText(phim.getMaPhim() + "");
    holder.tv_ml.setText(tenLoai);
    holder.tv_gia.setText( phim.getGia() + " "+"VND");
    holder.tv_thoiluong.setText(phim.getThoiluong() + " "+"phút");
    holder.tv_ten.setText(phim.getTen());

}

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class Viewholder extends RecyclerView.ViewHolder {
        TextView tv_mp, tv_ten, tv_gia, tv_thoiluong, tv_ml;
        ImageView imghinh;


        public Viewholder(@NonNull View itemView) {
            super(itemView);
            imghinh = itemView.findViewById(R.id.imgPhim);
            tv_mp = itemView.findViewById(R.id.tvmaphim);
            tv_ten = itemView.findViewById(R.id.tvtenphim);
            tv_gia = itemView.findViewById(R.id.tvgiave);
            tv_thoiluong = itemView.findViewById(R.id.tvthoiluong);
            tv_ml = itemView.findViewById(R.id.tvtheloai);


        }
    }
}
