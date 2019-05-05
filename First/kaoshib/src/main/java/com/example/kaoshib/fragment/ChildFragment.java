package com.example.kaoshib.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kaoshib.API.IService;
import com.example.kaoshib.R;
import com.example.kaoshib.adapter.MyRecyclerViewAdapter;
import com.example.kaoshib.bean.ArticleBean;
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
public class ChildFragment extends Fragment {
    private ArrayList<ArticleBean.DataBean.DatasBean> ArticleList;
    private MyRecyclerViewAdapter adapter;
    private RecyclerView child_rv;
    private ArrayList<ImgBean.ResultsBean> banList;

    public ChildFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_child, container, false);
        initView(inflate);
        return inflate;
    }

    private void initView(View inflate) {
        child_rv = (RecyclerView) inflate.findViewById(R.id.child_rv);
        ArticleList = new ArrayList<>();
        banList = new ArrayList<>();
        adapter = new MyRecyclerViewAdapter(banList,ArticleList,getActivity());
        child_rv.setAdapter(adapter);
       child_rv.setLayoutManager(new GridLayoutManager(getActivity(),2));
        initDate();

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


}
