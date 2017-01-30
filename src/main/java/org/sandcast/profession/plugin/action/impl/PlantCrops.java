package org.sandcast.profession.plugin.action.impl;

import org.bukkit.Material;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.sandcast.profession.service.model.SkilledPlayer;
import org.sandcast.profession.plugin.action.AbstractSkill;
import org.sandcast.profession.plugin.action.BlockSets;

public class PlantCrops<T> extends AbstractSkill<T> {

    public PlantCrops(String id, String description, int priority) {
        super(id, description, priority);
    }

    @Override
    public boolean appliesTo(T event) {
        if (!(event instanceof PlayerInteractEvent)) {
            return false;
        }
        PlayerInteractEvent playerInteractEvent = (PlayerInteractEvent) event;
        boolean rightClick = playerInteractEvent.getAction().equals(Action.RIGHT_CLICK_BLOCK);
        Material source = playerInteractEvent.getItem() == null ? null : playerInteractEvent.getItem().getType();
        Material target = playerInteractEvent.getClickedBlock() == null ? null : playerInteractEvent.getClickedBlock().getType();
        return rightClick && BlockSets.PLANTABLE_CROPS.contains(source) && Material.SOIL.equals(target);
    }

    @Override
    public void apply(T event, SkilledPlayer player) {
        ((PlayerInteractEvent) event).getPlayer().sendMessage(getRequiresMessage());
    }
}
