package main.event;

import main.Main;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectOutputStream;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class closeBP implements Listener {
    @EventHandler
    public void closeBackPack(InventoryCloseEvent event) {
        if (event.getInventory().getName().equals(ChatColor.AQUA + "背包")) {
            delete(event.getPlayer().getUniqueId().toString());
            Inventory inv = event.getInventory();
            ArrayList<ItemStack> array = new ArrayList<ItemStack>();
            for (int i = 0; i < inv.getSize(); i++) {
                ItemStack item = inv.getItem(i);
                if (item != null) {
                    array.add(item);
                }
            }
            for (int i = 0; i < array.size(); i++) {
                try {
                    Player player = (Player) event.getPlayer();
                    File dir = new File(Main.plugins.getDataFolder().getAbsolutePath()+ "\\"+ player.getUniqueId()+ "\\"+"backpack");
                    if(!dir.exists()){
                        dir.mkdirs();
                    }
                    FileOutputStream output = new FileOutputStream(
                            Main.plugins.getDataFolder().getAbsolutePath() + "\\" + player.getUniqueId() + "\\"+"backpack"+ "\\" + i + ".backpack");
                    BukkitObjectOutputStream objectOutput = new BukkitObjectOutputStream(output);
                    objectOutput.writeObject(array.get(i));
                    output.close();
                    objectOutput.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        }
    }
    private void delete(String uuid){
        File dir = new File(Main.plugins.getDataFolder().getAbsolutePath()+ "\\"+uuid + "\\" + "backpack");
        for (File f : dir.listFiles()){
            f.delete();
        }
    }
}
