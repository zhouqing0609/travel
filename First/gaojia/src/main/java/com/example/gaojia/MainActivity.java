package com.example.gaojia;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.gaojia.fragment.HomeFragment;
import com.example.gaojia.fragment.LoadFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ViewPager vp;
    private TabLayout tab;
    private MyViewPagerAdapter adapter;
    private ArrayList<Fragment> list;
    private ArrayList<String> title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        if (Build.VERSION.SDK_INT >= 23) {
            int REQUEST_CODE_CONTACT = 101;
            String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
            //验证是否许可权限
            for (String str : permissions) {
                if (this.checkSelfPermission(str) != PackageManager.PERMISSION_GRANTED) {
                    //申请权限
                    this.requestPermissions(permissions, REQUEST_CODE_CONTACT);
                    return;
                }
            }
        }
    }

    private void initView() {
        vp = (ViewPager) findViewById(R.id.vp);
        tab = (TabLayout) findViewById(R.id.tab);
        list = new ArrayList<>();
        title = new ArrayList<>();

        list.add(new HomeFragment());
        list.add(new LoadFragment());

        title.add("首页");
        title.add("下载");

        adapter = new MyViewPagerAdapter(getSupportFragmentManager(),list,title);
        vp.setAdapter(adapter);
        tab.setupWithViewPager(vp);


        tab.getTabAt(0).setCustomView(R.layout.layout_tab);
        tab.getTabAt(1).setCustomView(R.layout.layout_tab2);
    }
}
