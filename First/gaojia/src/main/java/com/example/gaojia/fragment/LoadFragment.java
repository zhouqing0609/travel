package com.example.gaojia.fragment;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.gaojia.R;
import com.example.gaojia.ServiceTest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoadFragment extends Fragment {


    //创建下载的文家夹

    private ProgressBar pro;
    private TextView tv;
    private AsyncTask<Integer, Integer, Integer> asyncTask;
    private static final String TAG = "LoadFragment";

    public LoadFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_load, container, false);
        initView(inflate);
        getActivity().startService(new Intent(getContext(),ServiceTest.class));
        asyncTask.execute();
        return inflate;
    }
    private void initView(View inflate) {
        pro = (ProgressBar) inflate.findViewById(R.id.pro);
        tv = (TextView) inflate.findViewById(R.id.tv);

        //进度条
        asyncTask = new AsyncTask<Integer, Integer, Integer>() {

            @Override
            protected void onPostExecute(Integer result) {
                Log.d("info", "myAsyncTask执行完成");
            }

            @Override
            protected void onProgressUpdate(Integer... values) {
                pro.setProgress(values[0]);
                tv.setText("当前进度：" + values[0].toString() + "%");
            }

            @Override
            protected Integer doInBackground(Integer... integers) {
                for (int i = 0; i < 100; i++) {
                    try {
                        Thread.sleep(250);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //执行该操作系统会调用onProgressUpdate方法
                    publishProgress(i);
                }
                return 100;
            }
        };

    }


}
