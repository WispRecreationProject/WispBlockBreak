package dev.yhdiamond.wispblockbreak;

import dev.yhdiamond.wispblockbreak.bstats.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public final class WispBlockBreak extends JavaPlugin {
    public static List<Player> diggingPlayers = new ArrayList<>();
    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new JoinQuitListener(), this);
        getCommand("wispblockbreak").setExecutor(new StartCommand());
        getCommand("wispblockbreak").setTabCompleter(new CommandTabCompleter());
        new Metrics(this, 10779);
        BukkitRunnable br = new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    if (diggingPlayers.contains(player)) {
                        double randx = Math.random() * 5;
                        double randy = Math.random() * 5;
                        double randz = Math.random() * 5;
                        if (player.getTargetBlockExact(5) != null) {
                            player.getWorld().getBlockAt(player.getTargetBlockExact(5).getLocation().add(2 - randx, 2 - randy, 2 - randz)).setType(player.getTargetBlockExact(5).getType());
                        }
                    }
                }
            }
        };
        br.runTaskTimer(this, 5, 5);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
