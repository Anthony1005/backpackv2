package main.functions;

import main.Main;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;

import java.io.*;
import java.util.ArrayList;

public class PluginFunctions {

    private String path;

    public PluginFunctions(String uuid) {
        path = Main.plugins.getDataFolder().getAbsolutePath() + File.separator + uuid + File.separator + "backpack" + File.separator;
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    public boolean save(ArrayList<ItemStack> itemStacks) {
        for (int i = 0; i < itemStacks.size(); i++) {
            try {
                FileOutputStream output = new FileOutputStream(path + i);
                BukkitObjectOutputStream objectoutput = new BukkitObjectOutputStream(output);

                objectoutput.writeObject(itemStacks.get(i));
                objectoutput.close();
                output.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return true;
    }

    public ArrayList<ItemStack> load() {
        ArrayList<ItemStack> array = new ArrayList<ItemStack>();

        File dir = new File(path);
        if (!dir.exists()) return array;

        for (String file : dir.list()) {
            try {
                FileInputStream fis = new FileInputStream(path + file);
                BukkitObjectInputStream bois = new BukkitObjectInputStream(fis);

                ItemStack itemStack = (ItemStack) bois.readObject();
                array.add(itemStack);

                bois.close();
                fis.close();
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

    public void delete() {
        File dir = new File(path);
        for (File f : dir.listFiles()) {
            f.delete();
        }
    }

    public void close(Inventory inv) {
        delete();
        ArrayList<ItemStack> array = new ArrayList<ItemStack>();
        for (int i = 0; i < inv.getSize(); i++) {
            ItemStack item = inv.getItem(i);
            if (item != null) {
                array.add(item);
            }
        }
        save(array);

    }
}

