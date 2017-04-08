package example.alvarezkevin.udacitynews.data;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Kevin on 4/4/2017.
 */

public class FetchCategoriesLoader extends AsyncTaskLoader<ArrayList<Categories>> {

    private static final String CATEGORIES_URL = "https://content.guardianapis.com/sections?api-key=ad393a22-cb7b-49ae-906b-c89b6b3e2adf";
    private static final String LOG_TAG = FetchCategoriesLoader.class.getSimpleName();

    public FetchCategoriesLoader(Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public ArrayList<Categories> loadInBackground() {
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        String categoriesJson = null;

        try {
            URL url = new URL(CATEGORIES_URL);

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            inputStream = urlConnection.getInputStream();

            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader reader = new BufferedReader(inputStreamReader);
            StringBuilder builder = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
            categoriesJson = builder.toString();

        } catch (Exception e) {
            Log.e(LOG_TAG, "Error getting categories ", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    Log.e(LOG_TAG, "error closing input stream", e);
                }
            }
        }
        try {
            return getCategoriesList(categoriesJson);
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Error getting category from json ", e);
        }
        return null;
    }

    private ArrayList<Categories> getCategoriesList(String json) throws JSONException {

        ArrayList<Categories> resultList = new ArrayList<>();

        JSONObject jsonObject = new JSONObject(json);
        JSONObject resultsObject = jsonObject.getJSONObject("response");
        JSONArray categoriesArr = resultsObject.getJSONArray("results");

        for (int i = 0; i < categoriesArr.length(); i++) {
            JSONObject categoryObject = categoriesArr.getJSONObject(i);
            String id = categoryObject.getString("id");
            String title = categoryObject.getString("webTitle");
            String webUrl = categoryObject.getString("webUrl");
            String apiUrl = categoryObject.getString("apiUrl");

            resultList.add(new Categories(id, title, webUrl, apiUrl));
        }
        return resultList;
    }
}
