package com.lataeviaberry.mybeauty;

/**
 * Created by lataeviaberry on 7/26/16.
 */
public class Constants {
    public static final String YELP_CONSUMER_KEY = BuildConfig.YELP_CONSUMER_KEY;
    public static final String YELP_CONSUMER_SECRET = BuildConfig.YELP_CONSUMER_SECRET;
    public static final String YELP_TOKEN = BuildConfig.YELP_TOKEN;
    public static final String YELP_TOKEN_SECRET = BuildConfig.YELP_TOKEN_SECRET;
    public static final String YELP_BASE_URL = "https://api.yelp.com/v2/search?category_filter=beautysvc";
    public static final String YELP_LOCATION_QUERY_PARAMETER = "location";

    public static final String PREFERENCES_LOCATION_KEY = "zipCode";
    public static final String FIREBASE_CHILD_SEARCHED_LOCATION = "searchedLocation";
    public static final String FIREBASE_CHILD_BEAUTYS = "beautys";
}
