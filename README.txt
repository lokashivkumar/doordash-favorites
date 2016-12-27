====================================================================================================
How to run this project
====================================================================================================

1. The user signs in to the application in the SigninActivity - This activity returns a reusable
user token.
2. The user token returned in Step 1 is now used to request User profile.
3. Once the user profile is received, the lat, lng values can be parsed to pass to the
RestaurantRequester, which makes a request to get the list of restaurants.

====================================================================================================
Features implemented in this project
====================================================================================================
1. Retrieving a list of nearby restaurants.
2. Create a favorites list.
3. Login with a given user credentials.
4. Search for a restaurant by name.

====================================================================================================
Activities in the project
====================================================================================================

1. Signin activity - The user signs in with Doordash credentials.
2. RestaurantSearchActivity - Lists the restaurants nearby the user.
3. FavoritesActivity - Lists the restaurants favorited by the user.

====================================================================================================
Third Party libraries used in this project
====================================================================================================

1. Picasso image downloader by squareup.
2. OKHttp3 library for web requests in Android.
3. FasterXML Jackson databind and annotations libraries for JSON Object mapping.
4. Butterknife - for Android view injection.

====================================================================================================
Could haves:
====================================================================================================
1. Progress bar before loading restaurants list
2. Filters
3. Add to cart and Checkout