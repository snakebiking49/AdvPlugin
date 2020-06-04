package AdvPlugin;

import org.bukkit.event.Listener;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public final class AdvEventListener implements Listener 
{
	AdvPlugin plugin;
    public AdvEventListener(AdvPlugin plugin) //constructor sets up a listener on server events
    {
    	this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void login(PlayerLoginEvent event) //Player logs on
    {
       plugin.playerlist.add(event.getPlayer());
    }
    
    @EventHandler(priority = EventPriority.MONITOR)
    public void logout(PlayerQuitEvent event)// player leaves
    {
    	Player player = event.getPlayer();
    		if(plugin.playerlist.contains(player))
    		{
    			plugin.playerlist.remove(player);
    			if(plugin.AdvPlayersList.contains(player))
    			{
    				plugin.AdvPlayersList.remove(player);
    			}
    		}
    	
    }

   
}