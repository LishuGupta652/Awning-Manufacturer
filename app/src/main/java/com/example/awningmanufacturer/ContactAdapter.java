package com.example.awningmanufacturer;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/*
Developed by lishu gupta
web: https://www.pakkabaniya.ml
 */

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {

    private List<ContactItem> contactItems ;
    private Context context;

    public ContactAdapter(List<ContactItem> contactItems, Context context) {
        this.contactItems = contactItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.contact_list_item, viewGroup, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        ContactItem contactItem = contactItems.get(i);

        viewHolder.tvAddress.setText(contactItem.getAddress());
        viewHolder.tvEmail.setText(contactItem.getEmail());
        viewHolder.tvMobile.setText(contactItem.getMobile());
    }

    @Override
    public int getItemCount() {
        return contactItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView tvAddress, tvEmail, tvMobile;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvAddress = itemView.findViewById(R.id.tvAddress);
            tvEmail = itemView.findViewById(R.id.tvEmail);
            tvMobile = itemView.findViewById(R.id.tvMobile);
        }
    }
}
