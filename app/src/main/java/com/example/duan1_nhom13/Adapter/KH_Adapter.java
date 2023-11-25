package com.example.duan1_nhom13.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.duan1_nhom13.DAO.KHDAO;
import com.example.duan1_nhom13.Model.khachhang;
import com.example.duan1_nhom13.Model.phonchieu;
import com.example.duan1_nhom13.R;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class KH_Adapter extends RecyclerView.Adapter<KH_Adapter.Viewholder>{
    private Context context;
    private ArrayList<khachhang> list;
    KHDAO khdao;

    public KH_Adapter(Context context, ArrayList<khachhang> list) {
        this.context = context;
        this.list = list;
        khdao = new KHDAO(context);
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view =((Activity)context).getLayoutInflater().inflate(R.layout.item_khach_hang,null);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        khachhang kh = list.get(position);
        holder.tvma.setText("Mã KH: "+list.get(position).getMaKH());
        holder.tvten.setText("Tên KH: "+list.get(position).getTenKH());
        holder.tvso.setText("SDT: "+list.get(position).getSoKH());
        holder.ln.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                udKH(kh);
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class Viewholder extends RecyclerView.ViewHolder {
        TextView tvma,tvten,tvso;
        LinearLayout ln;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            tvma =itemView.findViewById(R.id.tvMaKhachHang);
            tvten = itemView.findViewById(R.id.tvTenKhachHang);
            tvso = itemView.findViewById(R.id.tvSoDienThoai);
            ln = itemView.findViewById(R.id.cardKH);
        }
    }
    public void udKH(khachhang kh){
        AlertDialog.Builder builder  = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view =  inflater.inflate(R.layout.edit_khach_hang,null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();

        EditText edtmaKH = view.findViewById(R.id.edMaKHu);
        EditText edtTen = view.findViewById(R.id.edTenKHu);
        EditText edtSo = view.findViewById(R.id.edSoDTu);
        Button btnsave = view.findViewById(R.id.btnEditKH);
        Button btncancel = view.findViewById(R.id.btnDeleteKH);

        edtmaKH.setText(String.valueOf(kh.getMaKH()));
        edtTen.setText(kh.getTenKH());
        edtSo.setText(kh.getSoKH());
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtTen.getText().toString().isEmpty()||edtSo.getText().toString().isEmpty()) {
                    Toast.makeText(context, "Không bỏ trống", Toast.LENGTH_SHORT).show();
                    return;
                }
               if(edtSo.getText().toString().length()<=10&&edtSo.getText().toString().length()>10){
                   Toast.makeText(context, "Số điện thoại phải là số và = 10 chữ số!", Toast.LENGTH_SHORT).show();
                   return;
               }
                kh.setTenKH(edtTen.getText().toString());
                kh.setSoKH(edtSo.getText().toString());
                if (khdao.udKH(kh)){
                    list.clear();
                    list.addAll(khdao.getKH());
                    notifyDataSetChanged();
                    Toast.makeText(context, "sửa thành công", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }else{
                    Toast.makeText(context, "sửa không thành công", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }



            }
        });
        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder1  = new AlertDialog.Builder(context);
                LayoutInflater inflater = ((Activity)context).getLayoutInflater();
                View view =  inflater.inflate(R.layout.delete_loai_phim,null);
                builder1.setView(view);
                Dialog dialog1 = builder1.create();
                dialog1.show();
                ImageButton btnacept = view.findViewById(R.id.yes);
                ImageButton btnno = view.findViewById(R.id.no);
                btnacept.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(khdao.delete(kh.getMaKH())){
                            list.clear();
                            list.addAll(khdao.getKH());
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
