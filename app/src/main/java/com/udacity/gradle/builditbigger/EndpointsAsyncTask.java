package com.udacity.gradle.builditbigger;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.util.Pair;
import android.util.Log;
import android.widget.Toast;

import com.example.gaurav.myapplication.jokebackend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

/**
 * Created by Gaurav on 07-05-2016.
 */
class EndpointsAsyncTask extends AsyncTask<Pair<Context, String>, Void, String> {
    private static MyApi myApiService = null;
    private Context mContext;
    public AsyncResponse mAsyncResponse = null;
    private ProgressDialog progress;

    public EndpointsAsyncTask(Context context, AsyncResponse asyncResponse){
        mContext = context;
        this.mAsyncResponse = asyncResponse;
    }

    @Override
    protected void onPreExecute(){
        progress = new ProgressDialog(mContext);
        progress.setMessage("Loading Joke");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setIndeterminate(true);
        progress.setCancelable(false);
        progress.show();
    }

    @Override
    protected String doInBackground(Pair<Context, String>... params) {
        if(myApiService == null) {  // Only do this once
            /*MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });*/
            // end options for devappserver

            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("https://jokesupply.appspot.com/_ah/api/");

            myApiService = builder.build();
        }

        mContext = params[0].first;
        Log.v("EndPointsAsyncTask","context: "+mContext);
        String name = params[0].second;
        Log.v("EndPointsAsyncTask","Name: "+name);

        try {
            //Log.v("EndPointsAsyncTask", myApiService.sayHi(name).execute().getData())
            return myApiService.sayHi(name).execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        Log.v("EndPointsAsyncTask","result: "+result);
        progress.dismiss();
        this.mAsyncResponse.processFinish(result);
        Toast.makeText(mContext, result, Toast.LENGTH_LONG).show();
    }
}
