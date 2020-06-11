package main.event;

import main.functions.PluginFunctions;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;

public class closeBP implements Listener {
    @EventHandler
    public void closeBackPack(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();
        Inventory inv = event.getInventory();
        PluginFunctions functions = new PluginFunctions(player.getUniqueId().toString());
        if (inv.getName().equals(ChatColor.AQUA + "背包")) {
            functions.close(inv);
        }
    }
}

