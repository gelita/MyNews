package com.fanikiosoftware.mynews.controllers.activities;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fanikiosoftware.mynews.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ArticleViewHolder> {

    private String[] mDataset;

    public MyAdapter(String[] myDataset) {
        mDataset = myDataset;
    }

    //called everytime an instance of MyAdapter is created
    @Override
    public ArticleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflate the card view layout and setup each view within each individual card(row)of the rv
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item,
                parent, false);
        //obtain an instance of the viewHolder
        ArticleViewHolder viewHolder = new ArticleViewHolder(view);
        return viewHolder;
    }

    //binds the data with the view when the data is shown in the UI
    @Override
    public void onBindViewHolder(ArticleViewHolder viewHolder, int position) {
//        viewHolder.mTextView.setText(mDataset[position]);
    }

    @Override
    public int getItemCount() {
        return mDataset.length;
    }

    public static class ArticleViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.header_view) TextView headerView;
        @BindView(R.id.title_view) TextView titleView;
        @BindView(R.id.date_view) TextView dateView;
        @BindView(R.id.image_view) ImageView imageView;

        public ArticleViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }
}