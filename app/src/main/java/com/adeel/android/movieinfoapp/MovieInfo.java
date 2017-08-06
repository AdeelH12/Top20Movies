package com.adeel.android.movieinfoapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by adeel on 05/08/2017.
 */

public class MovieInfo extends AppCompatActivity {
    String poster, movie_thumb;
    ImageView mMoviePoster, mMovieThumb;
    TextView title, overview_tv, mRelease;
    RatingBar ratingBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie__info);
        mMoviePoster = (ImageView) findViewById(R.id.movie_poster);
        mMovieThumb = (ImageView) findViewById(R.id.movie_thumb);
        title = (TextView) findViewById(R.id.title_tv);
        overview_tv = (TextView) findViewById(R.id.movie_info);
        mRelease = (TextView) findViewById(R.id.release);


        Intent iin = getIntent();
        Bundle b = iin.getExtras();

        if (b != null) {
            String data = b.getString("movie_name");
            String overview  = b.getString("overview");
            String release  = b.getString("release_date");
            movie_thumb = b.getString("movie_thumb");
            poster = b.getString("movie_poster");
            title.setText(data.toString());
            overview_tv.setText(overview.toString());
            mRelease.setText("Release Date: " + release);



        }

        downloadImage downloadImage = new downloadImage();
        downloadImage.execute();

        dowloadthumb dowloadthumb = new dowloadthumb();
        dowloadthumb.execute();

    }
        public class downloadImage extends AsyncTask<String, Void, Bitmap> {


            @Override
            protected Bitmap doInBackground(String... strings) {


                Bitmap bm = null;

                try {
                    URL url = new URL("https://image.tmdb.org/t/p/w500"+poster);
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


                mMoviePoster.setImageBitmap(bitmap);
            }
        }

    public class dowloadthumb extends AsyncTask<String, Void, Bitmap>{


        @Override
        protected Bitmap doInBackground(String... strings) {

            Bitmap bm = null;

            try {
                URL url = new URL("https://image.tmdb.org/t/p/w500"+movie_thumb);
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


            mMovieThumb.setImageBitmap(bitmap);
        }

        }
    }




