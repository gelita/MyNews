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
import com.fanikiosoftware.mynews.controllers.network.Docs;
import com.fanikiosoftware.mynews.controllers.utility.Constants;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ArticleViewHolder> {

    private final List<Docs> docsList;

    public SearchAdapter(List<Docs> docsList) {
        this.docsList = docsList;
    }

    //called when an instance of SearchAdapter is created
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
        Docs docs = docsList.get(position);
        if (docs.getSearchSection() != null && !docs.getSearchSection().equals("None")) {
            viewHolder.tvSection.setText(docs.getSearchSection());
            //if subsection is available then list subsection after section >
            if (docs.getSearchSubsection() != null && !docs.getSearchSubsection().equals("")) {
                StringBuilder stringBuilder = new StringBuilder();
                viewHolder.tvSubsection.setText(stringBuilder.append(" > ").append(docs.getSearchSubsection()).toString());
            } else {
                //remove subsection from view if subsection variable is empty string
                viewHolder.tvSubsection.setVisibility(View.GONE);
            }
        } else {
            //if the Search Section  name is not available remove it and check for subsection only
            viewHolder.tvSection.setText(" ");
            viewHolder.tvSubsection.setText(" ");
        }

        String date = docs.getSearchDate();
        date = date.substring(5, 10) + "-" + date.substring(0, 4);
        viewHolder.tvDate.setText(date);
        String title = "";
        if (docs.getHeadlineResponse().

                getTitle() != null &&
                !docs.getHeadlineResponse().

                        getTitle().

                        equals("")) {
            title = docs.getHeadlineResponse().getTitle();
        }
        if (title != null && !title.equals("")) {
            viewHolder.tvTitle.setText(title);
        } else {
            viewHolder.tvTitle.setText("");
        }
        viewHolder.tvTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(v.getContext(), WebActivity.class);
                if (docsList.get(position).getSearchUrl() != null) {
                    intent.putExtra("url", docsList.get(position).getSearchUrl());
                }
                context.startActivity(intent);
            }
        });

        if (docs.getMultimediaList() != null && !docs.getMultimediaList().

                isEmpty()) {
            //if an image exists, get the image url for the Article Search
            String url = Constants.BASE_IMAGE_URL + docs.getMultimediaList().get(0).getUrl();
            Picasso.with(viewHolder.imageView.getContext())
                    .load(url)
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