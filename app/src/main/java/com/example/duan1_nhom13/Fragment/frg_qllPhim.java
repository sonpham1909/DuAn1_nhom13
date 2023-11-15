package com.example.duan1_nhom13.Fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.duan1_nhom13.Adapter.LPAdapter;
import com.example.duan1_nhom13.DAO.loaisachDAO;
import com.example.duan1_nhom13.Model.loaisach;
import com.example.duan1_nhom13.R;
import com.example.duan1_nhom13.SharedViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class frg_qllPhim extends Fragment {
    RecyclerView rcv;
    ArrayList<loaisach> list;
    LPAdapter adapter;
    loaisach ls;
    loaisachDAO dao;
    SharedViewModel sharedViewModel;
    FloatingActionButton fltbtn;




    public frg_qllPhim() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_frg_qll_phim, container, false);
        // Inflate the layout for this fragment
        rcv = view.findViewById(R.id.rcvLoaiphim);
        fltbtn = view.findViewById(R.id.btnaddlp);
        dao = new loaisachDAO(getContext());
        list = dao.getLoaiPhim();


        GridLayoutManager manager = new GridLayoutManager(getContext(),1);
        rcv.setLayoutManager(manager);

        adapter= new LPAdapter(getContext(),list);
        rcv.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);


        sharedViewModel.getSearchText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String newText) {

                if (TextUtils.isEmpty(newText)){
                    loadlist();
                }
                ArrayList<loaisach> filterList = new ArrayList<>();
                for (loaisach book : list) {
                    if (book.getTenloai().toLowerCase().contains(newText.toLowerCase())) {
                        filterList.add(book);
                    }
                }



                // Cập nhật giao diện hoặc thực hiện tìm kiếm với `newText`
                // Cập nhật dữ liệu trong RecyclerView
                 adapter = new LPAdapter(requireContext(), filterList);
                rcv.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        });
        fltbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                themLoaiphim();
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
    public void themLoaiphim(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view = getLayoutInflater().inflate(R.layout.add_lp,null);
        builder.setView(view);
        Dialog dialog =builder.create();
        dialog.show();
        EditText edtten = view.findViewById(R.id.edttentheloai);

        Button btnsm = view.findViewById(R.id.btnsave);
        Button btncancel = view.findViewById(R.id.btncancel);

        btnsm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtten.getText().toString().isEmpty()){
                    Toast.makeText(getContext(), "Không bỏ trống", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(edtten.getText().toString().length()<2){
                    Toast.makeText(getContext(), "Tên thể loại không được nhỏ hơn 2 kí tự !", Toast.LENGTH_SHORT).show();
                    return;


                }
                String ten= edtten.getText().toString();
                ls = new loaisach(ten);
                if (dao.themloaisach(ls)){
                    list.clear();
                    list.addAll(dao.getLoaiPhim());
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
    public  void loadlist(){
        list.clear();
        list.addAll(dao.getLoaiPhim());
        adapter = new LPAdapter(requireContext(), list);
        rcv.setAdapter(adapter);
        adapter.notifyDataSetChanged();



    }



}