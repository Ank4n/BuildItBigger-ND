package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dd.CircularProgressButton;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import space.ankan.joketellandroid.JokeView;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements JokeAsyncTask.ActivityCallback {
    private CircularProgressButton button;
    private InterstitialAd mInterstitialAd;
    private String mJoke;
    private View mRoot;
    private AdView mAdView;

    private final View.OnClickListener buttonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            clickAction();

        }
    };

    private final AdListener interstialAdListener = new AdListener() {
        @Override
        public void onAdClosed() {

            launchJokeActivity();
            loadAds();
        }
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mRoot = inflater.inflate(R.layout.fragment_main, container, false);

        init();
        loadAds();

        return mRoot;
    }


    @Override
    public void onResume() {
        super.onResume();
        resetButton();
    }

    private void resetButton() {
        button.setIndeterminateProgressMode(false);
        button.setProgress(CircularProgressButton.IDLE_STATE_PROGRESS);
        button.setClickable(true);
        button.setGravity(Gravity.CENTER);
    }

    private void errorButtonState() {
        button.setIndeterminateProgressMode(false);
        button.setProgress(CircularProgressButton.ERROR_STATE_PROGRESS);
        button.setClickable(true);


    }

    private void completeButtonState() {
        button.setIndeterminateProgressMode(false);
        button.setProgress(CircularProgressButton.SUCCESS_STATE_PROGRESS);

    }

    private void progressiveState() {
        button.setIndeterminateProgressMode(true);
        button.setProgress(CircularProgressButton.INDETERMINATE_STATE_PROGRESS);
        button.setClickable(false);
    }

    @Override
    public void showJoke(final String joke) {

        if (joke == null) {
            Toast.makeText(getActivity(), "Looks like our server is down", Toast.LENGTH_LONG).show();
            errorButtonState();
            return;
        }

        mJoke = joke;
        completeButtonState();

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (mInterstitialAd.isLoaded())
                    mInterstitialAd.show();
                else
                    launchJokeActivity();
                button.setClickable(true);

            }
        });


    }


    private void init() {
        mAdView = (AdView) mRoot.findViewById(R.id.adView);
        button = (CircularProgressButton) mRoot.findViewById(R.id.joke_button);
        mInterstitialAd = new InterstitialAd(getActivity());
        mInterstitialAd.setAdUnitId(getResources().getString(R.string.banner_ad_unit_id));
        mInterstitialAd.setAdListener(interstialAdListener);
        button.setOnClickListener(buttonListener);
    }


    private void launchJokeActivity() {
        Intent i = new Intent(getActivity(), JokeView.class);
        i.putExtra(JokeView.JOKE_EXTRA, mJoke);
        startActivity(i);
    }


    private void clickAction() {
        // if button is in error state, bring it back to initial state
        if (button.getProgress() == CircularProgressButton.ERROR_STATE_PROGRESS) {
            resetButton();
            return;
        }
        progressiveState();
        new JokeAsyncTask(this, null).execute();

    }

    private void loadAds() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        mInterstitialAd.loadAd(adRequest);
        mAdView.loadAd(adRequest);
    }
}
