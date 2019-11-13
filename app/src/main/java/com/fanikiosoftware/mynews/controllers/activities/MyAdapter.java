package com.fanikiosoftware.mynews.controllers.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fanikiosoftware.mynews.R;
import com.fanikiosoftware.mynews.controllers.network.Post;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ArticleViewHolder> {

    private final List<Post> postList;
    public MyAdapter(List<Post> postList) {
        this.postList = postList;
    }

    //called every time an instance of MyAdapter is created
    @NotNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        //inflate the card view layout and setup each view within each individual card(row)of the rv
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item,
                parent, false);
        //return a new instance of the viewHolder
        return new ArticleViewHolder(view);
    }

    //binds the data with the view when the data is shown in the UI
    @Override
    public void onBindViewHolder(@NotNull ArticleViewHolder viewHolder, final int position) {
        Post post = postList.get(position);
        viewHolder.tvSection.setText(post.getSection());
        //if subsection is not empty string then post subsection after section >
        if (!post.getSubsection().equals("")) {
            StringBuilder stringBuilder = new StringBuilder();
            viewHolder.tvSubsection.setText(stringBuilder.append(" > ").append(post.getSubsection()).toString());
        } else {
            //remove subsection from view if subsection variable is empty string
            viewHolder.tvSubsection.setVisibility(View.GONE);
        }
        String date = post.getDate();
        date = date.substring(5, 10) + "-" + date.substring(0, 4);
        viewHolder.tvDate.setText(date);
        viewHolder.tvTitle.setText(post.getTitle());
        viewHolder.tvTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(v.getContext(), WebActivity.class);
                intent.putExtra("url", postList.get(position).getUrl());
                context.startActivity(intent);
            }
        });
        if (!post.getMultimediaList().isEmpty()) {
            //if an image exists, get the image url for the Article Search
            Picasso.with(viewHolder.imageView.getContext())
                    .load(post.getMultimediaList().get(0).getUrl())
                    .resize(60, 60)
                    .into(viewHolder.imageView);
        } else {
            //if no image available use the generic NYT image
            Picasso.with(viewHolder.imageView.getContext())
                    .load(R.drawable.ic_nyt)
                    .resize(60, 60)
                    .into(viewHolder.imageView);
        }
        viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if article image is clicked -> open the web view of the article at SearchUrl
                Context context = v.getContext();
                Intent intent = new Intent(v.getContext(), WebActivity.class);
                intent.putExtra("url", postList.get(position).getUrl());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public static class ArticleViewHolder extends RecyclerView.ViewHolder {

        final CardView cardView;
        final TextView tvSection;
        final TextView tvSubsection;
        final TextView tvTitle;
        final TextView tvDate;
        final ImageView imageView;

        ArticleViewHolder(View v) {
            super(v);
            cardView = itemView.findViewById(R.id.card_view);
            tvTitle = itemView.findViewById(R.id.title_view);
            tvSection = itemView.findViewById(R.id.section_view);
            tvSubsection = itemView.findViewById(R.id.subsection_view);
            tvDate = itemView.findViewById(R.id.date_view);
            imageView = itemView.findViewById(R.id.image_view);
        }
    }
}