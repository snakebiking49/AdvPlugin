package AdvPlugin;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
public class AdvPLugin extends JavaPlugin
{
	List <Player> playerlist;
	@Override
	public void onEnable()
	{
		//TODO Add listener and command executer
		for (Player player : Bukkit.getServer().getOnlinePlayers()) 
		{
		    playerlist.add(player);   
		}
	}
	public void onDisable()
	{
		
	}
}
