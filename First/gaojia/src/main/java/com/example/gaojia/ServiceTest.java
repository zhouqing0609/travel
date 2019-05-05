package com.example.gaojia;

import android.app.Service;
import android.content.Intent;
import android.os.Environment;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ServiceTest extends Service {

    private String apkUrl = "http://cdn.banmi.com/banmiapp/apk/banmi_330.apk";
    private String targetFilePath = Environment.getExternalStorageDirectory() + File.separator;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        okhttpDownLoadFile();
        return super.onStartCommand(intent, flags, startId);
    }

    private void okhttpDownLoadFile() {
        String filename = apkUrl.substring(apkUrl.lastIndexOf("/") + 1);
        final File file = new File(targetFilePath + filename);
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(apkUrl)
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("=============", "onFailure: e="+e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                InputStream inputStream = response.body().byteStream();
                int length = (int) response.body().contentLength();
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                byte[] bytes = new byte[8*1024];
                int readlength =0;
                int currloadSize =0;
                while ((readlength=inputStream.read(bytes))!=-1){
                    fileOutputStream.write(readlength);
                    currloadSize += readlength;
                    Log.d("============", "run: readlength=" + ((100 * currloadSize) / length));
                }
                Log.d("=========", "run: 下载完成");
                inputStream.close();
                fileOutputStream.close();
            }
        });
    }
}
