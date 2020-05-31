package AdvPlugin;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
public class AdvPlugin extends JavaPlugin
{
	List <Player> playerlist;//online players
	List <Player> AdvPlayersList;//online players who have joined the event
	
	@Override
	public void onEnable()
	{
		getLogger().info("The AdvPlugin is being enabled!");
		new AdvEventListener(this);
		this.getCommand("setGroup").setExecutor(new AdvCommandExecutor(this));
		for (Player player : Bukkit.getServer().getOnlinePlayers()) 
		{	
		    playerlist.add(player);   
		}
		
	
	}
	public void onDisable()
	{
		getLogger().info("The Advplugin is being disabled!");
	}
}
