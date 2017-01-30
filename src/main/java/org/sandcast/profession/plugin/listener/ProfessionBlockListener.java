package org.sandcast.profession.plugin.listener;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.TreeSpecies;
import org.bukkit.block.Block;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.sandcast.profession.service.controller.SkillController;
import org.sandcast.profession.plugin.util.BlockUtil;

public class ProfessionBlockListener implements Listener {

    ConsoleCommandSender console;
    SkillController controller;

    public ProfessionBlockListener(SkillController controller) {
        this.controller = controller;
        console = Bukkit.getServer().getConsoleSender();
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onBlockDamageEvent(BlockDamageEvent bbe) {
        console.sendMessage("block damage:" + bbe.getEventName() + ":" + bbe.getBlock() + ":" + bbe.getItemInHand() + ":" + bbe.getInstaBreak());
    }

    @EventHandler(priority = EventPriority.LOW)
    public void blockBreak(BlockBreakEvent bbe) {
        controller.applySkills(bbe, bbe.getPlayer().getName());
        Block block = bbe.getBlock();
        Material toBreak = block.getType();
        TreeSpecies ts = BlockUtil.getTreeSpecies(block);
        console.sendMessage("BREAK: " + bbe.toString() + ":" + toBreak.toString() + ":" + (ts == null ? "" : ts.toString()) + ":" + bbe.getPlayer().getItemInHand());
        if (ts != null && ts.equals(TreeSpecies.BIRCH)) {
            bbe.setExpToDrop(1000);
            ItemStack bonemeal = new ItemStack(Material.INK_SACK, 16, (short) 15);
            block.getWorld().dropItemNaturally(bbe.getBlock().getLocation(), bonemeal);
            ItemStack apples = new ItemStack(Material.APPLE, 16);
            block.getWorld().dropItemNaturally(bbe.getBlock().getLocation(), apples);
            ItemStack carrot = new ItemStack(Material.CARROT_ITEM, 16);
            block.getWorld().dropItemNaturally(bbe.getBlock().getLocation(), carrot);
            ItemStack potato = new ItemStack(Material.POTATO_ITEM, 16);
            block.getWorld().dropItemNaturally(bbe.getBlock().getLocation(), potato);
            ItemStack doors = new ItemStack(Material.ACACIA_DOOR_ITEM, 16);
            block.getWorld().dropItemNaturally(bbe.getBlock().getLocation(), doors);
            bbe.getBlock().getDrops().clear();
        }
    }
}
