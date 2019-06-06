package com.fanikiosoftware.mynews.controllers.activities;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fanikiosoftware.mynews.R;
import com.fanikiosoftware.mynews.controllers.network.Post;
import com.fanikiosoftware.mynews.controllers.network.PostResponse;
import com.squareup.picasso.Picasso;

import java.util.List;

import static android.app.PendingIntent.getActivity;

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
        String date = postList.get(position).getDate();
        date = date.substring(5,10) + "-" + date.substring(0,4);
        viewHolder.tvDate.setText(date);
        viewHolder.tvTitle.setText(postList.get(position).getTitle());
        Picasso.get()
                .load("https://static01.nyt.com/images/2019/06/04/us/politics/04dc-senate1/04dc-senate1-thumbStandard.jpg")
                .resize(40, 40)
                .into(viewHolder.imageView);
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public static class ArticleViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        TextView tvSection;
        TextView tvTitle;
        TextView tvDate;
        ImageView imageView;

        public ArticleViewHolder(View v) {
            super(v);
            cardView = itemView.findViewById(R.id.card_view);
            tvTitle = itemView.findViewById(R.id.title_view);
            tvSection = itemView.findViewById(R.id.section_view);
            tvDate = itemView.findViewById(R.id.date_view);
            imageView = itemView.findViewById(R.id.image_view);
        }
    }
}