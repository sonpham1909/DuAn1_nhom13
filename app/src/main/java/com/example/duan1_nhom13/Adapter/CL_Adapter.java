package com.example.duan1_nhom13.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1_nhom13.DAO.CL_DAO;
import com.example.duan1_nhom13.Model.calam;
import com.example.duan1_nhom13.R;

import java.util.ArrayList;
import java.util.Calendar;

public class CL_Adapter extends RecyclerView.Adapter<CL_Adapter.Viewholder>{
    private Context context;
    private ArrayList<calam> list;

    CL_DAO cldao;

    public CL_Adapter(Context context, ArrayList<calam> list) {
        this.context = context;
        this.list = list;
        cldao=  new CL_DAO(context);
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = ((Activity)context).getLayoutInflater().inflate(R.layout.item_lich_lam_viec,null);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        calam cl = list.get(position);
        holder.tvMa.setText("Mã ca: "+cl.getMaca());
        holder.tvten.setText("Tên: "+cl.getTenCa());
        holder.tvbd.setText("Bắt đầu: "+cl.getGioBD());
        holder.tvkt.setText("Kết thúc: "+cl.getGioKT());
        holder.ln.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update(cl);
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class Viewholder extends RecyclerView.ViewHolder{
        TextView tvMa,tvten,tvbd,tvkt;
        LinearLayout ln;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            tvMa = itemView.findViewById(R.id.tvMaLichLam);
            tvten = itemView.findViewById(R.id.tvTenLichLam);
            tvbd = itemView.findViewById(R.id.tvGioStart);
            tvkt = itemView.findViewById(R.id.tvGioEnd);
            ln = itemView.findViewById(R.id.cardLich);
        }
    }
    public void update(calam cl){
        AlertDialog.Builder builder  = new AlertDialog.Builder(context);
        View view= ((Activity)context).getLayoutInflater().inflate(R.layout.edit_lich_lam_viec,null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();
        EditText edma = view.findViewById(R.id.edMaLichLamuu);
        EditText gbd = view.findViewById(R.id.edGioLamu);
        EditText gkt = view.findViewById(R.id.edGioEndu);
        EditText ten = view.findViewById(R.id.edTencau);
        gkt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimePickerDialog(gkt);
            }
        });
        gbd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimePickerDialog(gbd);
            }
        });
        edma.setText(String.valueOf(cl.getMaca()));
        ten.setText(cl.getTenCa());
        gbd.setText(cl.getGioBD());
        gkt.setText(cl.getGioKT());


        Button btnxn = view.findViewById(R.id.btnEditLLV);
        Button button = view.findViewById(R.id.btnDeleteLLV);

        btnxn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ten.getText().toString().isEmpty()||gbd.getText().toString().isEmpty()||gkt.getText().toString().isEmpty()){
                    Toast.makeText(context, "Không bỏ trống", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(ten.getText().toString().length()<2){
                    Toast.makeText(context, "Tên ca không được nhỏ hơn 2 kí tự !", Toast.LENGTH_SHORT).show();
                    return;


                }
                cl.setGioKT(ten.getText().toString());
                cl.setGioBD(gbd.getText().toString());
                cl.setGioKT(gkt.getText().toString());
                if(cldao.update(cl)){
                    list.clear();
                    list.addAll(cldao.getCalam());
                    notifyDataSetChanged();
                    Toast.makeText(context, "Thành công", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                android.app.AlertDialog.Builder builder1  = new android.app.AlertDialog.Builder(context);
                LayoutInflater inflater = ((Activity)context).getLayoutInflater();
                View view1 =  inflater.inflate(R.layout.delete_loai_phim,null);
                builder1.setView(view1);
                Dialog dialog1 = builder1.create();
                dialog1.show();
                ImageButton btnacept = view.findViewById(R.id.yes);
                ImageButton btnno = view.findViewById(R.id.no);
                btnacept.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(cldao.delete(cl.getMaca())){
                            list.clear();
                            list.addAll(cldao.getCalam());
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
    private void showTimePickerDialog(EditText editText) {
        // Lấy giờ hiện tại
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        // Tạo TimePickerDialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                // Xử lý giờ được chọn
                // Ở đây, bạn có thể làm bất kỳ xử lý nào với giờ được chọn, ví dụ: cập nhật EditText với giờ mới
                String selectedTime = hourOfDay + ":" + minute;
                editText.setText(selectedTime);
            }
        }, hour, minute, true);

        // Hiển thị dialog
        timePickerDialog.show();
    }
}
