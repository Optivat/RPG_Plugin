package com.optivat.plugin.Items;

import com.optivat.plugin.Main;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CustomItemStack extends ItemStack implements Listener {

    //Gets the main plugin
    private Main main;
    public CustomItemStack(Main main) {
        this.main = main;
    }
    //Variable time O-o

    private ItemMeta itemMeta;
    private

    public CustomItemStack(Item item) {

    }
    public CustomItemStack(ItemStack itemStack) {

    }

    @EventHandler
    public void onItemDrop(ItemSpawnEvent e) {
        new CustomItemStack(e.getEntity());
    }
    @EventHandler
    public void onItemCraft(CraftItemEvent e) {
        new CustomItemStack(e.getCurrentItem());
    }
}
