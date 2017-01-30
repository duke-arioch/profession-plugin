package org.sandcast.profession.service.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "experience")
@Table(name = "profession_player_experience")
public class Experience implements Serializable {

    @Id
    private int id;
    private String playerName;
    private String skillName;
    private int tries;
    private int successes;
    private int rating;

    public Experience() {
    }

    public Experience(String playerName, String skillName, int tries, int successes, int rating) {
        this.playerName = playerName;
        this.skillName = skillName;
        this.tries = tries;
        this.successes = successes;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public int getTries() {
        return tries;
    }

    public int getSuccesses() {
        return successes;
    }

    public int getRating() {
        return rating;
    }

    public void setTries(int tries) {
        this.tries = tries;
    }

    public void setSuccesses(int successes) {
        this.successes = successes;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return playerName;
    }
}
