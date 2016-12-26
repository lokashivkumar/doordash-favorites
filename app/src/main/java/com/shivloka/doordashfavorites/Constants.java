package com.shivloka.doordashfavorites;

import okhttp3.MediaType;

/**
 * Created by shiv.loka on 12/22/16.
 */

class Constants {

    public static final String DOORDASH_ROOT_URL = "https://api.doordash.com/v2/";
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    public static final String AUTH_URL = "/auth/token/";
    public static final String CONSUMER_URL = "consumer/me/";
    public static final String JSON_TOKEN_PREFIX = "JWT ";
}
