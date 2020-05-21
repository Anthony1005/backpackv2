package main.cmd;

import main.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class cmdLoad implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) return false;

        Player player = (Player) sender;
        File dir = new File(Main.plugins.getDataFolder().getAbsolutePath() + "\\" + player.getUniqueId() + "\\");
        if (dir.listFiles().length == 36) {
            try {
                for (int i = 0; i < 36; i++) {
                    FileInputStream fis = new FileInputStream(Main.plugins.getDataFolder().getAbsolutePath() + "\\" + player.getUniqueId() + "\\" + i + ".backpack");
                    BukkitObjectInputStream bois = new BukkitObjectInputStream(fis);
                    ItemStack tmp = (ItemStack) bois.readObject();

                    if (tmp != null) {
                        player.getInventory().setItem(i, tmp);
                    }
                    bois.close();
                    fis.close();
                }
                remove(player.getUniqueId().toString());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    private void remove(String uuid) {
        File dir = new File(Main.plugins.getDataFolder().getAbsolutePath() + "\\" + uuid + "\\");
        for (File f : dir.listFiles()) {
            f.delete();
        }
    }
}
