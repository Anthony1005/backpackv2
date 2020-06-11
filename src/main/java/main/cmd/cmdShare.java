package main.cmd;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class cmdShare implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return false;
        }
        Player player = (Player) sender;
        int invSize = 0;
        if (player.hasPermission("gui.4")) {
            invSize = 36;
        } else if (player.hasPermission("gui.3")) {
            invSize = 27;
        } else if (player.hasPermission("gui.2")) {
            invSize = 18;
        } else if (player.hasPermission("gui.1")) {
            invSize = 9;
        } else {
            player.sendMessage("你沒有權限");
            return false;
        }
        Inventory inv = Bukkit.createInventory(null, invSize, "§b倉庫");



        return false;
    }
}
