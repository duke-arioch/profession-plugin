package org.sandcast.profession.service.controller;

import org.sandcast.profession.service.model.SkilledPlayer;
import org.sandcast.profession.service.model.Experience;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import static java.util.stream.Collectors.*;
import org.sandcast.profession.plugin.ProfessionPlugin;
import org.sandcast.profession.plugin.action.Skill;
import org.sandcast.profession.plugin.action.impl.BoneMealCrops;
import org.sandcast.profession.plugin.action.impl.BoneMealGround;
import org.sandcast.profession.plugin.action.impl.ChopLog;
import org.sandcast.profession.plugin.action.impl.HarvestCrops;
import org.sandcast.profession.plugin.action.impl.HarvestSeeds;
import org.sandcast.profession.plugin.action.impl.PlantCrops;
import org.sandcast.profession.plugin.action.impl.TillEarth;

public class SkillController<T> {

    Map<String, Skill<T>> skills;
    ProfessionPlugin plugin;

    public SkillController(ProfessionPlugin plugin) {
        this.plugin = plugin;
        Set<Skill<T>> tempSkills = new HashSet<>();
        tempSkills.add(new TillEarth("TILL_EARTH", "Create farmland", 0));
        tempSkills.add(new ChopLog("CUT_LOGS", "Cut Logs", 0));
        tempSkills.add(new BoneMealGround("GROW_GRASS", "Grow grass", 0));
        tempSkills.add(new BoneMealCrops("BONEMEAL_CROPS", "Grow crops with bonemeal", 0));
        tempSkills.add(new PlantCrops("PLANT_CROPS", "Plant crops", 0));
        tempSkills.add(new HarvestCrops("HARVEST_CROPS", "Harvest crops", 0));
        tempSkills.add(new HarvestSeeds("HARVEST_SEEDS", "Harvest seeds", 0));
        skills = tempSkills.stream().collect(toMap(Skill<T>::getId, Function.identity()));
    }

    public Skill<T> getSkill(String skillId) {
        return skills.get(skillId);
    }

    public void applySkills(T e, String playerName) {
        SkilledPlayer player = getSkilledPlayer(playerName);
        skills.values().stream()
                .filter(s -> s.appliesTo(e))
                .sorted((s1, s2) -> s1.getPriority() - s2.getPriority())
                .forEach(s -> s.apply(e, player));
        plugin.getDatabase().save(player);
    }

    public SkilledPlayer getSkilledPlayer(String player) {
        return plugin.getDatabase().find(SkilledPlayer.class).where().ieq("playerName", player).findUnique();
    }

    public static Experience retrieveExperience(SkilledPlayer player, Skill skill) {
        return player.getExperience().stream().filter(e -> e.getSkillName().equals(skill.getId())).findFirst().get();
    }

    public static void improveExperience(SkilledPlayer player, Skill skill, int rank, int tries, int successes) {
        player.getExperience().stream().filter(e -> e.getSkillName().equals(skill.getId())).forEach(e -> increaseStats(e, rank, tries, successes));
    }

    public static void increaseStats(Experience e, int rank, int tries, int successes) {
        e.setSuccesses(e.getSuccesses() + successes);
        e.setRating(e.getRating() + rank);
        e.setTries(e.getTries() + tries);
    }

    public SkilledPlayer putPlayer(String player) {
        SkilledPlayer current = getSkilledPlayer(player);
        if (current == null) {
            current = new SkilledPlayer(player);
            current.addExperience(new Experience(player, "BONEMEAL_CROPS", 0, 0, 10));
            plugin.getDatabase().save(current);
        }
        return current;
    }
}
