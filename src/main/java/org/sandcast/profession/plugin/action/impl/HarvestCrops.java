package org.sandcast.profession.plugin.action.impl;

import org.bukkit.Material;
import org.bukkit.event.block.BlockBreakEvent;
import org.sandcast.profession.service.model.SkilledPlayer;
import org.sandcast.profession.plugin.action.AbstractSkill;
import org.sandcast.profession.plugin.action.BlockSets;

public class HarvestCrops<T> extends AbstractSkill<T> {

    public HarvestCrops(String id, String description, int priority) {
        super(id, description, priority);
    }

    @Override
    public boolean appliesTo(T event) {
        if (!(event instanceof BlockBreakEvent)) {
            return false;
        }
        BlockBreakEvent blockBreakEvent = (BlockBreakEvent) event;
        Material target = blockBreakEvent.getBlock() == null ? null : blockBreakEvent.getBlock().getType();
        return BlockSets.EDIBLE_CROPS.contains(target);
    }

    @Override
    public void apply(T event, SkilledPlayer player) {
        ((BlockBreakEvent) event).getPlayer().sendMessage(getRequiresMessage());
    }
}
