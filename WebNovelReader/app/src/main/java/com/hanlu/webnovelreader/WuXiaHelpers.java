package com.hanlu.webnovelreader;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.provider.DocumentsContract;
import android.support.v4.widget.CircularProgressDrawable;
import android.support.v7.widget.RecyclerView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class WuXiaHelpers {

    public void getLatestChapters(Context context) {
        new WuxiaLatestChapters(context).execute();
    }


    public static class WuxiaLatestChapters extends AsyncTask<Void, Void, Void> {

        @SuppressLint("StaticFieldLeak")
        private Context context;
        private ProgressDialog progressDialog;

        WuxiaLatestChapters(Context context) {
            this.context = context;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            String url = "https://www.wuxiaworld.com/";
            try{
                Document page = (Jsoup.connect(url).get());
                Elements table = page.select("table[class= table table-novels]");
                Elements titles = table.select("span[class = title]");
                for (Element e : titles){
                    System.out.println(e.text());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(context);
            progressDialog.setTitle("Wuxia World");
            progressDialog.setMessage("Loading Book Catalog...");
            progressDialog.setIndeterminate(false);
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressDialog.dismiss();
        }
    }
}
