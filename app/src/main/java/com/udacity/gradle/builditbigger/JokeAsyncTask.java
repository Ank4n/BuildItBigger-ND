package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

import space.ankan.builditbigger.backend.myApi.MyApi;

/**
 * Created by ankan.
 * TODO: Add a class comment
 */
public class JokeAsyncTask extends AsyncTask<Void, Void, String> {
    private Fragment fragment;
    private Context context;
    private MyApi myApiService = null;
    private CallbackListener helper;


    public JokeAsyncTask(Fragment context, CallbackListener signal) {
        this.fragment = context;
        this.context = fragment.getContext();
        this.helper = signal;
    }

    public JokeAsyncTask(Context context, CallbackListener signal) {
        this.context = context;
        this.helper = signal;
    }

    @Override
    protected String doInBackground(Void... params) {
        Log.i("BuildItBigger", "Fetching joke");
        if (myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
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
                    });
            // end options for devappserver

            myApiService = builder.build();
        }

        try {
            return myApiService.getGoodJoke().execute().getData();
        } catch (IOException e) {
            Log.e("BuildItBigger", "Exception: " + e.getMessage());
            return null;
        }
    }

    @Override
    protected void onPostExecute(String result) {
        if (helper != null) {
            helper.setData(result);
            helper.getSignal().countDown();
        }
        Log.i("BuildItBigger", "Result is :" + result);
        if (fragment!=null && fragment instanceof ActivityCallback) {
            ((ActivityCallback) fragment).showJoke(result);
        }
        //Toast.makeText(context, result, Toast.LENGTH_LONG).show();
    }

    public interface ActivityCallback {
        public void showJoke(String joke);
    }

}
