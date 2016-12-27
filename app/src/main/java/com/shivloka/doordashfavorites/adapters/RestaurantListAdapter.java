package com.shivloka.doordashfavorites.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.shivloka.doordashfavorites.R;
import com.shivloka.doordashfavorites.model.Restaurant;
import com.shivloka.doordashfavorites.util.FavoritesDbHelper;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by shiv.loka on 12/23/16.
 */

public class RestaurantListAdapter extends RecyclerView.Adapter<RestaurantListAdapter.RestaurantsViewHolder> {

    public static final String TAG = RestaurantListAdapter.class.getSimpleName();

    //TODO: Get CoverImage from url
    private List<Restaurant> restaurants;
    private List<Restaurant> favorites;
    private Context restaurantsContext;

    class RestaurantsViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.coverImage)
        ImageView coverImage;
        @BindView(R.id.restaurantName)
        TextView restaurantName;
        @BindView(R.id.cuisineType)
        TextView cuisineType;
        @BindView(R.id.deliveryFee)
        TextView deliveryFee;
        @BindView(R.id.deliveryTime)
        TextView deliveryTime;
        @BindView(R.id.favoriteButton)
        ImageButton favoriteButton;
        @BindView(R.id.card_view)
        CardView cardView;

        RestaurantsViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, itemView);
            restaurantsContext = view.getContext();
        }
    }

    public RestaurantListAdapter(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }

    @Override
    public RestaurantsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.restaurant_list_layout, parent, false);
        return new RestaurantsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final RestaurantsViewHolder holder, int position) {
        final Restaurant restaurant = restaurants.get(position);
        holder.restaurantName.setText(restaurant.getName());
        holder.cuisineType.setText(restaurant.getCuisineType());
        holder.deliveryFee.setText("$"+String.valueOf(restaurant.getDeliveryFee()));
        holder.deliveryTime.setText(restaurant.getDeliveryTime());

        Picasso.with(restaurantsContext).load(restaurant.getCoverImageUrl()).into(holder.coverImage);

        if (restaurant.isUserFavorite()) {
            holder.favoriteButton.setBackgroundResource(R.drawable.ic_favorite_selected);
        } else {
            holder.favoriteButton.setBackgroundResource(R.drawable.ic_favorite_unselected);
        }

        holder.favoriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (restaurant.isUserFavorite()) {
                    holder.favoriteButton.setBackgroundResource(R.drawable.ic_favorite_unselected);
                    removeFromFavoritesList(restaurant);
                } else {
                    holder.favoriteButton.setBackgroundResource(R.drawable.ic_favorite_selected);
                    addToFavoritesList(restaurant);
                }
            }
        });
    }


    private void removeFromFavoritesList(Restaurant restaurant) {
        FavoritesDbHelper favoritesDbHelper = new FavoritesDbHelper(restaurantsContext);
        restaurant.setUserFavorite(false);
        favoritesDbHelper.removeFavorite(restaurant);
    }

    private void addToFavoritesList(Restaurant restaurant) {
        FavoritesDbHelper favoritesDbHelper = new FavoritesDbHelper(restaurantsContext);
        restaurant.setUserFavorite(true);
        favoritesDbHelper.addFavorite(restaurant);
    }

    @Override
    public int getItemCount() {
        return restaurants.size();
    }

    private void filter(ArrayList<Restaurant> filteredList) {
        restaurants.addAll(filteredList);
        notifyDataSetChanged();
    }
}
