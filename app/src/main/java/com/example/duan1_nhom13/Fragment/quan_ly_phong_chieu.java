package com.example.duan1_nhom13.Fragment;

import android.app.Dialog;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.duan1_nhom13.Adapter.PC_Adapter;
import com.example.duan1_nhom13.Adapter.PhimAdapter;
import com.example.duan1_nhom13.Adapter.userAdapter;
import com.example.duan1_nhom13.DAO.phongchieuDAO;
import com.example.duan1_nhom13.Model.Phim;
import com.example.duan1_nhom13.Model.phonchieu;
import com.example.duan1_nhom13.R;
import com.example.duan1_nhom13.SharedViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class quan_ly_phong_chieu extends Fragment {

RecyclerView rcv;
FloatingActionButton fltbtn;
ArrayList<phonchieu> list = new ArrayList<>();
phongchieuDAO PCDAO;
phonchieu pc;
PC_Adapter adapter;
    SharedViewModel sharedViewModel;

    public quan_ly_phong_chieu() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quan_ly_phong_chieu, container, false);
        // Inflate the layout for this fragment
        rcv = view.findViewById(R.id.rcvPhongChieu);
        fltbtn = view.findViewById(R.id.btnaddPc);
        PCDAO = new phongchieuDAO(getContext());
        list = PCDAO.getPC();
        GridLayoutManager manager = new GridLayoutManager(getContext(),1);
        rcv.setLayoutManager(manager);
        adapter = new PC_Adapter(getContext(),list);
        rcv.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);


        sharedViewModel.getSearchText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String newText) {

                if (TextUtils.isEmpty(newText)){
                    loadlist();
                }
                ArrayList<phonchieu> filterList = new ArrayList<>();
                for (phonchieu book : list) {
                    if (book.getTenPC().toLowerCase().contains(newText.toLowerCase())) {
                        filterList.add(book);
                    }
                }



                // Cập nhật giao diện hoặc thực hiện tìm kiếm với `newText`
                // Cập nhật dữ liệu trong RecyclerView
                adapter = new PC_Adapter(requireContext(), filterList);
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
        View view = getLayoutInflater().inflate(R.layout.add_phong_chieu,null);
        builder.setView(view);
        Dialog dialog =builder.create();
        dialog.show();
        EditText edtTen = view.findViewById(R.id.edTenPhongChieu);
        EditText edtSG = view.findViewById(R.id.edSoLuongGhe);
        Button btnAdd = view.findViewById(R.id.btnAddPC);
        Button btnHuy = view.findViewById(R.id.btnHuyPC);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edtTen.getText().toString().isEmpty()||edtSG.getText().toString().isEmpty()){
                    Toast.makeText(getContext(), "Không được để trống", Toast.LENGTH_SHORT).show();
                    return;
                }
                try {
                    Integer.parseInt(edtSG.getText().toString());
                }catch (Exception e){
                    Toast.makeText(getContext(), "Số ghế phải là sô", Toast.LENGTH_SHORT).show();
                    return;


                }
                if(Integer.parseInt(edtSG.getText().toString())<=10||Integer.parseInt(edtSG.getText().toString())>=60){
                    Toast.makeText(getContext(), "Số ghế phải >10 và <=60", Toast.LENGTH_SHORT).show();
                    return;

                }
                String ten = edtTen.getText().toString();
                int ghe = Integer.parseInt(edtSG.getText().toString());
                pc = new phonchieu(ten,ghe);
                if(PCDAO.themPC(pc)){
                    list.clear();
                    list.addAll(PCDAO.getPC());
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
        list.addAll(PCDAO.getPC());
        adapter = new PC_Adapter(requireContext(), list);
        rcv.setAdapter(adapter);
        adapter.notifyDataSetChanged();



    }
}