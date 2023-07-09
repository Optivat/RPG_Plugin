package com.optivat.plugin.Items.Events;

import com.optivat.plugin.Entities.MobEntity;
import com.optivat.plugin.Entities.PlayerEntity;
import com.optivat.plugin.Items.CustomItems;
import com.optivat.plugin.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Entity;
import org.bukkit.entity.FallingBlock;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class WoodenWandEvent implements Listener {

    Main main;
    public WoodenWandEvent(Main main) {
        this.main = main;
    }

    @EventHandler
    public void onClick(PlayerInteractEvent e) {
        if(!e.getAction().equals(Action.RIGHT_CLICK_AIR)) {return;}
        if(e.getItem().getItemMeta().getDisplayName().contains(CustomItems.WOODEN_WAND.getName())) {
            PlayerEntity playerEntity = PlayerEntity.getPlayerEntity(e.getPlayer().getUniqueId());
            if (playerEntity.getMana()-CustomItems.WOODEN_WAND.getMana() <= 0) {
                CustomItems.notEnoughMana(e.getPlayer());
                return;
            }
            playerEntity.setMana(playerEntity.getMana()-CustomItems.WOODEN_WAND.getMana());
            e.getPlayer().getWorld().getBlockAt(e.getPlayer().getLocation().add(e.getPlayer().getLocation().getDirection())).setType(Material.OAK_WOOD);
            Block block = e.getPlayer().getWorld().getBlockAt(e.getPlayer().getLocation().add(e.getPlayer().getLocation().getDirection()));
            FallingBlock wood = e.getPlayer().getWorld().spawnFallingBlock(e.getPlayer().getLocation(), block.getBlockData());
            wood.setVelocity(e.getPlayer().getLocation().getDirection().multiply(5));
            block.setType(Material.AIR);
            new BukkitRunnable() {
                Location loc;
                @Override
                public void run() {
                    if(wood.isValid()) {
                        loc = wood.getLocation();
                        if (!loc.getWorld().getNearbyEntities(loc, 1, 2, 1).isEmpty()) {
                            wood.remove();
                            e.getPlayer().playSound(e.getPlayer(), Sound.BLOCK_ANVIL_LAND, 1.0F, 1.0F);
                            for(Entity ent: loc.getWorld().getNearbyEntities(loc, 1, 2, 1)) {
                                if(!MobEntity.getMob(ent.getUniqueId()).equals(null)) {
                                    MobEntity.getMob(ent.getUniqueId()).setCurrentIMHP(MobEntity.getMob(ent.getUniqueId()).getCurrentIMHP() - PlayerEntity.getPlayerEntity(e.getPlayer().getUniqueId()).calculateDamage(MobEntity.getMob(ent.getUniqueId())));
                                }
                            }
                        }
                    }
                }
            }.runTaskTimer(main, 10, 20);
        }
    }
}
