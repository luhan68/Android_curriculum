package com.hanlu.guessthisfood_v2;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.customsearch.Customsearch;
import com.google.api.services.customsearch.model.Result;
import com.google.api.services.customsearch.model.Search;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import es.dmoral.toasty.Toasty;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> foods;
    private ArrayList<String> urls;
    private static final int HTTP_REQUEST_TIMEOUT = 3 * 600000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        readFile();
        findUrls();
        //Toasty.error(this, "This is an error toast.", Toast.LENGTH_SHORT, true).show();
    }

    public void findUrls(){
        new Thread(new Runnable() {

            @Override
            public void run() {
                // Do the processing.
                urls = getUrls();
                for (String s : urls)
                {
                    System.out.println("LINKS IS : " + s);
                }
            }
        }).start();
    }


    public ArrayList<String> getUrls(){
        Customsearch customsearch = null;
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
        try {
            Customsearch.Cse.List list;
            if (customsearch != null) {
                list = customsearch.cse().list("");
                list.setKey("AIzaSyDAm34wZsFPB79S_3bTPO5Y4ZScB6DDYDo");
                list.setCx("001527314822421202900:u_s0rpv43ku");
                list.setSearchType("image");
                list.setNum(1L);
                for (String string : foods) {
                    list.setQ(string);
                    Search results = list.execute();
                    if (results != null && results.getItems() != null) {
                        Result result = results.getItems().get(0);
                        resultList.add(result.getLink());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultList;
    }

    public void readFile(){
        foods = new ArrayList<>();
        AssetManager assetManager = getAssets();
        InputStream inputStream;
        try {
            inputStream = assetManager.open("Food.txt");
            Scanner kb = new Scanner(inputStream);
            while (kb.hasNext()) {
                foods.add(kb.nextLine());
            }
            inputStream.close();
            kb.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
