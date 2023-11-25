package com.example.duan1_nhom13.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.duan1_nhom13.DAO.loaisachDAO;
import com.example.duan1_nhom13.DAO.phimDAO;
import com.example.duan1_nhom13.DAO.phongchieuDAO;
import com.example.duan1_nhom13.DAO.suatchieuDAO;
import com.example.duan1_nhom13.Model.Phim;
import com.example.duan1_nhom13.Model.loaisach;
import com.example.duan1_nhom13.Model.phonchieu;
import com.example.duan1_nhom13.Model.suatchieu;
import com.example.duan1_nhom13.R;
import com.example.duan1_nhom13.spinerAdapter.loaiphimAdapter;
import com.example.duan1_nhom13.spinerAdapter.pc_spinner;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Calendar;

public class SC_Adapter extends RecyclerView.Adapter<SC_Adapter.Viewholder> {

    private Context context;
    private ArrayList<suatchieu> list;
    suatchieuDAO scdao;
    ArrayList<phonchieu> loaiSaches;
    int ms,mst;

    public SC_Adapter(Context context, ArrayList<suatchieu> list) {
        this.context = context;
        this.list = list;
        scdao= new suatchieuDAO(context);
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = ((Activity)context).getLayoutInflater().inflate(R.layout.item_suat_chieu,null);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        suatchieu sc = list.get(position);


        if(sc== null){
            return;
        }else {
            String tenLoai;
            try {
                phongchieuDAO phongchieuDAO = new phongchieuDAO(context);

               phonchieu phonchieu = phongchieuDAO.getId(String.valueOf(sc.getMaPC()));
                tenLoai = phonchieu.getTenPC();
            } catch (Exception e) {
                tenLoai = "Đã xóa phòng chiếu";

            }





            holder.tv_maSC.setText("Mã SC: "+sc.getMaSC());
            holder.tv_tenSC.setText("Tên SC: "+sc.getTenSC());
            holder.tvtenPC.setText("Tên PC: "+tenLoai);
            holder.tv_ngayChieu.setText("Ngày chiếu: "+sc.getNgaychieu());
            holder.tv_gioChieu.setText("Giờ chiếu: "+sc.getGiochieu());


            //xl cardview



        }

        SharedPreferences preferences = context.getSharedPreferences("role", Context.MODE_PRIVATE);
        int role = preferences.getInt("role",1);
        if (role==1){
            holder.ln.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    updateanddel(sc);
                }
            });

        }else{
            holder.ln.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
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
        TextView tv_maSC,tv_tenSC,tvtenPC,tv_ngayChieu,tv_gioChieu;
        LinearLayout ln;

        public Viewholder(@NonNull View itemView) {
            super(itemView);


            tv_maSC = itemView.findViewById(R.id.tvMaSuatChieu);
            tv_tenSC = itemView.findViewById(R.id.tvTenSC);
            tvtenPC = itemView.findViewById(R.id.tvTenPC);
            tv_ngayChieu = itemView.findViewById(R.id.tvNgayChieu);
            tv_gioChieu = itemView.findViewById(R.id.tvGioChieu);
            ln=itemView.findViewById(R.id.cardSC);




        }
    }
    public void updateanddel(suatchieu sc){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.edit_suat_chieu,null);
        builder.setView(view);
        Dialog dialog= builder.create();
        dialog.show();



        EditText edtma = view.findViewById(R.id.edMaSuatChieuu);
        Spinner spns = view.findViewById(R.id.spnpcu);
        EditText edttenu = view.findViewById(R.id.edTenSCu);
        EditText edtngay = view.findViewById(R.id.edNgayChieuu);
        EditText edtgio = view.findViewById(R.id.edGioChieuu);
        Button btnsua = view.findViewById(R.id.btnEditSC);
        Button btnxoa = view.findViewById(R.id.btnDeleteSC);








        edtma.setText(String.valueOf(sc.getMaSC()));
        edttenu.setText(sc.getTenSC());
        edtngay.setText(sc.getNgaychieu());
        edtgio.setText(sc.getGiochieu());
        phongchieuDAO dao1 = new phongchieuDAO(context);
        loaiSaches = new ArrayList<>();
        dao1 = new phongchieuDAO(view.getContext());
        loaiSaches = (ArrayList<phonchieu>) dao1.GETLS();
        pc_spinner spnn = new pc_spinner(view.getContext(),loaiSaches);

        spns.setAdapter(spnn);


        spns.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ms = loaiSaches.get(position).getMaPC();
                Toast.makeText(view.getContext(), "Chọn: " + loaiSaches.get(position).getTenPC(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        mst = 0;
        for (int i = 0; i < loaiSaches.size(); i++) {
            if (sc.getMaPC() == loaiSaches.get(i).getMaPC()) {
                mst = i;
            }
        }
        spns.setSelection(mst);

        edtgio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimePickerDialog(edtgio);
            }
        });
        edtngay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog(edtngay);
            }
        });
        btnsua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                scdao = new suatchieuDAO(context);
                sc.setTenSC(edttenu.getText().toString());
                sc.setGiochieu(edtgio.getText().toString());

                sc.setNgaychieu(edtngay.getText().toString());

                sc.setMaPC(ms);

                if (scdao.udPC(sc)) {
                    list.clear();
                    list.addAll(scdao.getSC());
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
                View view1 = layoutInflater.inflate(R.layout.delete_loai_phim, null);
                builder1.setView(view1);
                Dialog dialog1 = builder1.create();
                dialog1.show();
                ImageButton btnyes = view1.findViewById(R.id.yes);
                ImageButton btnno = view1.findViewById(R.id.no);
                btnyes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (scdao.delete(sc.getMaSC())) {
                            list.clear();
                            list.addAll(scdao.getSC());
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
                String selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
                editText.setText(selectedDate);
            }
        }, year, month, day);

        // Hiển thị dialog
        datePickerDialog.show();
    }
}
