package com.example.kaoshib.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.kaoshib.R;
import com.example.kaoshib.bean.ArticleBean;
import com.example.kaoshib.bean.ImgBean;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;

public class MyRecyclerViewAdapter  extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<ImgBean.ResultsBean> bannerList;
    private ArrayList<ArticleBean.DataBean.DatasBean> artList;
    private Context context;
    private int newPositon;




    public MyRecyclerViewAdapter(ArrayList<ImgBean.ResultsBean> bannerList, ArrayList<ArticleBean.DataBean.DatasBean> artList, Context context) {
        this.bannerList = bannerList;
        this.artList = artList;
        this.context = context;
    }

    public void setBannerList(ArrayList<ImgBean.ResultsBean> bannerList) {
        this.bannerList = bannerList;
        notifyDataSetChanged();
    }

    public void setArtList(ArrayList<ArticleBean.DataBean.DatasBean> artList) {
        this.artList = artList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
         if (bannerList.size()>0) {
             if (viewType == 0) {
                 View inflate = View.inflate(context, R.layout.layout_banner, null);
                 BannerViewHolder bannerViewHolder = new BannerViewHolder(inflate);
                 return bannerViewHolder;
             } else {
                 View inflate = View.inflate(context, R.layout.layout_item, null);
                 ArticleViewHolder articleViewHolder = new ArticleViewHolder(inflate);
                 return articleViewHolder;
             }
         }else {
             View inflate = View.inflate(context, R.layout.layout_item, null);
             ArticleViewHolder articleViewHolder = new ArticleViewHolder(inflate);
             return articleViewHolder;
         }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int itemViewType = getItemViewType(position);
        if (bannerList.size()>0){
            if (itemViewType==0) {
               BannerViewHolder bannerViewHolder = (BannerViewHolder) holder;
               bannerViewHolder.banner.setImages(bannerList);
               bannerViewHolder.banner.setImageLoader(new ImageLoader() {
                   @Override
                   public void displayImage(Context context, Object path, ImageView imageView) {
                       ImgBean.ResultsBean bean = (ImgBean.ResultsBean) path;
                       Glide.with(context).load(bean.getUrl()).into(imageView);
                   }
               }).start();

            }else {
                ArticleViewHolder articleViewHolder = (ArticleViewHolder) holder;
                newPositon = position;
                if (bannerList.size()>0){
                    newPositon = position-1;
                }
                articleViewHolder.tv.setText(artList.get(newPositon).getTitle());
                Glide.with(context).load(artList.get(newPositon).getEnvelopePic()).into(((ArticleViewHolder) holder).iv);
            }
        }else {
            ArticleViewHolder articleViewHolder = (ArticleViewHolder) holder;
            articleViewHolder.tv.setText(artList.get(position).getTitle());
            Glide.with(context).load(artList.get(position).getEnvelopePic()).into(((ArticleViewHolder) holder).iv);
        }
    }

    @Override
    public int getItemViewType(int position) {

        if (bannerList.size()>0){
            if (position==0){
                return 0;
            }else {
                return 1;
            }
        }else {
            return 1;
        }
    }

    @Override
    public int getItemCount() {
         if (bannerList.size()>0){
             return artList.size()+1;
         }else {
             return artList.size();
         }

    }

    class BannerViewHolder extends RecyclerView.ViewHolder{
        private Banner banner;
        public BannerViewHolder(View itemView) {
            super(itemView);
            banner = itemView.findViewById(R.id.ban);
        }
    }

    class ArticleViewHolder extends RecyclerView.ViewHolder{
        private ImageView iv;
        private TextView tv;
        public ArticleViewHolder(View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv);
            tv = itemView.findViewById(R.id.tv);
        }
    }
}
