package com.optivat.plugin.Entities;

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


    public MobEntity(Mob mob) {
        this.mob = mob;
        acHp = (int) mob.getHealth();
        level = (int)((acHp/10)*(Math.random()*5));
        strength = level/2;
        damage = strength/3;
        imHp = (level%100)+(int)(Math.random()*50);
        uuid = mob.getUniqueId();
    }
    public static MobEntity getMob(UUID uuid) {

    }

}
