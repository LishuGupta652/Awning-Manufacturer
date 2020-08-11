package com.example.awningmanufacturer;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import static android.content.Intent.ACTION_VIEW;

public class WebsiteAdapter extends RecyclerView.Adapter<WebsiteAdapter.ViewHolder> {

    private List<WebsiteItem> websiteItems;
    private Context context;

    public WebsiteAdapter(List<WebsiteItem> websiteItem, Context context) {
        this.websiteItems = websiteItem;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.website_list_item, viewGroup, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final WebsiteItem websiteItem = websiteItems.get(i);


        viewHolder.tvTitle.setText(websiteItem.getTitle());
        viewHolder.tvUrl.setText(websiteItem.getUrl());

        viewHolder.linearLayoutConainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ACTION_VIEW);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setData(Uri.parse(websiteItem.getUrl()));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return websiteItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView tvTitle, tvUrl;
        public LinearLayout linearLayoutConainer;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvUrl = itemView.findViewById(R.id.tvUrl);
            linearLayoutConainer = itemView.findViewById(R.id.linearLayoutContainer);
        }
    }
}
