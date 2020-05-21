package main.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class cmdSort implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        sender.sendMessage("faq");
        if (!(sender instanceof Player)) {
            return false;
        }
        Player player = (Player) sender;
        ArrayList<ItemStack> tmp = new ArrayList<ItemStack>();
        for (int i = 9; i < 36; i++) {
            ItemStack eric = player.getInventory().getItem(i);
            if (eric != null) {
                tmp.add(eric);
                player.getInventory().clear(i);
            }
        }
        for (int i = 0; i < tmp.size(); i++) {
            for (int j = 0; j < tmp.size() - 1; j++) {
                ItemStack i1 = tmp.get(j);
                ItemStack i2 = tmp.get(j + 1);

                if (i1.getTypeId() > i2.getTypeId()) {
                    ItemStack tmpIs = i1;
                    tmp.set(j, i2);
                    tmp.set(j + 1, tmpIs);
                }

            }
        }
        for (int i = 9; i < 9+tmp.size(); i++) {
            ItemStack it = tmp.get(i - 9);
            player.getInventory().setItem(i, it);
        }
        return false;
    }
}