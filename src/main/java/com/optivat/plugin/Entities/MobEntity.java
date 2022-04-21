package com.optivat.plugin.Entities;

import com.optivat.plugin.Events.MobSpawn;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Mob;

import java.util.UUID;

public class MobEntity {
    private Mob mob;
    private int acHp;
    private int imHp;
    private int level;
    private int strength;
    private int damage;
    private UUID uuid;
    private ArmorStand armorStand;
    private boolean alive;
    private Location loc;


    public MobEntity(Mob mob, ArmorStand as) {
        this.mob = mob;
        acHp = (int) mob.getHealth();
        level = (int)((acHp/10)*(Math.random()*5));
        strength = level/2;
        damage = strength/3;
        imHp = (level%100)+(int)(Math.random()*50);
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
    public int getLevel() {
        return level;
    }
    public int getStrength() {
        return strength;
    }
    public int getDamage() {
        return damage;
    }
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

    public void setLocation(Location location) {
        loc = location;
    }




    public static MobEntity getMob(UUID uuid) {
        for(MobEntity mob: MobSpawn.totalMobs) {
            if (mob.uuid == uuid) {
                return mob;
            }
        }
        return null;
    }

}
