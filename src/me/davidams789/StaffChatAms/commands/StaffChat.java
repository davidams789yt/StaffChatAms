package me.davidams789.StaffChatAms.commands;

import me.davidams789.StaffChatAms.Main;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StaffChat implements CommandExecutor {

    private Main plugin;
    public StaffChat(Main plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)){
            return false;
        }

        Player p = (Player) sender;
        User user = LuckPermsProvider.get().getPlayerAdapter(Player.class).getUser(p);
        String prefix = user.getCachedData().getMetaData().getPrefix();

        if(p.hasPermission("staffchatams.use") || p.isOp()){
            if(args.length > 0){
                String a = "";
                a = args[0];
                for (int i = 1; i < args.length; i++) {
                    a += " " + args[i];
                }
                for(Player staff : Bukkit.getOnlinePlayers()){
                    if(staff.hasPermission("staffchatams.resive") || staff.isOp()){
                        staff.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&dStaffChat&8] " + prefix + " " + "&b" + p.getName() + ": &f" + a));
                    }
                }
            }else{
                if(!plugin.sc.contains(p)){
                    plugin.sc.add(p);
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&dStaffChat&8] &aEl StaffChat ha sido activado"));
                }else{
                    plugin.sc.remove(p);
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&dStaffChat&8] &cEl StaffChat ha sido desactivado"));
                }
            }
        }else {
            p.sendMessage(ChatColor.translateAlternateColorCodes('&',"&cNo tienes permisos para ejecutar ese comando!"));
        }

        return true;
    }
}
