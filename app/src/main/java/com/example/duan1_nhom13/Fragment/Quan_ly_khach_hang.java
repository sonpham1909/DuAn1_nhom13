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
import android.widget.Toast;

import com.example.duan1_nhom13.Adapter.KH_Adapter;
import com.example.duan1_nhom13.Adapter.LPAdapter;
import com.example.duan1_nhom13.DAO.KHDAO;
import com.example.duan1_nhom13.Model.khachhang;
import com.example.duan1_nhom13.Model.loaisach;
import com.example.duan1_nhom13.Model.phonchieu;
import com.example.duan1_nhom13.R;
import com.example.duan1_nhom13.SharedViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Quan_ly_khach_hang extends Fragment {
    RecyclerView rcv;
    FloatingActionButton fltbtn;
    ArrayList<khachhang> list;
    KHDAO khdao;
    khachhang kh;
    KH_Adapter adapter;
    SharedViewModel sharedViewModel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_quan_ly_khach_hang, container, false);
        // Inflate the layout for this fragment
        rcv = view.findViewById(R.id.rcvKhachHang);
        fltbtn = view.findViewById(R.id.btnaddKH);
        khdao = new KHDAO(getContext());
        list = khdao.getKH();
        GridLayoutManager manager = new GridLayoutManager(getContext(),1);
        rcv.setLayoutManager(manager);
        adapter = new KH_Adapter(getContext(),list);
        rcv.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);


        sharedViewModel.getSearchText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String newText) {

                if (TextUtils.isEmpty(newText)){
                    loadlist();
                }
                ArrayList<khachhang> filterList = new ArrayList<>();
                for (khachhang book : list) {
                    if (book.getTenKH().toLowerCase().contains(newText.toLowerCase())) {
                        filterList.add(book);
                    }
                }



                // Cập nhật giao diện hoặc thực hiện tìm kiếm với `newText`
                // Cập nhật dữ liệu trong RecyclerView
                adapter = new KH_Adapter(requireContext(), filterList);
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

        return view;
    }
    public void them(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view = getLayoutInflater().inflate(R.layout.add_khach_hang,null);
        builder.setView(view);
        Dialog dialog =builder.create();
        dialog.show();
        EditText edtTen = view.findViewById(R.id.edTenKhachHanga);
        EditText edtSG = view.findViewById(R.id.edSoDienThoaia);
        Button btnAdd = view.findViewById(R.id.btnAddKH);
        Button btnHuy = view.findViewById(R.id.btnHuyKH);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edtTen.getText().toString().isEmpty()||edtSG.getText().toString().isEmpty()){
                    Toast.makeText(getContext(), "Không được để trống", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(edtSG.getText().toString().length()!=10){
                    Toast.makeText(getContext(), "SDT phải là số và = 10 chữ số", Toast.LENGTH_SHORT).show();
                    return;

                }
                String ten = edtTen.getText().toString();
                String sdt = edtSG.getText().toString();
                kh = new khachhang(ten,sdt);
                if(khdao.themKH(kh)){
                    list.clear();
                    list.addAll(khdao.getKH());
                    adapter.notifyDataSetChanged();
                    Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }else{
                    Toast.makeText(getContext(), "Thêm không thành công", Toast.LENGTH_SHORT).show();

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
    public  void loadlist(){
        list.clear();
        list.addAll(khdao.getKH());
        adapter = new KH_Adapter(requireContext(), list);
        rcv.setAdapter(adapter);
        adapter.notifyDataSetChanged();



    }
}