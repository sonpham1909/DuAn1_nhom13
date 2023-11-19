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

import android.app.Activity;
import android.app.AlertDialog;
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
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.duan1_nhom13.Adapter.LPAdapter;
import com.example.duan1_nhom13.DAO.adminDAO;
import com.example.duan1_nhom13.Fragment.Frg_qlPhim;
import com.example.duan1_nhom13.Fragment.frg_qllPhim;
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
        actionBar.setSubtitle("Quản lý phim");
        actionBar.setTitle("SpecTraFlix");
        fragment = new Frg_qlPhim();
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


              if(item.getItemId()==R.id.manager_movie){
                  fragment = new Frg_qlPhim();
                  actionBar.setSubtitle("Quản lý phim");
                  replace(fragment);

              }else if(item.getItemId()==R.id.manager_category){
                  fragment = new frg_qllPhim();
                  actionBar.setSubtitle("Quản lý loại phim");
                  replace(fragment);
              }else if(item.getItemId()==R.id.logout){
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



        return false;
    }
}