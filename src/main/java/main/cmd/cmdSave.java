package main.cmd;

import main.functions.PluginFunctions;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class cmdSave implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) return false;

        Player player = (Player) sender;
        Inventory inv = player.getInventory();
        PluginFunctions functions = new PluginFunctions(player.getUniqueId().toString());
        ArrayList<ItemStack> items = new ArrayList<ItemStack>();
        for (int i = 0; i < 36; i++) {
            if(inv.getItem(i)!= null){
                items.add(inv.getItem(i));
            }
            functions.save(items);
        }

        return false;
    }
}
