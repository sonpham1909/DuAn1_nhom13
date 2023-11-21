package com.example.duan1_nhom13.Adapter;

import static android.app.Activity.RESULT_OK;
import static androidx.core.app.ActivityCompat.startActivityForResult;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1_nhom13.DAO.loaisachDAO;
import com.example.duan1_nhom13.DAO.phimDAO;
import com.example.duan1_nhom13.Model.Phim;
import com.example.duan1_nhom13.Model.loaisach;
import com.example.duan1_nhom13.R;
import com.example.duan1_nhom13.spinerAdapter.loaiphimAdapter;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class PhimAdapter extends RecyclerView.Adapter<PhimAdapter.Viewholder> {
    private ActivityResultLauncher<Intent> galleryLauncher;
Context context;
    int ms,mst;
ArrayList<Phim> list;
phimDAO dao;
    private Bitmap imageBitmap;

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


    //xl cardview
    holder.card.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            updateanddel(phim);


        }
    });


}

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class Viewholder extends RecyclerView.ViewHolder {
        TextView tv_mp, tv_ten, tv_gia, tv_thoiluong, tv_ml;
        ImageView imghinh;
        CardView card;


        public Viewholder(@NonNull View itemView) {
            super(itemView);
            imghinh = itemView.findViewById(R.id.imgPhim);
            tv_mp = itemView.findViewById(R.id.tvmaphim);
            tv_ten = itemView.findViewById(R.id.tvtenphim);
            tv_gia = itemView.findViewById(R.id.tvgiave);
            tv_thoiluong = itemView.findViewById(R.id.tvthoiluong);
            tv_ml = itemView.findViewById(R.id.tvtheloai);
            card = itemView.findViewById(R.id.card_phim);


        }
    }
    public void updateanddel(Phim phim){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.update_phim,null);
        builder.setView(view);
        Dialog dialog= builder.create();
        dialog.show();



        TextView tv_mau = view.findViewById(R.id.tvmaP);
        Spinner spns = view.findViewById(R.id.spnloaisachu);
        EditText edttenu = view.findViewById(R.id.edttenPhimu);
        EditText edtgiave = view.findViewById(R.id.edtGiaveu);
        EditText edtthoiluongu = view.findViewById(R.id.edtthoiluongu);
        Button btnsua = view.findViewById(R.id.btnsuaphimu);
        Button btnxoa = view.findViewById(R.id.btnxoau);
        Button chooseu = view.findViewById(R.id.btnchooseu);




        ImageView imghinhedit = view.findViewById(R.id.imgphimu);

        byte[] hih = phim.getAnh();
        Bitmap bitmap = BitmapFactory.decodeByteArray(hih, 0, hih.length);
        imghinhedit.setImageBitmap(bitmap);
        chooseu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }



        });
        tv_mau.setText(String.valueOf(phim.getMaPhim()));
        edttenu.setText(phim.getTen());
        edtgiave.setText(String.valueOf(phim.getGia()));
        edtthoiluongu.setText(String.valueOf(phim.getThoiluong()));
        loaisachDAO dao1 = new loaisachDAO(context);
        loaiSaches = new ArrayList<>();
        dao1 = new loaisachDAO(view.getContext());
        loaiSaches = (ArrayList<loaisach>) dao1.GETLS();
        loaiphimAdapter spnn = new loaiphimAdapter(view.getContext(),loaiSaches);

        spns.setAdapter(spnn);

        spns.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ms = loaiSaches.get(position).getMaloai();
                Toast.makeText(view.getContext(), "Chọn: " + loaiSaches.get(position).getTenloai(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        mst = 0;
        for (int i = 0; i < loaiSaches.size(); i++) {
            if (phim.getMaloai() == loaiSaches.get(i).getMaloai()) {
                mst = i;
            }
        }
        spns.setSelection(mst);
        btnsua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                        float giaThue = Float.parseFloat(edtgiave.getText().toString());
                        dao = new phimDAO(context);
                        phim.setTen(edttenu.getText().toString());
                        phim.setAnh(hih);
                        phim.setGia(Float.parseFloat(edtgiave.getText().toString()));
                        phim.setThoiluong(Integer.parseInt(edtthoiluongu.getText().toString()));
                        phim.setMaloai(ms);

                        if (dao.suaPhim(phim)) {
                            list.clear();
                            list.addAll(dao.getPhim());
                            Toast.makeText(view.getContext(), "Sửa Thành Công", Toast.LENGTH_SHORT).show();
                            notifyDataSetChanged();

                            dialog.dismiss();
                        } else {
                            Toast.makeText(view.getContext(), "Sửa Thất Bại", Toast.LENGTH_SHORT).show();
                        }



            }
        });
        btnxoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                LayoutInflater layoutInflater = ((Activity) context).getLayoutInflater();
                View view1 = layoutInflater.inflate(R.layout.delete_lp, null);
                builder1.setView(view1);
                Dialog dialog1 = builder1.create();
                dialog1.show();
                ImageButton btnyes = view1.findViewById(R.id.yes);
                ImageButton btnno = view1.findViewById(R.id.no);
                btnyes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (dao.del(phim.getMaPhim())) {
                            list.clear();
                            list.addAll(dao.getPhim());
                            Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                            notifyDataSetChanged();
                            dialog1.dismiss();
                            dialog.dismiss();
                        } else {
                            Toast.makeText(context, "Xóa không thành công", Toast.LENGTH_SHORT).show();
                            dialog1.dismiss();
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
    public void updateImage(Bitmap bitmap) {
        imageBitmap = bitmap;
        notifyDataSetChanged(); // Cập nhật RecyclerView
    }
    public void xoa(Phim phim){

    }


}
