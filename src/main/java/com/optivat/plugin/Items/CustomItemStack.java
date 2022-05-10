package com.optivat.plugin.Items;

import com.optivat.plugin.Entities.PlayerEntity;
import com.optivat.plugin.Main;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.UUID;

public class CustomItemStack extends ItemStack implements Listener {

    //Gets the main plugin
    private Main main;
    public CustomItemStack(Main main) {
        this.main = main;
    }
    //Variable time O-o

    private ItemMeta itemMeta;
    private UUID id;
    private UUID ownerID;

    public CustomItemStack(Item item) {
        itemMeta = item.getItemStack().getItemMeta();
        id = item.getUniqueId();
        ownerID = item.getOwner();
        Material.ACACIA_FENCE
    }
    public CustomItemStack(ItemStack itemStack, Player player) {
        itemMeta = itemStack.getItemMeta();
        id = UUID.randomUUID();
        ownerID = PlayerEntity.getPlayerEntity(player.getUniqueId()).getUUID();
    }

    @EventHandler
    public void onItemDrop(ItemSpawnEvent e) {
        new CustomItemStack(e.getEntity());
    }
    @EventHandler
    public void onItemCraft(CraftItemEvent e) {
        new CustomItemStack(e.getCurrentItem(), e.getWhoClicked().getKiller());
    }
}
