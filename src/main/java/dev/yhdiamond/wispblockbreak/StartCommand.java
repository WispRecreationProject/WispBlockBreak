package dev.yhdiamond.wispblockbreak;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StartCommand implements CommandExecutor {
    public static boolean isStarted = false;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            Player p = (Player) sender;
            if (sender.hasPermission("wispblockbreak.trigger")){
                if (args.length == 1){
                    if (args[0].equals("start")){
                        isStarted = true;
                        Bukkit.broadcastMessage(ChatColor.GREEN+"Wisp Block Break challenge has started!");
                    }
                    else if (args[0].equals("stop")){
                        isStarted = false;
                        Bukkit.broadcastMessage(ChatColor.GREEN+"Wisp Block Break challenge has ended!");
                        WispBlockBreak.diggingPlayers.clear();
                    }
                    else if (args[0].equals("reload")){
                        if (WispBlockBreak.diggingPlayers.contains(p)) {
                            WispBlockBreak.diggingPlayers.remove(p);
                        } else {
                            WispBlockBreak.diggingPlayers.add(p);
                        }
                        p.sendMessage(ChatColor.GREEN+"You should be saved from the Blocks by Looking curse!");
                    }
                    else if (!args[0].equals("start") && !args[0].equals("stop")){
                        p.sendMessage(ChatColor.RED+"/wispblockbreak <start/stop/reload>");
                    }
                }
                else {
                    p.sendMessage(ChatColor.RED+"/wispblockbreak <start/stop/reload>");
                }
            }
            else {
                p.sendMessage(ChatColor.RED+"You do not have the permission to run this command!");
            }
        }
        else {
            sender.sendMessage(ChatColor.RED+"This command is for players only!");
        }
        return true;
    }
}
