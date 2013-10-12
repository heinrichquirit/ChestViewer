package main.java.net.bigbadcraft.chestviewer;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ViewCommand implements CommandExecutor {

	private final ChatColor RED = ChatColor.RED;
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmdObj, String lbl, String[] strings) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (cmdObj.getName().equalsIgnoreCase("viewchestfor")) {
				return viewChestFor(player, strings);
			}
		} else {
			sender.sendMessage(RED + "You are not a player, get back in game!");
		}
		return true;
	}
	
	private boolean viewChestFor(Player player, String[] strings) {
		return true;
	}
	
}
