package com.everywhere.trip.ui.main.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.everywhere.trip.R;
import com.everywhere.trip.base.BaseActivity;
import com.everywhere.trip.presenter.EmptyPresenter;
import com.everywhere.trip.view.main.EmptyView;
import com.just.agentweb.AgentWeb;

import butterknife.BindView;

public class WebViewActivity extends BaseActivity<EmptyView, EmptyPresenter> implements EmptyView {


    @BindView(R.id.container)
    LinearLayout mContainer;
    @BindView(R.id.toolBar)
    Toolbar mToolBar;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    private AgentWeb mAgentWeb;

    public static void startAct(Context context) {
        Intent intent = new Intent(context, WebViewActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected EmptyPresenter initPresenter() {
        return new EmptyPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_web_view;
    }

    @Override
    protected void initView() {
        mToolBar.setTitle("");
        mToolBar.setNavigationIcon(R.drawable.back_white);
        setSupportActionBar(mToolBar);
        mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mAgentWeb.back()){
                    finish();
                }
            }
        });

        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent(mContainer, new LinearLayout.LayoutParams(-1, -1))
                .useDefaultIndicator()
                .createAgentWeb()
                .ready()
                .go("https://api.banmi.com/app2017/agreement.html");



        mAgentWeb.getWebCreator().getWebView().setWebChromeClient(new WebChromeClient(){
            @Override
            public void onReceivedTitle(WebView view, String title) {
                if (!TextUtils.isEmpty(title)){
                    mTvTitle.setText(title);
                }
                super.onReceivedTitle(view, title);
            }
        });
    }

    @Override
    protected void onPause() {
        mAgentWeb.getWebLifeCycle().onPause();
        super.onPause();

    }

    @Override
    protected void onResume() {
        mAgentWeb.getWebLifeCycle().onResume();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        mAgentWeb.getWebLifeCycle().onDestroy();
        super.onDestroy();
    }

    @Override
    public void toastShort(String msg) {

    }
}
