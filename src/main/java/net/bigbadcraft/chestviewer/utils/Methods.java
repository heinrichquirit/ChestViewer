package main.java.net.bigbadcraft.chestviewer.utils;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Chest;
import org.bukkit.block.DoubleChest;
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
	
	public void saveDoubleChest(DoubleChest chest) {
		ItemStack[] dbleChestContents = chest.getInventory().getContents();
		doubleChest.put(chest.getLocation(), dbleChestContents);
	}
	
	public void removeSingleContents(Chest chest) {
		singleChest.remove(chest.getLocation());
	}
	
	public void removeDoubleContents(DoubleChest chest) {
		doubleChest.remove(chest.getLocation());
	}
	
	public void clearMaps() {
		singleChest.clear();
		doubleChest.clear();
	}
	
	public void sendSingleChestView(Player player, Chest chest) {
		if (isSingleChest(chest)) {
			Inventory visual = Bukkit.createInventory(player, 27, DARK_BLUE + "Viewing Chest");
			visual.setContents(singleChest.get(chest.getLocation()));
			player.openInventory(visual);
		}
	}
	
	public void sendDoubleChestView(Player player, DoubleChest chest) {
		Inventory visual = Bukkit.createInventory(player, 54, DARK_BLUE + "Viewing Chest");
		visual.setContents(doubleChest.get(chest.getLocation()));
		player.openInventory(visual);
	}
	
	public boolean isSingleChest(Chest chest) {
		return chest.getInventory().getSize() == 27;
	}
	
}
