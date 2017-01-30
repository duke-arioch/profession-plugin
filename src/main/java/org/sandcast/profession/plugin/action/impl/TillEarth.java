package org.sandcast.profession.plugin.action.impl;

import org.bukkit.Material;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.sandcast.profession.service.model.SkilledPlayer;
import org.sandcast.profession.plugin.action.AbstractSkill;
import org.sandcast.profession.plugin.action.BlockSets;

public class TillEarth<T> extends AbstractSkill<T> {

    public TillEarth(String id, String description, int priority) {
        super(id, description, priority);
    }

    @Override
    public boolean appliesTo(T event) {
        if (event instanceof PlayerInteractEvent) {
            PlayerInteractEvent playerInteractionEvent = (PlayerInteractEvent) event;
            boolean rightClick;
            Material source;
            Material target;
            rightClick = playerInteractionEvent.getAction().equals(Action.RIGHT_CLICK_BLOCK);
            source = playerInteractionEvent.getMaterial();
            target = playerInteractionEvent.getClickedBlock() == null ? null : playerInteractionEvent.getClickedBlock().getType();
            return (rightClick && BlockSets.HOES.contains(source) && BlockSets.TILLABLE.contains(target));
        } else {
            return false;
        }
    }

    @Override
    public void apply(T event, SkilledPlayer skilledPlayer) {
        ((PlayerInteractEvent) event).getPlayer().sendMessage(getRequiresMessage());
    }
}
