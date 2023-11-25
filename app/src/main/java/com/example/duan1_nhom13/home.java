package com.example.duan1_nhom13;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duan1_nhom13.DAO.adminDAO;
import com.example.duan1_nhom13.Fragment.Quan_ly_hoa_don;
import com.example.duan1_nhom13.Fragment.Quan_ly_khach_hang;
import com.example.duan1_nhom13.Fragment.man_hinh_chinh;
import com.example.duan1_nhom13.Fragment.quan_ly_phim;
import com.example.duan1_nhom13.Fragment.quan_ly_nhan_vien;
import com.example.duan1_nhom13.Fragment.quan_ly_loai_phim;
import com.example.duan1_nhom13.Fragment.quan_ly_phong_chieu;
import com.example.duan1_nhom13.Fragment.quan_ly_suat_chieu;
import com.example.duan1_nhom13.Fragment.thong_tin_tai_khoan;
import com.example.duan1_nhom13.Model.user;
import com.google.android.material.navigation.NavigationView;

public class home extends AppCompatActivity {
    DrawerLayout drawerLayout;
    Fragment fragment = null;
    View headerview;
    TextView tvuser,tvfullname,tvrole;
    adminDAO dao;
    SearchView searchView;
    private SharedViewModel sharedViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar =findViewById(R.id.toolbar);
        FrameLayout frameLayout = findViewById(R.id.Framelayout);
        NavigationView navigationView = findViewById(R.id.navigationview);
        drawerLayout = findViewById(R.id.drawable);

        setSupportActionBar(toolbar);
        ActionBar actionBar  = getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.menu);
        actionBar.setSubtitle("Màn hình chính");
        actionBar.setTitle("SpecTraFlix");
        fragment = new man_hinh_chinh();
        replace(fragment);
        navigationView.invalidate();
        headerview = navigationView.getHeaderView(0);
        tvuser = headerview.findViewById(R.id.user_name);
       tvfullname = headerview.findViewById(R.id.user_full_name);
       tvrole = headerview.findViewById(R.id.txt_role);
       Intent intent = getIntent();
        String user = intent.getStringExtra("username");
        tvuser.setText("Wellcome " + user + "!");
        user us;
        us = new user();

        dao = new adminDAO(this);
        us = dao.getUser(user);







       tvfullname.setText(us.getTen());
        SharedPreferences preferences = getSharedPreferences("role",MODE_PRIVATE);
        SharedPreferences.Editor editor =preferences.edit();
        editor.putInt("role",us.getRole());
        editor.putString("fullname",us.getTen());
        editor.commit();

        if(us.getRole()==1){
            tvrole.setText("Quản lý");


        }else{
            tvrole.setText("Nhân viên");
        }
        if (us.getRole()==1) {

            navigationView.getMenu().findItem(R.id.add_user).setVisible(true);
        } else {
            navigationView.getMenu().findItem(R.id.add_user).setVisible(false);
        }


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                boolean tt = true;


              if(item.getItemId()==R.id.manager_movie){
                  fragment = new quan_ly_phim();
                  actionBar.setSubtitle("Quản lý phim");
                  replace(fragment);
                  tt =false;

              }else if(item.getItemId()==R.id.manager_category){
                  fragment = new quan_ly_loai_phim();
                  actionBar.setSubtitle("Quản lý loại phim");
                  replace(fragment);
                  tt =false;


              }else if(item.getItemId()==R.id.manager_SC) {
                  fragment = new quan_ly_suat_chieu();
                  actionBar.setSubtitle("Quản lý suất chiếu");
                  replace(fragment);
              }else if(item.getItemId()==R.id.user_profile) {
                  fragment = new thong_tin_tai_khoan();
                  actionBar.setSubtitle("Thông tin tài khoản");
                  replace(fragment);
              } else if(item.getItemId()==R.id.manager_user) {
                  fragment = new Quan_ly_khach_hang();
                  actionBar.setSubtitle("Quản lý khách hàng");
                  replace(fragment);
              }
              else if(item.getItemId()==R.id.add_user) {


                          nxPass();



              }else if(item.getItemId()==R.id.home1){
                  fragment = new man_hinh_chinh();
                  actionBar.setSubtitle("Màn hình chính");
                  replace(fragment);
              }
              else if(item.getItemId()==R.id.manager_bill){
                  fragment = new Quan_ly_hoa_don();
                  actionBar.setSubtitle("Quản lý hóa đơn");
                  replace(fragment);
              }
              else if(item.getItemId()==R.id.manager_PC){
                  fragment = new quan_ly_phong_chieu();
                  actionBar.setSubtitle("Quản lý phòng chiếu");
                  replace(fragment);

                }
              else if(item.getItemId()==R.id.logout){

                  tt =false;
                  android.app.AlertDialog.Builder builder = new AlertDialog.Builder(navigationView.getContext());
                  builder.setIcon(R.drawable.exit);
                  builder.setTitle("Đăng xuất");
                  builder.setMessage("Bạn có muốn đăng xuất không?");
                  builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                      @Override
                      public void onClick(DialogInterface dialog, int which) {
                          SharedPreferences sharedPreferences = getSharedPreferences("thongtin",MODE_PRIVATE);
                          SharedPreferences.Editor editor = sharedPreferences.edit();
                          editor.clear();
                          editor.apply();

                          Intent intent = new Intent(getApplicationContext(), login.class);
                          startActivity(intent);
                          finish();
                      }
                  });
                  builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                      @Override
                      public void onClick(DialogInterface dialog, int which) {
                      }
                  });
                  AlertDialog dialog = builder.create();
                  dialog.show();

              }


                return false;
            }
        });


    }
    public void replace(Fragment fragment){
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.Framelayout,fragment).commit();
        drawerLayout.close();


    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()== android.R.id.home){
            drawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu,menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        int color = getResources().getColor(R.color.white); // Thay thế your_color bằng mã màu mong muốn

        SpannableString spannableString = new SpannableString("Search");
        spannableString.setSpan(new ForegroundColorSpan(color), 0, spannableString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        searchView.setQueryHint(spannableString);
        ImageView closeButton = searchView.findViewById(androidx.appcompat.R.id.search_close_btn);
        closeButton.setColorFilter(color);
        ImageView backButton = searchView.findViewById(androidx.appcompat.R.id.search_mag_icon);
        backButton.setColorFilter(color);


        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);
        sharedViewModel = new ViewModelProvider(this).get(SharedViewModel.class);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                sharedViewModel.getSearchText().setValue(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                sharedViewModel.getSearchText().setValue(newText);
                SpannableString spannableString = new SpannableString(newText);
                spannableString.setSpan(new ForegroundColorSpan(color), 0, spannableString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

                EditText searchEditText = searchView.findViewById(androidx.appcompat.R.id.search_src_text); // Lấy EditText trong SearchView
                searchEditText.setTextColor(color); // Thiết lập màu chữ cho EditText
                searchEditText.setHintTextColor(color); // Thiết lập màu chữ cho gợi ý trống

                searchEditText.setHint(spannableString);
                return true;
            }
        });



        return true;
    }
    public void nxPass(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.xnpass,null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();

        EditText edtxnpass = view.findViewById(R.id.edtpassxn);
        Button btnxz = view.findViewById(R.id.btnxn);
        Button btnkxz = view.findViewById(R.id.btnkxn);
        btnxz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("thongtin",MODE_PRIVATE);
                String pass = sharedPreferences.getString("pass","");
                if(edtxnpass.getText().toString().isEmpty()){
                    Toast.makeText(home.this, "Không để trống", Toast.LENGTH_SHORT).show();
                }else{
                    if(edtxnpass.getText().toString().equals(pass)){
                        fragment = new quan_ly_nhan_vien();


                        ActionBar actionBar  = getSupportActionBar();

                        actionBar.setDisplayHomeAsUpEnabled(true);
                        actionBar.setHomeAsUpIndicator(R.drawable.menu);
                        actionBar.setSubtitle("Quản lý nhân viên");
                        actionBar.setTitle("SpecTraFlix");
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putBoolean("hh",true);
                        editor.apply();



                        replace(fragment);
                        dialog.dismiss();


                    }else{
                        Toast.makeText(home.this, "Sai mật khẩu", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        btnkxz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }
    public int getFragmentContainerId() {
        return R.id.Framelayout;
    }

}