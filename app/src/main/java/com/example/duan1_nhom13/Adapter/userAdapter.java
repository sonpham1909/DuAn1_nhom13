package com.example.duan1_nhom13.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1_nhom13.DAO.adminDAO;
import com.example.duan1_nhom13.Model.user;
import com.example.duan1_nhom13.R;

import java.util.ArrayList;

public class userAdapter extends RecyclerView.Adapter<userAdapter.Viewholder> {
    private Context context;
    private ArrayList<user> list;
    adminDAO dao;

    public userAdapter(Context context, ArrayList<user> list) {
        this.context = context;
        this.list = list;
        dao = new adminDAO(context);
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_nhan_vien, null);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        user us = list.get(position);
        holder.tvtennv.setText("Họ tên: " + list.get(position).getTen());
        holder.tvsdt.setText("Sđt: " + list.get(position).getSodienthoai());
        if (us.getRole() == 1) {
            holder.tvchucvu.setText("Quản lý");
        } else {
            holder.tvchucvu.setText("Nhân viên");
        }
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                LayoutInflater inflater = ((Activity) context).getLayoutInflater();
                View view = inflater.inflate(R.layout.item_thongtinnv, null);
                builder.setView(view);
                Dialog dialog = builder.create();
                dialog.show();
                LinearLayout ln = view.findViewById(R.id.lnTTnv);
                TextView txtten = view.findViewById(R.id.txthoten);
                TextView txttendn = view.findViewById(R.id.txttendangnhap);
                TextView txtmatkhau = view.findViewById(R.id.txtmatkhau);
                TextView txtcv = view.findViewById(R.id.txtChucVu);
                TextView txtsdt = view.findViewById(R.id.txtSDT);
                TextView txtsua = view.findViewById(R.id.tvsuanv);
                TextView txtxoa = view.findViewById(R.id.tvxoanv);

                txtten.setText(us.getTen());
                txttendn.setText(us.getUser());
                txtmatkhau.setText(us.getPass());
                txtsdt.setText(us.getSodienthoai());
                if (us.getRole() == 1) {
                    txtcv.setText("Quản lý");
                } else {
                    txtcv.setText("Nhân viên");

                }
                txtsua.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        update(us);
                        dialog.dismiss();
                    }
                });
                txtxoa.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                        LayoutInflater inflater1 = ((Activity)context).getLayoutInflater();
                        View view1 = inflater1.inflate(R.layout.delete_loai_phim,null);
                        builder1.setView(view1);
                        Dialog dialog1 = builder1.create();
                        dialog1.show();
                        ImageButton imgyes = view1.findViewById(R.id.yes);
                        ImageButton imgno = view1.findViewById(R.id.no);
                        imgyes.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if(dao.xoa(us.getUser())){
                                    list.clear();
                                    list.addAll(dao.getALl());
                                    notifyDataSetChanged();
                                    Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                                    dialog1.dismiss();
                                    dialog.dismiss();
                                }
                            }
                        });
                        imgno.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog1.dismiss();
                            }
                        });
                    }
                });
                ln.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class Viewholder extends RecyclerView.ViewHolder {

        TextView tvtennv, tvchucvu, tvsdt;
        CardView card;


        public Viewholder(@NonNull View itemView) {
            super(itemView);
            tvtennv = itemView.findViewById(R.id.tvtenNhanvien);
            tvsdt = itemView.findViewById(R.id.tvSdt);
            tvchucvu = itemView.findViewById(R.id.tvrole);
            card = itemView.findViewById(R.id.cardNV);
        }
    }

    public void update(user us) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.edit_nhan_vien, null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();
        EditText edtten = view.findViewById(R.id.edttennvu);
        EditText edttendn = view.findViewById(R.id.edtmanvu);
        EditText edtmk = view.findViewById(R.id.edtpassu);
        EditText edtsdt = view.findViewById(R.id.edtsdtu);
        EditText edtcv = view.findViewById(R.id.edtchucvuu);
        Button btnsua = view.findViewById(R.id.btnsuanvu);
        Button btnhuy = view.findViewById(R.id.btnhuynvu);


        edtten.setText(us.getTen());
        edttendn.setText(us.getUser());
        edtmk.setText(us.getPass());
        edtsdt.setText(us.getSodienthoai());
        if (us.getRole() == 1) {
            edtcv.setText("Quản lý");
        } else {
            edtcv.setText("Nhân viên");
        }

        edtcv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                androidx.appcompat.app.AlertDialog.Builder builder1 = new androidx.appcompat.app.AlertDialog.Builder(context);
                View view1 = ((Activity) context).getLayoutInflater().inflate(R.layout.item_chucv, null);
                builder1.setView(view1);
                Dialog dialog1 = builder1.create();
                dialog1.show();
                TextView nv = view1.findViewById(R.id.cvNhanvien);
                TextView ql = view1.findViewById(R.id.cvQuanly);

                if (edtcv.getText().toString().equals("Nhân viên")) {
                    nv.setBackgroundResource(R.drawable.brcv);
                } else {
                    ql.setBackgroundResource(R.drawable.brcv);
                }

                nv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        edtcv.setText("Nhân viên");
                        nv.setBackgroundResource(R.drawable.brcv);
                        ql.setBackgroundResource(R.drawable.brloaiphim);
                        dialog1.dismiss();

                    }
                });
                ql.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        edtcv.setText("Quản lý");
                        ql.setBackgroundResource(R.drawable.brcv);
                        nv.setBackgroundResource(R.drawable.brloaiphim);
                        dialog1.dismiss();

                    }
                });
            }
        });
        btnsua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edttendn.getText().toString().isEmpty() || edtten.getText().toString().isEmpty() || edtsdt.getText().toString().isEmpty() || edtmk.getText().toString().isEmpty()) {
                    Toast.makeText(context, "Không bỏ trống form", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (edtmk.getText().toString().length() < 4) {
                    Toast.makeText(context, "Mật khẩu phải >= 4 kí tự", Toast.LENGTH_SHORT).show();
                    return;

                }
                if (edtsdt.getText().toString().length() != 10) {
                    Toast.makeText(context, "Số điện thoại phải = 10 số", Toast.LENGTH_SHORT).show();
                    return;

                }

                us.setTen(edtten.getText().toString());

                us.setPass(edtmk.getText().toString());
                us.setSodienthoai(edtsdt.getText().toString());
                if (edtcv.getText().toString().equals("Nhân viên")) {
                    us.setRole(2);
                } else {
                    us.setRole(1);
                }
                if (dao.sua(us)) {
                    list.clear();
                    list.addAll(dao.getALl());
                    Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                    notifyDataSetChanged();
                    dialog.dismiss();
                } else {

                    Toast.makeText(context, "Sửa ko thành công", Toast.LENGTH_SHORT).show();

                }


            }
        });
        btnhuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


    }
}
