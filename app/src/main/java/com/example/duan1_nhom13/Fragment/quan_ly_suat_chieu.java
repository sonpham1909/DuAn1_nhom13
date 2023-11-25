package com.example.duan1_nhom13.Fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.duan1_nhom13.Adapter.PC_Adapter;
import com.example.duan1_nhom13.Adapter.PhimAdapter;
import com.example.duan1_nhom13.Adapter.SC_Adapter;
import com.example.duan1_nhom13.DAO.loaisachDAO;
import com.example.duan1_nhom13.DAO.phongchieuDAO;
import com.example.duan1_nhom13.DAO.suatchieuDAO;
import com.example.duan1_nhom13.Model.loaisach;
import com.example.duan1_nhom13.Model.phonchieu;
import com.example.duan1_nhom13.Model.suatchieu;
import com.example.duan1_nhom13.R;
import com.example.duan1_nhom13.SharedViewModel;
import com.example.duan1_nhom13.spinerAdapter.loaiphimAdapter;
import com.example.duan1_nhom13.spinerAdapter.pc_spinner;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;


public class quan_ly_suat_chieu extends Fragment {
    RecyclerView rcv;
    ArrayList<suatchieu> list = new ArrayList<>();
    suatchieu sc;
    SC_Adapter adapter;
    suatchieuDAO scdao;
    FloatingActionButton fltbtn;
    int mapc;
    ArrayList<phonchieu> listpc;
    SharedViewModel sharedViewModel;
    public quan_ly_suat_chieu() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_quan_ly_suat_chieu, container, false);
        // Inflate the layout for this fragment
        rcv = view.findViewById(R.id.rcvSustCHieu);
        fltbtn = view.findViewById(R.id.btnaddsc);
        scdao = new suatchieuDAO(getContext());
        list = scdao.getSC();
        GridLayoutManager manager = new GridLayoutManager(getContext(),1);
        rcv.setLayoutManager(manager);
        adapter = new SC_Adapter(getContext(),list);
        rcv.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);


        sharedViewModel.getSearchText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String newText) {

                if (TextUtils.isEmpty(newText)){
                    loadlist();
                }
                ArrayList<suatchieu> filterList = new ArrayList<>();
                for (suatchieu book : list) {
                    if (book.getTenSC().toLowerCase().contains(newText.toLowerCase())) {
                        filterList.add(book);
                    }
                }



                // Cập nhật giao diện hoặc thực hiện tìm kiếm với `newText`
                // Cập nhật dữ liệu trong RecyclerView
                adapter = new SC_Adapter(requireContext(), filterList);
                rcv.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        });
        fltbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                them();
                loadlist();
            }
        });
        SharedPreferences preferences = getContext().getSharedPreferences("role", Context.MODE_PRIVATE);
        int role = preferences.getInt("role",1);
        if (role==1){
            fltbtn.setVisibility(View.VISIBLE);
        }else{
            fltbtn.setVisibility(View.GONE);
        }
        return view;


    }




    public void them(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view = getLayoutInflater().inflate(R.layout.add_suat_chieu,null);
        builder.setView(view);
        Dialog dialog =builder.create();
        dialog.show();
        EditText edtTen = view.findViewById(R.id.edTenSuatChieu);
        Spinner spnpc = view.findViewById(R.id.spnpc);
        EditText edtngay = view.findViewById(R.id.edNgayChieu);
        EditText edtgio = view.findViewById(R.id.edGioChieu);
        Button btnAdd = view.findViewById(R.id.btnsaveSC);
        Button btnHuy = view.findViewById(R.id.btnHuySC);
        listpc = new ArrayList<>();
        phongchieuDAO dao = new phongchieuDAO(getContext());
        listpc = (ArrayList<phonchieu>) dao.getPC();
        pc_spinner spn_pc =new pc_spinner(getContext(),listpc);
        spnpc.setAdapter(spn_pc);

        spnpc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mapc= listpc.get(position).getMaPC();
                Toast.makeText(getContext(), "Chọn" + listpc.get(position).getTenPC(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        edtngay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog(edtngay);
            }
        });
        edtgio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimePickerDialog(edtgio);
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edtTen.getText().toString().isEmpty()||edtngay.getText().toString().isEmpty()||edtgio.getText().toString().isEmpty()){
                    Toast.makeText(getContext(), "Không được để trống", Toast.LENGTH_SHORT).show();
                    return;
                }

                String ten = edtTen.getText().toString();
                String ngay = edtngay.getText().toString();
                String gio = edtgio.getText().toString();

                sc = new suatchieu(ten,ngay,gio,mapc);
                if(scdao.themPC(sc)){
                    list.clear();
                    list.addAll(scdao.getSC());
                    adapter.notifyDataSetChanged();
                    Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            }
        });
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                String selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
                editText.setText(selectedDate);
            }
        }, year, month, day);

        // Hiển thị dialog
        datePickerDialog.show();
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
    public  void loadlist(){
        list.clear();
        list.addAll(scdao.getSC());
        adapter = new SC_Adapter(requireContext(), list);
        rcv.setAdapter(adapter);
        adapter.notifyDataSetChanged();



    }
}