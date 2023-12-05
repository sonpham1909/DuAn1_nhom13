package com.example.duan1_nhom13.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.duan1_nhom13.Adapter.top5apdapter;
import com.example.duan1_nhom13.DAO.HDDAO;
import com.example.duan1_nhom13.Model.top;
import com.example.duan1_nhom13.R;

import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;


public class thong_ke extends Fragment {
    HDDAO hddao;
    ArrayList<top> list;
    ListView lv;
    top5apdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_thong_ke, container, false);
        TextView tvthongke = view.findViewById(R.id.tvLoiNhuan);
        Button btnngay = view.findViewById(R.id.btnNgay);
        Button btntuan = view.findViewById(R.id.btnTuan);
        Button btnthang = view.findViewById(R.id.btnThang);
        TextView tvthongkeve = view.findViewById(R.id.tvVe);
        ImageButton close = view.findViewById(R.id.btnbo);
        hddao = new HDDAO(getContext());
        int doanhthu;
        doanhthu =hddao.laygiatriall();
        Locale locale = new Locale("nv", "VN");
        NumberFormat nf = NumberFormat.getCurrencyInstance(locale);
        String tienfomat = nf.format(doanhthu);
        tvthongke.setText( tienfomat);
        int ve;
        ve = hddao.laysoluotdatv();
        tvthongkeve.setText("Số vé:"+ve);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int doanhthu;
                doanhthu =hddao.laygiatriall();
                Locale locale = new Locale("nv", "VN");
                NumberFormat nf = NumberFormat.getCurrencyInstance(locale);
                String tienfomat = nf.format(doanhthu);
                tvthongke.setText( tienfomat);
                int ve;
                ve = hddao.laysoluotdatv();
                tvthongkeve.setText("Số vé:"+ve);
                btnngay.setBackgroundResource(R.drawable.brnhanvien);
                btnthang.setBackgroundResource(R.drawable.brnhanvien);
                btntuan.setBackgroundResource(R.drawable.brnhanvien);

            }
        });




        hddao = new HDDAO(getContext());
       btnngay.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               btnngay.setBackgroundResource(R.drawable.brchoose);
               btnthang.setBackgroundResource(R.drawable.brnhanvien);
               btntuan.setBackgroundResource(R.drawable.brnhanvien);
               LocalDate currentDate = LocalDate.now();
               LocalDate previousDate = currentDate.minusDays(2);


               DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
               String formattedDate = currentDate.format(formatter);
               String formattedDate2 = previousDate.format(formatter);

               System.out.println("Ngày hiện tại: " + formattedDate);


               int doanhthu;
               doanhthu =hddao.laygiatritheongay1();
               Locale locale = new Locale("nv", "VN");
               NumberFormat nf = NumberFormat.getCurrencyInstance(locale);
               String tienfomat = nf.format(doanhthu);
               tvthongke.setText( tienfomat);
               int ve;
               ve = hddao.laysoluotdatve1();
               tvthongkeve.setText("Số vé:"+ve);

           }
       });
        btntuan.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                btntuan.setBackgroundResource(R.drawable.brchoose);
                btnthang.setBackgroundResource(R.drawable.brnhanvien);
                btnngay.setBackgroundResource(R.drawable.brnhanvien);

                LocalDate currentDate = LocalDate.now();
                LocalDate previousDate = currentDate.minusDays(2);


                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                String formattedDate = currentDate.format(formatter);
                String formattedDate2 = previousDate.format(formatter);

                System.out.println("Ngày hiện tại: " + formattedDate);


                int doanhthu;
                doanhthu =hddao.laygiatritheongay2();
                Locale locale = new Locale("nv", "VN");
                NumberFormat nf = NumberFormat.getCurrencyInstance(locale);
                String tienfomat = nf.format(doanhthu);
                tvthongke.setText( tienfomat);
                int ve;
                ve = hddao.laysoluotdatv2();
                tvthongkeve.setText("Số vé:"+ve);


            }
        });
        btnthang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnthang.setBackgroundResource(R.drawable.brchoose);
                btntuan.setBackgroundResource(R.drawable.brnhanvien);
                btnngay.setBackgroundResource(R.drawable.brnhanvien);
                LocalDate currentDate = LocalDate.now();
                LocalDate previousDate = currentDate.minusDays(2);


                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                String formattedDate = currentDate.format(formatter);
                String formattedDate2 = previousDate.format(formatter);

                System.out.println("Ngày hiện tại: " + formattedDate);


                int doanhthu;
                doanhthu =hddao.laygiatritheongay3();
                Locale locale = new Locale("nv", "VN");
                NumberFormat nf = NumberFormat.getCurrencyInstance(locale);
                String tienfomat = nf.format(doanhthu);
                tvthongke.setText( tienfomat);
                int ve;
                ve = hddao.laysoluotdatv3();
                tvthongkeve.setText("Số vé:"+ve);

            }
        });
        lv = view.findViewById(R.id.listView);
        HDDAO top10Dao =new HDDAO(getActivity());
        list = (ArrayList<top>) top10Dao.getTop();
        adapter = new top5apdapter(getActivity(),this,list);
        lv.setAdapter(adapter);
        // Inflate the layout for this fragment
        return view;
    }
}