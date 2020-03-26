package io.github.RESKOM326.guillimanutils;

import org.bukkit.plugin.java.JavaPlugin;

public class GuillimanUtils extends JavaPlugin{
	@Override
	public void onEnable() {
		this.getCommand("ignite").setExecutor(new GuillimanUtilsCommandExecutor(this));
		this.getCommand("hideme").setExecutor(new GuillimanUtilsCommandExecutor(this));
		this.getCommand("unhideme").setExecutor(new GuillimanUtilsCommandExecutor(this));
		this.getLogger().info("GuillimanUtils is enabled!");
	}
	@Override
	public void onDisable() {
		this.getLogger().info("GuillimanUtils is disabled!");
	}
}