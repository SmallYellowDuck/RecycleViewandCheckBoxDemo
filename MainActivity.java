package com.bawei.recycleviewandcheckboxdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button butall;
    private Button butnull;
    private Button butok;
    private RecyclerView recycler;
    private ArrayList<MyBean> mList;
    private MyAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initdata();
        initRecyclerView();

    }

    private void initRecyclerView() {
        mAdapter = new MyAdapter(mList, MainActivity.this);
        recycler.setAdapter(mAdapter);
        recycler.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        recycler.setItemAnimator(new DefaultItemAnimator());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(linearLayoutManager);
        mAdapter.setOnItemClickLitener(new MyAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {

            }

            @Override
            public void onItemLongClick(View view, int position) {
                mAdapter.setflag();
                mAdapter.notifyDataSetChanged();
            }
        });
    }

    private void initdata() {
        mList = new ArrayList<>();
        for (int i = 1; i <51 ; i++) {
            MyBean myBean = new MyBean();
            myBean.name="这是第"+i+"条";
            myBean.checkbox=false;
            mList.add(myBean);
        }
    }

    private void initView() {
        butall = (Button) findViewById(R.id.butall);
        butnull = (Button) findViewById(R.id.butnull);
        butok = (Button) findViewById(R.id.butok);
        recycler = (RecyclerView) findViewById(R.id.recycler);
        butall.setOnClickListener(this);
        butnull.setOnClickListener(this);
        butok.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.butall:
                for (int i = 0; i < mList.size(); i++) {
                    mList.get(i).checkbox=true;
                    mAdapter.notifyItemChanged(i);
                }
                break;
            case R.id.butnull:
                for (int i = 0; i < mList.size(); i++) {
                    mList.get(i).checkbox=false;
                    mAdapter.notifyItemChanged(i);
                }

                break;
            case R.id.butok:
                EventBus.getDefault().post(new MyEventBus("aaa"));
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intent);
                break;
        }
    }
}
