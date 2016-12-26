package com.shivloka.doordashfavorites;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.shivloka.doordashfavorites.adapters.FavoritesListAdapter;
import com.shivloka.doordashfavorites.adapters.RestaurantListAdapter;
import com.shivloka.doordashfavorites.model.Restaurant;
import com.shivloka.doordashfavorites.util.FavoritesDbHelper;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavoritesActivity extends AppCompatActivity {

    private static final String TAG = FavoritesActivity.class.getSimpleName();
    @BindView(R.id.favorites_recycler_view)
    RecyclerView favoritesRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        ButterKnife.bind(this);

        List<Restaurant> favorites = showFavorites();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        favoritesRecyclerView.setHasFixedSize(true);
        favoritesRecyclerView.setLayoutManager(layoutManager);

        FavoritesListAdapter favoritesAdapter = new FavoritesListAdapter(favorites);
        favoritesRecyclerView.setItemAnimator(new DefaultItemAnimator());
        favoritesRecyclerView.setAdapter(favoritesAdapter);

        if (favoritesAdapter.getItemCount() == 0) {
            Toast.makeText(this, "You do not have any favorites", Toast.LENGTH_LONG).show();
        }
        favoritesAdapter.notifyDataSetChanged();

    }

    private List<Restaurant> showFavorites() {
        FavoritesDbHelper favoritesDbHelper = new FavoritesDbHelper(this);
        List<Restaurant> favorites = favoritesDbHelper.getFavorites();
        return favorites;
    }
}
