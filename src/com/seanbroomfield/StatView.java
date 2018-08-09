package com.seanbroomfield;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.squareup.picasso.Picasso;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

/**
 * Created by seanbroomfield on 9/12/15.
 */
public class StatView extends Activity {

    ListAdapter PveListAdapter;
    ListAdapter PvpListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(android.R.style.Theme_Translucent_NoTitleBar);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stats_display);
        Player player1 = null, player2 = null;

        try {
            player1 = Geronimo.createPlayer((new JSONObject(getIntent().getStringExtra("player1")).getJSONArray("Response")));
            player2 = Geronimo.createPlayer((new JSONObject(getIntent().getStringExtra("player2")).getJSONArray("Response")));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if(player1 != null && player2 != null) {
            downloadAndSetStats(player1);
            downloadAndSetStats(player2);
            ArrayList<String> categories = new ArrayList<String>(player1.getCumulativePveStats().asList().keySet());
            ArrayList<Integer> actualStatPlayer1 = new ArrayList<Integer>(player1.getCumulativePveStats().asList().values());
            ArrayList<Integer> actualStatPlayer2 = new ArrayList<Integer>(player2.getCumulativePveStats().asList().values());
            ListView mainListView = (ListView) findViewById(R.id.statsList);
            this.PveListAdapter = new ListAdapter(actualStatPlayer1, actualStatPlayer2, categories, this);
            this.PvpListAdapter = new ListAdapter(new ArrayList<Integer>(player1.getCumulativePvpStats().asList().values()), new ArrayList<Integer>(player2.getCumulativePvpStats().asList().values()), categories, this);
            ( (TextView) findViewById(R.id.player1Name)).setText(player1.getName());
            ( (TextView) findViewById(R.id.player2Name)).setText(player2.getName());
            ImageView player1Emblem = (ImageView) findViewById(R.id.player1Emblem);
            ImageView player2Emblem = (ImageView) findViewById(R.id.player2Emblem);
            Picasso.with(this).load(player1.getCharacters().get(0).getEmblem()).into(player1Emblem);
            Picasso.with(this).load(player2.getCharacters().get(0).getEmblem()).into(player2Emblem);
            mainListView.setAdapter(PveListAdapter);
        } else {
            return;
        }
    }

    public void downloadAndSetStats(Player player) {
        JSONObject jsonObject = Geronimo.downloadStats(player);
        Geronimo.setPlayerStats(player, jsonObject);
    }

    public void check(View view) {
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioStat);
        ListView mainListView = (ListView) findViewById(R.id.statsList);
        if(radioGroup.getCheckedRadioButtonId() == (R.id.PveStatistics)) {
            mainListView.setAdapter(this.PveListAdapter);
            ((RadioButton)findViewById(R.id.PveStatistics)).setClickable(false);
            ((RadioButton)findViewById(R.id.PvpStatistics)).setClickable(true);
        } else if(radioGroup.getCheckedRadioButtonId() == (R.id.PvpStatistics)) {
            mainListView.setAdapter(this.PvpListAdapter);
            ((RadioButton)findViewById(R.id.PveStatistics)).setClickable(true);
            ((RadioButton)findViewById(R.id.PvpStatistics)).setClickable(false);
        }
    }

}
