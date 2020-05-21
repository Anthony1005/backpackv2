package main.cmd;

import main.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class cmdGUI implements CommandExecutor {
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
        Inventory inv = Bukkit.createInventory(null, invSize, "§b背包");
        ArrayList<ItemStack> pre = load(player.getUniqueId().toString());
        for (int i = 0; i < pre.size(); i++) {
            ItemStack item = pre.get(i);
            inv.addItem(item);
        }
        player.openInventory(inv);

        return false;
    }

    private ArrayList<ItemStack> load(String uuid) {
        File dir = new File(Main.plugins.getDataFolder().getAbsolutePath() + "\\" + uuid + "\\" + "backpack");
        ArrayList<ItemStack> array = new ArrayList<ItemStack>();
        if (dir.exists()) {
            try {
                for (int i = 0; i < dir.listFiles().length; i++) {
                    FileInputStream input = new FileInputStream(Main.plugins.getDataFolder().getAbsolutePath() + "\\" + uuid + "\\" + "backpack" + "\\" + i + ".backpack");
                    BukkitObjectInputStream objectInput = new BukkitObjectInputStream(input);
                    ItemStack tmp = (ItemStack) objectInput.readObject();
                    array.add(tmp);
                    objectInput.close();
                    input.close();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return array;
    }
}
