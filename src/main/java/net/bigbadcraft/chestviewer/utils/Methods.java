package main.java.net.bigbadcraft.chestviewer.utils;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class Methods {
	
	private final ChatColor DARK_BLUE = ChatColor.DARK_BLUE;
	
	private final HashMap<Location, ItemStack[]> singleChest = new HashMap<Location, ItemStack[]>();
	
	public void saveSingleChest(Chest chest) {
		ItemStack[] singleChestContents = chest.getBlockInventory().getContents();
		singleChest.put(chest.getLocation(), singleChestContents);
	}
	
	public void removeChestContents() {
		singleChest.clear();
	}
	
	public void sendChestView(Player player, Chest chest) {
		if (isSingleChest(chest)) {
			Inventory visual = Bukkit.createInventory(player, 27, DARK_BLUE + "Viewing Chest");
			visual.setContents(singleChest.get(chest.getLocation()));
			player.openInventory(visual);
		}
	}
	
	public boolean isSingleChest(Chest chest) {
		if (chest.getInventory().getSize() == 27) return true;
		return false;
	}
	
}
