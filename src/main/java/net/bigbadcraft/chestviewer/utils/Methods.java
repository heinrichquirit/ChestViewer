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
	private final HashMap<Location, ItemStack[]> doubleChest = new HashMap<Location, ItemStack[]>();
	
	public void saveSingleChest(Chest chest) {
		ItemStack[] singleChestContents = chest.getBlockInventory().getContents();
		singleChest.put(chest.getLocation(), singleChestContents);
	}
	
	public void saveDoubleChest(Chest chest) {
		ItemStack[] doubleChestContents = chest.getBlockInventory().getContents();
		doubleChest.put(chest.getLocation(), doubleChestContents);
	}
	
	public void removeChestContents(Chest chest) {
		if (isSingleChest(chest)) {
			singleChest.clear();
		} else if (isDoubleChest(chest)) {
			doubleChest.clear();
		}
	}
	
	public void sendChestView(Player player, Chest chest) {
		if (isSingleChest(chest)) {
			Inventory visual = Bukkit.createInventory(player, 27, DARK_BLUE + "Viewing Chest");
			visual.setContents(singleChest.get(chest.getLocation()));
			player.openInventory(visual);
		} else if (isDoubleChest(chest)) {
			Inventory visual = Bukkit.createInventory(player, 54, DARK_BLUE + "Viewing Chest");
			visual.setContents(doubleChest.get(chest.getLocation()));
			player.openInventory(visual);
		}
	}
	
	public void clearMaps() {
		singleChest.clear();
		doubleChest.clear();
	}
	
	public boolean isSingleChest(Chest chest) {
		if (chest.getInventory().getSize() == 27) return true;
		return false;
	}
	
	public boolean isDoubleChest(Chest chest) {
		if (chest.getInventory().getSize() == 54) return true;
		return false;
	}
	
}
