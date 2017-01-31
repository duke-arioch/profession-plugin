package org.sandcast.profession.plugin.action.impl;

import org.bukkit.Material;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.sandcast.profession.service.model.SkilledPlayer;
import org.sandcast.profession.plugin.action.AbstractSkill;
import org.sandcast.profession.plugin.action.BlockSets;
import static org.sandcast.profession.plugin.util.BlockUtil.*;

public class BoneMealGround<T> extends AbstractSkill<T> {

    public BoneMealGround(String id, String description, int priority) {
        super(id, description, priority);
    }

    @Override
    public boolean appliesTo(T event) {
        if (!(event instanceof PlayerInteractEvent)) {
            return false;
        }
        PlayerInteractEvent playerInteractEvent = (PlayerInteractEvent) event;
        ItemStack sourceDetails = playerInteractEvent.getItem();
        Material target = playerInteractEvent.getClickedBlock() == null ? null : playerInteractEvent.getClickedBlock().getType();
        return (rightClick(event) && sourceDetails != null && sourceDetails.isSimilar(BONEMEAL) && BlockSets.TILLABLE.contains(target));
    }

    @Override
    public void apply(T event, SkilledPlayer player) {
        ((PlayerInteractEvent) event).getPlayer().sendMessage(getRequiresMessage());
    }
}
