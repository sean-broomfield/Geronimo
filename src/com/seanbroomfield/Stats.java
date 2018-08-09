package com.seanbroomfield;

import android.os.Parcel;
import android.os.Parcelable;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;

/**
 * Created by seanbroomfield on 8/17/15.
 */
public class Stats implements Parcelable {

    /* JSON for values */
    JSONObject json;

    private int weaponKillsSuper;
    private int weaponKillsMelee;
    private int weaponKillsGrenade;
    private int weaponKillsAutoRifle;
    private int weaponKillsFusionRifle;
    private int weaponKillsHandCannon;
    private int weaponKillsMachinegun;
    private int weaponKillsPulseRifle;
    private int weaponKillsRocketLauncher;
    private int weaponKillsScoutRifle;
    private int weaponKillsShotgun;
    private int weaponKillsSniper;
    private int weaponKillsSubMachinegun;
    private int weaponKillsRelic;
    private int weaponKillsSideArm;
    private int assists;
    private int kills;
    private int secondsPlayed;
    private int deaths;
    private int bestSingleGameKills;
    private int precisionKills;
    private int resurrectionsPerformed;
    private int resurrectionsReceived;
    private int suicides;
    private int longestKillSpree;
    private int longestSingleLife;
    private int mostPrecisionKills;
    private int orbsDropped;
    private int orbsGathered;
    private int publicEventsCompleted;      //PVE ONLY
    private int publicEventsJoined;         //PVE ONLY

    /* Start of PVP Only */

    private int gamesPlayed = 0;
    private int gamesWon = 0;
    private int score = 0;
    private int bestSingleGameScore = 0;
    private int defensiveKills = 0;
    private int offensiveKills = 0;
    private int zonesCaptured = 0;
    private int zonesNeutralized = 0;

    /* End of PVP Only */

    public Stats(JSONObject json, boolean value) {
        this.json = json;
        if(value)
            initializeValuesPVE();
        else
            initializeValuesPVP();
    }

    public int valueFromJSON(String data) {
        try {
            return json.getJSONObject(data).getJSONObject("basic").getInt("value");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public void initializeValuesPVE() {
        this.weaponKillsAutoRifle = valueFromJSON("weaponKillsAutoRifle");
        this.weaponKillsMelee = valueFromJSON("weaponKillsMelee");
        this.weaponKillsGrenade = valueFromJSON("weaponKillsGrenade");
        this.weaponKillsFusionRifle = valueFromJSON("weaponKillsFusionRifle");
        this.weaponKillsHandCannon = valueFromJSON("weaponKillsHandCannon");
        this.weaponKillsMachinegun = valueFromJSON("weaponKillsMachinegun");
        this.weaponKillsPulseRifle = valueFromJSON("weaponKillsPulseRifle");
        this.weaponKillsRocketLauncher = valueFromJSON("weaponKillsRocketLauncher");
        this.weaponKillsScoutRifle = valueFromJSON("weaponKillsScoutRifle");
        this.weaponKillsShotgun = valueFromJSON("weaponKillsShotgun");
        this.weaponKillsSniper = valueFromJSON("weaponKillsSniper");
        this.weaponKillsSubMachinegun = valueFromJSON("weaponKillsSubmachinegun");
        this.weaponKillsRelic = valueFromJSON("weaponKillsRelic");
        this.weaponKillsSideArm = valueFromJSON("weaponKillsSideArm");
        this.assists = valueFromJSON("assists");
        this.kills = valueFromJSON("kills");
        this.secondsPlayed = valueFromJSON("secondsPlayed");
        this.deaths = valueFromJSON("deaths");
        this.bestSingleGameKills = valueFromJSON("bestSingleGameKills");
        this.precisionKills = valueFromJSON("precisionKills");
        this.resurrectionsPerformed = valueFromJSON("resurrectionsPerformed");
        this.resurrectionsReceived = valueFromJSON("resurrectionsReceived");
        this.suicides = valueFromJSON("suicides");
        this.longestKillSpree = valueFromJSON("longestKillSpree");
        this.longestSingleLife = valueFromJSON("longestSingleLife");
        this.mostPrecisionKills = valueFromJSON("mostPrecisionKills");
        this.orbsDropped = valueFromJSON("orbsDropped");
        this.orbsGathered = valueFromJSON("orbsGathered");
        this.publicEventsCompleted = valueFromJSON("publicEventsCompleted");
        this.publicEventsJoined = valueFromJSON("publicEventsJoined");
    }

    public void initializeValuesPVP() {
        this.weaponKillsAutoRifle = valueFromJSON("weaponKillsAutoRifle");
        this.weaponKillsMelee = valueFromJSON("weaponKillsMelee");
        this.weaponKillsGrenade = valueFromJSON("weaponKillsGrenade");
        this.weaponKillsFusionRifle = valueFromJSON("weaponKillsFusionRifle");
        this.weaponKillsHandCannon = valueFromJSON("weaponKillsHandCannon");
        this.weaponKillsMachinegun = valueFromJSON("weaponKillsMachinegun");
        this.weaponKillsPulseRifle = valueFromJSON("weaponKillsPulseRifle");
        this.weaponKillsRocketLauncher = valueFromJSON("weaponKillsRocketLauncher");
        this.weaponKillsScoutRifle = valueFromJSON("weaponKillsScoutRifle");
        this.weaponKillsShotgun = valueFromJSON("weaponKillsShotgun");
        this.weaponKillsSniper = valueFromJSON("weaponKillsSniper");
        this.weaponKillsSubMachinegun = valueFromJSON("weaponKillsSubmachinegun");
        this.weaponKillsRelic = valueFromJSON("weaponKillsRelic");
        this.weaponKillsSideArm = valueFromJSON("weaponKillsSideArm");
        this.assists = valueFromJSON("assists");
        this.kills = valueFromJSON("kills");
        this.secondsPlayed = valueFromJSON("secondsPlayed");
        this.deaths = valueFromJSON("deaths");
        this.bestSingleGameKills = valueFromJSON("bestSingleGameKills");
        this.precisionKills = valueFromJSON("precisionKills");
        this.resurrectionsPerformed = valueFromJSON("resurrectionsPerformed");
        this.resurrectionsReceived = valueFromJSON("resurrectionsReceived");
        this.suicides = valueFromJSON("suicides");
        this.longestKillSpree = valueFromJSON("longestKillSpree");
        this.longestSingleLife = valueFromJSON("longestSingleLife");
        this.mostPrecisionKills = valueFromJSON("mostPrecisionKills");
        this.orbsDropped = valueFromJSON("orbsDropped");
        this.orbsGathered = valueFromJSON("orbsGathered");

       /* PVP */

        //this.gamesPlayed = valueFromJSON("gamesPlayed");
        //this.gamesWon = valueFromJSON("gamesWon");
        this.score = valueFromJSON("score");
        this.bestSingleGameScore = valueFromJSON("bestSingleGameScore");
        this.defensiveKills = valueFromJSON("defensiveKills");
        this.offensiveKills = valueFromJSON("offensiveKills");
        this.zonesCaptured = valueFromJSON("zonesCaptured");
        this.zonesNeutralized = valueFromJSON("zonesNeutralized");

       /* End of PVP */
    }

    public Stats(Stats other) {
        this.weaponKillsSuper = other.weaponKillsSuper;
        this.weaponKillsMelee = other.weaponKillsMelee;
        this.weaponKillsGrenade = other.weaponKillsGrenade;
        this.weaponKillsAutoRifle = other.weaponKillsAutoRifle;
        this.weaponKillsFusionRifle = other.weaponKillsFusionRifle;
        this.weaponKillsHandCannon = other.weaponKillsHandCannon;
        this.weaponKillsMachinegun = other.weaponKillsMachinegun;
        this.weaponKillsPulseRifle = other.weaponKillsPulseRifle;
        this.weaponKillsRocketLauncher = other.weaponKillsRocketLauncher;
        this.weaponKillsScoutRifle = other.weaponKillsScoutRifle;
        this.weaponKillsShotgun = other.weaponKillsShotgun;
        this.weaponKillsSniper = other.weaponKillsSniper;
        this.weaponKillsSubMachinegun = other.weaponKillsSubMachinegun;
        this.weaponKillsRelic = other.weaponKillsRelic;
        this.weaponKillsSideArm = other.weaponKillsSideArm;
        this.assists = other.assists;
        this.kills = other.kills;
        this.secondsPlayed = other.secondsPlayed;
        this.deaths = other.deaths;
        this.bestSingleGameKills = other.bestSingleGameKills;
        this.precisionKills = other.precisionKills;
        this.resurrectionsPerformed = other.resurrectionsPerformed;
        this.resurrectionsReceived = other.resurrectionsReceived;
        this.suicides = other.suicides;
        this.longestKillSpree = other.longestKillSpree;
        this.longestSingleLife = other.longestSingleLife;
        this.mostPrecisionKills = other.mostPrecisionKills;
        this.orbsDropped = other.orbsDropped;
        this.orbsGathered = other.orbsGathered;
        this.publicEventsCompleted = other.publicEventsCompleted;
        this.publicEventsJoined = other.publicEventsJoined;
        this.gamesPlayed = other.gamesPlayed;
        this.gamesWon = other.gamesWon;
        this.score = other.score;
        this.bestSingleGameScore = other.bestSingleGameScore;
        this.defensiveKills = other.defensiveKills;
        this.offensiveKills = other.offensiveKills;
        this.zonesCaptured = other.zonesCaptured;
        this.zonesNeutralized = other.zonesNeutralized;
    }

    public LinkedHashMap<String, Integer> asList() {
        LinkedHashMap<String, Integer> linkedHashMap = new LinkedHashMap<String, Integer>();

        linkedHashMap.put("kills", getKills());
        linkedHashMap.put("deaths", getDeaths());
        linkedHashMap.put("assists", getAssists());
        linkedHashMap.put("secondsPlayed", getSecondsPlayed());
        linkedHashMap.put("score", getScore());
        linkedHashMap.put("bestSingleGameKills", getBestSingleGameKills());
        linkedHashMap.put("resurrectionsPerformed", getResurrectionsPerformed());
        linkedHashMap.put("resurrectionsReceived", getResurrectionsReceived());
        linkedHashMap.put("suicides", getSuicides());
        linkedHashMap.put("longestKillSpree", getLongestKillSpree());
        linkedHashMap.put("longestSingleLife", getLongestSingleLife());
        linkedHashMap.put("precisionKills", getPrecisionKills());
        linkedHashMap.put("mostPrecisionKills", getMostPrecisionKills());
        linkedHashMap.put("orbsDropped", getOrbsDropped());
        linkedHashMap.put("orbsGathered", getOrbsGathered());
        linkedHashMap.put("publicEventsCompleted", getPublicEventsCompleted());
        linkedHashMap.put("publicEventsJoined", getPublicEventsJoined());
        linkedHashMap.put("gamesPlayed", getGamesPlayed());
        linkedHashMap.put("gamesWon", getGamesWon());
        linkedHashMap.put("bestSingleGameScore", getBestSingleGameScore());
        linkedHashMap.put("defensiveKills", getDefensiveKills());
        linkedHashMap.put("offensiveKills", getOffensiveKills());
        linkedHashMap.put("zonesCaptured", getZonesCaptured());
        linkedHashMap.put("zonesNeutralized", getZonesNeutralized());
        linkedHashMap.put("weaponKillsSuper", getWeaponKillsSuper());
        linkedHashMap.put("weaponKillsMelee", getWeaponKillsMelee());
        linkedHashMap.put("weaponKillsGrenade", getWeaponKillsGrenade());
        linkedHashMap.put("weaponKillsAutoRifle", getWeaponKillAutoRifle());
        linkedHashMap.put("weaponKillsFusionRifle", getWeaponKillsFusionRifle());
        linkedHashMap.put("weaponKillsHandCannon", getWeaponKillsHandCannon());
        linkedHashMap.put("weaponKillsMachinegun", getWeaponKillsMachinegun());
        linkedHashMap.put("weaponKillsPulseRifle", getWeaponKillsPulseRile());
        linkedHashMap.put("weaponKillsRocketLauncher", getWeaponKillsRocketLauncher());
        linkedHashMap.put("weaponKillsScoutRifle", getWeaponKillsScoutRifle());
        linkedHashMap.put("weaponKillsShotgun", getWeaponKillsShotgun());
        linkedHashMap.put("weaponKillsSniper", getWeaponKillsSniper());
        linkedHashMap.put("weaponKillsSubMachinegun", getWeaponKillsSubMachinegun());
        linkedHashMap.put("weaponKillsRelic", getWeaponKillsRelic());
        linkedHashMap.put("weaponKillsSideArm", getWeaponKillsSideArm());

        return linkedHashMap;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(kills);
        dest.writeInt(deaths);
        dest.writeInt(assists);
        dest.writeInt(secondsPlayed);
        dest.writeInt(score);
        dest.writeInt(bestSingleGameKills);
        dest.writeInt(resurrectionsPerformed);
        dest.writeInt(resurrectionsReceived);
        dest.writeInt(suicides);
        dest.writeInt(longestKillSpree);
        dest.writeInt(longestSingleLife);
        dest.writeInt(precisionKills);
        dest.writeInt(mostPrecisionKills);
        dest.writeInt(orbsDropped);
        dest.writeInt(orbsGathered);
        dest.writeInt(publicEventsCompleted);
        dest.writeInt(publicEventsJoined);
        dest.writeInt(gamesPlayed);
        dest.writeInt(gamesWon);
        dest.writeInt(bestSingleGameScore);
        dest.writeInt(defensiveKills);
        dest.writeInt(offensiveKills);
        dest.writeInt(zonesCaptured);
        dest.writeInt(zonesNeutralized);
        dest.writeInt(weaponKillsSuper);
        dest.writeInt(weaponKillsMelee);
        dest.writeInt(weaponKillsGrenade);
        dest.writeInt(weaponKillsAutoRifle);
        dest.writeInt(weaponKillsFusionRifle);
        dest.writeInt(weaponKillsHandCannon);
        dest.writeInt(weaponKillsMachinegun);
        dest.writeInt(weaponKillsPulseRifle);
        dest.writeInt(weaponKillsRocketLauncher);
        dest.writeInt(weaponKillsScoutRifle);
        dest.writeInt(weaponKillsShotgun);
        dest.writeInt(weaponKillsSniper);
        dest.writeInt(weaponKillsSubMachinegun);
        dest.writeInt(weaponKillsRelic);
        dest.writeInt(weaponKillsSideArm);
    }

    public void setWeaponKillsSuper(int weaponKillsSuper) {
        this.weaponKillsSuper = weaponKillsSuper;
    }

    public void setWeaponKillsMelee(int weaponKillsMelee) {
        this.weaponKillsMelee = weaponKillsMelee;
    }

    public void setWeaponKillsGrenade(int weaponKillsGrenade) {
        this.weaponKillsGrenade = weaponKillsGrenade;
    }

    public void setWeaponKillAutoRifle(int weaponKillAutoRifle) {
        this.weaponKillsAutoRifle = weaponKillAutoRifle;
    }

    public void setWeaponKillsFusionRifle(int weaponKillsFusionRifle) {
        this.weaponKillsFusionRifle = weaponKillsFusionRifle;
    }

    public void setWeaponKillsHandCannon(int weaponKillsHandCannon) {
        this.weaponKillsHandCannon = weaponKillsHandCannon;
    }

    public void setWeaponKillsMachinegun(int weaponKillsMachinegun) {
        this.weaponKillsMachinegun = weaponKillsMachinegun;
    }

    public void setWeaponKillsPulseRifle(int weaponKillsPulseRifle) {
        this.weaponKillsPulseRifle = weaponKillsPulseRifle;
    }

    public void setWeaponKillsRocketLauncher(int weaponKillsRocketLauncher) {
        this.weaponKillsRocketLauncher = weaponKillsRocketLauncher;
    }

    public void setWeaponKillsScoutRifle(int weaponKillsScoutRifle) {
        this.weaponKillsScoutRifle = weaponKillsScoutRifle;
    }

    public void setWeaponKillsShotgun(int weaponKillsShotgun) {
        this.weaponKillsShotgun = weaponKillsShotgun;
    }

    public void setWeaponKillsSniper(int weaponKillsSniper) {
        this.weaponKillsSniper = weaponKillsSniper;
    }

    public void setWeaponKillsSubMachinegun(int weaponKillsSubMachinegun) {
        this.weaponKillsSubMachinegun = weaponKillsSubMachinegun;
    }

    public void setWeaponKillsRelic(int weaponKillsRelic) {
        this.weaponKillsRelic = weaponKillsRelic;
    }

    public void setWeaponKillsSideArm(int weaponKillsSideArm) {
        this.weaponKillsSideArm = weaponKillsSideArm;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public void setSecondsPlayed(int secondsPlayed) {
        this.secondsPlayed = secondsPlayed;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public void setBestSingleGameKills(int bestSingleGameKills) {
        this.bestSingleGameKills = bestSingleGameKills;
    }

    public void setPrecisionKills(int precisionKills) {
        this.precisionKills = precisionKills;
    }

    public void setResurrectionsPerformed(int resurrectionsPerformed) {
        this.resurrectionsPerformed = resurrectionsPerformed;
    }

    public void setResurrectionsReceived(int resurrectionsReceived) {
        this.resurrectionsReceived = resurrectionsReceived;
    }

    public void setSuicides(int suicides) {
        this.suicides = suicides;
    }

    public void setLongestKillSpree(int longestKillSpree) {
        this.longestKillSpree = longestKillSpree;
    }

    public void setLongestSingleLife(int longestSingleLife) {
        this.longestSingleLife = longestSingleLife;
    }

    public void setMostPrecisionKills(int mostPrecisionKills) {
        this.mostPrecisionKills = mostPrecisionKills;
    }

    public void setOrbsDropped(int orbsDropped) {
        this.orbsDropped = orbsDropped;
    }

    public void setOrbsGathered(int orbsGathered) {
        this.orbsGathered = orbsGathered;
    }

    public void setPublicEventsCompleted(int publicEventsCompleted) {
        this.publicEventsCompleted = publicEventsCompleted;
    }

    public void setPublicEventsJoined(int publicEventsJoined) {
        this.publicEventsJoined = publicEventsJoined;
    }

    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    public void setGamesWon(int gamesWon) {
        this.gamesWon = gamesWon;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setBestSingleGameScore(int bestSingleGameScore) {
        this.bestSingleGameScore = bestSingleGameScore;
    }

    public void setDefensiveKills(int defensiveKills) {
        this.defensiveKills = defensiveKills;
    }

    public void setOffensiveKills(int offensiveKills) {
        this.offensiveKills = offensiveKills;
    }

    public void setZonesCaptured(int zonesCaptured) {
        this.zonesCaptured = zonesCaptured;
    }

    public void setZonesNeutralized(int zonesNeutralized) {
        this.zonesNeutralized = zonesNeutralized;
    }

    public int getWeaponKillsSuper() {
        return weaponKillsSuper;
    }

    public int getWeaponKillsMelee() {
        return weaponKillsMelee;
    }

    public int getWeaponKillsGrenade() {
        return weaponKillsGrenade;
    }

    public int getAssists() {
        return assists;
    }

    public int getKills() {
        return kills;
    }

    public int getSecondsPlayed() {
        return secondsPlayed;
    }

    public int getDeaths() {
        return deaths;
    }

    public int getBestSingleGameKills() {
        return bestSingleGameKills;
    }

    public int getPrecisionKills() {
        return precisionKills;
    }

    public int getResurrectionsPerformed() {
        return resurrectionsPerformed;
    }

    public int getResurrectionsReceived() {
        return resurrectionsReceived;
    }

    public int getSuicides() {
        return suicides;
    }

    public int getWeaponKillAutoRifle() {
        return weaponKillsAutoRifle;
    }

    public int getWeaponKillsFusionRifle() {
        return weaponKillsFusionRifle;
    }

    public int getWeaponKillsHandCannon() {
        return weaponKillsHandCannon;
    }

    public int getWeaponKillsMachinegun() {
        return weaponKillsMachinegun;
    }

    public int getWeaponKillsPulseRile() {
        return weaponKillsPulseRifle;
    }

    public int getWeaponKillsRocketLauncher() {
        return weaponKillsRocketLauncher;
    }

    public int getWeaponKillsScoutRifle() {
        return weaponKillsScoutRifle;
    }

    public int getWeaponKillsShotgun() {
        return weaponKillsShotgun;
    }

    public int getWeaponKillsSniper() {
        return weaponKillsSniper;
    }

    public int getWeaponKillsSubMachinegun() {
        return weaponKillsSubMachinegun;
    }

    public int getWeaponKillsRelic() {
        return weaponKillsRelic;
    }

    public int getWeaponKillsSideArm() {
        return weaponKillsSideArm;
    }

    public int getLongestKillSpree() {
        return longestKillSpree;
    }

    public int getLongestSingleLife() {
        return longestSingleLife;
    }

    public int getMostPrecisionKills() {
        return mostPrecisionKills;
    }

    public int getOrbsDropped() {
        return orbsDropped;
    }

    public int getOrbsGathered() {
        return orbsGathered;
    }

    public int getPublicEventsCompleted() {
        return publicEventsCompleted;
    }

    public int getPublicEventsJoined() {
        return publicEventsJoined;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public int getGamesWon() {
        return gamesWon;
    }

    public int getScore() {
        return score;
    }

    public int getBestSingleGameScore() {
        return bestSingleGameScore;
    }

    public int getDefensiveKills() {
        return defensiveKills;
    }

    public int getOffensiveKills() {
        return offensiveKills;
    }

    public int getZonesCaptured() {
        return zonesCaptured;
    }

    public int getZonesNeutralized() {
        return zonesNeutralized;
    }


}
