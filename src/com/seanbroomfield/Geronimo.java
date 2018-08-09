package com.seanbroomfield;

import android.os.AsyncTask;
import org.jsoup.Jsoup;
import org.json.*;
import java.io.IOException;
import java.lang.*;
import java.util.concurrent.ExecutionException;

public class Geronimo {

    public static JSONArray obtainCharacters(JSONObject json) {
        try {
            return json.getJSONObject("Response").getJSONArray("characters");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static JSONObject downloadStats (Player player) {
        JSONObject json = null;
        try {
            json = new AsyncTask<Player, Void, JSONObject>() {
                @Override
                protected JSONObject doInBackground(Player... params) {
                    try {
                        return new JSONObject(Jsoup.connect("http://www.bungie.net/Platform/Destiny/Stats/Account/" + params[0].getConsole() + "/" + params[0].getMembershipID() + "/").ignoreContentType(true).get().body().text());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return null;
                }
                protected void onPostExecute(JSONObject jsonObject) {
                    super.onPostExecute(jsonObject);
                }

            }.execute(player).get();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return json;
    }

    public static void setPlayerStats(Player player, JSONObject json) {
        try {
            player.setCumulativePveStats(new Stats(json.getJSONObject("Response").getJSONObject("mergedAllCharacters").getJSONObject("results").getJSONObject("allPvE").getJSONObject("allTime"), true));
            player.setCumulativePvpStats(new Stats(json.getJSONObject("Response").getJSONObject("mergedAllCharacters").getJSONObject("results").getJSONObject("allPvP").getJSONObject("allTime"), false));
            //Character character = new Character(jsonArray.getJSONObject(0));
            player.fillCharacters(json.getJSONObject("Response").getJSONArray("characters"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static JSONObject searchForPlayer (String console, String gamerTag) {
        JSONObject json = null;
        try {
            json = new AsyncTask<String, Void, JSONObject>() {
                     @Override
                     protected JSONObject doInBackground(String... params) {
                         try {
                            return new JSONObject(Jsoup.connect("http://www.bungie.net/Platform/Destiny/SearchDestinyPlayer/" + params[0] + "/" + params[1] + "/").ignoreContentType(true).get().body().text());
                         } catch (JSONException e) {
                             e.printStackTrace();
                         } catch (IOException e) {
                             e.printStackTrace();
                         }
                         return null;
                     }

                     protected void onPostExecute(JSONObject json) {
                         super.onPostExecute(json);
                     }
             }.execute(console, gamerTag).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return json;
    }

    public static Player createPlayer(JSONArray jsonArray) {
        Player player = null;
        try {
            player = new AsyncTask<JSONArray, Void, Player>() {
                 @Override
                 protected Player doInBackground(JSONArray... params) {
                     if(params[0].length() > 0) {
                         try {
                             return new Player(params[0].getJSONObject(0).getInt("membershipType"), params[0].getJSONObject(0).getString("membershipId"), params[0].getJSONObject(0).getString("displayName"));
                         } catch (JSONException e) {
                             e.printStackTrace();
                         }
                     }
                     return null;
                 }

                 protected void onPostExecute(Player player) {
                     super.onPostExecute(player);
                 }
             }.execute(jsonArray).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return player;
    }
}