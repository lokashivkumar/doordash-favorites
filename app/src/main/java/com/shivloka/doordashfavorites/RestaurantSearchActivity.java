package com.shivloka.doordashfavorites;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shivloka.doordashfavorites.adapters.RestaurantListAdapter;
import com.shivloka.doordashfavorites.model.Restaurant;
import com.shivloka.doordashfavorites.model.User;
import com.shivloka.doordashfavorites.util.FavoritesDbHelper;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RestaurantSearchActivity extends AppCompatActivity {

    @BindView(R.id.restaurants_recycle_view)
    RecyclerView restaurantRecyclerView;

    private static final String TAG = RestaurantSearchActivity.class.getSimpleName();

    private List<Restaurant> restaurants = new ArrayList<>();

    @Override
    protected void onResume() {
        super.onResume();
        getFavorites();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_search);

        ButterKnife.bind(this);

        Toolbar favoritesToolbar = (Toolbar) findViewById(R.id.favorites_toolbar);
        restaurantRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        restaurantRecyclerView.setLayoutManager(layoutManager);

        String tokenString = this.getIntent().getStringExtra("token");

        final OkHttpClient client = new OkHttpClient();
        Request userDataRequest = constructUserDataRequest(tokenString);
        Call call = client.newCall(userDataRequest);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {

                    String responseString = response.body().string();
                    User user = parseUserDetails(responseString);
                    double lat = user.getDefaultAddress().getLat();
                    double lng = user.getDefaultAddress().getLng();
                    Request restaurantListRequest = constructRestaurantListRequest(lat, lng);

                    Call restaurantsCall = client.newCall(restaurantListRequest);
                    RestaurantRequester restaurantRequesterCallback = new RestaurantRequester();
                    restaurantsCall.enqueue(restaurantRequesterCallback);
                } else {
                    Log.i(TAG, String.valueOf(response.code()));
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.favorites_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_favorite:
                Intent favoritesIntent = new Intent(this, FavoritesActivity.class);
                startActivity(favoritesIntent);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    private class RestaurantRequester implements Callback {

        @Override
        public void onFailure(Call call, IOException e) {

        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
            Log.i(TAG, String.valueOf(response.code()));
            if (response.isSuccessful()) {
                String responseString = response.body().string();
                parseRestaurantData(responseString, getFavorites());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        RestaurantListAdapter restaurantAdapter;
                        restaurantAdapter = new RestaurantListAdapter(restaurants);
                        restaurantRecyclerView.setItemAnimator(new DefaultItemAnimator());
                        restaurantRecyclerView.setAdapter(restaurantAdapter);
                        restaurantAdapter.notifyDataSetChanged();
                    }
                });
            }
        }
    }

    private List<Restaurant> getFavorites() {
        FavoritesDbHelper favoritesDbHelper = new FavoritesDbHelper(getApplicationContext());
        return (List<Restaurant>) favoritesDbHelper.getFavorites();
    }

    private Request constructRestaurantListRequest(double lat, double lng) {
        HttpUrl url = new HttpUrl.Builder()
                .scheme("https")
                .host("api.doordash.com")
                .addPathSegment("v2")
                .addPathSegment("restaurant")
                .addPathSegment("")
                .addQueryParameter("lat", String.valueOf(lat))
                .addQueryParameter("lng", String.valueOf(lng))
                .build();

        return new Request.Builder()
                .url(url)
                .build();
    }

    private Request constructUserDataRequest(String tokenString) {
        String authUrl = Constants.DOORDASH_ROOT_URL + Constants.CONSUMER_URL;

        return new Request.Builder()
                .header("Authorization", Constants.JSON_TOKEN_PREFIX + tokenString)
                .url(authUrl)
                .build();
    }

    private User parseUserDetails(String responseString) {
        User user = new User();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            user = objectMapper.readValue(responseString, User.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return user;
    }

    private void parseRestaurantData(String responseString, List<Restaurant> favorites) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(JsonGenerator.Feature.AUTO_CLOSE_JSON_CONTENT, true);
            restaurants = objectMapper.readValue(responseString, objectMapper.getTypeFactory().constructCollectionType(
                    List.class, Restaurant.class));
            for (Restaurant restaurant : favorites) {
                restaurant.setUserFavorite(true);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
