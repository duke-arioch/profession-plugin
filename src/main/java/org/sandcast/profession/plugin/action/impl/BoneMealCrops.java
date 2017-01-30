package org.sandcast.profession.plugin.action.impl;

import static org.bukkit.CropState.*;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.sandcast.profession.service.model.Experience;
import org.sandcast.profession.service.model.SkilledPlayer;
import org.sandcast.profession.plugin.action.AbstractSkill;
import static org.sandcast.profession.plugin.action.BlockSets.*;
import static org.sandcast.profession.plugin.util.BlockUtil.*;
import org.sandcast.profession.service.controller.SkillController;

public class BoneMealCrops<T> extends AbstractSkill<T> {

    public BoneMealCrops(String id, String description, int priority) {
        super(id, description, priority);
    }

    @Override
    public boolean appliesTo(T event) {
        final boolean retval;
        if (!(event instanceof PlayerInteractEvent)) {
            retval = false;
        } else {
            PlayerInteractEvent playerInteractEvent = (PlayerInteractEvent) event;
            ItemStack sourceDetails = playerInteractEvent.getItem();
            Block targetBlock = playerInteractEvent.getClickedBlock();
            Material target = targetBlock == null ? null : targetBlock.getType();
            retval = rightClick(event) && BONEMEAL.isSimilar(sourceDetails) && GROWABLE_CROPS.contains(target) && RIPE != cropState(targetBlock).orElse(RIPE);
        }
        return retval;
    }

    @Override
    public void apply(T event, SkilledPlayer player) {
        final boolean success;
        Experience e = SkillController.retrieveExperience(player, this);
        if (e == null) {
            success = false;
            ((PlayerInteractEvent) event).getPlayer().sendMessage(getRequiresMessage());
        } else {
            int rating = e.getRating();
            double successChance = rating / 100D;
            success = Math.random() < successChance;
            int levelIncrease = (success && (Math.random() >= successChance)) ? 1 : 0;
            SkillController.improveExperience(player, this, levelIncrease, 1, success ? 1 : 0);
            if (!success) {
                ((PlayerInteractEvent) event).getPlayer().sendMessage(getFailureMessage());
            }
            if (levelIncrease > 0) {
                ((PlayerInteractEvent) event).getPlayer().sendMessage(getIncreaseMessage(e.getRating()));
                ((PlayerInteractEvent) event).getPlayer().getWorld().dropItem(((PlayerInteractEvent) event).getClickedBlock().getLocation(), new ItemStack(Material.WHEAT));
            }
        } //here we need a better way to organize the standard effects - does it reduce the item, drop exp etc...
        if (!success) {
            ((PlayerInteractEvent) event).setCancelled(true);
            int amount = Math.max(0, ((PlayerInteractEvent) event).getItem().getAmount() - 1);
            ((PlayerInteractEvent) event).getItem().setAmount(amount);
        } else {
            ((ExperienceOrb) ((PlayerInteractEvent) event).getPlayer().getWorld().spawn(((PlayerInteractEvent) event).getClickedBlock().getLocation(), ExperienceOrb.class)).setExperience(10);
        }
    }
}
