package org.sandcast.profession.plugin.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.sandcast.profession.service.controller.SkillController;
import org.sandcast.profession.service.model.SkilledPlayer;
import org.sandcast.profession.plugin.ProfessionPlugin;

public class ProfessionSkillCommand implements CommandExecutor {

    private final SkillController controller;
    ConsoleCommandSender console;

    public ProfessionSkillCommand(SkillController controller) {
        this.controller = controller;
        console = Bukkit.getServer().getConsoleSender();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] split) {
        if (sender instanceof Player) {
            console.sendMessage("registered skill command");
            Player player = (Player) sender;
            SkilledPlayer skilledPlayer = controller.getSkilledPlayer(player.getName());
            if (split.length > 0) {
                String first = split[0];
                player.sendMessage(String.join("/", split) + ":" + player.getName());
                if (first.equalsIgnoreCase("list")) {
                    skilledPlayer.getExperience().forEach((e) -> player.sendMessage(ProfessionPlugin.MESSAGE_PREFIX + e.getSkillName() + "[" + e.getRating() + "%:t=" + e.getTries() + ":s=" + e.getSuccesses() + "]"));
                }
                if (first.equalsIgnoreCase("add")) {
                    if (split.length > 1) {
                        player.sendMessage(ChatColor.AQUA + "" + ChatColor.BOLD + "[Skills] " + ChatColor.GOLD + "Added skill " + ChatColor.GREEN + "");
                    }
                }
            }
            player.getInventory().clear();
            player.getInventory().setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE));
            player.getInventory().setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS));
            player.getInventory().setHelmet(new ItemStack(Material.DIAMOND_HELMET));
            player.getInventory().setBoots(new ItemStack(Material.DIAMOND_BOOTS));
            ItemStack sword = new ItemStack(Material.DIAMOND_AXE);
            sword.addEnchantment(Enchantment.DAMAGE_ALL, 5);
            sword.addEnchantment(Enchantment.DIG_SPEED, 5);
            player.getInventory().setItemInMainHand(sword);
            ItemStack bonemeal = new ItemStack(Material.INK_SACK, 64, (short) 15);
            ItemStack hoe = new ItemStack(Material.WOOD_HOE);
            player.getInventory().addItem(bonemeal);
            player.getInventory().addItem(hoe);
            return true;
        } else {
            return false;
        }
    }
}
