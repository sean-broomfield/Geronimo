package com.seanbroomfield;

import android.os.AsyncTask;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by seanbroomfield on 8/17/15.
 */
public class Player {
    private int console;
    private String membershipID;
    private String name;
    private Stats cumulativePveStats;
    private Stats cumulativePvpStats;
    ArrayList<Character> charactersList;

    public Player(int console, String membershipID, String name) {
        this.console = console;
        this.membershipID = membershipID;
        this.name = name;
        charactersList = new ArrayList<Character>();
    }

    public void fillCharacters(JSONArray jsonArray) {
        for(int i = 0; i < jsonArray.length(); i++) {
            try {
                if(!jsonArray.getJSONObject(i).getBoolean("deleted")) {
                    this.charactersList.add(new Character(jsonArray.getJSONObject(i)));
                    if(i + 1 > charactersList.size()){
                        setCharClassAndEmblem(this.charactersList.get(this.charactersList.size() - 1), Integer.toString(this.console), membershipID, jsonArray.getJSONObject(i).getString("characterId"));
                    } else {
                        setCharClassAndEmblem(this.charactersList.get(i), Integer.toString(this.console), membershipID, jsonArray.getJSONObject(i).getString("characterId"));
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public void setCharClassAndEmblem(Character character, String console, String membershipID, String characterId) {
        JSONObject json = null;
        try {
            json =  new AsyncTask<String, Void, JSONObject>() {

                 @Override
                 protected JSONObject doInBackground(String... params) {
                     try {
                         return new JSONObject(Jsoup.connect("http://www.bungie.net/Platform/Destiny/" + params[0] + "/Account/" + params[1] + "/Character/" + params[2] + "/").ignoreContentType(true).get().body().text());
                     } catch (JSONException e) {
                         e.printStackTrace();
                     } catch (IOException e) {
                         e.printStackTrace();
                     }
                     return null;
                 }

                @Override
                protected void onPostExecute(JSONObject jsonObject) {
                    super.onPostExecute(jsonObject);
                }
            }.execute(console, membershipID, characterId).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        if(json != null) {
            try {
                setCharacterClass(character, Integer.toString(json.getJSONObject("Response").getJSONObject("data").getJSONObject("characterBase").getInt("classType")));
                setCharacterEmblem(character, json.getJSONObject("Response").getJSONObject("data").getString("emblemPath"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public void setCharacterClass(Character character, String charClass) {
        character.setCharClass(charClass);
    }

    public void setCharacterEmblem(Character character, String emblem) {
        character.setEmblem(emblem);
    }

    public ArrayList<Character> getCharacters() {
        return charactersList;
    }

    public String getMembershipID() {
        return membershipID;
    }

    public int getConsole() {
        return console;
    }

    public String getName() {
        return name;
    }

    public Stats getCumulativePveStats() {
        return cumulativePveStats;
    }

    public void setCumulativePveStats(Stats cumulativePveStats) {
        this.cumulativePveStats = cumulativePveStats;
    }

    public Stats getCumulativePvpStats() {
        return cumulativePvpStats;
    }

    public void setCumulativePvpStats(Stats cumulativePvpStats) {
        this.cumulativePvpStats = cumulativePvpStats;
    }

}

