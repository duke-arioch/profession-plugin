package org.sandcast.profession.plugin.action;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.sandcast.profession.plugin.ProfessionPlugin;

abstract public class AbstractSkill<T> implements Skill<T> {

    protected String id;
    protected String description;
    protected int priority;
    public static final ItemStack BONEMEAL = new ItemStack(Material.INK_SACK, 1, (short) 15);

    public AbstractSkill(String id, String description, int priority) {
        this.id = id;
        this.description = description;
        this.priority = priority;
    }

    public String getRequiresMessage() {
        return ProfessionPlugin.MESSAGE_PREFIX + "Action requires " + ChatColor.WHITE + ChatColor.BOLD + "[" + getDescription() + "]";
    }

    public String getFailureMessage() {
        return ProfessionPlugin.MESSAGE_PREFIX + " failed " + ChatColor.WHITE + ChatColor.BOLD + "[" + getDescription() + "] due to skill check.";
    }

    public String getIncreaseMessage(int newSkill) {
        return ProfessionPlugin.MESSAGE_PREFIX + "Skill Rating for " + ChatColor.WHITE + ChatColor.BOLD + "[" + getDescription() + "] increased to " + newSkill;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public int getPriority() {
        return priority;
    }
}
