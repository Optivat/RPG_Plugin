package com.optivat.plugin.Items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public enum CustomItems {
    WOODEN_WAND("Wooden Wand",2, 20, 5, 10, 10, 100, new ItemStack(Material.STICK), true, "SHOOTER", "You shoot wood and give people plenty of splinters.");

    private String name;
    private int rarity;
    private int damage;
    private int critDamage;
    private int critChance;
    private int strength;
    private int mana;
    private ItemStack itemStack;
    private String ability;
    private String abilityDesc;

    private CustomItems(String n, int r, int d, int cD, int cC, int s, int m, ItemStack itemStack, boolean isEnchanted) {
        damage = d;
        critDamage = cD;
        critChance = cC;
        strength = s;
        mana = m;
        this.itemStack = itemStack;
        ability = "";
        abilityDesc = "";
    }
    private CustomItems(String n, int r, int d, int cD, int cC, int s, int m, ItemStack itemStack, boolean isEnchanted, String iA, String desciA) {
        damage = d;
        critDamage = cD;
        critChance = cC;
        strength = s;
        mana = m;
        this.itemStack = itemStack;
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(getRarityColor() + name);
        String[] abilityDesc = desciA.split(" ");
    }

    private String getName() {return name;}
    private String getRarity() {
        switch(rarity) {
            case 1:
                return ChatColor.WHITE + "" + ChatColor.BOLD + "COMMON";
            case 2:
                return ChatColor.YELLOW + "" + ChatColor.BOLD + "UNCOMMON";
            case 3:
                return ChatColor.BLUE + "" + ChatColor.BOLD + "RARE";
            case 4:
                return ChatColor.DARK_BLUE + "" + ChatColor.BOLD + "VERY RARE";
            case 5:
                return ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "EPIC";
            case 6:
                return ChatColor.GOLD + "" + ChatColor.BOLD + "LEGENDARY";
            case 7:
                return ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "MYTHIC";
            case 8:
                return ChatColor.RED + "" + ChatColor.BOLD + "GODLIKE";
            case 9:
                return ChatColor.DARK_RED + "" + ChatColor.BOLD + "ADMIN";
            default:
                return ChatColor.GRAY + "" + ChatColor.BOLD + "" + ChatColor.MAGIC + "UNKNOWN";
        }
    }
    private ChatColor getRarityColor() {
        switch(rarity) {
            case 1:
                return ChatColor.WHITE;
            case 2:
                return ChatColor.YELLOW;
            case 3:
                return ChatColor.BLUE;
            case 4:
                return ChatColor.DARK_BLUE;
            case 5:
                return ChatColor.DARK_PURPLE;
            case 6:
                return ChatColor.GOLD;
            case 7:
                return ChatColor.LIGHT_PURPLE;
            case 8:
                return ChatColor.RED;
            case 9:
                return ChatColor.DARK_RED;
            default:
                return ChatColor.GRAY;
        }
    }
    private int getDamage() {return damage;}
    private int getCritDamage() {return critDamage;}
    private int getCritChance() {return critChance;}
    private int getStrength() {return strength;}
    private int getMana() {return mana;}
    private ItemStack getItemStack() {return itemStack;}
}
