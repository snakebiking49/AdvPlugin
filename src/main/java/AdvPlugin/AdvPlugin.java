package AdvPlugin;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
public class AdvPlugin extends JavaPlugin
{
	List <Player> playerlist;
	@Override
	public void onEnable()
	{
		
		//TODO Add listener and command executer
		for (Player player : Bukkit.getServer().getOnlinePlayers()) 
		{
			this.getCommand("setGroup").setExecutor(new AdvCommandExecutor(this));
		    playerlist.add(player);   
		}
	}
	public void onDisable()
	{
		
	}
}
