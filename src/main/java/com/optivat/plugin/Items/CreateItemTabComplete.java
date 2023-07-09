package com.optivat.plugin.Items;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class CreateItemTabComplete implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        if(strings.length == 1) {
            CustomItems[] customItems = CustomItems.values();
            List<String> customItemsNames = new ArrayList<>();
            for(CustomItems item : customItems) {
                customItemsNames.add(item.name());
            }
            return customItemsNames;
        }
        return null;
    }
}
