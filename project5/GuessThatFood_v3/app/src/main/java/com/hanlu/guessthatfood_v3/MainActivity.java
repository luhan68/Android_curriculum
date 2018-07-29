package com.hanlu.guessthatfood_v3;

import android.content.res.AssetManager;
import android.support.v4.widget.CircularProgressDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Priority;
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

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> foods;
    private ArrayList<String> urls;
    private static final int HTTP_REQUEST_TIMEOUT = 3 * 600000;
    @BindView(R.id.picture)
    ImageView picture;
    @BindView(R.id.rounds)
    TextView score;
    @BindViews ({R.id.option1, R.id.option2, R.id.option3, R.id.option4})
    List<Button> buttonList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        readFile();
        readUrls();
        setImageViewFromURL(urls.get(0),foods.get(0));
        // findUrls();
        //Toasty.error(this, "This is an error toast.", Toast.LENGTH_SHORT, true).show();
    }

    @OnClick({R.id.option1, R.id.option2, R.id.option3, R.id.option4, R.id.hint})
    public void onClick(View view) {
        //cast the button
    }

    public void findUrls() {
        new Thread(new Runnable() {

            @Override
            public void run() {
                // Do the processing.
                urls = getUrls();
                for (String s : urls) {
                    System.out.println("LINKS IS : " + s);
                }
            }
        }).start();
    }

    public void readUrls() {
        urls = new ArrayList<>();
        AssetManager assetManager = getAssets();
        InputStream inputStream;
        try {
            inputStream = assetManager.open("FoodURLS.txt");
            Scanner kb = new Scanner(inputStream);
            while (kb.hasNext()) {
                urls.add(kb.nextLine());
            }
            inputStream.close();
            kb.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public ArrayList<String> getUrls() {
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

    public void setImageViewFromURL(String url, String name) {
        name = name.toLowerCase();
        name = name.replaceAll(" ", "");
        CircularProgressDrawable circularProgressDrawable = new CircularProgressDrawable(this);
        circularProgressDrawable.setStrokeWidth(25f);
        circularProgressDrawable.setCenterRadius(100f);
        circularProgressDrawable.start();
        GlideApp.with(this)
                .load(url)
                .fitCenter()
                .placeholder(circularProgressDrawable)
                .error(R.drawable.ic_cloud_off_red)
                .priority(Priority.HIGH)
                .into(picture);
    }

    public void readFile() {
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
