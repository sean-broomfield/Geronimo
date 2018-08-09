package com.seanbroomfield;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.*;
import org.json.JSONException;
import org.json.JSONObject;

public class MyActivity extends Activity {
    /**
     * Called when the activity is first created.
     */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setTheme(android.R.style.Theme_Translucent_NoTitleBar);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    //MUST USE AYSNC TASK TO EXECUTE jsoup.connect();

    public void fightBegin(View v) throws JSONException {

        //Get GT/PSN and check to see if it exists, pass both JSONArrays in the intent

        EditText firstCompetitor = (EditText) findViewById(R.id.player1);
        EditText secondCompetitor = (EditText) findViewById(R.id.player2);
        JSONObject player1 = null;
        JSONObject player2 = null;
        if(checkText(firstCompetitor) && checkText(secondCompetitor) && checkRadioButtons((RadioGroup)findViewById(R.id.radio1)) && (checkRadioButtons((RadioGroup)findViewById(R.id.radio2)))) {
            if( ((RadioButton)findViewById(R.id.xbox1)).isChecked() ) {
                player1 = Geronimo.searchForPlayer("1", firstCompetitor.getText().toString().trim());
                if(player1.getJSONArray("Response").length() == 0) {
                    firstCompetitor.setError("USER DOES NOT EXIST ON THIS CONSOLE.");
                    return;
                }
            } else if ( ((RadioButton)findViewById(R.id.ps1)).isChecked() ) {
                player1 = Geronimo.searchForPlayer("0", secondCompetitor.getText().toString().trim());
                try {
                    if(player1.getJSONArray("Response").length() == 0) {
                        firstCompetitor.setError("This user does not exist on this console!");
                        return;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            if( ((RadioButton)findViewById(R.id.xbox2)).isChecked() ) {
                player2 = Geronimo.searchForPlayer("1", secondCompetitor.getText().toString().trim());
                try {
                    if(player2.getJSONArray("Response").length() == 0) {
                        secondCompetitor.setError("This user does not exist on this console!");
                        return;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else if ( ((RadioButton)findViewById(R.id.ps2)).isChecked() ) {
                player2 = Geronimo.searchForPlayer("0", secondCompetitor.getText().toString().trim());
                try {
                    if(player2.getJSONArray("Response").length() == 0) {
                        secondCompetitor.setError("This user does not exist on this console!");
                        return;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            firstCompetitor.setError(null);
            secondCompetitor.setError(null);
            Intent intent = new Intent(this, StatView.class);
            intent.putExtra("player1", player1.toString()).putExtra("player2", player2.toString());
            startActivity(intent);
        }
    }


    public Boolean checkText(EditText player) {
        if(TextUtils.isEmpty(player.getText().toString().trim())) {
            player.setError("GT/PSN cannot be empty.");
            return false;
        }
        return true;
    }

    public Boolean checkRadioButtons(RadioGroup radioGroup) {
        if(radioGroup.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "Console missing!", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

}
