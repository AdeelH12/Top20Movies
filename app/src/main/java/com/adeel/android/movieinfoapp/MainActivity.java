package com.adeel.android.movieinfoapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
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

public class MainActivity extends AppCompatActivity {

    TextView mMovie_title;
    ListView mList;
    Bitmap bmp;
    JSONArray jArray;
    JSONObject jsonObject, finalObject;
    ArrayList<String> overview = new ArrayList<>();
    List<String> list = new ArrayList<String>();
    ArrayList<String> posterArray = new ArrayList<>();
    ArrayList<String> thumbArray = new ArrayList<>();
    ArrayList<String>releasedate = new ArrayList();
    private String getMovieInfo = "https://api.themoviedb.org/3/movie/top_rated?api_key=f1d314280284e94ff7d1feeed7d44fdf&language=en-US&page=1";


    public class downloadData extends AsyncTask<String, Void, String> {
        String result = "";

        @Override
        protected String doInBackground(String... strings) {

            try {
                URL url = new URL(getMovieInfo);

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

                httpURLConnection.connect();

                InputStream inputStream = httpURLConnection.getInputStream();

                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

                int data = inputStreamReader.read();

                while (data != -1) {

                    char current = (char) data;

                    result += current;

                    data = inputStreamReader.read();

                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return result;
        }

        @Override
        protected void onPostExecute(String s) {

            super.onPostExecute(s);

            List<MovieModel> movies = new ArrayList<MovieModel>();

            try {

                jsonObject = new JSONObject(result);

                jArray = jsonObject.getJSONArray("results");

                list = new ArrayList<String>();
                posterArray = new ArrayList<String>();
                thumbArray = new ArrayList<String>();
                releasedate = new ArrayList<String>();

                for (int i = 0; i < jArray.length(); i++) {

                    finalObject = jArray.getJSONObject(i);

                    posterArray.add(jArray.getJSONObject(i).optString("backdrop_path"));

                    thumbArray.add(jArray.getJSONObject(i).optString("poster_path"));
                    releasedate.add(jArray.getJSONObject(i).optString("release_date"));
                    Log.d("Movie_Demo", posterArray.toString());

                    overview.add(jArray.getJSONObject(i).optString("overview"));
                    list.add(jArray.getJSONObject(i).getString("title"));

                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                            MainActivity.this,
                          R.layout.row_layout,
                            list);

                    mList.setAdapter(arrayAdapter);

                    listCheck();

                }


            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
    }

        public class downloadImage extends AsyncTask<String, Void, Bitmap> {


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

    public void listCheck(){


        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long id) {


                if(pos == 0) {

                    Intent a = new Intent(getApplicationContext(), MovieInfo.class);
                    String movie = (String) adapterView.getItemAtPosition(pos);
                    a.putExtra("movie_name", movie);
                    a.putExtra("overview", overview.get(0).toString());
                    a.putExtra("movie_poster", posterArray.get(0).toString());
                    a.putExtra("movie_thumb", thumbArray.get(0).toString());
                    a.putExtra("release_date", releasedate.get(0).toString());
                    startActivityForResult(a, 0);

                }

                if(pos == 1){

                    Intent a = new Intent(getApplicationContext(), MovieInfo.class);
                    String movie = (String) adapterView.getItemAtPosition(pos);
                    a.putExtra("movie_name", movie);
                    a.putExtra("overview", overview.get(1).toString());
                    a.putExtra("movie_poster", posterArray.get(1).toString());
                    a.putExtra("movie_thumb", thumbArray.get(1).toString());
                    a.putExtra("release_date", releasedate.get(1).toString());
                    startActivityForResult(a, 0);
                }

                if(pos == 2){

                    Intent a = new Intent(getApplicationContext(), MovieInfo.class);
                    String movie = (String) adapterView.getItemAtPosition(pos);
                    a.putExtra("movie_name", movie);
                    a.putExtra("overview", overview.get(2).toString());
                    a.putExtra("movie_poster", posterArray.get(2).toString());
                    a.putExtra("movie_thumb", thumbArray.get(2).toString());
                    a.putExtra("release_date", releasedate.get(2).toString());
                    startActivityForResult(a, 0);
                }

                if(pos == 3){

                    Intent a = new Intent(getApplicationContext(), MovieInfo.class);
                    String movie = (String) adapterView.getItemAtPosition(pos);
                    a.putExtra("movie_name", movie);
                    a.putExtra("overview", overview.get(3).toString());
                    a.putExtra("movie_poster", posterArray.get(3).toString());
                    a.putExtra("movie_thumb", thumbArray.get(3).toString());
                    a.putExtra("release_date", releasedate.get(3).toString());
                    startActivityForResult(a, 0);
                }

                if(pos == 4){

                    Intent a = new Intent(getApplicationContext(), MovieInfo.class);
                    String movie = (String) adapterView.getItemAtPosition(pos);
                    a.putExtra("movie_name", movie);
                    a.putExtra("overview", overview.get(4).toString());
                    a.putExtra("movie_poster", posterArray.get(4).toString());
                    a.putExtra("movie_thumb", thumbArray.get(4).toString());
                    a.putExtra("release_date", releasedate.get(4).toString());
                    startActivityForResult(a, 0);
                }

                if(pos == 5){

                    Intent a = new Intent(getApplicationContext(), MovieInfo.class);
                    String movie = (String) adapterView.getItemAtPosition(pos);
                    a.putExtra("movie_name", movie);
                    a.putExtra("overview", overview.get(5).toString());
                    a.putExtra("movie_poster", posterArray.get(5).toString());
                    a.putExtra("movie_thumb", thumbArray.get(5).toString());
                    a.putExtra("release_date", releasedate.get(5).toString());
                    startActivityForResult(a, 0);
                }

                if(pos == 6){

                    Intent a = new Intent(getApplicationContext(), MovieInfo.class);
                    String movie = (String) adapterView.getItemAtPosition(pos);
                    a.putExtra("movie_name", movie);
                    a.putExtra("overview", overview.get(6).toString());
                    a.putExtra("movie_poster", posterArray.get(6).toString());
                    a.putExtra("movie_thumb", thumbArray.get(6).toString());
                    a.putExtra("release_date", releasedate.get(6).toString());
                    startActivityForResult(a, 0);
                }

                if(pos == 7){

                    Intent a = new Intent(getApplicationContext(), MovieInfo.class);
                    String movie = (String) adapterView.getItemAtPosition(pos);
                    a.putExtra("movie_name", movie);
                    a.putExtra("overview", overview.get(7).toString());
                    a.putExtra("movie_poster", posterArray.get(7).toString());
                    a.putExtra("movie_thumb", thumbArray.get(7).toString());
                    a.putExtra("release_date", releasedate.get(7).toString());
                    startActivityForResult(a, 0);
                }

                if(pos == 8){

                    Intent a = new Intent(getApplicationContext(), MovieInfo.class);
                    String movie = (String) adapterView.getItemAtPosition(pos);
                    a.putExtra("movie_name", movie);
                    a.putExtra("overview", overview.get(8).toString());
                    a.putExtra("movie_poster", posterArray.get(8).toString());
                    a.putExtra("movie_thumb", thumbArray.get(8).toString());
                    a.putExtra("release_date", releasedate.get(8).toString());
                    startActivityForResult(a, 0);
                }

                if(pos == 9){

                    Intent a = new Intent(getApplicationContext(), MovieInfo.class);
                    String movie = (String) adapterView.getItemAtPosition(pos);
                    a.putExtra("movie_name", movie);
                    a.putExtra("overview", overview.get(9).toString());
                    a.putExtra("movie_poster", posterArray.get(9).toString());
                    a.putExtra("movie_thumb", thumbArray.get(9).toString());
                    a.putExtra("release_date", releasedate.get(9).toString());
                    startActivityForResult(a, 0);
                }

                if(pos == 10){

                    Intent a = new Intent(getApplicationContext(), MovieInfo.class);
                    String movie = (String) adapterView.getItemAtPosition(pos);
                    a.putExtra("movie_name", movie);
                    a.putExtra("overview", overview.get(10).toString());
                    a.putExtra("movie_poster", posterArray.get(10).toString());
                    a.putExtra("movie_thumb", thumbArray.get(10).toString());
                    a.putExtra("release_date", releasedate.get(10).toString());
                    startActivityForResult(a, 0);
                }

                if(pos == 11){

                    Intent a = new Intent(getApplicationContext(), MovieInfo.class);
                    String movie = (String) adapterView.getItemAtPosition(pos);
                    a.putExtra("movie_name", movie);
                    a.putExtra("overview", overview.get(11).toString());
                    a.putExtra("movie_poster", posterArray.get(11).toString());
                    a.putExtra("movie_thumb", thumbArray.get(11).toString());
                    a.putExtra("release_date", releasedate.get(11).toString());
                    startActivityForResult(a, 0);
                }

                if(pos == 12){

                    Intent a = new Intent(getApplicationContext(), MovieInfo.class);
                    String movie = (String) adapterView.getItemAtPosition(pos);
                    a.putExtra("movie_name", movie);
                    a.putExtra("overview", overview.get(12).toString());
                    a.putExtra("movie_poster", posterArray.get(12).toString());
                    a.putExtra("movie_thumb", thumbArray.get(12).toString());
                    a.putExtra("release_date", releasedate.get(12).toString());
                    startActivityForResult(a, 0);
                }

                if(pos == 13){

                    Intent a = new Intent(getApplicationContext(), MovieInfo.class);
                    String movie = (String) adapterView.getItemAtPosition(pos);
                    a.putExtra("movie_name", movie);
                    a.putExtra("overview", overview.get(13).toString());
                    a.putExtra("movie_poster", posterArray.get(13).toString());
                    a.putExtra("movie_thumb", thumbArray.get(13).toString());
                    a.putExtra("release_date", releasedate.get(13).toString());
                    startActivityForResult(a, 0);
                }

                if(pos == 14){

                    Intent a = new Intent(getApplicationContext(), MovieInfo.class);
                    String movie = (String) adapterView.getItemAtPosition(pos);
                    a.putExtra("movie_name", movie);
                    a.putExtra("overview", overview.get(14).toString());
                    a.putExtra("movie_poster", posterArray.get(14).toString());
                    a.putExtra("movie_thumb", thumbArray.get(14).toString());
                    a.putExtra("release_date", releasedate.get(14).toString());
                    startActivityForResult(a, 0);
                }

                if(pos == 15){

                    Intent a = new Intent(getApplicationContext(), MovieInfo.class);
                    String movie = (String) adapterView.getItemAtPosition(pos);
                    a.putExtra("movie_name", movie);
                    a.putExtra("overview", overview.get(15).toString());
                    a.putExtra("movie_poster", posterArray.get(15).toString());
                    a.putExtra("movie_thumb", thumbArray.get(15).toString());
                    a.putExtra("release_date", releasedate.get(15).toString());
                    startActivityForResult(a, 0);

                }

                if(pos == 16){

                    Intent a = new Intent(getApplicationContext(), MovieInfo.class);
                    String movie = (String) adapterView.getItemAtPosition(pos);
                    a.putExtra("movie_name", movie);
                    a.putExtra("overview", overview.get(16).toString());
                    a.putExtra("movie_poster", posterArray.get(16).toString());
                    a.putExtra("movie_thumb", thumbArray.get(16).toString());
                    a.putExtra("release_date", releasedate.get(16).toString());
                    startActivityForResult(a, 0);
                }

                if(pos == 17){

                    Intent a = new Intent(getApplicationContext(), MovieInfo.class);
                    String movie = (String) adapterView.getItemAtPosition(pos);
                    a.putExtra("movie_name", movie);
                    a.putExtra("overview", overview.get(17).toString());
                    a.putExtra("movie_poster", posterArray.get(17).toString());
                    a.putExtra("movie_thumb", thumbArray.get(17).toString());
                    a.putExtra("release_date", releasedate.get(17).toString());
                    startActivityForResult(a, 0);
                }

                if(pos == 18){

                    Intent a = new Intent(getApplicationContext(), MovieInfo.class);
                    String movie = (String) adapterView.getItemAtPosition(pos);
                    a.putExtra("movie_name", movie);
                    a.putExtra("overview", overview.get(18).toString());
                    a.putExtra("movie_poster", posterArray.get(18).toString());
                    a.putExtra("movie_thumb", thumbArray.get(18).toString());
                    a.putExtra("release_date", releasedate.get(18).toString());
                    startActivityForResult(a, 0);
                }

                if(pos == 19){

                    Intent a = new Intent(getApplicationContext(), MovieInfo.class);
                    String movie = (String) adapterView.getItemAtPosition(pos);
                    a.putExtra("movie_name", movie);
                    a.putExtra("overview", overview.get(19).toString());
                    a.putExtra("movie_thumb", thumbArray.get(19).toString());
                    a.putExtra("movie_poster", posterArray.get(19).toString());
                    a.putExtra("release_date", releasedate.get(19).toString());
                    startActivityForResult(a, 0);
                }


                }

            });

        }





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        downloadData data = new downloadData();
        data.execute();

        mList = (ListView) findViewById(R.id.listView);



//        mImage = (ImageView) findViewById(R.id.poster);





        downloadImage dwn = new downloadImage();
        dwn.execute();


    }
}









//TODO (1) Download the movie titles for other movies
//TODO (2) Download the images, ratings & overview associated with them movies
//TODO (3) Make a listview with this information


