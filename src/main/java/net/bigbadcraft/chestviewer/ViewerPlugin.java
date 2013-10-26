package main.java.net.bigbadcraft.chestviewer;

import main.java.net.bigbadcraft.chestviewer.utils.Methods;

import org.bukkit.plugin.java.JavaPlugin;

public class ViewerPlugin extends JavaPlugin {
	
	public Methods methods;
	
	@Override
	public void onEnable() {
		methods = new Methods();
		getCommand("viewchestfor").setExecutor(new ViewCommand(this));
		getServer().getPluginManager().registerEvents(new ChestViewerListener(this), this);
	}
	
	@Override
	public void onDisable() {
		methods.clearMaps();
	}
	
}
