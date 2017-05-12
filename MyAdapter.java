package com.bawei.recycleviewandcheckboxdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.List;

/**
 * Created by chengqianlang on 2017/5/12.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<MyBean>list;
    private Context context;
    private OnItemClickLitener mOnItemClickLitener;
    private boolean flag;

    public MyAdapter(List<MyBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.itemview, null);
         final ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        if(flag){
            holder.cb.setVisibility(View.VISIBLE);
        }else{
            holder.cb.setVisibility(View.INVISIBLE);
        }
        holder.cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    list.get(position).checkbox=true;
                }else{
                    list.get(position).checkbox=false;
                }
            }
        });

        if (mOnItemClickLitener != null)
        {
            holder.myview.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
//得到点击条目的下标
                    int pos = holder.getLayoutPosition();
//把点击的view与下标传给接口
                    mOnItemClickLitener.onItemClick(holder.myview, pos);
                }
            });

            holder.myview.setOnLongClickListener(new View.OnLongClickListener()
            {
                @Override
                public boolean onLongClick(View v)
                {
                    int pos = holder.getLayoutPosition();
//把长按的view与下标传给接口内长按方法
                    mOnItemClickLitener.onItemLongClick(holder.myview, pos);
//记得事件传递机制，返回true不传递给点击事件
                    return true;
                }
            });
        }


        holder.tv.setText(list.get(position).name);
        holder.cb.setChecked(list.get(position).checkbox);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv;
        CheckBox cb;
        View myview;
        public ViewHolder(View itemView) {
            super(itemView);
            tv= (TextView) itemView.findViewById(R.id.tv);
            cb= (CheckBox) itemView.findViewById(R.id.cb);
            myview=itemView;
        }
    }
    public interface OnItemClickLitener
    {
        void onItemClick(View view, int position);
        void onItemLongClick(View view , int position);
    }

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener)
    {
//把参数的mOnItemClickLitener返回给外部定义的接口的实例的mOnItemClickLitener
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    public void setflag(){
        flag=!flag;
    }

}
