package com.shivloka.doordashfavorites.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.shivloka.doordashfavorites.R;
import com.shivloka.doordashfavorites.model.Restaurant;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by shiv.loka on 12/25/16.
 */

public class FavoritesListAdapter extends RecyclerView.Adapter<FavoritesListAdapter.FavoritesListViewHolder> {

    private List<Restaurant> favorites;

    public FavoritesListAdapter(List<Restaurant> favorites) {
        this.favorites = favorites;
    }


    class FavoritesListViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.favorite_coverImage)
        ImageView favoritesCoverImage;
        @BindView(R.id.favorite_restaurant_name)
        TextView favoritesName;
        @BindView(R.id.favorite_cuisine_type)
        TextView favoriteCuisineType;
        @BindView(R.id.favorite_deliveryFee)
        TextView favoriteDeliveryFee;
        @BindView(R.id.favorite_deliveryTime)
        TextView favoriteDeliveryTime;
        @BindView(R.id.favorite_card_view)
        CardView favoriteCardView;

        FavoritesListViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @Override
    public FavoritesListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.favorites_list_layout, parent, false);

        return new FavoritesListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(FavoritesListViewHolder holder, int position) {
        final Restaurant restaurant = favorites.get(position);
        holder.favoritesName.setText(restaurant.getName());
        holder.favoriteCuisineType.setText(restaurant.getCuisineType());
        holder.favoriteDeliveryFee.setText(String.valueOf(restaurant.getDeliveryFee()));
        holder.favoriteDeliveryTime.setText(restaurant.getDeliveryTime());
    }

    @Override
    public int getItemCount() {
        return favorites.size();
    }
}
