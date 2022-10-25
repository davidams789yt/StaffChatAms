package me.davidams789.StaffChatAms.events;

import me.davidams789.StaffChatAms.Main;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class StaffChatEvent implements Listener {

    private Main plugin;
    public StaffChatEvent(Main plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void chatAsync(AsyncPlayerChatEvent e){
        Player p = e.getPlayer();
        User user = LuckPermsProvider.get().getPlayerAdapter(Player.class).getUser(p);
        String prefix = user.getCachedData().getMetaData().getPrefix();
        String message = e.getMessage();
        if(plugin.sc.contains(p)){
            e.setCancelled(true);
            for(Player staff : Bukkit.getOnlinePlayers()){
                if(staff.hasPermission("staffchatams.resive") || staff.isOp()){
                    staff.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&dStaffChat&8] " + prefix + " " + "&b" + p.getName() + ": &f" + message));
                }
            }
        }
    }
}
