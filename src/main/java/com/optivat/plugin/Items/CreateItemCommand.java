package com.optivat.plugin.Items;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CreateItemCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;
            if(args.length == 1) {
                if (CustomItems.valueOf(args[0]) != null) {
                    p.getInventory().addItem(CustomItems.valueOf(args[0]).getItemStack());
                } else {
                    p.sendMessage(ChatColor.RED + "Invalid item ID!");
                }
            } else {
                p.sendMessage(ChatColor.RED + "Error! Usage: /createitem (Item ID) (Player)");
            }
        }
        return false;
    }
}
