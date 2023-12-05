package com.example.duan1_nhom13.Adapter;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1_nhom13.DAO.CL_DAO;
import com.example.duan1_nhom13.DAO.LL_DAO;
import com.example.duan1_nhom13.DAO.adminDAO;
import com.example.duan1_nhom13.DAO.phongchieuDAO;
import com.example.duan1_nhom13.Model.calam;
import com.example.duan1_nhom13.Model.lichlam;
import com.example.duan1_nhom13.Model.phonchieu;
import com.example.duan1_nhom13.Model.user;
import com.example.duan1_nhom13.R;
import com.example.duan1_nhom13.spinerAdapter.cl_spinner;
import com.example.duan1_nhom13.spinerAdapter.nv_spinner;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;

public class LL_Adapter extends RecyclerView.Adapter<LL_Adapter.Viewholder> {
    private Context context;
    private ArrayList<lichlam> list;
    ArrayList<user> listnn;
    ArrayList<calam> listcl;
    LL_DAO lldao;
    String gbd, gkt, manv;
    int maca, mst, msc;

    public LL_Adapter(Context context, ArrayList<lichlam> list) {
        this.context = context;
        this.list = list;
        lldao = new LL_DAO(context);
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = ((Activity) context).getLayoutInflater().inflate(R.layout.item_sap_ca, null);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        lichlam ls = list.get(position);
        String tenNV;
        try {
            adminDAO adminDAO = new adminDAO(context);

            user us = adminDAO.getID(ls.getManv());
            tenNV = us.getTen();
        } catch (Exception e) {
            tenNV = "Đã xóa nhân viên";

        }
        String tenCa, gioBD, gioKT;
        try {
            CL_DAO cldao = new CL_DAO(context);

            calam cl = cldao.getId(String.valueOf(ls.getMaca()));
            tenCa = cl.getTenCa();
            gioBD = cl.getGioBD();
            gioKT = cl.getGioKT();

        } catch (Exception e) {
            tenCa = "Đã xóa Ca làm";
            gioBD = "";
            gioKT = "";

        }
        holder.tvma.setText("Nhân viên: " + tenNV);
        holder.tvtenca.setText("Ca: " + tenCa);
        holder.tvgbd.setText("Giờ bắt đầu: " + gioBD);
        holder.tvgkt.setText("Giờ kết thúc: " + gioKT);
        holder.tvnbd.setText("Ngày bắt đầu: " + ls.getNBD());
        holder.tvmkt.setText("Ngày kết thúc: " + ls.getNKT());
        SharedPreferences sharedPreferences =context.getSharedPreferences("role",context.MODE_PRIVATE);
        int role = sharedPreferences.getInt("role",1);
        if(role==1){
            holder.ln.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    update(ls);
                }
            });
        }else{

        }


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class Viewholder extends RecyclerView.ViewHolder {
        TextView tvma, tvtenca, tvnbd, tvmkt, tvgbd, tvgkt;
        LinearLayout ln;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            tvma = itemView.findViewById(R.id.tvtenNVL);
            tvtenca = itemView.findViewById(R.id.tvCAlam);
            tvnbd = itemView.findViewById(R.id.tvNBD);
            tvmkt = itemView.findViewById(R.id.tvNKT);
            tvgbd = itemView.findViewById(R.id.tvGBD);
            tvgkt = itemView.findViewById(R.id.tvGKT);
            ln = itemView.findViewById(R.id.cardLichlam);
        }
    }

    public void update(lichlam ll) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View view = ((Activity) context).getLayoutInflater().inflate(R.layout.edit_lich_lam_nhan_vien, null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();
        Spinner spnNV = view.findViewById(R.id.spnNVLu);
        Spinner spnCA = view.findViewById(R.id.spnCalamu);
        EditText edtNBD = view.findViewById(R.id.edtNBDu);
        EditText edtNKT = view.findViewById(R.id.edtNKTu);
        EditText edtGBD = view.findViewById(R.id.edtGBDu);
        EditText edtGKT = view.findViewById(R.id.edtGKTu);
        TextView tvma = view.findViewById(R.id.malichlamu);
        tvma.setText(String.valueOf(ll.getMalich()));


        listnn = new ArrayList<>();
        adminDAO dao = new adminDAO(context);
        listnn = (ArrayList<user>) dao.getALl();
        nv_spinner spnnvadapter = new nv_spinner(view.getContext(), listnn);
        spnNV.setAdapter(spnnvadapter);
        listcl = new ArrayList<>();
        CL_DAO daocl = new CL_DAO(context);
        listcl = (ArrayList<calam>) daocl.getCalam();
        cl_spinner spncladapter = new cl_spinner(view.getContext(), listcl);
        spnCA.setAdapter(spncladapter);


        spnCA.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                gbd = listcl.get(position).getGioBD();
                gkt = listcl.get(position).getGioKT();
                maca = listcl.get(position).getMaca();
                edtGBD.setText(gbd);
                edtGKT.setText(gkt);
                Toast.makeText(context, "Chọn " + listcl.get(position).getTenCa(), Toast.LENGTH_SHORT).show();
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mst = 0;
        for (int i = 0; i < listcl.size(); i++) {
            if (ll.getMaca() == listcl.get(i).getMaca()) {
                mst = i;
            }
        }
        spnCA.setSelection(mst);
        spnNV.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                manv = listnn.get(i).getUser();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        msc = 0;
        for (int i = 0; i < listnn.size(); i++) {
            if (ll.getManv() == listnn.get(i).getUser()) {
                msc = i;
            }
            spnNV.setSelection(msc);
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String date = dateFormat.format(calendar.getTime());
            edtNBD.setText(ll.getNBD());
            edtNKT.setText(ll.getNKT());

            edtNBD.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showDatePickerDialog(edtNBD);
                }
            });
            edtNKT.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showDatePickerDialog(edtNKT);
                }
            });


            Button btnsm = view.findViewById(R.id.btnXNCu);
            Button btncancel = view.findViewById(R.id.btnXoa);

            btnsm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (edtGBD.getText().toString().isEmpty() || edtGKT.getText().toString().isEmpty() || edtNBD.getText().toString().isEmpty() || edtNKT.getText().toString().isEmpty()) {
                        Toast.makeText(context, "Không bỏ trống", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");


                    LocalDate date1 = LocalDate.parse(edtNBD.getText().toString(), formatter);
                    LocalDate date2 = LocalDate.parse(edtNKT.getText().toString(), formatter);
                    LocalDate date3 = LocalDate.parse(date, formatter);
                    if (date2.isBefore(date1)) {
                        Toast.makeText(context, "Ngày kết thúc không hợp lệ", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (date1.isBefore(date3)) {
                        Toast.makeText(context, "Ngày bắt đầu không được nhỏ hơn ngày hiện tại", Toast.LENGTH_SHORT).show();
                        return;


                    }
                    ll.setManv(manv);
                    ll.setMaca(maca);
                    ll.setNBD(edtNBD.getText().toString());
                    ll.setNKT(edtNKT.getText().toString());
                    if(lldao.update(ll)){
                        list.clear();
                        list.addAll(lldao.getLichlam());
                        Toast.makeText(context, "Thành công", Toast.LENGTH_SHORT).show();
                        notifyDataSetChanged();
                        dialog.dismiss();
                    }



                }
            });
            btncancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    android.app.AlertDialog.Builder builder1  = new android.app.AlertDialog.Builder(context);
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
                            if(lldao.delete(ll.getMalich())){
                                list.clear();
                                list.addAll(lldao.getLichlam());
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
    private void showDatePickerDialog(EditText editText) {
        // Lấy ngày hiện tại
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        // Tạo DatePickerDialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                // Xử lý ngày được chọn
                // Ở đây, bạn có thể làm bất kỳ xử lý nào với ngày được chọn, ví dụ: cập nhật EditText với ngày mới
                String selectedDate = String.format("%02d/%02d/%d", dayOfMonth, (month + 1), year);
                editText.setText(selectedDate);
            }
        }, year, month, day);

        // Hiển thị dialog
        datePickerDialog.show();
    }
}
