package AdvPlugin;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Tag;
import org.bukkit.World;
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
		if(command.getName().equalsIgnoreCase("createGame"))
		{
			return createGame(sender);
		}
		if(command.getName().equalsIgnoreCase("destroyGame"))
		{
			return destroyGame(sender);
		}
		if(command.getName().equalsIgnoreCase("leaveAdv"))
		{
			return leaveGame(sender);
		}
		if(command.getName().equalsIgnoreCase("startGame"))
		{
			return startGame(sender);
		}
		if(command.getName().equalsIgnoreCase("setGroupHome"))
		{
			return setGroupHome(sender, args);
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
		if(sender instanceof Player && plugin.event == true)//is sender player?, is the game actually on?
		{
			if(plugin.AdvPlayersList.contains(sender))//has the player already joined?
			{
				sender.sendMessage("You have already joined the Pure Quests!");
				return false;
			}
			else
			{
				Player player = (Player) sender;
				plugin.AdvPlayersList.add(player);
				player.sendMessage("Welcome to the Pure Quests!");
				player.sendMessage("Please wait for the event Admins to teleport you, dont move");
				plugin.getServer().dispatchCommand(player, "saveInv " + player.getName());
				plugin.getServer().getPlayer(player.getName()).teleport(plugin.getServer().getWorld("AdventureWorld").getSpawnLocation());
				plugin.getServer().getPlayer(player.getName()).setGameMode(GameMode.ADVENTURE);
				if(player.getLocation() == plugin.getServer().getWorld("AdventureWorld").getSpawnLocation())//Was the teleportation successful?
				{
					player.sendMessage("Teleportation successful");
					return true;
				}
				else
				{
					player.sendMessage("Sorry you moved teleportation was cancelled, join again.");
					plugin.AdvPlayersList.remove(player);
					return false;
				}
				
			}
		}
		else
		{
			if(sender instanceof Player)// is the sender a player?
			{
				sender.sendMessage("The event isnt on!");
				return true;
			}
			sender.sendMessage("You must be a player to use this command!");
			return false;
		}
		
	}
	
	public boolean createGame(CommandSender sender)
	{
		if(sender.hasPermission("AdvPlugin.createGame"))
		{
			if(plugin.event == false)
			{
				plugin.event = true;
				return true;
			}
		}
		return false;
	}
	
	public boolean destroyGame(CommandSender sender)
	{
		if(sender.hasPermission("AdvPlugin.destroyGame"))
		{
			if(plugin.event == true)
			{
				plugin.event = false;
				for(Player player: plugin.AdvPlayersList)
				{
					player.teleport(new Location(plugin.getServer().getWorld("World"),plugin.getServer().getWorld("World").getSpawnLocation().getX(),plugin.getServer().getWorld("World").getSpawnLocation().getY(),plugin.getServer().getWorld("World").getSpawnLocation().getZ()));
					plugin.AdvPlayersList.remove(player);
					player.setGameMode(GameMode.SURVIVAL);
				}
			}
		}
		return false;
	}
	
	public boolean leaveGame(CommandSender sender) 
	{
		if(sender instanceof Player)
		{
			if(plugin.event = true && plugin.AdvPlayersList.contains((Player)sender))
			{
				sender.sendMessage("You are leaving the Pure Quests. :(");
				plugin.AdvPlayersList.remove((Player) sender);
				Player player = (Player) sender;
				player.teleport(new Location(plugin.getServer().getWorld("World"),plugin.getServer().getWorld("World").getSpawnLocation().getX(),plugin.getServer().getWorld("World").getSpawnLocation().getY(),plugin.getServer().getWorld("World").getSpawnLocation().getZ()));
				player.setGameMode(GameMode.SURVIVAL);
				return true;
			}
			
		}
		return false;
	}
	public boolean startGame(CommandSender sender)
	{
		//teleports players in the event to their specific start locations
		if(sender.hasPermission("AvPlugin.startGame"))
		{
			plugin.getServer().broadcastMessage("Dont move, you will be teleported!");
			for(Player player: plugin.AdvPlayersList)
			{
				if(player.getScoreboardTags().contains("1"))
				{
					player.teleport(plugin.group1);
				}
				if(player.getScoreboardTags().contains("2"))
				{
					player.teleport(plugin.group2);
				}
				if(player.getScoreboardTags().contains("3"))
				{
					player.teleport(plugin.group3);
				}
				if(player.getScoreboardTags().contains("4"))
				{
					player.teleport(plugin.group4);
				}
				plugin.getServer().broadcastMessage("The Quest has begun! Survive and Win!");
				
				return true;
			}
		}
		return false;
	}
	public boolean setGroupHome(CommandSender sender, String args[])
	{
		if(sender.hasPermission("AdvPlugin.setGroupHomes"))
		{
			if(args.length == 4)
			{
				if(args[0] == "1")
				{
					plugin.group1.setX(Integer.parseInt(args[1]));
					plugin.group1.setY(Integer.parseInt(args[2]));
					plugin.group1.setZ(Integer.parseInt(args[3]));
					sender.sendMessage("Group 1" + " home has been set to "+ args[1] + ","+ args[2] + "," + args[3]);
					return true;
				}
				if(args[0] == "2")
				{
					plugin.group2.setX(Integer.parseInt(args[1]));
					plugin.group2.setY(Integer.parseInt(args[2]));
					plugin.group2.setZ(Integer.parseInt(args[3]));
					sender.sendMessage("Group 2" + " home has been set to "+ args[1] + ","+ args[2] + "," + args[3]);
					return true;
				}
				if(args[0] == "3")
				{
					plugin.group3.setX(Integer.parseInt(args[1]));
					plugin.group3.setY(Integer.parseInt(args[2]));
					plugin.group3.setZ(Integer.parseInt(args[3]));
					sender.sendMessage("Group 3" + " home has been set to "+ args[1] + ","+ args[2] + "," + args[3]);
					return true;
				}
				if(args[0] == "4")
				{
					plugin.group4.setX(Integer.parseInt(args[1]));
					plugin.group4.setY(Integer.parseInt(args[2]));
					plugin.group4.setZ(Integer.parseInt(args[3]));
					sender.sendMessage("Group 4" + " home has been set to "+ args[1] + ","+ args[2] + "," + args[3]);
					return true;
				}
			}
			sender.sendMessage("Wrong number of arguments!");
			return false;
		}
		return false;
	}
}

