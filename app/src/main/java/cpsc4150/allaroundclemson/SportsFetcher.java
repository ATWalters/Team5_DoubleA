package cpsc4150.allaroundclemson;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


public class SportsFetcher {
    public interface OnSportsDataReceivedListener{
        void onCurrentGamesReceived(Boolean isGame);
        void onErrorResponse(VolleyError error);
    }

    private final String TAG = "SportsFetcher";

    private final String KEY;
    private RequestQueue mRequestQueue;

    public SportsFetcher(Context context){
        mRequestQueue = Volley.newRequestQueue(context);
        KEY = context.getString(R.string.sports_api_key);
    }

    public void fetchCurrentGames(final OnSportsDataReceivedListener listener){
        String url = "https://fly.sportsdata.io/v3/cfb/scores/json/AreAnyGamesInProgress?key=" + KEY;

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                Boolean areGamesHappening = parseCurrentGames(response);
                listener.onCurrentGamesReceived(areGamesHappening);
                Log.e(TAG, response);
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){
                listener.onErrorResponse(error);
                Log.e(TAG, "Error.Response");
            }
        });
        mRequestQueue.add(request);
    }

    private Boolean parseCurrentGames(String str){
        return Boolean.parseBoolean(str);
    }
}
