package com.optivat.plugin;

import com.optivat.plugin.Events.MobSpawn;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new MobSpawn(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
