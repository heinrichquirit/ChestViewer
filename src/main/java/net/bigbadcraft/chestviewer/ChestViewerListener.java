package main.java.net.bigbadcraft.chestviewer;

import main.java.net.bigbadcraft.chestviewer.utils.Methods;
import main.java.net.bigbadcraft.chestviewer.utils.Perm;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Chest;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

public class ChestViewerListener implements Listener {
	
	private final ChatColor GREEN = ChatColor.GREEN;
	private final ChatColor YELLOW = ChatColor.YELLOW;
	private final ChatColor RED = ChatColor.RED;
	
	private Methods methods;
	@SuppressWarnings("unused")
	private ViewerPlugin plugin;
	
	public ChestViewerListener(ViewerPlugin plugin) {
		this.plugin = plugin;
		methods = plugin.methods;
	}
	
	@EventHandler
	public void onSignChange(SignChangeEvent event) {
		final Player player = event.getPlayer();
		String line = event.getLine(1);
		if (event.getLine(1).equalsIgnoreCase(ChatColor.DARK_BLUE + "View-Only")) {
			event.setCancelled(true);
			player.sendMessage(RED + "You cannot create a view only chest like that.");
		} else if (event.getLine(0).equalsIgnoreCase("[viewonly]") && line.equals("")) {
			if (player.hasPermission(Perm.PERM)) {
				event.setLine(0, YELLOW + "Everyone");
				event.setLine(1, ChatColor.DARK_BLUE + "View-Only");
				player.sendMessage(GREEN + "Succesfully created a view only chest.");
			} 
		} else if (event.getLine(0).equalsIgnoreCase("[viewonly]") && !line.equals("")) {
			if (player.hasPermission(Perm.PERM)) {
				event.setLine(0, YELLOW + line);
				event.setLine(1, ChatColor.DARK_BLUE + "View-Only");
				player.sendMessage(GREEN + "Succesfully created a view only chest for " + YELLOW + line + ".");
			}
		}
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		final Player player = event.getPlayer();
		Block clickedBlock = event.getClickedBlock();
		if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if (clickedBlock.getType() == Material.WALL_SIGN || clickedBlock.getType() == Material.SIGN_POST) {
				Sign sign = (Sign) clickedBlock.getState();
				Block chestBlock = getAttachedBlock(Material.WALL_SIGN, clickedBlock);
				if (chestBlock == null) return;
				if (chestBlock.getType() == Material.CHEST) {
					Chest chest = (Chest) chestBlock.getState();
					if (sign.getLine(0).equals(YELLOW + "Everyone")) {
						if (sign.getLine(1).equals(ChatColor.DARK_BLUE + "View-Only")) {
							if (methods.isSingleChest(chest)) {
								methods.saveSingleChest(chest);
								methods.sendSingleChestView(player, chest);
								methods.removeSingleContents(chest);
								player.sendMessage(GREEN + "You're now viewing the chest.");
							}
						}
					}
					if (sign.getLine(0).equalsIgnoreCase(player.getName())) {
						if (sign.getLine(1).equals(ChatColor.DARK_BLUE + "View-Only")) {
							if (methods.isSingleChest(chest)) {
								methods.saveSingleChest(chest);
								methods.sendSingleChestView(player, chest);
								methods.removeSingleContents(chest);
								player.sendMessage(GREEN + "You're now viewing the chest.");
							}
						}
					} else {
						if (!sign.getLine(0).equals(YELLOW + "Everyone")) {
							player.sendMessage(GREEN + "Only " + YELLOW + sign.getLine(0) + GREEN + " can view this chest.");
						}
					}
				} 
			}
		}
	}
	
	private Block getAttachedBlock(Material mat, Block b) {
		if (b.getType() != mat) return null;
	    @SuppressWarnings("deprecation")
		int face = b.getData();
	    switch(face) {
	    case 3: return b.getRelative(BlockFace.NORTH);
	    case 4: return b.getRelative(BlockFace.EAST);
	    case 5: return b.getRelative(BlockFace.WEST);
	    case 2: return b.getRelative(BlockFace.SOUTH);
	    }
	    return null;
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent event) {
		if (event.getInventory().getType() == InventoryType.CHEST) {
			Inventory visual = (Inventory) event.getInventory();
			if (visual.getTitle().equalsIgnoreCase(ChatColor.DARK_BLUE + "Viewing Chest")) {
				event.setCancelled(true);
			}
		}
	}

}
