package phr33ze.google.com.paladins.model;

/**
 * Created by Des. Android on 26/09/2017.
 */

public class ProBuild {
    private String teamLogo;
    private String playerName;
    private String legendary;
    private String card1;
    private String card2;
    private String card3;
    private String card4;
    private String card5;
    private int kills;
    private int deaths;
    private int assists;


    public ProBuild() {
    }

    public ProBuild(String teamLogo, String playerName, String legendary, String card1, String card2, String card3, String card4, String card5, int kills, int deaths, int assists) {
        this.teamLogo = teamLogo;
        this.playerName = playerName;
        this.legendary = legendary;
        this.card1 = card1;
        this.card2 = card2;
        this.card3 = card3;
        this.card4 = card4;
        this.card5 = card5;
        this.kills = kills;
        this.deaths = deaths;
        this.assists = assists;
    }

    public String getTeamLogo() {
        return teamLogo;
    }

    public void setTeamLogo(String teamLogo) {
        this.teamLogo = teamLogo;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getLegendary() {
        return legendary;
    }

    public void setLegendary(String legendary) {
        this.legendary = legendary;
    }

    public String getCard1() {
        return card1;
    }

    public void setCard1(String card1) {
        this.card1 = card1;
    }

    public String getCard2() {
        return card2;
    }

    public void setCard2(String card2) {
        this.card2 = card2;
    }

    public String getCard3() {
        return card3;
    }

    public void setCard3(String card3) {
        this.card3 = card3;
    }

    public String getCard4() {
        return card4;
    }

    public void setCard4(String card4) {
        this.card4 = card4;
    }

    public String getCard5() {
        return card5;
    }

    public void setCard5(String card5) {
        this.card5 = card5;
    }

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public int getAssists() {
        return assists;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }
}
