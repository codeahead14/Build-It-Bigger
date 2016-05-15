package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.util.Pair;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import android.widget.Button;

import com.example.JokeLib;
import com.example.gaurav.displayjokelib.JokeActivity;
import com.example.gaurav.myapplication.jokebackend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements AsyncResponse {

    Context context;
    EndpointsAsyncTask endpointsAsyncTask;
    AsyncResponse asyncResponse;
    String mJoke;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;
        asyncResponse = this;
        Button mButton = (Button)findViewById(R.id.joke_button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                endpointsAsyncTask = new EndpointsAsyncTask(context, asyncResponse);
                endpointsAsyncTask.execute(new Pair<Context, String>(context, "Gaurav"));;
                //tellJoke();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //public void tellJoke(View view){
        //Toast.makeText(this, new JokeLib().getJoke(), Toast.LENGTH_SHORT).show();
    /*public void tellJoke(){
        Intent intent = new Intent(this, JokeActivity.class);
        JokeLib jokeSource = new JokeLib();
        String joke = jokeSource.getJoke();
        intent.putExtra(JokeActivity.JOKE_KEY, joke);
        startActivity(intent);
    }*/

    public void tellJoke(String joke){
        Intent intent = new Intent(this, JokeActivity.class);
        intent.putExtra(JokeActivity.JOKE_KEY, joke);
        startActivity(intent);
    }

    @Override
    public void processFinish(String output) {
        if( output != null)
            mJoke = output;
        else
            mJoke = "Unable to Fetch Jokes at this time";
        tellJoke(mJoke);
    }

}
