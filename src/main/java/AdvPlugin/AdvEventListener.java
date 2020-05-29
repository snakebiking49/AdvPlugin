package AdvPlugin;

import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerLoginEvent;

public final class AdvEventListener implements Listener 
{
	AdvPlugin plugin;
    public AdvEventListener(AdvPlugin plugin) 
    {
    	this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void login(PlayerLoginEvent event) 
    {
       plugin.playerlist.add(event.getPlayer());
       event.getPlayer().sendMessage("Welcome to the Pure Quests!");
    }

   
}