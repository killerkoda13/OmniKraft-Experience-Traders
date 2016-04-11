package me.killerkoda13.OmniExperienceTrader;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class OmniExperienceTrader extends JavaPlugin{
	
	
	static Plugin plugin;
	
	@Override
	public void onEnable()
	{
		this.plugin = plugin;
	}
	
	@Override
	public void onDisable()
	{
		
	}
	
	public static Plugin getInstance()
	{
		return plugin;
	}
}
