package com.everywhere.trip.base;

import android.app.Application;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;

/**
 * Created by asus on 2019/3/5.
 */

public class BaseApp extends Application {
    private static BaseApp sBaseApp;
    public static int mWidthPixels;
    public static int mHeightPixels;

    @Override
    public void onCreate() {
        super.onCreate();
        sBaseApp = this;
        getScreenWH();

        UMConfigure.init(this,"5ccebd9e570df3676400121c"
                ,"umeng",UMConfigure.DEVICE_TYPE_PHONE,"");//58edcfeb310c93091c000be2 5965ee00734be40b580001a0


        //todo 三方ak没有申请
        ///需要设置各个平台的appkey：
        PlatformConfig.setWeixin("wxdc1e388c3822c80b", "3baf1193c85774b3fd9d18447d76cab0");
        //豆瓣RENREN平台目前只能在服务器端配置
        PlatformConfig.setSinaWeibo("2248884681", "7ed722f6244fb5ce4504b4fe46c394bb",
                "http://sns.whalecloud.com");
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
    }

    //计算屏幕宽高
    private void getScreenWH() {
        WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);
        Display defaultDisplay = manager.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        defaultDisplay.getMetrics(metrics);
        mWidthPixels = metrics.widthPixels;
        mHeightPixels = metrics.heightPixels;
    }

    public static BaseApp getInstance(){
        return sBaseApp;
    }

    public static Resources getRes() {
        return sBaseApp.getResources();
    }
}
