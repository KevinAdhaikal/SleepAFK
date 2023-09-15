package com.kevinadhaikal;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class SleepAFK extends JavaPlugin implements Listener, CommandExecutor {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        getCommand("setafk").setExecutor(this);
        getCommand("unafk").setExecutor(this);
    }

    // Command executor for /afk
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (command.getName().equalsIgnoreCase("setafk")) {
                player.setSleepingIgnored(true);
                player.sendMessage("You are set AFK to on and won't affect sleep.");
                return true;
            }
            else if (command.getName().equalsIgnoreCase("unafk")) {
                player.setSleepingIgnored(false);
                player.sendMessage("You are set AFK to off and will affect sleep.");
                return true;
            }
        }
        return false;
    }

    // Event handler for player sleeping
    @EventHandler
    public void onPlayerSleep(PlayerBedEnterEvent event) {
        if (!event.getPlayer().isSleepingIgnored()) event.setCancelled(false);
    }
}
