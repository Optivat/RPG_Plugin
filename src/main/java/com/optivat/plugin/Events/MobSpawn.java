package com.optivat.plugin.Events;

import com.optivat.plugin.Entities.MobEntity;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Mob;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

import java.util.ArrayList;

public class MobSpawn implements Listener {

    public static ArrayList<MobEntity> totalMobs = new ArrayList<>();

    @EventHandler
    public static void onMobSpawn(EntitySpawnEvent e) {
        if(e.getEntity() instanceof Mob) {
            Mob bombs = (Mob) e.getEntity();
            Location loc = bombs.getLocation(); loc.add(0, 1, 0);
            ArmorStand as = bombs.getWorld().spawn(loc, ArmorStand.class);
            totalMobs.add(new MobEntity(bombs, as));
            MobEntity mobEntity = MobEntity.getMob(bombs.getUniqueId());
            as.setVisible(false);
            as.setGravity(false);
            as.setInvulnerable(true);
            as.setCustomNameVisible(true);
            as.setCustomName(ChatColor.DARK_GRAY + "[" + ChatColor.GRAY + mobEntity.getLevel() + ChatColor.DARK_GRAY + "]" + " " + ChatColor.RED + mobEntity.getMob().getName() + " " + ChatColor.YELLOW + mobEntity.getDamage() + "/" + mobEntity.getMob());
            while (mobEntity.getAlive()) {
                mobEntity.setLocation(bombs.getLocation());
                as.teleport(mobEntity.getLocation());
                loc.add(0, 1, 0);
            }
        }
    }
}
