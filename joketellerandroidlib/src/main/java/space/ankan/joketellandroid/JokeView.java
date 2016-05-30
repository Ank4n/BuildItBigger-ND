package space.ankan.joketellandroid;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by ankan.
 * TODO: Add a class comment
 */
public class JokeView extends Activity {

    public static final String JOKE_EXTRA = "joke_key";
    private TextView mJokeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke_view);
        mJokeText = (TextView) findViewById(R.id.joke);
        mJokeText.setText(getIntent().getStringExtra(JOKE_EXTRA));
    }
}
