package me.davidams789.StaffChatAms;

import me.davidams789.StaffChatAms.commands.StaffChat;
import me.davidams789.StaffChatAms.events.StaffChatEvent;
import net.luckperms.api.LuckPerms;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public class Main extends JavaPlugin {

     public ArrayList<Player> sc = new ArrayList<Player>();

    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&dStaffChatAms&8] &aHa sido activado"));
        RegisteredServiceProvider<LuckPerms> provider = Bukkit.getServicesManager().getRegistration(LuckPerms.class);
        if(provider != null){
            LuckPerms api = provider.getProvider();
        }
        registerCommands();
        registerEvents();
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&dStaffChatAms&8] &cHa sido desactivado"));
    }

    public void registerCommands(){
        getCommand("sc").setExecutor(new StaffChat(this));
    }

    public void registerEvents(){
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new StaffChatEvent(this), this);
    }
}
