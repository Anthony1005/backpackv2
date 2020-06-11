package main;

import main.cmd.cmdGUI;
import main.event.closeBP;
import main.functions.PluginFunctions;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    public static Main plugins;
    @Override
    public void onEnable(){
        plugins = this;
        System.out.println("啟動");
//        Bukkit.getPluginCommand("test").setExecutor(new cmdSort());
        Bukkit.getPluginCommand("gui").setExecutor(new cmdGUI());
//        Bukkit.getPluginCommand("load").setExecutor(new cmdLoad());
//        Bukkit.getPluginCommand("save").setExecutor(new cmdSave());
        Bukkit.getPluginManager().registerEvents(new closeBP(), this);

    }
    @Override
    public void onDisable(){
        for(Player p : Bukkit.getOnlinePlayers()){
            Inventory inv = p.getOpenInventory().getTopInventory();
            PluginFunctions functions = new PluginFunctions(p.getUniqueId().toString());
            functions.close(inv);

        }
    }
}
