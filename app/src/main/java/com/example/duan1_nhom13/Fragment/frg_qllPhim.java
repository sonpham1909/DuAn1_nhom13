package com.example.duan1_nhom13.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.duan1_nhom13.Adapter.LPAdapter;
import com.example.duan1_nhom13.DAO.loaisachDAO;
import com.example.duan1_nhom13.Model.loaisach;
import com.example.duan1_nhom13.R;
import com.example.duan1_nhom13.SharedViewModel;

import java.util.ArrayList;

public class frg_qllPhim extends Fragment {
    RecyclerView rcv;
    ArrayList<loaisach> list;
    LPAdapter adapter;
    loaisach ls;
    loaisachDAO dao;
    SharedViewModel sharedViewModel;




    public frg_qllPhim() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_frg_qll_phim, container, false);
        // Inflate the layout for this fragment
        rcv = view.findViewById(R.id.rcvLoaiphim);
        dao = new loaisachDAO(getContext());
        list = dao.getLoaiPhim();



        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        rcv.setLayoutManager(manager);

        adapter= new LPAdapter(getContext(),list);
        rcv.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        sharedViewModel.getSearchText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String newText) {
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

        return view;
    }


}