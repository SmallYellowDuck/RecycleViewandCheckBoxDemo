package com.bawei.recycleviewandcheckboxdemo;

import android.os.Handler;
import android.os.Message;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * Created by chengqianlang on 2017/5/13.
 */

public class MyOkHttp {
    public static void jiexi(final Handler handler) {
        OkHttpUtils
                .get()
                .url(MyPath.URL)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Message message = new Message();
                        message.obj=response;
                        handler.sendMessage(message);
                    }


                });
    }
}
