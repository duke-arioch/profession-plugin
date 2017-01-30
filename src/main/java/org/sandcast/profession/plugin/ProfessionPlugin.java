package org.sandcast.profession.plugin;

import java.util.ArrayList;
import org.sandcast.profession.plugin.listener.ProfessionPlayerListener;
import org.sandcast.profession.plugin.listener.ProfessionBlockListener;
import java.util.List;
import java.util.logging.Level;
import javax.persistence.PersistenceException;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.sandcast.profession.service.controller.SkillController;
import org.sandcast.profession.plugin.command.ProfessionSkillCommand;
import org.sandcast.profession.service.model.Experience;
import org.sandcast.profession.service.model.SkilledPlayer;

public class ProfessionPlugin extends JavaPlugin {

    private SkillController controller;
    private ProfessionPlayerListener playerListener;
    private ProfessionBlockListener blockListener;
    public static final String MESSAGE_PREFIX = ChatColor.AQUA + "" + ChatColor.BOLD + "[Skills] " + ChatColor.GOLD;

    @Override
    public void onDisable() {
    }

    @Override
    public void onEnable() {
        setupDatabase();
        controller = new SkillController<>(this);
        playerListener = new ProfessionPlayerListener(controller);
        blockListener = new ProfessionBlockListener(controller);
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(playerListener, this);
        pm.registerEvents(blockListener, this);
        getServer().getOnlinePlayers().forEach((key) -> controller.putPlayer(key.getName()));
        // Register our commands
        getCommand("skill").setExecutor(new ProfessionSkillCommand(controller));
        PluginDescriptionFile pdfFile = this.getDescription();
        getLogger().log(Level.INFO, "{0} version {1} is enabled!", new Object[]{pdfFile.getName(), pdfFile.getVersion()});
    }

    private void setupDatabase() {
        try {
            getDatabase().find(SkilledPlayer.class).findRowCount();
        } catch (PersistenceException ex) {
            getLogger().log(Level.INFO, "Installing database for {0} due to first time usage", getDescription().getName());
            installDDL();
        }
    }

    @Override
    public List<Class<?>> getDatabaseClasses() {
        List<Class<?>> list = new ArrayList<>();
        list.add(Experience.class);
        list.add(SkilledPlayer.class);
        return list;
    }
}
