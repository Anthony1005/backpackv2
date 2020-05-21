package main.cmd;

import main.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectOutputStream;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class cmdSave implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) return false;

        Player player = (Player) sender;
        for (int i = 0; i < 36; i++) {
            ItemStack item = player.getInventory().getItem(i);
            File dir = new File(Main.plugins.getDataFolder().getAbsolutePath()+"\\" + player.getUniqueId()+"\\");
            if(!(dir.exists())){
                dir.mkdirs();
            }

            try {
                FileOutputStream fos = new FileOutputStream(Main.plugins.getDataFolder().getAbsolutePath() +"\\"+ player.getUniqueId()+"\\"+i +".backpack");
                BukkitObjectOutputStream boos = new BukkitObjectOutputStream(fos);

                boos.writeObject(item);

                boos.close();
                fos.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return false;
    }
}
