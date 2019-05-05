package com.example.gaojia;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IService {
    public String url = "https://www.wanandroid.com/project/";


    @GET("list/1/json?")
    Observable<ArticleBean> getArtList(@Query("cid") int cid);

}
