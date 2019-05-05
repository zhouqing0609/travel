package com.example.kaoshib;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kaoshib.adapter.MyViewPagerAdapter;
import com.example.kaoshib.bean.TabBean;
import com.example.kaoshib.fragment.ChildFragment;
import com.example.kaoshib.fragment.HomeFragment;
import com.example.kaoshib.presenter.Presenterimp;
import com.example.kaoshib.view.IView;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements IView {

    private EditText mSearch;
    private Toolbar mToobar;
    private TabLayout tab;
    private ViewPager mVp;
    private MyViewPagerAdapter adapter;
    private ArrayList<Fragment> list;
    private ArrayList<String> title;
    private Presenterimp presenterimp;
    private String tag;
    private EditText et_search;
    private Toolbar toobar;
    private ViewPager vp;
    private DrawerLayout dl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
        initMvp();
    }

    private void initMvp() {
        presenterimp = new Presenterimp(this);
        presenterimp.getPresenterList();
    }

    private void initView() {
        mSearch = (EditText) findViewById(R.id.et_search);
        mToobar = (Toolbar) findViewById(R.id.toobar);
        tab = (TabLayout) findViewById(R.id.tab);
        mVp = (ViewPager) findViewById(R.id.vp);

//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, dl, toobar, R.string.app_name, R.string.app_name);
//        dl.addDrawerListener(toggle);
//        toggle.syncState();

        list = new ArrayList<>();
        title = new ArrayList<>();

        et_search = (EditText) findViewById(R.id.et_search);

        toobar = (Toolbar) findViewById(R.id.toobar);

        vp = (ViewPager) findViewById(R.id.vp);

        dl = (DrawerLayout) findViewById(R.id.dl);

    }


    @Override
    public void getViewList(TabBean tabBean) {
        List<TabBean.DataBean> data = tabBean.getData();
        Bundle bundle = new Bundle();
        for (TabBean.DataBean datum : data) {
            String name = datum.getName();
            title.add(name);
            int id = datum.getId();
        }


        list.add(new HomeFragment());
        for (int i = 0; i < title.size() - 1; i++) {
            ChildFragment childFragment = new ChildFragment();
            list.add(childFragment);
        }

        adapter = new MyViewPagerAdapter(getSupportFragmentManager(), list, title);
        mVp.setAdapter(adapter);
        tab.setupWithViewPager(mVp);

        Log.e("HomeActivity", "getViewList: " + list.size() + "==========" + title.size());
    }

    @Override
    public void getViewFailed(String info) {
        Log.e("===============", "getViewFailed: +i" + info);
    }


}
