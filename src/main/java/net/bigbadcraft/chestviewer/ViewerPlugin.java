package main.java.net.bigbadcraft.chestviewer;

import main.java.net.bigbadcraft.chestviewer.utils.Methods;

import org.bukkit.plugin.java.JavaPlugin;

public class ViewerPlugin extends JavaPlugin {
	
	// Get the user
	// Get the user's block that's been looked at
	// Get the contents
	// Temporarily save the contents in an inventory type
	// and send the view to selected user
	
	// Use a sign
	// Check if the sign on the block's second line is equal to View Only
	// If the chest has a view only sign
	// Users can click the chest and view the temporarily saved contents
	
	// Events
	// InventoryClickEvent
	// SignChangeEvent
	// PlayerInteractedEvent
	
	// CHECK FOR DOUBLE CHESTS
	
	public Methods methods;
	
	@Override
	public void onEnable() {
		methods = new Methods();
		getCommand("viewchestfor").setExecutor(new ViewCommand());
		getServer().getPluginManager().registerEvents(new ChestViewerListener(this), this);
	}
	
	@Override
	public void onDisable() {
		methods.removeChestContents();
	}
	
}
