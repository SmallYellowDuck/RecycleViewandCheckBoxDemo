package com.bawei.recycleviewandcheckboxdemo;

import com.google.gson.Gson;

/**
 * Created by chengqianlang on 2017/5/13.
 */

public class MyGson {

            private String json;
            private Class jsonclass;

        public MyGson(String json, Class jsonclass) {
            this.json = json;
            this.jsonclass = jsonclass;
        }

        public Object jsonToGson(){
                Gson gson = new Gson();
                return  gson.fromJson(json, jsonclass);
            }


}
