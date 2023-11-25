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
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1_nhom13.DAO.phongchieuDAO;
import com.example.duan1_nhom13.Model.loaisach;
import com.example.duan1_nhom13.Model.phonchieu;
import com.example.duan1_nhom13.R;

import java.util.ArrayList;


public class PC_Adapter extends RecyclerView.Adapter<PC_Adapter.Viewholder> {

    private Context context;
    private ArrayList<phonchieu> list;

    phongchieuDAO PCDAO;

    public PC_Adapter(Context context, ArrayList<phonchieu> list) {
        this.context = context;
        this.list = list;
        PCDAO = new phongchieuDAO(context);
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_phong_chieu,null);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        holder.tvMa.setText("Mã PC: "+list.get(position).getMaPC()+"");
        holder.tvTen.setText("Tên PC: "+list.get(position).getTenPC()+"");
        holder.tvsoGhe.setText("Số ghế: "+list.get(position).getSoGhe()+"");
        phonchieu pc = list.get(position);

        SharedPreferences sharedPreferences =context.getSharedPreferences("role",context.MODE_PRIVATE);
        int role = sharedPreferences.getInt("role",1);

        if(role==1) {
            holder.ln.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.ln.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            udPC(pc);
                        }
                    });
                }

            });

        }else {
            holder.ln.setOnClickListener(new View.OnClickListener() {
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


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class Viewholder extends RecyclerView.ViewHolder{
        TextView tvMa,tvTen,tvsoGhe;
        LinearLayout ln;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            tvMa = itemView.findViewById(R.id.tvMaPhongChieu);
            tvTen = itemView.findViewById(R.id.tvTenPhongChieu);
            tvsoGhe = itemView.findViewById(R.id.tvSoLuongGhe);
            ln = itemView.findViewById(R.id.cardPC);


        }
    }
    public void udPC(phonchieu pc){
        AlertDialog.Builder builder  = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view =  inflater.inflate(R.layout.edit_phong_chieu,null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();

        EditText edtmaPC = view.findViewById(R.id.edMaPhongChieu);
        EditText edtTen = view.findViewById(R.id.edTenPhongChieuu);
        EditText edtSoghe = view.findViewById(R.id.edSoLuongGheu);
        Button btnsave = view.findViewById(R.id.btnEditPC);
        Button btncancel = view.findViewById(R.id.btnDeletePC);

        edtmaPC.setText(String.valueOf(pc.getMaPC()));
        edtTen.setText(pc.getTenPC());
        edtSoghe.setText(pc.getSoGhe()+"");
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtTen.getText().toString().isEmpty()||edtSoghe.getText().toString().isEmpty()) {
                    Toast.makeText(context, "Không bỏ trống", Toast.LENGTH_SHORT).show();
                    return;
                }
                try {
                    Integer.parseInt(edtSoghe.getText().toString());
                }catch (Exception e){
                    Toast.makeText(context, "Số ghế phải là sô", Toast.LENGTH_SHORT).show();
                    return;


                }
                if(Integer.parseInt(edtSoghe.getText().toString())<=10||Integer.parseInt(edtSoghe.getText().toString())>=60){
                    Toast.makeText(context, "Số ghế phải >10 và <=60", Toast.LENGTH_SHORT).show();
                    return;

                }

                pc.setTenPC(edtTen.getText().toString());
                pc.setSoGhe(Integer.parseInt(edtSoghe.getText().toString()));
                if (PCDAO.udPC(pc)){
                    list.clear();
                    list.addAll(PCDAO.getPC());
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
                        if(PCDAO.delete(pc.getMaPC())){
                            list.clear();
                            list.addAll(PCDAO.getPC());
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
