package com.example.duan1_nhom13.Fragment;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.duan1_nhom13.DAO.adminDAO;
import com.example.duan1_nhom13.Model.user;
import com.example.duan1_nhom13.R;
import com.example.duan1_nhom13.login;

public class doi_mat_khau extends Fragment {
    EditText edtpass,edtcfpass;



    public doi_mat_khau() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_doi_mat_khau, container, false);
         edtpass = view.findViewById(R.id.edtpassdmk);
         edtcfpass = view.findViewById(R.id.edtcfpassdmk);
        Button btndmk = view.findViewById(R.id.btndmk);
        btndmk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getContext().getSharedPreferences("thongtin", Context.MODE_PRIVATE);
                String user= sharedPreferences.getString("user","");
                if (validate()>0){
                    adminDAO dao = new adminDAO(getContext());
                    user tt = dao.getID(user);
                   tt.setPass(edtpass.getText().toString());

                    if(dao.sua(tt)){
                        Toast.makeText(getContext(), "Thay đổi thành công hãy đăng nhập lại", Toast.LENGTH_SHORT).show();
                        edtpass.setText("");
                        edtcfpass.setText("");
                        startActivity(new Intent(getContext(), login.class));

                    }else{
                        Toast.makeText(getContext(), "Không thành công", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        // Inflate the layout for this fragment
        return view;
    }
    public int validate(){

        int check = 1;
        if(edtpass.getText().toString().isEmpty()||edtcfpass.getText().toString().isEmpty()){

            Toast.makeText(getContext(), "Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check = -1;

        }else{

            SharedPreferences pref = getActivity().getSharedPreferences("thongtin", MODE_PRIVATE);

            String pass = edtpass.getText().toString();
            String repas = edtcfpass.getText().toString();

            if(!pass.equals(repas)){
                Toast.makeText(getContext(), "Mật khẩu không trùng khớp ", Toast.LENGTH_SHORT).show();
                check = -1;
            }



        }



        return check;
    }

}