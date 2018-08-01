package com.sszg.guessthatfoodv2;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.AssetManager;
import android.support.v7.app.AlertDialog;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.Callable;

public class HelperClass {

    public static ArrayList<String> readFoods(AssetManager assetManager) {
        ArrayList<String> foods = new ArrayList<>();
        InputStream inputStream;
        try {
            inputStream = assetManager.open("Foods.txt");
            Scanner kb = new Scanner(inputStream);
            while (kb.hasNext()) {
                foods.add(kb.nextLine());
            }
            inputStream.close();
            kb.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return foods;
    }

    public static ArrayList<String> readFoodURLS(AssetManager assetManager) {
        ArrayList<String> foods = new ArrayList<>();
        InputStream inputStream;
        try {
            inputStream = assetManager.open("FoodURLS.txt");
            Scanner kb = new Scanner(inputStream);
            while (kb.hasNext()) {
                foods.add(kb.nextLine());
            }
            inputStream.close();
            kb.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return foods;
    }
}
