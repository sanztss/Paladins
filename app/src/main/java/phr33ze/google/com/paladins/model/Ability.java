package phr33ze.google.com.paladins.model;

/**
 * Created by Des. Android on 11/09/2017.
 */

public class Ability {
    private String abilityName;
    private int id;
    private String abilityDescription;
    private String abilityIcon;
    private String abilityNumber;

    public Ability() {
    }

    public Ability(String abilityName, int id, String abilityDescription, String abilityIcon, String abilityNumber) {
        this.abilityName = abilityName;
        this.id = id;
        this.abilityDescription = abilityDescription;
        this.abilityIcon = abilityIcon;
        this.abilityNumber = abilityNumber;
    }

    public String getAbilityName() {
        return abilityName;
    }

    public void setAbilityName(String abilityName) {
        this.abilityName = abilityName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAbilityDescription() {
        return abilityDescription;
    }

    public void setAbilityDescription(String abilityDescription) {
        this.abilityDescription = abilityDescription;
    }

    public String getAbilityIcon() {
        return abilityIcon;
    }

    public void setAbilityIcon(String abilityIcon) {
        this.abilityIcon = abilityIcon;
    }

    public String getAbilityNumber() {
        return abilityNumber;
    }

    public void setAbilityNumber(String abilityNumber) {
        this.abilityNumber = abilityNumber;
    }
}
