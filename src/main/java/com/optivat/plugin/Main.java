package com.optivat.plugin;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registEvents()

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
