package main.java.net.bigbadcraft.chestviewer;

import main.java.net.bigbadcraft.chestviewer.utils.Methods;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ViewCommand implements CommandExecutor {

	private final ChatColor GREEN = ChatColor.GREEN;
	private final ChatColor YELLOW = ChatColor.YELLOW;
	private final ChatColor RED = ChatColor.RED;
	
	private Methods methods;
	@SuppressWarnings("unused")
	private ViewerPlugin plugin;
	
	public ViewCommand(ViewerPlugin plugin) {
		this.plugin = plugin;
		methods = plugin.methods;
	}
	
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
	
	@SuppressWarnings("deprecation")
	private boolean viewChestFor(Player player, String[] strings) {
		if (player.getTargetBlock(null, 50).getType() == Material.CHEST) {
			Chest chest = (Chest) player.getTargetBlock(null, 50).getState();
			if (methods.isSingleChest(chest)) {
				if (strings.length == 0) {
					player.sendMessage(RED + "Incorrect syntax, usage: /viewchestfor <player>");
					return true;
				} else if (strings.length == 1) {
					Player target = Bukkit.getPlayer(strings[0]);
					if (target != null) {
						methods.saveSingleChest(chest);
						methods.sendChestView(target, chest);
						methods.removeChestContents();
						player.sendMessage(YELLOW + target.getName() + GREEN + " is now viewing the chest.");
						target.sendMessage(YELLOW + player.getName() + GREEN + " made you view the chest.");
					} else {
						player.sendMessage(RED + strings[0] + " is offline.");
					}
				}
			}
		} else {
			player.sendMessage(RED + "That is not a chest.");
		}
		return true;
	}
	
}
