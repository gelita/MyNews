package com.fanikiosoftware.mynews.controllers.activities;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fanikiosoftware.mynews.R;
import com.fanikiosoftware.mynews.controllers.network.Post;
import com.fanikiosoftware.mynews.controllers.network.PostResponse;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ArticleViewHolder> {

   List<Post> postList;

    public MyAdapter(List<Post> postList) {
        this.postList = postList;
    }

    //called everytime an instance of MyAdapter is created
    @Override
    public ArticleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflate the card view layout and setup each view within each individual card(row)of the rv
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item,
                parent, false);
        //return a new instance of the viewHolder
        return new ArticleViewHolder(view);
    }

    //binds the data with the view when the data is shown in the UI
    @Override
    public void onBindViewHolder(ArticleViewHolder viewHolder, int position) {
        viewHolder.tvSection.setText(postList.get(position).getSection());
        viewHolder.tvDate.setText(postList.get(position).getDate());
        viewHolder.tvTitle.setText(postList.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

//    public void updatePostList(List<Post> postList) {
//        this.postList.clear();
//        this.postList.addAll(postList);
//        //notify the adapter that the postList has been updated
//        notifyDataSetChanged();
//    }

    public static class ArticleViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        TextView tvSection;
        TextView tvTitle;
        TextView tvDate;

        public ArticleViewHolder(View v) {
            super(v);
            cardView = (CardView) itemView.findViewById(R.id.card_view);
            tvTitle = (TextView) itemView.findViewById(R.id.title_view);
            tvSection = (TextView) itemView.findViewById(R.id.section_view);
            tvDate = (TextView) itemView.findViewById(R.id.date_view);
        }
    }
}