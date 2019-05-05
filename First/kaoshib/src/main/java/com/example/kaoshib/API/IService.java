package com.example.kaoshib.API;

import com.example.kaoshib.bean.ArticleBean;
import com.example.kaoshib.bean.Bean;
import com.example.kaoshib.bean.ImgBean;
import com.example.kaoshib.bean.TabBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IService {
    public String url = "https://www.wanandroid.com/project/";
    public String fuli = "https://gank.io/api/data/";

    @GET("tree/json")
    Observable<TabBean> getTabList();

    @GET("list/1/json?")
    Observable<ArticleBean> getArtList(@Query("cid")int cid);

    @GET("福利/10/1")
    Observable<ImgBean> getImgList();


}
