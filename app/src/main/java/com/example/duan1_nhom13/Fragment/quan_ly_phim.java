package com.example.duan1_nhom13.Fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.duan1_nhom13.Adapter.PhimAdapter;
import com.example.duan1_nhom13.DAO.loaisachDAO;
import com.example.duan1_nhom13.DAO.phimDAO;
import com.example.duan1_nhom13.Model.Phim;
import com.example.duan1_nhom13.Model.loaisach;
import com.example.duan1_nhom13.R;
import com.example.duan1_nhom13.SharedViewModel;
import com.example.duan1_nhom13.spinerAdapter.loaiphimAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;


public class quan_ly_phim extends Fragment{

    RecyclerView rcv;
    FloatingActionButton fltbtn;

    ArrayList<loaisach> listls;
    ArrayList<Phim> listp = new ArrayList<>();
    SharedViewModel sharedViewModel;
    int mals;
    loaisachDAO dao;
    loaiphimAdapter spnadapter;


    private ActivityResultLauncher<Intent> galleryLauncher;
    ImageView imgHinh;
    phimDAO Pdao;


    private PhimAdapter adapter;



    public quan_ly_phim() {

        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        galleryLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                        Uri uri = result.getData().getData();
                        try {
                            InputStream inputStream = requireActivity().getContentResolver().openInputStream(uri);
                            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                            imgHinh.setImageBitmap(bitmap);
                            Intent data = result.getData();


                        } catch (FileNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
        PhimAdapter adapter = new PhimAdapter(getContext(), listp);


    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quan_ly_phim, container, false);
        // Inflate the layout for this fragment
        // Inflate the layout for this fragment
        rcv = view.findViewById(R.id.rcvPhim);
        fltbtn = view.findViewById(R.id.btnaddPhim);
        Pdao = new phimDAO(getContext());
        listp = Pdao.getPhim();


        GridLayoutManager manager = new GridLayoutManager(getContext(),3);
        rcv.setLayoutManager(manager);

        adapter= new PhimAdapter(getContext(),listp);
        rcv.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);


        sharedViewModel.getSearchText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String newText) {

                if (TextUtils.isEmpty(newText)){
                    loadlist();
                }
                ArrayList<Phim> filterList = new ArrayList<>();
                for (Phim book : listp) {
                    if (book.getTen().toLowerCase().contains(newText.toLowerCase())) {
                        filterList.add(book);
                    }
                }



                // Cập nhật giao diện hoặc thực hiện tìm kiếm với `newText`
                // Cập nhật dữ liệu trong RecyclerView
                adapter = new PhimAdapter(requireContext(), filterList);
                rcv.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        });





        fltbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
    private Dialog dialog; // Thêm biến dialog vào lớp MainActivity

    public void them(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view = getLayoutInflater().inflate(R.layout.add_phim,null);
        builder.setView(view);
          dialog = builder.create();
        dialog.show();
        imgHinh = view.findViewById(R.id.imgphim);
        Spinner spn = view.findViewById(R.id.spnloaisach);
        EditText edtTen = view.findViewById(R.id.edttenPhim);
        EditText edtgia = view.findViewById(R.id.edtGiave);
        EditText editThoilg= view.findViewById(R.id.edtthoiluong);
        Button btnButton = view.findViewById(R.id.btnaddphim);
        Button choose = view.findViewById(R.id.btnchoose);
        Button btncancle = view.findViewById(R.id.btnhuy);
        listls = new ArrayList<>();
        dao = new loaisachDAO(getContext());
        listls = (ArrayList<loaisach>) dao.getLoaiPhim();
        spnadapter = new loaiphimAdapter(getContext(),listls);
        spn.setAdapter(spnadapter);
        spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mals = listls.get(position).getMaloai();
                Toast.makeText(getContext(), "Chọn" + listls.get(position).getTenloai(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                galleryLauncher.launch(galleryIntent);


            }
        });
        btncancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //chuyên data sang bte
                BitmapDrawable bitmapDrawable = (BitmapDrawable) imgHinh.getDrawable();
                Bitmap bitmap = bitmapDrawable.getBitmap();
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100,stream);
                if(edtTen.getText().toString().isEmpty()||edtgia.getText().toString().isEmpty()||editThoilg.getText().toString().isEmpty()){
                    Toast.makeText(getContext(), "Vui lòng nhập đầy đủ", Toast.LENGTH_SHORT).show();
                    return;
                }
                try {
                    Float.parseFloat(edtgia.getText().toString());
                }catch (Exception e){
                    Toast.makeText(getContext(), "Vui lòng nhập số", Toast.LENGTH_SHORT).show();
                    return;
                }

                byte[] hinh = stream.toByteArray();
                String ten = edtTen.getText().toString();
                Float gia = Float.parseFloat(edtgia.getText().toString());
                int thoiLuong = Integer.parseInt(editThoilg.getText().toString());

                Phim phim = new Phim(hinh,ten,gia,thoiLuong,mals);


                if(Pdao.themPhim(phim)){
                    Toast.makeText(getContext(), "thêm tc", Toast.LENGTH_SHORT).show();
                    listp.clear();
                    listp.addAll(Pdao.getPhim());
                    adapter.notifyDataSetChanged();
                    dialog.dismiss();


                }
            }
        });




    }
    public  void loadlist(){
        listp.clear();
        listp.addAll(Pdao.getPhim());
        adapter = new PhimAdapter(requireContext(), listp);
        rcv.setAdapter(adapter);
        adapter.notifyDataSetChanged();



    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }

    }


}