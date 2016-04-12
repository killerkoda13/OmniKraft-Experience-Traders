package me.killerkoda13.OmniExperienceTrader;

import me.killerkoda13.OmniExperienceTrader.Utils.Trader;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
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
	
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		
		Player p = (Player) sender;
		
		if(cmd.getName().equalsIgnoreCase("xptrader"))
		{
			Trader trader = new Trader("first", "second", 1, 1, p.getItemInHand(), true, p.getWorld(), p.getLocation());
			trader.createTrader();
			trader.save();
		}
		return false;
		
	}
}
