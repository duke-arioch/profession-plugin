package org.sandcast.profession.service.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.sandcast.profession.plugin.action.Skill;

@Entity(name = "player")
@Table(name = "profession_player")
public class SkilledPlayer implements Serializable {

    @OneToMany(cascade = CascadeType.ALL)
    Set<Experience> experience = new HashSet<>();
    @Id
    private String playerName;

    public SkilledPlayer() {
    }

    public SkilledPlayer(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void addExperience(Experience experience) {
        this.experience.add(experience);
    }

    public Set<Experience> getExperience() {
        return experience;
    }

    public void setExperience(Set<Experience> experience) {
        this.experience = experience;
    }
}
