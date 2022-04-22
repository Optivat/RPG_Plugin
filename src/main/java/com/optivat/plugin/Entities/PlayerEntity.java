package com.optivat.plugin.Entities;

import com.optivat.plugin.Main;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;

import java.util.UUID;

public class PlayerEntity {

        private Player player;
        private int maxHp;
        private int currentHp;
        private int strength;
        private int damage;
        private UUID uuid;
        private Location loc;
        private static Main main;

        public PlayerEntity(Main main) {
            this.main = main;
        }


        public PlayerEntity(Player p) {
            this.player = p;
            maxHp = (int) p.getHealth()*5;
            strength = 20;
            damage = strength/4;
            currentHp = maxHp;
            uuid = p.getUniqueId();
            loc = p.getLocation();

        }
        public Player getPlayer() { return player; }
        public int getMaxHP() { return maxHp; }
        public int getCurrentHP() { return currentHp; }
        public int getStrength() { return strength; }
        public int getDamage() { return damage; }
        public UUID getUUID() { return uuid; }
        public Location getLocation() { return loc; }

        public void setCurrentHp(int chp) { currentHp = chp; }
        public void setLocation(Location location) { loc = location; }




        public static PlayerEntity getPlayerEntity(UUID uuid) {
            for(PlayerEntity p: main.totalPlayers) {
                if (p.uuid == uuid) {
                    return p;
                }
            }
            return null;
        }

}
