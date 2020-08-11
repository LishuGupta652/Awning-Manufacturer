package com.example.awningmanufacturer;


import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class OfferAdapter extends RecyclerView.Adapter<OfferAdapter.ViewHolder> {

    private List<OfferItem> offerItems;
    private Context context;

    public OfferAdapter(List<OfferItem> offerItems, Context context) {
        this.offerItems = offerItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.offers_list_item, viewGroup, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        OfferItem offerItem = offerItems.get(i);

        viewHolder.tvOfferHeading.setText(offerItem.getOfferName());
        viewHolder.tvOfferContent.setText(offerItem.getValid());
        viewHolder.linearLayoutContainer.setBackgroundColor(Color.parseColor(offerItem.getBackground()));
    }

    @Override
    public int getItemCount() {
        return offerItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView tvOfferHeading, tvOfferContent;
        public LinearLayout linearLayoutContainer;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvOfferHeading = itemView.findViewById(R.id.tvOfferHeading);
            tvOfferContent = itemView.findViewById(R.id.tvOfferContent);
            linearLayoutContainer = itemView.findViewById(R.id.linearLayoutContainer);
        }
    }
}
