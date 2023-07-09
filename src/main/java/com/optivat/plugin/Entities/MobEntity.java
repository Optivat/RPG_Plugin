package com.optivat.plugin.Entities;

import com.optivat.plugin.Main;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Mob;

import java.util.UUID;

public class MobEntity {
    private Mob mob;
    private int acHp;
    private int imHp;
    private int regenRateHp;
    private int currentimHp;
    private int level;
    private int strength;
    private int damage;
    private int defense;
    private UUID uuid;
    private ArmorStand armorStand;
    private boolean alive;
    private Location loc;
    private static Main main;

    public MobEntity(Main main) {
        this.main = main;
    }


    public MobEntity(Mob mob, ArmorStand as) {
        this.mob = mob;
        acHp = (int) mob.getHealth();
        level = (int)((acHp)*(Math.random()*2));
        strength = level;
        damage = strength/2;
        defense = (int)(level*(1+Math.random()*2));
        imHp = (level)*(int)(1+Math.random()*50);
        currentimHp = imHp;
        uuid = mob.getUniqueId();
        armorStand = as;
        alive = true;
        loc = mob.getLocation();
    }

    public Mob getMob() {
        return mob;
    }
    public int getACHP() {
        return acHp;
    }
    public int getIMHP() { return imHp; }
    public int getRegenRateHp() { return regenRateHp; }
    public int getCurrentIMHP() { return currentimHp;}
    public int getLevel() {
        return level;
    }
    public int getStrength() {
        return strength;
    }
    public int getDamage() {
        return damage;
    }
    public int getDefense() { return defense; }
    public UUID getUUID() {
        return uuid;
    }
    public ArmorStand getArmorStand() {
        return armorStand;
    }
    public boolean getAlive() {
        return alive;
    }
    public Location getLocation() {
        return loc;
    }

    public void setAlive(boolean a) { alive = a; }
    public void setCurrentIMHP(int cimhp) { currentimHp = cimhp; }
    public void setLocation(Location location) {
        loc = location;
    }
    public void setArmorStand(ArmorStand as) {armorStand = as;}




    public static MobEntity getMob(UUID uuid) {
        for(MobEntity mob: main.totalMobs) {
            if (mob.uuid == uuid) {
                return mob;
            }
        }
        return null;
    }

}
