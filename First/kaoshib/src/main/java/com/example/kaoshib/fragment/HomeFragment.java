package com.example.kaoshib.fragment;


import android.os.Bundle;
import android.renderscript.ScriptC;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kaoshib.API.IService;
import com.example.kaoshib.R;
import com.example.kaoshib.adapter.MyRecyclerViewAdapter;
import com.example.kaoshib.bean.ArticleBean;
import com.example.kaoshib.bean.Bean;
import com.example.kaoshib.bean.ImgBean;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    private RecyclerView home_rv;
    private ArrayList<ArticleBean.DataBean.DatasBean> ArticleList;
    private ArrayList<ImgBean.ResultsBean> banList;
    private MyRecyclerViewAdapter adapter;
    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_home, container, false);
        initView(inflate);

        return inflate;
    }




    private void initView(View inflate) {
        home_rv = (RecyclerView) inflate.findViewById(R.id.home_rv);
        banList = new ArrayList<>();
        ArticleList = new ArrayList<>();
        adapter = new MyRecyclerViewAdapter(banList,ArticleList,getActivity());
        home_rv.setAdapter(adapter);
        home_rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        initDate();
        initBanner();


    }

    private void initDate() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(IService.url)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        IService iService = retrofit.create(IService.class);
        final Observable<ArticleBean> artList = iService.getArtList(294);
         artList.observeOn(AndroidSchedulers.mainThread())
                 .subscribeOn(Schedulers.newThread())
                 .subscribe(new Observer<ArticleBean>() {
                     @Override
                     public void onSubscribe(Disposable d) {

                     }

                     @Override
                     public void onNext(ArticleBean articleBean) {
                         List<ArticleBean.DataBean.DatasBean> datas = articleBean.getData().getDatas();
                         ArticleList.addAll(datas);
                         adapter.setArtList(ArticleList);
                     }

                     @Override
                     public void onError(Throwable e) {

                     }

                     @Override
                     public void onComplete() {

                     }
                 });

    }

    private void initBanner() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(IService.fuli)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        IService iService = retrofit.create(IService.class);
        Observable<ImgBean> imgList = iService.getImgList();
        imgList.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ImgBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ImgBean imgBean) {
                        List<ImgBean.ResultsBean> results = imgBean.getResults();
                        banList.addAll(results);
                        adapter.setBannerList(banList);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("", "onError: "+e.getMessage() );
                    }

                    @Override
                    public void onComplete() {
                        Log.e("HomeFragment", "onComplete: " );
                    }
                });

    }

}
