package com.example.duan1_nhom13.Fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duan1_nhom13.Adapter.HD_Adapter;
import com.example.duan1_nhom13.Adapter.PhimAdapter;
import com.example.duan1_nhom13.DAO.HDDAO;
import com.example.duan1_nhom13.DAO.KHDAO;
import com.example.duan1_nhom13.DAO.loaisachDAO;
import com.example.duan1_nhom13.DAO.phimDAO;
import com.example.duan1_nhom13.DAO.suatchieuDAO;
import com.example.duan1_nhom13.Model.Phim;
import com.example.duan1_nhom13.Model.hoadon;
import com.example.duan1_nhom13.Model.khachhang;
import com.example.duan1_nhom13.Model.loaisach;
import com.example.duan1_nhom13.Model.suatchieu;
import com.example.duan1_nhom13.R;
import com.example.duan1_nhom13.spinerAdapter.kh_spinner;
import com.example.duan1_nhom13.spinerAdapter.loaiphimAdapter;
import com.example.duan1_nhom13.spinerAdapter.phim_spinner;
import com.example.duan1_nhom13.spinerAdapter.sc_spinner;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Quan_ly_hoa_don extends Fragment {
    RecyclerView rvc;
    FloatingActionButton fltbtn;
    ArrayList<hoadon> list = new ArrayList<>();
    hoadon hd;
    HD_Adapter adapter;
    HDDAO hddao;
    ArrayList<khachhang> listkh;
    ArrayList<Phim> listp;
    ArrayList<suatchieu> listsc;
    phimDAO pdao;
    suatchieuDAO scdao;
    KHDAO khdao;
    phim_spinner adapterphimspn;

    kh_spinner adapterkhspn;
    sc_spinner adapterscspn;
    int maphim,masc,makh;
    float giave;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quan_ly_hoa_don, container, false);
        // Inflate the layout for this fragment
        rvc = view.findViewById(R.id.rcvHoaDon);
        fltbtn = view.findViewById(R.id.btnaddHD);

        hddao = new HDDAO(getContext());
        list= hddao.getHD();
        GridLayoutManager manager = new GridLayoutManager(getContext(),1);
        rvc.setLayoutManager(manager);
        adapter = new HD_Adapter(getContext(),list);
        rvc.setAdapter(adapter);
        adapter.notifyDataSetChanged();


        fltbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                them();
            }
        });

        return view;
    }
    private Dialog dialog; // Thêm biến dialog vào lớp MainActivity

    public void them(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view = getLayoutInflater().inflate(R.layout.add_hoa_don,null);
        builder.setView(view);
        dialog = builder.create();
        dialog.show();

        Spinner spnKhachang = view.findViewById(R.id.spn_khachhang);
        Spinner spnPhim = view.findViewById(R.id.spn_phim);
        Spinner spnSC = view.findViewById(R.id.spn_SC);
        EditText edtSL = view.findViewById(R.id.edSoLuongVe);

        EditText edtNv = view.findViewById(R.id.edMaNhanVien);
        EditText edngayin= view.findViewById(R.id.edNgayInHoaDon);
        TextView tvtongtien = view.findViewById(R.id.tvTongtienadd);
        Button btnButton = view.findViewById(R.id.btnthemHD);
        SharedPreferences preferences = getContext().getSharedPreferences("thongtin", Context.MODE_PRIVATE);
        String user= preferences.getString("user","");
        edtNv.setText(user);
        // Lấy ngày giờ hiện tại
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String time = timeFormat.format(calendar.getTime());
        String date = dateFormat.format(calendar.getTime());

        // In ngày giờ hiện tại
       edngayin.setText(time+" "+date);

        Button btncancle = view.findViewById(R.id.btnHuyHD);
        listp = new ArrayList<>();
        pdao = new phimDAO(getContext());
        listp = (ArrayList<Phim>) pdao.getPhim();
        adapterphimspn = new phim_spinner(getContext(),listp);
        spnPhim.setAdapter(adapterphimspn);
        spnPhim.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maphim = listp.get(position).getMaPhim();
                giave = listp.get(position).getGia();
                Toast.makeText(getContext(), "Chọn" + listp.get(position).getTen(), Toast.LENGTH_SHORT).show();
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




                edtSL.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        // Không làm gì trong trường hợp này
                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        // Cập nhật dữ liệu của TextView khi EditText thay đổi
                        String input = edtSL.getText().toString();
                        int number;


                        if (!input.isEmpty()) {
                            number = Integer.parseInt(input);
                            float tongtien = number*giave;

                            tvtongtien.setText(String.valueOf(tongtien));
                        } else {
                            tvtongtien.setText("0");
                        }

                    }

                    @Override
                    public void afterTextChanged(Editable editable) {
                        // Không làm gì trong trường hợp này
                    }
                });





        listkh = new ArrayList<>();
        khdao = new KHDAO(getContext());
        listkh = (ArrayList<khachhang>) khdao.getKH();
        adapterkhspn = new kh_spinner(getContext(),listkh);
        spnKhachang.setAdapter(adapterkhspn);
        spnKhachang.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                makh = listkh.get(position).getMaKH();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        listsc = new ArrayList<>();
        scdao = new suatchieuDAO(getContext());
        listsc = (ArrayList<suatchieu>) scdao.getSC();
        adapterscspn = new sc_spinner(getContext(),listsc);
        spnSC.setAdapter(adapterscspn);
        spnSC.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                masc = listsc.get(position).getMaSC();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

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
                if (edtSL.getText().toString().isEmpty()){
                    Toast.makeText(getContext(), "Vui lòng nhập sô lượng", Toast.LENGTH_SHORT).show();
                    return;
                }



                String manv = edtNv.getText().toString();
                int soluong = Integer.parseInt(edtSL.getText().toString());
                String ngay = edngayin.getText().toString();
                float tongtien = Float.parseFloat(tvtongtien.getText().toString());





                hoadon hd = new hoadon(manv,makh,maphim,masc,soluong,tongtien,ngay);


                if(hddao.themHD(hd)){
                    Toast.makeText(getContext(), "thêm tc", Toast.LENGTH_SHORT).show();
                    list.clear();
                    list.addAll(hddao.getHD());
                    adapter.notifyDataSetChanged();
                    dialog.dismiss();


                }
            }
        });




    }
    public  void loadlist(){
        list.clear();
        list.addAll(hddao.getHD());
        adapter = new HD_Adapter(requireContext(), list);
        rvc.setAdapter(adapter);
        adapter.notifyDataSetChanged();



    }

}