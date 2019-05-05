package com.example.kaoshib.moudle;

import android.util.Log;

import com.example.kaoshib.API.IService;
import com.example.kaoshib.bean.TabBean;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Moudleimp implements IMoudle {
    @Override
    public void getMoudleList(final CallBack callBack) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(IService.url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        IService iService = retrofit.create(IService.class);
        Observable<TabBean> tabList = iService.getTabList();
        tabList.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Observer<TabBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(TabBean tabBean) {
                        callBack.getList(tabBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.getFailed(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.e("=========", "onComplete: ");
                    }
                });
    }
}
