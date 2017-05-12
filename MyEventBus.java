package com.bawei.recycleviewandcheckboxdemo;

/**
 * Created by chengqianlang on 2017/5/12.
 */

public class MyEventBus {
    private String mMsg;

    public MyEventBus(String msg) {
        mMsg = msg;
    }

    public String getMsg(){
        return mMsg;
    }
}
