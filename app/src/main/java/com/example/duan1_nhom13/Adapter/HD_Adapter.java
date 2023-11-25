package com.example.duan1_nhom13.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1_nhom13.DAO.HDDAO;
import com.example.duan1_nhom13.DAO.KHDAO;
import com.example.duan1_nhom13.DAO.adminDAO;
import com.example.duan1_nhom13.DAO.phimDAO;
import com.example.duan1_nhom13.DAO.phongchieuDAO;
import com.example.duan1_nhom13.DAO.suatchieuDAO;
import com.example.duan1_nhom13.Model.Phim;
import com.example.duan1_nhom13.Model.hoadon;
import com.example.duan1_nhom13.Model.khachhang;
import com.example.duan1_nhom13.Model.phonchieu;
import com.example.duan1_nhom13.Model.suatchieu;
import com.example.duan1_nhom13.Model.user;
import com.example.duan1_nhom13.R;

import java.util.ArrayList;

public class HD_Adapter extends RecyclerView.Adapter<HD_Adapter.Viewholder> {




    private Context context;
    private ArrayList<hoadon> list;
    String tenPhim;
    String TenKH;
    String TenSC;


    HDDAO hddao;

    public HD_Adapter(Context context, ArrayList<hoadon> list) {
        this.context = context;
        this.list = list;
        hddao = new HDDAO(context);
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = ((Activity)context).getLayoutInflater().inflate(R.layout.item_hoa_don,null);

        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        hoadon hd = list.get(position);


        if(hd== null){
            return;
        }else {

            try {
                phimDAO pdao = new phimDAO(context);

                Phim phim = pdao.getId(String.valueOf(hd.getMaPhim()));
                tenPhim = phim.getTen();

            } catch (Exception e) {
                tenPhim = "Đã xóa phim";

            }


            user us;
            us = new user();

           adminDAO dao = new adminDAO(context);
            us = dao.getUser(hd.getMaNV());

            SharedPreferences preferences = context.getSharedPreferences("role", Context.MODE_PRIVATE);
            String fullName = preferences.getString("fullname","");





            holder.tvmaHD.setText("Mã HD: "+hd.getMaHD());
            holder.tvmaNV.setText("NV thanh toán: "+us.getTen());
            holder.tvSLve.setText("SL vé: "+hd.getSlVe());
            holder.tvNgayIn.setText("Ngày chiếu: "+hd.getNgayInHoaDon());
            holder.tvtenPhim.setText("Phim: "+tenPhim);


            //xl cardview
            holder.ln.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ttin(hd);
                }
            });



        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static  class Viewholder extends RecyclerView.ViewHolder {
        TextView tvmaHD,tvmaNV,tvSLve,tvtenPhim,tvNgayIn;
        LinearLayout ln;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            tvmaHD = itemView.findViewById(R.id.tvMaHoaDon);
            tvmaNV= itemView.findViewById(R.id.tvNhanVien);
            tvSLve = itemView.findViewById(R.id.tvSLVe);
            tvtenPhim = itemView.findViewById(R.id.tvTenPhim);
            tvNgayIn = itemView.findViewById(R.id.tvInHoaDon);
            ln=itemView.findViewById(R.id.cardHD);

        }
    }
    public void ttin(hoadon hd){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View view = ((Activity)context).getLayoutInflater().inflate(R.layout.item_thong_tin_hoa_don,null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();

        TextView tvma = view.findViewById(R.id.tvmahd);
        TextView tvnv = view.findViewById(R.id.tvnvtt);
        TextView tvKh = view.findViewById(R.id.tvkhachhangtt);
        TextView tvPhim = view.findViewById(R.id.tvPhimTT);
        TextView tvSC = view.findViewById(R.id.tvsuatchieutt);
        TextView tvSL = view.findViewById(R.id.tvsoLuongtt);
        TextView tvNgay = view.findViewById(R.id.tvngayintt);
        TextView tvgia = view.findViewById(R.id.tvthanhtien);
        ImageView back = view.findViewById(R.id.backtt);
        Button btnsua = view.findViewById(R.id.suaHD);
        Button btnxoa = view.findViewById(R.id.xoaHD);
        user us;
        us = new user();
        String tenPhim1;
        String khachHang;
        String suatChieu;

        try {
            phimDAO pdao = new phimDAO(context);

            Phim phim = pdao.getId(String.valueOf(hd.getMaPhim()));
            tenPhim1 = phim.getTen();

//            KHDAO khdao = new KHDAO(context);
//            khachhang khachhang = khdao.getId(String.valueOf(hd.getMaKH()));
//
//            khachHang = khachhang.getTenKH();
//            suatchieuDAO scdao = new suatchieuDAO(context);
//            suatchieu suatchieu = scdao.getId(String.valueOf(hd.getMaSC()));
//            suatChieu = suatchieu.getTenSC();


        } catch (Exception e) {
            tenPhim1 = "Đã xóa phim";
//            khachHang="Đã xóa khách hàng";
//            suatChieu="Đã xóa suất chiếu";

        }

        try {
//            phimDAO pdao = new phimDAO(context);
//
//            Phim phim = pdao.getId(String.valueOf(hd.getMaPhim()));
//            tenPhim1 = phim.getTen();

            KHDAO khdao = new KHDAO(context);
            khachhang khachhang = khdao.getId(String.valueOf(hd.getMaKH()));

            khachHang = khachhang.getTenKH();
//            suatchieuDAO scdao = new suatchieuDAO(context);
//            suatchieu suatchieu = scdao.getId(String.valueOf(hd.getMaSC()));
//            suatChieu = suatchieu.getTenSC();


        } catch (Exception e) {

            khachHang="Đã xóa khách hàng";
//            suatChieu="Đã xóa suất chiếu";

        }

        try {


//            KHDAO khdao = new KHDAO(context);
//            khachhang khachhang = khdao.getId(String.valueOf(hd.getMaKH()));
//
//            khachHang = khachhang.getTenKH();
            suatchieuDAO scdao = new suatchieuDAO(context);
            suatchieu suatchieu = scdao.getId(String.valueOf(hd.getMaSC()));
            suatChieu = suatchieu.getTenSC();


        } catch (Exception e) {

//            khachHang="Đã xóa khách hàng";
            suatChieu="Đã xóa suất chiếu";

        }



        adminDAO dao = new adminDAO(context);
        us = dao.getUser(hd.getMaNV());

        SharedPreferences preferences = context.getSharedPreferences("role", Context.MODE_PRIVATE);
        String fullName = preferences.getString("fullname","");
        int role = preferences.getInt("role",1);

        tvma.setText("Mã HD: "+hd.getMaHD());
        tvnv.setText("Nhân viên thanh toán: "+us.getTen());
        tvKh.setText("Khach hàng: "+khachHang);
        tvPhim.setText("Phim: "+tenPhim1);
        tvSC.setText("Suât chiếu: "+suatChieu);
        tvSL.setText("Số lượng vé: "+hd.getSlVe());
        tvNgay.setText("Ngày in: "+hd.getNgayInHoaDon());
        tvgia.setText(hd.getTongtien()+" VND");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        if (role==1){
            btnsua.setVisibility(View.VISIBLE);
            btnxoa.setVisibility(View.VISIBLE);

        }else{
            btnsua.setVisibility(View.GONE);
            btnxoa.setVisibility(View.GONE);


        }



    }
}
