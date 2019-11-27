package com.example.testnewdialog;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PropertiesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public int size;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PropertiesViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.properties_item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return size;
    }

    class PropertiesViewHolder extends RecyclerView.ViewHolder {

        public PropertiesViewHolder(@NonNull View itemView) {
            super(itemView);
        }

    }
}
