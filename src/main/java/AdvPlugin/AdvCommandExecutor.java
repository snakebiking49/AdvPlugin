package AdvPlugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AdvCommandExecutor implements CommandExecutor
{
	private final AdvPlugin plugin;

	public AdvCommandExecutor(AdvPlugin plugin) 
	{
		this.plugin = plugin; 
	}
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) 
	{	
		if(command.getName().equalsIgnoreCase("setGroup"))
		{
			return tagCommandsargs1(sender,command,command.getName(),args);
		}
	
			return false;
	}
	
	
	private boolean tagCommandsargs1(CommandSender sender, Command command, String name, String args[])
	{
		//Commands that add tags to players in args[1]
			if(sender.hasPermission("AdvPlugin." + name))
			{
				if(args.length != 2)
				{
					sender.sendMessage("incorrect ammount of arguments!");
				}
				else
				{
					for(Player player: plugin.playerlist) 
					{
						if(player.getName().contentEquals(args[0]))
						{
							player.addScoreboardTag(args[1]);
							return true;
						}
					}
				}
			}
			return false;
	}
}
			
	
	
	
	
	

