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
 * Created by Kevin on 4/3/2017.
 */

public class FetchArticlesLoader extends AsyncTaskLoader<ArrayList<Article>> {

    private static final String LOG_TAG = FetchArticlesLoader.class.getSimpleName();
    private static String mUrl;

    public FetchArticlesLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public ArrayList<Article> loadInBackground() {
        String jsonStr = null;
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            URL url = new URL(mUrl);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            if (urlConnection.getResponseCode() != 200) {
                Log.e(LOG_TAG, "Bad response code");
                return null;
            }
            inputStream = urlConnection.getInputStream();

            StringBuilder builder = new StringBuilder();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader reader = new BufferedReader(inputStreamReader);

            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
            reader.close();
            jsonStr = builder.toString();
        } catch (Exception e) {
            Log.e(LOG_TAG, "Error getting json ", e);
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    Log.e(LOG_TAG, "Error closing input stream", e);
                }
            }
        }
        try {
            return getArticleList(jsonStr);
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Error parsing json data");
        }
        return null;
    }

    private ArrayList<Article> getArticleList(String jsonStr) throws JSONException {
        ArrayList<Article> articleArrayList = new ArrayList<>();

        JSONObject jsonObject = new JSONObject(jsonStr);
        JSONObject responseObject = jsonObject.getJSONObject("response");
        JSONArray jsonArray = responseObject.getJSONArray("results");

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject articleJson = jsonArray.getJSONObject(i);
            String id = articleJson.getString("id");
            String date = articleJson.getString("webPublicationDate");
            String sectionId = articleJson.getString("sectionId");
            String sectionName = articleJson.getString("sectionName");
            String url = articleJson.getString("webUrl");
            String title = articleJson.getString("webTitle");

            articleArrayList.add(new Article(id, sectionId, sectionName, date, title, url));
        }

        return articleArrayList;
    }

}
