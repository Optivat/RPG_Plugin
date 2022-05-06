package com.optivat.plugin;

import com.nesaak.noreflection.NoReflection;
import com.optivat.plugin.Entities.MobEntity;
import com.optivat.plugin.Entities.PlayerEntity;
import com.optivat.plugin.Events.MobEntityInterActPlayerEntity;
import com.optivat.plugin.Events.MobSpawn;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public final class Main extends JavaPlugin {

    public ArrayList<MobEntity> totalMobs = new ArrayList<>();
    public ArrayList<PlayerEntity> totalPlayers = new ArrayList<>();
    private MobEntity mobEntity;
    private PlayerEntity playerEntity;

    @Override
    public void onEnable() {
        NoReflection.shared();
        mobEntity = new MobEntity(this);
        playerEntity = new PlayerEntity(this);
        Bukkit.getPluginManager().registerEvents(new MobSpawn(this), this);
        Bukkit.getPluginManager().registerEvents(new MobEntityInterActPlayerEntity(this), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
