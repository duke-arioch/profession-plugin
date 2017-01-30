package org.sandcast.profession.plugin.listener;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.sandcast.profession.service.controller.SkillController;

public class ProfessionPlayerListener implements Listener {

    private final SkillController controller;
    ConsoleCommandSender console;

    public ProfessionPlayerListener(SkillController controller) {
        this.controller = controller;
        console = Bukkit.getServer().getConsoleSender();
    }

    @EventHandler
    public void onPlayerInteractEvent(PlayerInteractEvent event) {
        controller.applySkills(event, event.getPlayer().getName());
    }

    @EventHandler
    public void onPlayerInteractEntityEvent(PlayerInteractEntityEvent event) {
//        console.sendMessage("PIEE:" + event.getRightClicked() + ":" + event.getHand());
    }

    @EventHandler
    public void onPlayerInteractAtEntityEvent(PlayerInteractAtEntityEvent event) {
//        console.sendMessage("PIAEE:" + event.getRightClicked() + ":" + event.getHand());
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        console.sendMessage(event.getPlayer().getName() + " joined the server! :D");
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        console.sendMessage(event.getPlayer().getName() + " left the server! :'(");
    }
}
