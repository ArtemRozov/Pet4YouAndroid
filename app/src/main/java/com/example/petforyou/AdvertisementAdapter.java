package com.example.petforyou;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdvertisementAdapter extends RecyclerView.Adapter<AdvertisementAdapter.AdViewHolder> {
    private List<AdvertisementModel> adsList;
    private OnItemClickListener listener;

    public AdvertisementAdapter(List<AdvertisementModel> adsList) {
        this.adsList = adsList;
    }

    @NonNull
    @Override
    public AdViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.advertisement_item, parent, false);
        return new AdViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdViewHolder holder, int position) {
        AdvertisementModel ad = adsList.get(position);
        holder.titleTextView.setText(ad.getTitle());
        holder.subtitle1TextView.setText(ad.getSubtitle1());
        holder.subtitle2TextView.setText(ad.getSubtitle2());
        holder.priceTextView.setText(ad.getPrice());
        holder.imageView.setImageResource(ad.getImageResId());

        holder.itemView.setOnClickListener(view -> {
            if (listener != null) {
                listener.onItemClick(ad);
            }
        });
    }

    public interface OnItemClickListener {
        void onItemClick(AdvertisementModel advertisement);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }


    @Override
    public int getItemCount() {
        return adsList.size();
    }

    public static class AdViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView titleTextView;
        public TextView subtitle1TextView;
        public TextView subtitle2TextView;
        public TextView priceTextView;

        public AdViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            subtitle1TextView = itemView.findViewById(R.id.subtitle1TextView);
            subtitle2TextView = itemView.findViewById(R.id.subtitle2TextView);
            priceTextView = itemView.findViewById(R.id.priceTextView);
        }
    }
}
