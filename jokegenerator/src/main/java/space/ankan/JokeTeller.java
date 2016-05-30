package space.ankan;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class JokeTeller {

    private final String[] goodJokes = {

            "The closest I’ve been to a diet this year is erasing food searches from my browser history.",

            "I put so much more effort into naming my first Wi-Fi than my first child.",

            "A system administrator has 2 problems: \n- dumb users \n- smart users",

            "Q: How many programmers does it take to change a light bulb? \nA: None, that's a hardware problem."
    };

    private final String[] badJokes = {"Bad Joke 1", "Bad Joke 2", "Bad Joke 3", "Bad Joke4"};
    private boolean pjMode;
    private static final int JOKE_COUNT = 4;
    private Map<Double, String> map;
    private List<String> jokesArray;
    private int counter;

    public JokeTeller(boolean pjMode) {
        setJokeMode(pjMode);
    }

    private void randomizeJokes() {
        counter = 0;
        Collections.shuffle(jokesArray);
    }

    public String nextJoke() {
        if (counter == JOKE_COUNT)
            randomizeJokes();
        return jokesArray.get(counter++);
    }

    public void setJokeMode(boolean pjMode) {
        this.pjMode = pjMode;
        jokesArray = this.pjMode ? Arrays.asList(badJokes) : Arrays.asList(goodJokes);
        randomizeJokes();
    }


}
