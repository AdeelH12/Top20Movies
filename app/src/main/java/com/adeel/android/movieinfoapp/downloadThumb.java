package com.adeel.android.movieinfoapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by adeel on 06/08/2017.
 */

public class downloadThumb extends AsyncTask<String, Void, Bitmap> {

    MainActivity mMainActivity = new MainActivity();

    @Override
    protected Bitmap doInBackground(String... strings) {


        Bitmap bm = null;

        try {
            URL url = new URL("https://image.tmdb.org/t/p/w500/hAPeXBdGDGmXRPj4OZZ0poH65Iu.jpg");
            InputStream is = url.openStream();
            bm = BitmapFactory.decodeStream(is);


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return bm;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);


        //mImage.setImageBitmap(bitmap);
    }
}

