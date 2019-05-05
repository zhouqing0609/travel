package com.example.gaojia.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gaojia.ArticleBean;
import com.example.gaojia.IService;
import com.example.gaojia.MyRecyclerAdapter;
import com.example.gaojia.R;

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


    private RecyclerView mRv;
    private ArrayList<ArticleBean.DataBean.DatasBean> list;
    private MyRecyclerAdapter adapter;


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
        mRv = (RecyclerView) inflate.findViewById(R.id.home_rv);
        list = new ArrayList<>();
        adapter = new MyRecyclerAdapter(list,getActivity());
        mRv.setAdapter(adapter);
        mRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        initData();

    }
    private void initData(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(IService.url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        IService iService = retrofit.create(IService.class);
        Observable<ArticleBean> artList = iService.getArtList(294);
        artList.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ArticleBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ArticleBean articleBean) {
                        List<ArticleBean.DataBean.DatasBean> datas = articleBean.getData().getDatas();
                        list.addAll(datas);
                        adapter.setList(list);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("===========", "onError: "+e.getMessage() );
                    }

                    @Override
                    public void onComplete() {
                        Log.e("==============", "onComplete: " );
                    }
                });
    }

}
