package com.optivat.plugin.Events;

import com.optivat.plugin.Entities.MobEntity;
import com.optivat.plugin.Entities.PlayerEntity;
import com.optivat.plugin.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Mob;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class MobSpawn implements Listener {

    static Main main;
    public MobSpawn(Main main) {
        this.main = main;
    }

    @EventHandler
    public static void onMobSpawn(EntitySpawnEvent e) {
        if(e.getEntity() instanceof Mob) {
            Mob bombs = (Mob) e.getEntity();
            Location loc = bombs.getLocation(); loc.add(0, 1, 0);
            ArmorStand as = bombs.getWorld().spawn(loc, ArmorStand.class);
            main.totalMobs.add(new MobEntity(bombs, as));
            MobEntity mobEntity = MobEntity.getMob(bombs.getUniqueId());
            as.setVisible(false);
            as.setGravity(false);
            as.setInvulnerable(true);
            as.setCustomNameVisible(true);
            as.setMarker(true);
            new BukkitRunnable()
            {
                @Override
                public void run() {
                    if(Bukkit.getServer().getEntity(bombs.getUniqueId()) != null) {
                        if (mobEntity.getAlive()) {
                            Location loc = Bukkit.getServer().getEntity(bombs.getUniqueId()).getBoundingBox().getCenter().toLocation(mobEntity.getMob().getWorld());
                            mobEntity.setLocation(loc);
                            loc.add(0, 0.5, 0);
                            as.teleport(loc);
                            as.setCustomName(ChatColor.DARK_GRAY + "[" + ChatColor.GRAY + mobEntity.getLevel() + ChatColor.DARK_GRAY + "]" + " " + ChatColor.RED + mobEntity.getMob().getName() + " " + ChatColor.YELLOW + mobEntity.getCurrentIMHP() + "/" + mobEntity.getIMHP());
                            mobEntity.setArmorStand(as);
                        } else {
                            mobEntity.getArmorStand().remove();
                            main.totalMobs.remove(mobEntity);
                            cancel();
                        }
                    } else {
                        main.totalMobs.remove(mobEntity);
                        mobEntity.getArmorStand().remove();
                        cancel();
                    }
                }
            }.runTaskTimer(main, 0, 5);
        }
    }
    @EventHandler
    public void onMobDeath(EntityDeathEvent e) {
        if(e.getEntity() == null) {return;}
        if(e.getEntity() instanceof Mob) {
            MobEntity mobEntity = MobEntity.getMob(e.getEntity().getUniqueId());
            main.totalMobs.remove(mobEntity);
            mobEntity.getArmorStand().remove();
        }
    }
}
