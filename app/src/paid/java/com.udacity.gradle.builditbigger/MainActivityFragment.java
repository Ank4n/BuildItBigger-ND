package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dd.CircularProgressButton;

import space.ankan.joketellandroid.JokeView;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements JokeAsyncTask.ActivityCallback {
    private CircularProgressButton button;
    private final View.OnClickListener buttonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            clickAction();

        }
    };

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        button = (CircularProgressButton) root.findViewById(R.id.joke_button);
        button.setOnClickListener(buttonListener);
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        resetButton();
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

    private void resetButton() {
        button.setIndeterminateProgressMode(false);
        button.setProgress(CircularProgressButton.IDLE_STATE_PROGRESS);
        button.setClickable(true);
        button.setGravity(Gravity.CENTER);
    }

    private void progressiveState() {
        button.setIndeterminateProgressMode(true);
        button.setProgress(CircularProgressButton.INDETERMINATE_STATE_PROGRESS);
        button.setClickable(false);
    }

    private void errorButtonState() {
        button.setIndeterminateProgressMode(false);
        button.setProgress(CircularProgressButton.ERROR_STATE_PROGRESS);
        button.setClickable(true);


    }

    private void completeButtonState() {
        button.setIndeterminateProgressMode(false);
        button.setProgress(CircularProgressButton.SUCCESS_STATE_PROGRESS);
        button.setClickable(true);

    }

    @Override
    public void showJoke(final String joke) {
        if (joke == null) {
            Toast.makeText(getActivity(), "Looks like our server is down", Toast.LENGTH_LONG).show();
            errorButtonState();
            return;
        }
        completeButtonState();
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Intent i = new Intent(getActivity(), JokeView.class);
                i.putExtra(JokeView.JOKE_EXTRA, joke);
                startActivity(i);

            }
        });
    }
}
