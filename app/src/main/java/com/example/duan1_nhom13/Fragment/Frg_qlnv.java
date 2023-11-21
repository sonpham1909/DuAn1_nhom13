package com.example.duan1_nhom13.Fragment;

import android.app.Dialog;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.duan1_nhom13.Adapter.userAdapter;
import com.example.duan1_nhom13.DAO.adminDAO;
import com.example.duan1_nhom13.Model.user;
import com.example.duan1_nhom13.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class Frg_qlnv extends Fragment {
    FloatingActionButton fltbtn;
    RecyclerView rcv;
    userAdapter adapter;
    adminDAO dao;
    ArrayList<user> list = new ArrayList<>();




    public Frg_qlnv() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_frg_qlnv, container, false);
        // Inflate the layout for this fragment
        rcv = view.findViewById(R.id.rcvNhanvien);
        dao = new adminDAO(getContext());
        list = dao.getALl();
        GridLayoutManager manager = new GridLayoutManager(getContext(),1);
        rcv.setLayoutManager(manager);
        adapter = new userAdapter(getContext(),list);
        rcv.setAdapter(adapter);
        adapter.notifyDataSetChanged();



        fltbtn = view.findViewById(R.id.btnaddnv);
        fltbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                them();

            }
        });

        return view;
    }
    public  void  them(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view = getLayoutInflater().inflate(R.layout.add_qlnv,null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();
    }
}