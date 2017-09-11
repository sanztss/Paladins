package phr33ze.google.com.paladins.model;

import java.util.List;

/**
 * Created by Des. Android on 11/09/2017.
 */

public class Champion {
    private List<Ability> abilities;
    private String championCardURL;
    private String championIconURL;
    private String cons;
    private int health;
    private String lore;
    private String name;
    private String onFreeRotation;
    private String phanteon;
    private String pros;
    private String roles;
    private int speed;
    private String title;
    private String type;
    private int id;
    private String latestChampion;

    public Champion() {
    }

    public Champion(List<Ability> abilities, String championCardURL, String championIconURL, String cons, int health, String lore, String name, String onFreeRotation, String phanteon, String pros, String roles, int speed, String title, String type, int id, String latestChampion) {
        this.abilities = abilities;
        this.championCardURL = championCardURL;
        this.championIconURL = championIconURL;
        this.cons = cons;
        this.health = health;
        this.lore = lore;
        this.name = name;
        this.onFreeRotation = onFreeRotation;
        this.phanteon = phanteon;
        this.pros = pros;
        this.roles = roles;
        this.speed = speed;
        this.title = title;
        this.type = type;
        this.id = id;
        this.latestChampion = latestChampion;
    }

    public List<Ability> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<Ability> abilities) {
        this.abilities = abilities;
    }

    public String getChampionCardURL() {
        return championCardURL;
    }

    public void setChampionCardURL(String championCardURL) {
        this.championCardURL = championCardURL;
    }

    public String getChampionIconURL() {
        return championIconURL;
    }

    public void setChampionIconURL(String championIconURL) {
        this.championIconURL = championIconURL;
    }

    public String getCons() {
        return cons;
    }

    public void setCons(String cons) {
        this.cons = cons;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public String getLore() {
        return lore;
    }

    public void setLore(String lore) {
        this.lore = lore;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOnFreeRotation() {
        return onFreeRotation;
    }

    public void setOnFreeRotation(String onFreeRotation) {
        this.onFreeRotation = onFreeRotation;
    }

    public String getPhanteon() {
        return phanteon;
    }

    public void setPhanteon(String phanteon) {
        this.phanteon = phanteon;
    }

    public String getPros() {
        return pros;
    }

    public void setPros(String pros) {
        this.pros = pros;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLatestChampion() {
        return latestChampion;
    }

    public void setLatestChampion(String latestChampion) {
        this.latestChampion = latestChampion;
    }
}
