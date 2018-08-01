package com.sszg.guessthatfoodv2;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.ProgressBar;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.customsearch.Customsearch;
import com.google.api.services.customsearch.model.Result;
import com.google.api.services.customsearch.model.Search;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class ImageSearchApi extends AsyncTask<ArrayList<String>, Integer, ArrayList<String>> {

    private static final int HTTP_REQUEST_TIMEOUT = 3 * 600000;
    private WeakReference<Activity> activity;
    private WeakReference<ProgressBar> progressBar;

    public ImageSearchApi(Activity activity) {
        this.activity = new WeakReference<>(activity);
        this.progressBar = new WeakReference<>(new ProgressBar(activity));
    }

    @Override
    protected ArrayList<String> doInBackground(ArrayList<String>... strings) {
        Customsearch customsearch = null;
        ArrayList<String> foods = strings[0];
        //progressBar.get().setMax(foods.size());
        try {
            customsearch = new Customsearch(new NetHttpTransport(), new JacksonFactory(), new HttpRequestInitializer() {
                public void initialize(HttpRequest httpRequest) {
                    try {
                        // set connect and read timeouts
                        httpRequest.setConnectTimeout(HTTP_REQUEST_TIMEOUT);
                        httpRequest.setReadTimeout(HTTP_REQUEST_TIMEOUT);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        ArrayList<String> resultList = new ArrayList<>();
        int count = 1;
        try {
            Customsearch.Cse.List list;
            if (customsearch != null) {
                list = customsearch.cse().list("");
                list.setKey("AIzaSyB058366Q83_r2QtNskYAtmOhTbDyOTWt4");
                list.setCx("002740584417081531455:fnr_tspgkay");
                list.setSearchType("image");
                list.setNum(1L);
                for (String string : foods) {
                    list.setQ(string);
                    Search results = list.execute();
                    if (results != null && results.getItems() != null) {
                        Result result = results.getItems().get(0);
                        resultList.add(result.getLink());
                        publishProgress(count);
                        count++;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultList;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        //progressBar.get().setVisibility(View.VISIBLE);
        //progressBar.get().setProgress(0);

    }

    @Override
    protected void onPostExecute(ArrayList<String> strings) {
        super.onPostExecute(strings);
        //progressBar.get().setVisibility(View.GONE);
        ((MainActivity) activity.get()).setFoodURLS(strings);

    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        //progressBar.get().setProgress(values[0]);
    }

}
