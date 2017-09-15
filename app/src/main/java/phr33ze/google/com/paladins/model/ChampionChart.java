package phr33ze.google.com.paladins.model;

/**
 * Created by Des. Android on 15/09/2017.
 */

public class ChampionChart {
    private int championId;
    private int health;
    private int speed;
    private int damage;
    private int heal;
    private int control;

    public ChampionChart() {
    }

    public ChampionChart(int championId, int health, int speed, int damage, int heal, int control) {
        this.championId = championId;
        this.health = health;
        this.speed = speed;
        this.damage = damage;
        this.heal = heal;
        this.control = control;
    }

    public int getChampionId() {
        return championId;
    }

    public void setChampionId(int championId) {
        this.championId = championId;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getHeal() {
        return heal;
    }

    public void setHeal(int heal) {
        this.heal = heal;
    }

    public int getControl() {
        return control;
    }

    public void setControl(int control) {
        this.control = control;
    }
}
