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
		//setgroup r
		if(command.getName().equalsIgnoreCase("setGroup"))
		{
			return tagCommandsargs1(sender,command,command.getName(),args);
		}
		//setRank
		if(command.getName().equalsIgnoreCase("setRank"))
		{
			return tagCommandsargs1(sender,command,command.getName(),args);
		}
		//joinAdv event
		if(command.getName().equalsIgnoreCase("joinAdv"))
		{
			return eventJoin(sender,command,command.getName(),args);
		}
	
		return false;
	}
	
	//Commands that add tags to players in args[1]
	private boolean tagCommandsargs1(CommandSender sender, Command command, String name, String args[])
	{
		
			if(sender.hasPermission("AdvPlugin." + name))//Does the sender have permission?
			{
				if(args.length != 2)//Has the sender typed the correct amount of arguments?
				{
					sender.sendMessage("incorrect ammount of arguments!");
				}
				else
				{
					for(Player player: plugin.playerlist) //Iterate through player list to find the mentioned player
					{
						if(player.getName().contentEquals(args[0]))
						{
							player.addScoreboardTag(args[1]);
							return true;
						}
						else
						{
							sender.sendMessage(args[0] + "is not online!");
						}
					}
				}
			}
			return false;
	}
	//The method for /joinAdv
	private boolean eventJoin(CommandSender sender, Command command, String name, String args[])
	{
		if(sender instanceof Player)//is sender player?
		{
			if(plugin.AdvPlayersList.contains(sender))//has the player already joined?
			{
				sender.sendMessage("You have allready joined the Pure Quests!");
				return false;
			}
			else
			{
				plugin.AdvPlayersList.add((Player)sender);
				sender.sendMessage("Welcome to the Pure Quests!");
				sender.sendMessage("Please wait for the event Admins to teleport you.");
				plugin.getServer().dispatchCommand(sender, "saveInv");
				plugin.getServer().dispatchCommand(sender, "mv goto AdventureWorld");
				plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "adventure " + sender);
				return true;
			}
		}
		else
		{
			sender.sendMessage("You must be a player to use this command!");
			return false;
		}
		
	}
}

