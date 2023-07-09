package com.optivat.plugin.Items;

import com.optivat.plugin.Items.Events.WoodenWandEvent;
import com.optivat.plugin.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public enum CustomItems {
    WOODEN_WAND("Wooden Wand",2, 20, 5, 10, 10, 100, 20, 2, new ItemStack(Material.WOODEN_AXE), true, "SHOOTER", "You shoot wood and give people plenty of splinters."),
    BRADLEY_ITEM("Aluminum Can", 9, 420, 69, 69, 69, 1, 1, 10000, new ItemStack(Material.WATER_BUCKET), true, "Throw Can", "Can gos brrr"),
    ISSAC_ITEM("Rat", 0, -100, -50, 0, -10000, 0, 0, 0, new ItemStack(Material.NETHERITE_AXE), true);


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
    private int intelligence;
    private int cooldown;

    CustomItems(String n, int r, int d, int cD, int cC, int s, int i, int m, int c, ItemStack itemStack, boolean isEnchanted) {
        name = n;
        rarity = r;
        damage = d;
        critDamage = cD;
        critChance = cC;
        strength = s;
        intelligence = i;
        mana = m;
        cooldown = c;
        this.itemStack = itemStack;
        ability = "";
        abilityDesc = "";
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(getRarityColor() + name);
        itemMeta.setLore(createItemLore());
        if(isEnchanted) {
            itemMeta.addEnchant(Enchantment.LUCK, 1, true);
            itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        itemStack.setItemMeta(itemMeta);
        this.itemStack = itemStack;
    }
    CustomItems(String n, int r, int d, int cD, int cC, int s, int i, int m, int c, ItemStack itemStack, boolean isEnchanted, String iA, String desciA) {
        name = n;
        rarity = r;
        damage = d;
        critDamage = cD;
        critChance = cC;
        strength = s;
        intelligence = i;
        mana = m;
        cooldown = c;
        ability = iA;
        abilityDesc = desciA;
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(getRarityColor() + name);
        itemMeta.setLore(createItemLore());
        if(isEnchanted) {
            itemMeta.addEnchant(Enchantment.LUCK, 1, true);
            itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        itemStack.setItemMeta(itemMeta);
        this.itemStack = itemStack;
    }


    public String getName() {return name;}
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
    public int getDamage() {return damage;}
    public int getCritDamage() {return critDamage;}
    public int getCritChance() {return critChance;}
    public int getStrength() {return strength;}
    public int getIntelligence() {return intelligence;}
    public int getMana() {return mana;}
    public ItemStack getItemStack() {return itemStack;}


    private ArrayList<String> createItemLore() {
        ArrayList<String> itemLore = new ArrayList<>();
        if(damage != 0) {
            itemLore.add(ChatColor.GRAY + "Damage: " + ChatColor.RED + "+" + damage);
        }
        if(strength != 0) {
            itemLore.add(ChatColor.GRAY + "Strength: " + ChatColor.RED + "+" + strength);
        }
        if(critChance != 0) {
            itemLore.add(ChatColor.GRAY + "Crit Chance: " + ChatColor.RED + "+" + critChance + "%");
        }
        if (critDamage != 0) {
            itemLore.add(ChatColor.GRAY + "Crit Damage: " + ChatColor.RED + "+" + critDamage + "%");
        }
        if (intelligence != 0) {
            itemLore.add(ChatColor.GRAY + "Intelligence: " + ChatColor.AQUA + intelligence);
        }
        itemLore.add("");
        if(!ability.equalsIgnoreCase("")) {
            itemLore.add(ChatColor.GOLD + "Ability: " + ability + ChatColor.YELLOW + "" + ChatColor.BOLD + " RIGHT CLICK");
            if (!abilityDesc.equalsIgnoreCase("")) {
                String[] strings = abilityDesc.split("\\s");
                int x = 0;
                String stuff = "";
                for (String str : strings) {
                    if (x % 5 == 0) {
                        itemLore.add(ChatColor.GRAY + stuff);
                        stuff = "";
                    } else {
                        stuff.concat(str + " ");
                    }
                    x++;
                }
                itemLore.add(stuff);
            }
            itemLore.add(ChatColor.DARK_GRAY + "Mana Cost: " + ChatColor.DARK_AQUA + String.valueOf(mana));
            if (cooldown >= 0) {
                itemLore.add(ChatColor.DARK_GRAY + "Cooldown: " + ChatColor.GREEN + String.valueOf(cooldown) + "s");
            }
            itemLore.add("");
        }
        itemLore.add(getRarity());
        return itemLore;
    }
    public static void initalizeEvents(Main main) {
        Bukkit.getPluginManager().registerEvents(new WoodenWandEvent(main), main);
    }
    public static CustomItems displayNameCustom(String str) {
        for (CustomItems ci : CustomItems.values()) {
            if(ci.getName().contains(str)) {
                return ci;
            }
            return null;
        }
        return null;
    }
    public static void notEnoughMana(Player p) {
        p.sendMessage(ChatColor.RED + "Not enough mana!");
    }
}
