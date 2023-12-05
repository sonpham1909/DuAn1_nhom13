package com.example.duan1_nhom13.Fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.duan1_nhom13.Adapter.LL_Adapter;
import com.example.duan1_nhom13.DAO.CL_DAO;
import com.example.duan1_nhom13.DAO.LL_DAO;
import com.example.duan1_nhom13.DAO.adminDAO;
import com.example.duan1_nhom13.DAO.loaisachDAO;
import com.example.duan1_nhom13.Model.calam;
import com.example.duan1_nhom13.Model.lichlam;
import com.example.duan1_nhom13.Model.loaisach;
import com.example.duan1_nhom13.Model.user;
import com.example.duan1_nhom13.R;
import com.example.duan1_nhom13.spinerAdapter.cl_spinner;
import com.example.duan1_nhom13.spinerAdapter.loaiphimAdapter;
import com.example.duan1_nhom13.spinerAdapter.nv_spinner;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;


public class sap_ca_lam_nv extends Fragment {
    RecyclerView rcv;
    FloatingActionButton fltbtn;
    ArrayList<lichlam> list = new ArrayList<>();
    LL_Adapter adapter;
    lichlam ll;
    ArrayList<user> listnn;
    ArrayList<calam> listcl;
    LL_DAO lldao;
    String gbd,gkt,manv;
    int maca;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_sap_ca_lam_nv, container, false);
        // Inflate the layout for this fragment
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("thongtin", Context.MODE_PRIVATE);
        String user= sharedPreferences.getString("user","");
        SharedPreferences sharedPreferences1 = getContext().getSharedPreferences("role", Context.MODE_PRIVATE);
        int role= sharedPreferences1.getInt("role",1);
     if(role==1){
         rcv = view.findViewById(R.id.rcvSapCa);
         fltbtn=  view.findViewById(R.id.btnSApca);
         lldao = new LL_DAO(getContext());
         list= lldao.getLichlam();
         GridLayoutManager manager = new GridLayoutManager(getContext(),1);
         rcv.setLayoutManager(manager);
         adapter = new LL_Adapter(getContext(),list);
         rcv.setAdapter(adapter);
         adapter.notifyDataSetChanged();
         fltbtn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 themLoaiphim();
             }
         });
     }else{
         rcv = view.findViewById(R.id.rcvSapCa);
         fltbtn=  view.findViewById(R.id.btnSApca);
         lldao = new LL_DAO(getContext());
         ArrayList<lichlam> listll = lldao.getId(String.valueOf(user));

         GridLayoutManager manager = new GridLayoutManager(getContext(),1);
         rcv.setLayoutManager(manager);
         adapter = new LL_Adapter(getContext(),listll);
         rcv.setAdapter(adapter);
         adapter.notifyDataSetChanged();
        fltbtn.setVisibility(View.GONE);

     }

        return view;
    }
    public void themLoaiphim(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view = getLayoutInflater().inflate(R.layout.add_lich_cua_nv,null);
        builder.setView(view);
        Dialog dialog =builder.create();
        dialog.show();
        Spinner spnNV = view.findViewById(R.id.spnNVL);
        Spinner spnCA = view.findViewById(R.id.spnCalam);
        EditText edtNBD = view.findViewById(R.id.edtNBD);
        EditText edtNKT = view.findViewById(R.id.edtNKT);
        EditText edtGBD = view.findViewById(R.id.edtGBD);
        EditText edtGKT = view.findViewById(R.id.edtGKT);


        listnn = new ArrayList<>();
        adminDAO dao = new adminDAO(getContext());
        listnn = (ArrayList<user>) dao.getALl();
        nv_spinner spnnvadapter =new nv_spinner(getContext(),listnn);
        spnNV.setAdapter(spnnvadapter);
        listcl = new ArrayList<>();
        CL_DAO daocl = new CL_DAO(getContext());
        listcl = (ArrayList<calam>) daocl.getCalam();
        cl_spinner spncladapter = new cl_spinner(getContext(),listcl);
        spnCA.setAdapter(spncladapter);


        spnCA.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                gbd = listcl.get(position).getGioBD();
                gkt = listcl.get(position).getGioKT();
                maca = listcl.get(position).getMaca();
                edtGBD.setText(gbd);
                edtGKT.setText(gkt);
                Toast.makeText(getContext(), "Chọn " + listcl.get(position).getTenCa(), Toast.LENGTH_SHORT).show();
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spnNV.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                manv = listnn.get(i).getUser();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String date = dateFormat.format(calendar.getTime());
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



        Button btnsm = view.findViewById(R.id.btnXNC);
        Button btncancel = view.findViewById(R.id.btnHXNC);

        btnsm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtGBD.getText().toString().isEmpty()||edtGKT.getText().toString().isEmpty()||edtNBD.getText().toString().isEmpty()||edtNKT.getText().toString().isEmpty()){
                    Toast.makeText(getContext(), "Không bỏ trống", Toast.LENGTH_SHORT).show();
                    return;
                }
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");


                LocalDate date1 = LocalDate.parse(edtNBD.getText().toString(), formatter);
                LocalDate date2 = LocalDate.parse(edtNKT.getText().toString(), formatter);
                LocalDate date3 = LocalDate.parse(date, formatter);
                if (date2.isBefore(date1)){
                    Toast.makeText(getContext(), "Ngày kết thúc không hợp lệ", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(date1.isBefore(date3)){
                    Toast.makeText(getContext(), "Ngày bắt đầu không được nhỏ hơn ngày hiện tại", Toast.LENGTH_SHORT).show();
                    return;


                }
                String nbd = edtNBD.getText().toString();
                String nkt = edtNKT.getText().toString();
                ll = new lichlam(manv,maca,nbd,nkt);
                if(lldao.them(ll)){
                    list.clear();
                    list.addAll(lldao.getLichlam());
                    Toast.makeText(getContext(), "THêm thành công", Toast.LENGTH_SHORT).show();
                    adapter.notifyDataSetChanged();
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
    private void showDatePickerDialog(EditText editText) {
        // Lấy ngày hiện tại
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        // Tạo DatePickerDialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
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