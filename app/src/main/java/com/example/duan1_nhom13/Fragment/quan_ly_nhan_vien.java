package com.example.duan1_nhom13.Fragment;

import android.app.Dialog;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duan1_nhom13.Adapter.LPAdapter;
import com.example.duan1_nhom13.Adapter.userAdapter;
import com.example.duan1_nhom13.DAO.adminDAO;
import com.example.duan1_nhom13.Model.loaisach;
import com.example.duan1_nhom13.Model.user;
import com.example.duan1_nhom13.R;
import com.example.duan1_nhom13.SharedViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class quan_ly_nhan_vien extends Fragment {
    FloatingActionButton fltbtn;
    RecyclerView rcv;
    userAdapter adapter;
    adminDAO dao;
    ArrayList<user> list = new ArrayList<>();
    SharedViewModel sharedViewModel;


    public quan_ly_nhan_vien() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quan_ly_nhan_vien, container, false);
        // Inflate the layout for this fragment
        rcv = view.findViewById(R.id.rcvNhanvien);
        dao = new adminDAO(getContext());
        list = dao.getALl();
        GridLayoutManager manager = new GridLayoutManager(getContext(), 1);
        rcv.setLayoutManager(manager);
        adapter = new userAdapter(getContext(), list);
        rcv.setAdapter(adapter);
        adapter.notifyDataSetChanged();


        fltbtn = view.findViewById(R.id.btnaddnv);
        fltbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                them();

            }
        });
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);


        sharedViewModel.getSearchText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String newText) {

                if (TextUtils.isEmpty(newText)){
                    loadlist();
                }
                ArrayList<user> filterList = new ArrayList<>();
                for (user book : list) {
                    if (book.getTen().toLowerCase().contains(newText.toLowerCase())) {
                        filterList.add(book);
                    }
                }



                // Cập nhật giao diện hoặc thực hiện tìm kiếm với `newText`
                // Cập nhật dữ liệu trong RecyclerView
                adapter = new userAdapter(requireContext(), filterList);
                rcv.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        });

        return view;
    }

    public void them() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view = getLayoutInflater().inflate(R.layout.add_nhan_vien, null);
        builder.setView(view);
        Dialog dialog = builder.create();

        dialog.show();
        EditText edtuser = view.findViewById(R.id.edtmanv);
        EditText edtpass = view.findViewById(R.id.edtpass);
        EditText edttenuser = view.findViewById(R.id.edttennv);
        EditText edtsdt = view.findViewById(R.id.edtsdt);
        EditText edtchucvu = view.findViewById(R.id.edtchucvu);
        Button btnadd = view.findViewById(R.id.btnaddnv);
        Button btnhuy = view.findViewById(R.id.btnhuynv);

        edtchucvu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(getContext());
                View view1 = getLayoutInflater().inflate(R.layout.item_chucv, null);
                builder1.setView(view1);
                Dialog dialog1 = builder1.create();
                dialog1.show();
                TextView nv = view1.findViewById(R.id.cvNhanvien);
                TextView ql = view1.findViewById(R.id.cvQuanly);

                if (edtchucvu.getText().toString().equals("Nhân viên")) {
                    nv.setBackgroundResource(R.drawable.brcv);
                } else {
                    ql.setBackgroundResource(R.drawable.brcv);
                }

                nv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        edtchucvu.setText("Nhân viên");
                        nv.setBackgroundResource(R.drawable.brcv);
                        ql.setBackgroundResource(R.drawable.brloaiphim);
                        dialog1.dismiss();

                    }
                });
                ql.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        edtchucvu.setText("Quản lý");
                        ql.setBackgroundResource(R.drawable.brcv);
                        nv.setBackgroundResource(R.drawable.brloaiphim);
                        dialog1.dismiss();

                    }
                });

            }


        });
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtuser.getText().toString().isEmpty() || edttenuser.getText().toString().isEmpty() || edtsdt.getText().toString().isEmpty() || edtpass.getText().toString().isEmpty()) {
                    Toast.makeText(getContext(), "Không bỏ trống form", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (edtpass.getText().toString().length() < 4) {
                    Toast.makeText(getContext(), "Mật khẩu phải >= 4 kí tự", Toast.LENGTH_SHORT).show();
                    return;

                }
                if (edtsdt.getText().toString().length() != 10) {
                    Toast.makeText(getContext(), "Số điện thoại phải = 10 số", Toast.LENGTH_SHORT).show();
                    return;

                }
                String ma = edtuser.getText().toString();
                String ten = edttenuser.getText().toString();
                String pass = edtpass.getText().toString();
                String sdt = edtsdt.getText().toString();
                String cv = edtchucvu.getText().toString();
                int cvu = 2;
                if (cv.equals("Nhân viên")) {
                    cvu = 2;

                } else {
                    cvu = 1;
                }
                user user = new user(ma, ten, cvu, pass, sdt);
                if (dao.them(user)) {
                    list.clear();
                    list.addAll(dao.getALl());
                    Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                    adapter.notifyDataSetChanged();
                    dialog.dismiss();
                } else {
                    Toast.makeText(getContext(), "Mã nhân viên đã được sd", Toast.LENGTH_SHORT).show();

                }

            }
        });
        btnhuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });


    }
    public  void loadlist(){
        list.clear();
        list.addAll(dao.getALl());
        adapter = new userAdapter(requireContext(), list);
        rcv.setAdapter(adapter);
        adapter.notifyDataSetChanged();



    }
}