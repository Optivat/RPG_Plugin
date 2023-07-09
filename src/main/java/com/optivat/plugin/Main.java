package com.optivat.plugin;

import com.optivat.plugin.Entities.MobEntity;
import com.optivat.plugin.Entities.PlayerEntity;
import com.optivat.plugin.Events.MobEntityInteractPlayerEntity;
import com.optivat.plugin.Events.MobSpawn;
import com.optivat.plugin.Items.CreateItemCommand;
import com.optivat.plugin.Items.CustomItemStack;
import com.optivat.plugin.Items.CustomItems;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public class Main extends JavaPlugin {

    public ArrayList<MobEntity> totalMobs = new ArrayList<>();
    public ArrayList<PlayerEntity> totalPlayers = new ArrayList<>();
    private MobEntity mobEntity;
    private PlayerEntity playerEntity;

    @Override
    public void onEnable() {
        CustomItems.initalizeEvents(this);
        mobEntity = new MobEntity(this);
        playerEntity = new PlayerEntity(this);
        getCommand("createitem").setExecutor(new CreateItemCommand());
        Bukkit.getPluginManager().registerEvents(new MobSpawn(this), this);
        Bukkit.getPluginManager().registerEvents(new MobEntityInteractPlayerEntity(this), this);
        Bukkit.getPluginManager().registerEvents(new CustomItemStack(this), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
