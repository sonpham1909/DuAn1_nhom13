package com.example.duan1_nhom13.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1_nhom13.DAO.loaisachDAO;
import com.example.duan1_nhom13.Model.loaisach;
import com.example.duan1_nhom13.R;

import java.util.ArrayList;

public class LPAdapter extends RecyclerView.Adapter<LPAdapter.Viewholder> implements Filterable {
    private Context context;
    private ArrayList<loaisach> list;
    private ArrayList<loaisach> listold;
    loaisachDAO dao;

    public LPAdapter(Context context, ArrayList<loaisach> list) {
        this.context = context;
        this.list = list;
        dao = new loaisachDAO(context);
        this.listold = list;
    }






    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_loaisach,null);
        return new  Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        holder.tvTen.setText(list.get(position).getTenloai());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class Viewholder extends RecyclerView.ViewHolder {
        TextView tvTen;

        public Viewholder(@NonNull View itemView) {
            super(itemView);

             tvTen = itemView.findViewById(R.id.tvtenloai);
        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String strSearch = constraint.toString();
                if(strSearch.isEmpty()){
                    list =listold;
                }else{
                    ArrayList<loaisach> lists = new ArrayList<>();
                    for (loaisach ls: listold){
                        if(ls.getTenloai().toLowerCase().contains(strSearch.toLowerCase())){
                            lists.add(ls);
                        }
                    }
                    list =lists;
                }
                FilterResults filterResults =new FilterResults();
                filterResults.values = list;

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                list = (ArrayList<loaisach>) results.values;
                notifyDataSetChanged();

            }
        };
    }

}
