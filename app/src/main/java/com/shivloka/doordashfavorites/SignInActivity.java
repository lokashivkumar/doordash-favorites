package com.shivloka.doordashfavorites;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SignInActivity extends AppCompatActivity {

    @BindView(R.id.email)
    EditText email;

    @BindView(R.id.password)
    EditText passwordText;

    @BindView(R.id.signInButton)
    Button signInButton;

    private static final String TAG = SignInActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        ButterKnife.bind(this);

        if (isNetworkAvailable()) {
            signInButton.setEnabled(true);
        } else {
            Toast.makeText(getApplicationContext(), "Network unavailable",
                    Toast.LENGTH_LONG).show();
        }

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = email.getText().toString();
                String password = passwordText.getText().toString();

                if (username.length() > 0
                        && password.length() > 0) {

                    OkHttpClient client = new OkHttpClient();
                    final Request authRequest = constructUserAuthRequest(username, password);

                    Call call = client.newCall(authRequest);
                    call.enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {

                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {

                            if (response.isSuccessful()) {
                                String authResponse = response.body().string();
                                final String tokenString = parseAuthToken(authResponse);

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Intent restaurantsIntent = new Intent(getApplicationContext(), RestaurantSearchActivity.class);
                                        restaurantsIntent.putExtra("token", tokenString);

                                        startActivity(restaurantsIntent);
                                    }
                                });
                            } else {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        alertUserAboutError();
                                    }
                                });
                            }
                        }
                    });
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Please enter doordash username and password", Toast.LENGTH_LONG).show();
                }

            }

        });
    }

    private String parseAuthToken(String authResponse) {
        Log.i(TAG, authResponse);
        String token = "";
        try {
            JSONObject authTokenJson = new JSONObject(authResponse);
            token = authTokenJson.getString("token");
            Log.i(TAG, authTokenJson.getString("token"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return token;
    }

    private Request constructUserAuthRequest(String username, String password) {
        String authUrl = Constants.DOORDASH_ROOT_URL + Constants.AUTH_URL;
        String EMAIL_HEADER = "email";
        String PASSWORD_HEADER = "password";
        JSONObject body = new JSONObject();

        try {
            body.put(EMAIL_HEADER, username);
            body.put(PASSWORD_HEADER, password);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestBody authBody = RequestBody.create(Constants.JSON, body.toString());

        return new Request.Builder()
                .addHeader("Content-Type", "application/json")
                .post(authBody)
                .url(authUrl)
                .build();
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager manager = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        boolean isAvailable = false;
        if (networkInfo != null && networkInfo.isConnected()) {
            isAvailable = true;
        }

        return isAvailable;
    }

    private void alertUserAboutError() {
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setMessage("Invalid credentials provided").create();
        dialog.show();
    }
}
