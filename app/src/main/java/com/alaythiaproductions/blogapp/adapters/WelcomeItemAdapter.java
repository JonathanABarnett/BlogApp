package com.alaythiaproductions.blogapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alaythiaproductions.blogapp.R;
import com.alaythiaproductions.blogapp.models.WelcomeItem;

import java.util.List;

public class WelcomeItemAdapter extends RecyclerView.Adapter<WelcomeItemAdapter.WelcomeViewHolder> {

    private List<WelcomeItem> welcomeItems;

    public WelcomeItemAdapter(List<WelcomeItem> welcomeItems) {
        this.welcomeItems = welcomeItems;
    }

    @NonNull
    @Override
    public WelcomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new WelcomeViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.item_container_welcome, parent, false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull WelcomeViewHolder holder, int position) {
        holder.setWelcomeData(welcomeItems.get(position));
    }

    @Override
    public int getItemCount() {
        return welcomeItems.size();
    }

    class WelcomeViewHolder extends RecyclerView.ViewHolder {

        private TextView titleTextView, descriptionTextView;
        private ImageView welcomeImageView;

        public WelcomeViewHolder(@NonNull View itemView) {
            super(itemView);

            titleTextView = itemView.findViewById(R.id.welcome_title_textview);
            descriptionTextView = itemView.findViewById(R.id.welcome_description_textview);
            welcomeImageView = itemView.findViewById(R.id.welcome_imageview);
        }

        void setWelcomeData(WelcomeItem welcomeItem) {
            titleTextView.setText(welcomeItem.getTitle());
            descriptionTextView.setText(welcomeItem.getDescription());
            welcomeImageView.setImageResource(welcomeItem.getImage());
        }
    }
}
