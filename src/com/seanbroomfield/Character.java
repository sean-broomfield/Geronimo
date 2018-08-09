package com.seanbroomfield;

import android.os.Parcel;
import android.os.Parcelable;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by seanbroomfield on 8/17/15.
 */
public class Character implements Parcelable{
    private Stats PVE;
    private Stats PVP;
    private String emblem;
    private String charId;
    private String charClass;

    public Character(JSONObject jsonObject) {
        try {
            this.PVE = new Stats(jsonObject.getJSONObject("results").getJSONObject("allPvE").getJSONObject("allTime"), true);
            this.PVP = new Stats(jsonObject.getJSONObject("results").getJSONObject("allPvP").getJSONObject("allTime"), false);
            this.charId = jsonObject.getString("characterId");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(emblem);
        dest.writeString(charId);
        dest.writeString(charClass);
        dest.writeParcelable(PVP, flags);
        dest.writeParcelable(PVE, flags);
    }

    public String getCharId() {
        return charId;
    }

    public void setCharId(String charId) {
        this.charId = charId;
    }

    public String getEmblem() {
        return emblem;
    }

    public void setEmblem(String emblem) {
        this.emblem = "http://www.bungie.net" + emblem;
    }

    public String getCharClass() {
        return charClass;
    }

    public void setCharClass(String charClass) {
        this.charClass = charClass;
    }

    public Stats getPVE() {
        return PVE;
    }

    public void setPVE(Stats PVE) {
        this.PVE = PVE;
    }

    public Stats getPVP() {
        return PVP;
    }

    public void setPVP(Stats PVP) {
        this.PVP = PVP;
    }

}
