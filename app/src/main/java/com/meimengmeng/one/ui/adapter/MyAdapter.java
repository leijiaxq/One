package com.meimengmeng.one.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.meimengmeng.one.R;
import com.meimengmeng.one.bean.DataBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by leijiaxq
 * Data       2016/12/9 10:55
 * Describe
 */
public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int TYPE1 = 1;
    private Context                   mContext;
    private List<DataBean.UrlsEntity> mDatas;
    private LayoutInflater            inflater;


    public MyAdapter(Context context, List<DataBean.UrlsEntity> datas) {
        this.mContext = context;
        this.mDatas = datas;
        inflater = LayoutInflater.from(context);


    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case TYPE1:
                view = inflater.inflate(R.layout.item_task_type1, parent, false);
                return new ViewHolderType1(view);
            default:
                throw new RuntimeException("there is no type that matches the type " +
                        viewType + " + make sure your using types correctly");
        }
    }

    @Override
    public int getItemCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    @Override
    public int getItemViewType(int position) {

        return TYPE1;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof ViewHolderType1) {
            setDataType1((ViewHolderType1) holder, position);
        }
    }


    //设置type1 的数据
    private void setDataType1(ViewHolderType1 holder, final int position) {

        //        holder.mItemType1Tv.setText(TextUtils.isEmpty(bean.getTimeFlag()) ? "" : bean.getTimeFlag());

        DataBean.UrlsEntity bean = mDatas.get(position);

        holder.mItemTv.setText(bean.id + "");


        holder.mItemTv.setBackgroundColor(Color.parseColor("#"+bean.color));


        holder.mItemTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onItemClick(position);
                }
            }
        });
    }


    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public OnItemClickListener mListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    static class ViewHolderType1 extends RecyclerView.ViewHolder {

        @BindView(R.id.item_tv)
        TextView mItemTv;

        ViewHolderType1(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}
