package com.fanikiosoftware.mynews.controllers.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fanikiosoftware.mynews.R;
import com.fanikiosoftware.mynews.controllers.network.Docs;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ArticleViewHolder> {

    List<Docs> docsList;
    private static final String TAG = "SearchAdapter";

    public SearchAdapter(List<Docs> docsList) {
        this.docsList = docsList;
    }

    //called when an instance of SearchAdapter is created
    @Override
    public ArticleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder started");
        //inflate the card view layout and setup each view within each individual card(row)of the rv
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item,
                parent, false);
        //return a new instance of the viewHolder
        return new ArticleViewHolder(view);
    }

    //binds the data with the view when the data is shown in the UI
    @Override
    public void onBindViewHolder(ArticleViewHolder viewHolder, final int position) {
        Log.d(TAG, "onBindViewHolder started");
        Docs docs = docsList.get(position);
        viewHolder.tvSection.setText(docs.getSearchSection());
        //if subsection is not empty string then docs subsection after section >
        if (!docs.getSearchSubsection().equals("")) {
            viewHolder.tvSubsection.setText(" > " + docs.getSearchSubsection());
        } else {
            //remove subsection from view if subsection variable is empty string
            viewHolder.tvSubsection.setVisibility(View.GONE);
        }
        String date = docs.getSearchDate();
        date = date.substring(5, 10) + "-" + date.substring(0, 4);
        viewHolder.tvDate.setText(date);
        viewHolder.tvTitle.setText(docs.getMultimediaList().get(position).getHeadline().getTitle());
        viewHolder.tvTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "clicked on title from position: " + position);
                Context context = v.getContext();
                Intent intent = new Intent(v.getContext(), WebActivity.class);
                intent.putExtra("url", docsList.get(position).getSearchUrl());
                context.startActivity(intent);
            }
        });
        if (!docs.getMultimediaList().isEmpty()) {
            //todo update image url for search api with Constants.BASE_IMAGE_URL
            //if an image exists, get the image url for the Article Search
            Picasso.with(viewHolder.imageView.getContext())
                    .load(docs.getMultimediaList().get(0).getUrl())
                    .resize(40, 40)
                    .into(viewHolder.imageView);
        } else {
            //if no image available use the generic NYT image
            Picasso.with(viewHolder.imageView.getContext())
                    .load(R.drawable.ic_nyt)
                    .resize(40, 40)
                    .into(viewHolder.imageView);
        }
        viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if article image is clicked -> open the webview of the article at SearchUrl
                Log.d(TAG, "clicked on image from position: " + position);
                Context context = v.getContext();
                Intent intent = new Intent(v.getContext(), WebActivity.class);
                intent.putExtra("url", docsList.get(position).getSearchUrl());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return docsList.size();
    }

    public static class ArticleViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        TextView tvSection;
        TextView tvSubsection;
        TextView tvTitle;
        TextView tvDate;
        ImageView imageView;

        public ArticleViewHolder(View v) {
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