package com.example.duan1_nhom13.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1_nhom13.DAO.loaisachDAO;
import com.example.duan1_nhom13.Model.loaisach;
import com.example.duan1_nhom13.R;

import java.util.ArrayList;

public class LPAdapter extends RecyclerView.Adapter<LPAdapter.Viewholder>{
    private Context context;
    private ArrayList<loaisach> list;
    private ArrayList<loaisach> listold;
    loaisachDAO dao;

    public LPAdapter(Context context, ArrayList<loaisach> list) {
        this.context = context;
        this.list = list;
        dao = new loaisachDAO(context);
        this.listold = list;
    }






    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_loaisach,null);
        return new  Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        holder.tvTen.setText(list.get(position).getTenloai());
        loaisach ls = list.get(position);
        SharedPreferences sharedPreferences =context.getSharedPreferences("role",context.MODE_PRIVATE);
        int role = sharedPreferences.getInt("role",1);
        if(role==1) {
            holder.card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (holder.ln.getVisibility() == View.VISIBLE) {
                        // Nếu bottomLayout đang hiển thị, ẩn nó đi
                        holder.ln.setVisibility(View.GONE);
                    } else {
                        // Nếu bottomLayout đang ẩn, hiển thị nó lên
                        holder.ln.setVisibility(View.VISIBLE);
                    }
                }

            });

        }else {
            holder.card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setIcon(R.drawable.baseline_warning_amber_24);

                    builder.setMessage("Nhân viên chỉ được xem danh sách!");


                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    Dialog dialog = builder.create();
                    dialog.show();
                }
            });

        }
        holder.btnedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                udLoaiPhim(ls);
            }
        });
        holder.btndel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dellP(ls);
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class Viewholder extends RecyclerView.ViewHolder {
        TextView tvTen;
        CardView card;
        LinearLayout ln;
        ImageButton btndel,btnedit;

        public Viewholder(@NonNull View itemView) {
            super(itemView);

             tvTen = itemView.findViewById(R.id.tvtenloai);
             card = itemView.findViewById(R.id.cardlp);
             ln = itemView.findViewById(R.id.editanddel);
             btndel = itemView.findViewById(R.id.btndellp);
             btnedit = itemView.findViewById(R.id.btneditlp);
        }
    }
    public void udLoaiPhim(loaisach ls){
        AlertDialog.Builder builder  = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view =  inflater.inflate(R.layout.edit_lp,null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();

        EditText edtmalp = view.findViewById(R.id.edtmaLP);
        EditText edtTenTL = view.findViewById(R.id.edttentheloai);
        Button btnsave = view.findViewById(R.id.btnsavelpu);
        Button btncancel = view.findViewById(R.id.btncancellpu);

        edtmalp.setText(String.valueOf(ls.getMaloai()));
        edtTenTL.setText(ls.getTenloai());
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtTenTL.getText().toString().isEmpty()) {
                    Toast.makeText(context, "Không bỏ trống", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (edtTenTL.getText().toString().length() < 2) {
                    Toast.makeText(context, "Tên thể loại không được nhỏ hơn 2 kí tự !", Toast.LENGTH_SHORT).show();
                    return;
                }
                ls.setTenloai(edtTenTL.getText().toString());
                if (dao.udloaisach(ls)){
                    list.clear();
                    list.addAll(dao.getLoaiPhim());
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
                dialog.dismiss();
            }
        });




    }
    public void dellP(loaisach ls){

        AlertDialog.Builder builder  = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view =  inflater.inflate(R.layout.delete_lp,null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();
        ImageButton btnacept = view.findViewById(R.id.yes);
        ImageButton btnno = view.findViewById(R.id.no);
        btnacept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dao.deleteloaiphim(ls.getMaloai())){
                    list.clear();
                    list.addAll(dao.getLoaiPhim());
                    notifyDataSetChanged();
                    Toast.makeText(context, "Đã xóa!", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            }
        });
        btnno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });



    }




}

