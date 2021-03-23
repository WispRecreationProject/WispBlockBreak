package dev.yhdiamond.wispblockbreak;

import net.minecraft.server.v1_16_R3.EntityPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.lang.reflect.InvocationTargetException;

public class JoinQuitListener implements Listener {


    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        PacketListener.start(e.getPlayer());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        PacketListener.end(e.getPlayer());
    }
}
