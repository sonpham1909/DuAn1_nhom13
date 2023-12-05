package com.example.duan1_nhom13.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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
    String TenSC,ngayChieu,phongChieu;
    Double giAVE;


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
        String ngaychieu;


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
            try {
                suatchieuDAO scdao = new suatchieuDAO(context);

                suatchieu phim = scdao.getId(String.valueOf(hd.getMaSC()));
                ngaychieu = phim.getNgaychieu();



            } catch (Exception e) {
                ngaychieu = "";

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
            holder.tvNgayIn.setText("Ngày chiếu: "+ngaychieu);
            holder.tvtenPhim.setText("Phim: "+tenPhim);
            holder.tvTongtien.setText(hd.getTongtien()+" VND");




            //xl cardview
            holder.ln.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ttin(hd);
                }
            });
            if(hd.getThanhtoan()==2){
                holder.thanhToan.setVisibility(View.GONE);
                holder.tvThanhtoan.setTextColor(Color.GREEN);
                holder.tvThanhtoan.setText("Đã thanh toán");
            }else{
                holder.thanhToan.setVisibility(View.VISIBLE);
                holder.tvThanhtoan.setTextColor(Color.RED);
                holder.tvThanhtoan.setText("Chưa thanh toán");


            }
            holder.thanhToan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    hd.setThanhtoan(2);
                    if (hddao.suaPhim(hd)){
                        list.clear();
                        list.addAll(hddao.getHD());
                        notifyDataSetChanged();
                        Toast.makeText(context, "Thanh toán", Toast.LENGTH_SHORT).show();
                        holder.thanhToan.setVisibility(View.GONE);

                    }
                }
            });



        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static  class Viewholder extends RecyclerView.ViewHolder {
        TextView tvmaHD,tvmaNV,tvSLve,tvtenPhim,tvNgayIn,tvTongtien,tvThanhtoan;
        LinearLayout ln;
        Button thanhToan;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            tvmaHD = itemView.findViewById(R.id.tvMaHoaDon);
            tvmaNV= itemView.findViewById(R.id.tvNhanVien);
            tvSLve = itemView.findViewById(R.id.tvSLVe);
            tvtenPhim = itemView.findViewById(R.id.tvTenPhim);
            tvNgayIn = itemView.findViewById(R.id.tvInHoaDon);
            ln=itemView.findViewById(R.id.cardHD);
            thanhToan = itemView.findViewById(R.id.btnthanhtoan);
            tvTongtien = itemView.findViewById(R.id.tv_tinhtien);
            tvThanhtoan = itemView.findViewById(R.id.tv_thanhtoan);

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
        TextView tvngay = view.findViewById(R.id.tvngaysuatchieutt);
        TextView tv_user = view.findViewById(R.id.tvmnvtt);
        TextView tvgiave = view.findViewById(R.id.tvGiaVE);
        TextView tvpc = view.findViewById(R.id.tvphongchieutt);
        ImageView back = view.findViewById(R.id.backtt);

        Button btnxoa = view.findViewById(R.id.xoaHD);
        user us;
        us = new user();
        String tenPhim1;
        String khachHang;
        String suatChieu;
        int mapc;

        try {
            phimDAO pdao = new phimDAO(context);

            Phim phim = pdao.getId(String.valueOf(hd.getMaPhim()));
            tenPhim1 = phim.getTen();
            giAVE=Double.parseDouble(String.valueOf(phim.getGia()));

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
            giAVE = Double.valueOf("0");

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
            mapc =suatchieu.getMaPC();
            ngayChieu = suatchieu.getGiochieu()+" "+suatchieu.getNgaychieu();



        } catch (Exception e) {

//            khachHang="Đã xóa khách hàng";
            suatChieu="Đã xóa suất chiếu";
            mapc= Integer.parseInt("");
            ngayChieu="";

        }
        try {


//            KHDAO khdao = new KHDAO(context);
//            khachhang khachhang = khdao.getId(String.valueOf(hd.getMaKH()));
//
//            khachHang = khachhang.getTenKH();
            phongchieuDAO pcdao = new phongchieuDAO(context);
            phonchieu suatchieu = pcdao.getId(String.valueOf(mapc));
            phongChieu = suatchieu.getTenPC();



        } catch (Exception e) {

//            khachHang="Đã xóa khách hàng";
            phongChieu="Đã xóa suất chiếu";

        }




        adminDAO dao = new adminDAO(context);
        us = dao.getUser(hd.getMaNV());

        SharedPreferences preferences = context.getSharedPreferences("role", Context.MODE_PRIVATE);
        String fullName = preferences.getString("fullname","");
        int role = preferences.getInt("role",1);
        tv_user.setText("Mã NV: "+hd.getMaNV());

        tvma.setText("Mã HD: "+hd.getMaHD());
        tvnv.setText("Nhân viên thanh toán: "+us.getTen());
        tvKh.setText("Khach hàng: "+khachHang);
        tvPhim.setText("Phim: "+tenPhim1);
        tvSC.setText("Suât chiếu: "+suatChieu);
        tvSL.setText("Số lượng vé: "+hd.getSlVe());
        tvNgay.setText("Ngày in: "+hd.getNgayInHoaDon());
        tvgia.setText(hd.getTongtien()+" VND");
        tvgiave.setText("Giá vé: "+giAVE);
        tvpc.setText("Phòng chiếu: "+phongChieu);

        tvngay.setText("Ngày chiếu: "+ngayChieu);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        if (role==1){

            btnxoa.setVisibility(View.VISIBLE);

        }else{
            btnxoa.setVisibility(View.GONE);


        }
        if(hd.getThanhtoan()==1){
            btnxoa.setVisibility(View.VISIBLE);
        }else {
            btnxoa.setVisibility(View.GONE);

        }
        btnxoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                android.app.AlertDialog.Builder builder1  = new android.app.AlertDialog.Builder(context);
                LayoutInflater inflater = ((Activity)context).getLayoutInflater();
                View view1 =  inflater.inflate(R.layout.delete_loai_phim,null);
                builder1.setView(view1);
                Dialog dialog1 = builder1.create();
                dialog1.show();
                ImageButton btnacept = view1.findViewById(R.id.yes);
                ImageButton btnno = view1.findViewById(R.id.no);
                btnacept.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(hddao.del(hd.getMaHD())){
                            list.clear();
                            list.addAll(hddao.getHD());
                            notifyDataSetChanged();
                            Toast.makeText(context, "Đã xóa!", Toast.LENGTH_SHORT).show();
                            dialog1.dismiss();
                            dialog.dismiss();
                        }
                    }
                });
                btnno.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog1.dismiss();
                    }
                });
            }
        });




    }
}
