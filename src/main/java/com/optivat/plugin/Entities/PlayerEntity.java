package com.optivat.plugin.Entities;

import com.optivat.plugin.Main;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.UUID;

public class PlayerEntity {

    private Player player;
    private int maxHp;
    private int currentHp;
    private int regenRateHp;
    private int defense;
    private int intelligence;
    private int mana;
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
        maxHp = 100;
        strength = 20;
        damage = strength/4;
        currentHp = maxHp;
        regenRateHp = 5;
        uuid = p.getUniqueId();
        loc = p.getLocation();
        defense = 0;
        intelligence = 100;
        mana = intelligence;
    }

    public Player getPlayer() { return player; }
    public int getMaxHP() { return maxHp; }
    public int getCurrentHP() { return currentHp; }
    public int getRegenRateHp() { return regenRateHp; }
    public int getDefense() { return defense; }
    public int getStrength() { return strength; }
    public int getDamage() { return damage; }
    public int getIntelligence() { return intelligence; }
    public int getMana() { return mana; }
    public UUID getUUID() { return uuid; }
    public Location getLocation() { return loc; }

    public void setCurrentHp(int chp) { currentHp = chp; }
    public void setLocation(Location location) { loc = location; }
    public void setMana(int newmana) { mana = newmana; }
    public void setIntelligence(int newintelligence) { intelligence = newintelligence; }
    public void setStrength(int strength) { this.strength = strength; }
    public void setDamage(int damage) { this.damage = damage; }

    public static PlayerEntity getPlayerEntity(UUID uuid) {
        for(PlayerEntity p: main.totalPlayers) {
            if (p.uuid == uuid) {
                return p;
            }
        }
        return null;
    }
    public int calculateDamage(MobEntity mobEntity) {
        return mobEntity.getDamage() + mobEntity.getStrength();
    }

}