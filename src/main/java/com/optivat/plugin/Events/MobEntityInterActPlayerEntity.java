package com.optivat.plugin.Events;

import com.optivat.plugin.Entities.MobEntity;
import com.optivat.plugin.Entities.PlayerEntity;
import com.optivat.plugin.Entities.Ray;
import com.optivat.plugin.Main;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.util.Vector;

public class MobEntityInterActPlayerEntity implements Listener {
    static Main main;
    public MobEntityInterActPlayerEntity(Main main) {
        this.main = main;
    }


    //Until making a new class just for players, I am adding player to Player Entity
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Ray.from(e.getPlayer());
        main.totalPlayers.add(new PlayerEntity(e.getPlayer()));
    }


    @EventHandler
    public void onAttack(EntityDamageByEntityEvent e) {
        e.setCancelled(true);
        if (e.getDamager() instanceof Player) {
            PlayerEntity playerEntity = PlayerEntity.getPlayerEntity(e.getDamager().getUniqueId());
            MobEntity mobEntity = MobEntity.getMob(e.getEntity().getUniqueId());
            int damageDone = (playerEntity.getDamage()* playerEntity.getStrength());
            Location loc = e.getDamager().getLocation().getDirection().toLocation(e.getDamager().getWorld());
            ArmorStand as = playerEntity.getPlayer().getWorld().spawn(loc, ArmorStand.class);
            as.setVisible(false);
            as.setGravity(false);
            as.setInvulnerable(true);
            as.setCustomNameVisible(true);
            as.setMarker(true);
            String damageDoneString = String.valueOf(damageDone);
            as.setCustomName(ChatColor.GRAY + damageDoneString);
            mobEntity.setCurrentIMHP(mobEntity.getIMHP() - damageDone);
            if(mobEntity.getIMHP() <= 0) {
                mobEntity.setAlive(false);
            }
        }
    }
}
