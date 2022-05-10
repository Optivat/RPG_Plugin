package com.optivat.plugin.Events;

import com.optivat.plugin.Entities.MobEntity;
import com.optivat.plugin.Entities.PlayerEntity;
import com.optivat.plugin.Main;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.lang.reflect.InvocationTargetException;

public class MobEntityInteractPlayerEntity implements Listener {
    static Main main;
    public MobEntityInteractPlayerEntity(Main main) {
        this.main = main;
    }


    //Until making a new class just for players, I am adding player to Player Entity
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        main.totalPlayers.add(new PlayerEntity(e.getPlayer()));

        new BukkitRunnable() {
            @Override
            public void run() {

                e.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.RED + "" +
                        PlayerEntity.getPlayerEntity(e.getPlayer().getUniqueId()).getCurrentHP()
                        + "/"
                        + PlayerEntity.getPlayerEntity(e.getPlayer().getUniqueId()).getMaxHP()
                        + "❤     " + ChatColor.GREEN
                        + PlayerEntity.getPlayerEntity(e.getPlayer().getUniqueId()).getDefense() + "❈ Defense"));

            }
        }.runTaskTimer(main, 0, 5);
    }

    @EventHandler
    public void onJoinHeal(PlayerJoinEvent e) {
        new BukkitRunnable() {
            @Override
            public void run() {
                PlayerEntity playerEntity = PlayerEntity.getPlayerEntity(e.getPlayer().getUniqueId());
                if(playerEntity.getCurrentHP() + playerEntity.getRegenRateHp() >= playerEntity.getMaxHP()) {
                    playerEntity.setCurrentHp(playerEntity.getMaxHP());
                } else {
                    playerEntity.setCurrentHp(playerEntity.getCurrentHP()+playerEntity.getRegenRateHp());
                }
                double actualHearts = ((double)playerEntity.getCurrentHP()/(double)playerEntity.getMaxHP())*20;
                playerEntity.getPlayer().setHealth(actualHearts);
            }
        }.runTaskTimer(main, 10, 20);
    }


    //End of "player class"
    @EventHandler
    public void onAttack(EntityDamageByEntityEvent e) {
        e.setCancelled(true);
        if (e.getDamager() instanceof Player) {
            PlayerEntity playerEntity = PlayerEntity.getPlayerEntity(e.getDamager().getUniqueId());
            MobEntity mobEntity = MobEntity.getMob(e.getEntity().getUniqueId());
            int damageDone = (int)(playerEntity.getDamage()* playerEntity.getStrength()*e.getDamage());
            Vector PlayerLoc = e.getDamager().getLocation().getDirection();
            Location loc = new Location(e.getDamager().getWorld(), (PlayerLoc.getX() + playerEntity.getPlayer().getLocation().toVector().getX()), PlayerLoc.getY() + playerEntity.getPlayer().getLocation().toVector().getY()+1, (PlayerLoc.getZ() + playerEntity.getPlayer().getLocation().toVector().getZ()));

            ArmorStand as = playerEntity.getPlayer().getWorld().spawn(loc,ArmorStand.class);
            as.setVisible(false);
            as.setGravity(false);
            as.setInvulnerable(true);
            as.setCustomNameVisible(true);
            as.setMarker(true);
            String damageDoneString = String.valueOf(damageDone);
            as.setCustomName(ChatColor.GRAY + damageDoneString);

            new BukkitRunnable() {
                @Override
                public void run() {
                    as.remove();
                    cancel();
                }
            }.runTaskLater(main, 100);
            mobEntity.setCurrentIMHP(mobEntity.getCurrentIMHP() - damageDone);
            if(mobEntity.getCurrentIMHP() <= 0) {
                mobEntity.getMob().remove();
                mobEntity.setAlive(false);
            }
        }
        if (e.getDamager() instanceof Mob && e.getEntity() instanceof Player) {
            PlayerEntity playerEntity = PlayerEntity.getPlayerEntity(e.getEntity().getUniqueId());
            MobEntity mobEntity = MobEntity.getMob(e.getDamager().getUniqueId());
            int damageDone = (int)((mobEntity.getDamage() + mobEntity.getStrength()) + e.getDamage());
            playerEntity.setCurrentHp(playerEntity.getCurrentHP()-damageDone);
            playerEntity.getPlayer().playSound(e.getDamager(), Sound.ENTITY_PLAYER_HURT, 3.0F, 1F);
            if(playerEntity.getCurrentHP() <= 0) {
                playerEntity.getPlayer().teleport(playerEntity.getPlayer().getWorld().getSpawnLocation());
                playerEntity.getPlayer().sendMessage(ChatColor.RED + "You have died to " + mobEntity.getMob().getName() + "!");
                playerEntity.setCurrentHp(playerEntity.getMaxHP());
                playerEntity.getPlayer().playSound(e.getDamager(), Sound.ENTITY_PLAYER_DEATH, 3.0F, 0.5F);
            }
        }
    }
    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        for(Entity ent : e.getPlayer().getNearbyEntities(30, 20, 30)) {
            Class craftPlayerClass = getBukkitClass("entity.CraftPlayer");
            if(MobEntity.getMob(ent.getUniqueId()) != null) {
                if(!e.getPlayer().hasLineOfSight(ent)) {
                    try {
                        try {
                            Object handle = craftPlayerClass.getMethod("getHandle").invoke(e.getPlayer());
                            Object craftPlayer = craftPlayerClass.getMethod("getEntity", getBukkitClass("CraftServer"), net.minecraft.world.entity.Entity.class).invoke(null, getBukkitClass("CraftServer").cast(main.getServer()), handle);
                            craftPlayer.getClass().getMethod("hideEntity", org.bukkit.plugin.Plugin.class, org.bukkit.entity.Entity.class).invoke(e.getPlayer(), main, MobEntity.getMob(ent.getUniqueId()).getArmorStand());
                        } catch (IllegalAccessException | InvocationTargetException ex) {ex.printStackTrace();}
                    } catch (NoSuchMethodException  ex) {ex.printStackTrace();}
                } else {
                    try {
                        try {
                            Object handle = craftPlayerClass.getMethod("getHandle").invoke(e.getPlayer());
                            Object craftPlayer = craftPlayerClass.getMethod("getEntity", getBukkitClass("CraftServer"), net.minecraft.world.entity.Entity.class).invoke(null, getBukkitClass("CraftServer").cast(main.getServer()), handle);
                            craftPlayer.getClass().getMethod("showEntity", org.bukkit.plugin.Plugin.class, org.bukkit.entity.Entity.class).invoke(e.getPlayer(), main, MobEntity.getMob(ent.getUniqueId()).getArmorStand());
                        } catch (IllegalAccessException | InvocationTargetException ex) {ex.printStackTrace();}
                    } catch (NoSuchMethodException  ex) {ex.printStackTrace();}
                }
            }
        }
    }
    public Class<?> getBukkitClass(String str) {
        try {
            return Class.forName(Bukkit.getServer().getClass().getPackage().getName() + "." + str);
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    public Class<?> getClass(String str) {
        try {
            return Class.forName(str);
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}