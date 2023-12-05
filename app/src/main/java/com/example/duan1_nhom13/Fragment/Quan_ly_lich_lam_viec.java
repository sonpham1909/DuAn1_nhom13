package com.example.duan1_nhom13.Fragment;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.duan1_nhom13.Adapter.CL_Adapter;
import com.example.duan1_nhom13.DAO.CL_DAO;
import com.example.duan1_nhom13.Model.calam;
import com.example.duan1_nhom13.Model.loaisach;
import com.example.duan1_nhom13.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;


public class Quan_ly_lich_lam_viec extends Fragment {
    RecyclerView rcv;
    FloatingActionButton fltbtn;
    CL_Adapter adapter;
    CL_DAO cldao;
    calam cl;
    ArrayList<calam> list = new ArrayList<>();



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quan_ly_lich_lam_viec, container, false);
        // Inflate the layout for this fragment
        rcv = view.findViewById(R.id.rcvLich);
        fltbtn = view.findViewById(R.id.fltLich);
        cldao = new CL_DAO(getContext());
        list= cldao.getCalam();
        GridLayoutManager manager = new GridLayoutManager(getContext(),1);
        rcv.setLayoutManager(manager);
        adapter = new CL_Adapter(getContext(),list);
        rcv.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        fltbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                them();

            }
        });

        return view;
    }
    public void them(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view = getLayoutInflater().inflate(R.layout.add_lich_lam_viec,null);
        builder.setView(view);
        Dialog dialog =builder.create();
        dialog.show();
        EditText edtten = view.findViewById(R.id.edTenca);
        EditText edgioBD = view.findViewById(R.id.edGioLV);
        EditText edgioKT = view.findViewById(R.id.edGioKT);
        edgioBD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimePickerDialog(edgioBD);
            }
        });
        edgioKT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimePickerDialog(edgioKT);
            }
        });


        Button btnsm = view.findViewById(R.id.btnsaveLLV);
        Button btncancel = view.findViewById(R.id.btnHuyLLV);

        btnsm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtten.getText().toString().isEmpty()||edgioBD.getText().toString().isEmpty()||edgioKT.getText().toString().isEmpty()){
                    Toast.makeText(getContext(), "Không bỏ trống", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(edtten.getText().toString().length()<2){
                    Toast.makeText(getContext(), "Tên ca không được nhỏ hơn 2 kí tự !", Toast.LENGTH_SHORT).show();
                    return;


                }
                String ten= edtten.getText().toString();
                String gioBD = edgioBD.getText().toString();
                String gioKT = edgioKT.getText().toString();
                cl = new calam(ten,gioBD,gioKT);
                if (cldao.them(cl)){
                    list.clear();
                    list.addAll(cldao.getCalam());
                    adapter.notifyDataSetChanged();
                    Toast.makeText(getContext(), "Thêm thành công !", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }else {
                    Toast.makeText(getContext(), "Thêm không thành công !", Toast.LENGTH_SHORT).show();
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
    private void showTimePickerDialog(EditText editText) {
        // Lấy giờ hiện tại
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        // Tạo TimePickerDialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
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