package me.killerkoda13.OmniExperienceTrader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import me.killerkoda13.OmniExperienceTrader.Utils.Trader;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class OmniExperienceTrader extends JavaPlugin{
	
	
	static Plugin plugin;
	
	@Override
	public void onEnable()
	{
		this.plugin = this;
		if(!plugin.getDataFolder().exists())
		{
			plugin.getDataFolder().mkdirs();
		}
		
		File traderDirectory = new File(plugin.getDataFolder()+"/traders/");

		if(!traderDirectory.exists())
		{
			traderDirectory.mkdir();
		}
		

		for(File file : traderDirectory.listFiles())
		{		
				try {
					BufferedReader reader = new BufferedReader(new FileReader(file));
					String read = reader.readLine();
					reader.close();
					JSONParser parser = new JSONParser();
					JSONObject json = (JSONObject) parser.parse(read);
					Trader trader = new Trader(json);
					trader.setline1("TEST LINE 2");
					trader.createTrader();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

		}
	}
	
	@Override
	public void onDisable()
	{
		File traderDirectory = new File(plugin.getDataFolder()+"/traders/");

		for(File file : traderDirectory.listFiles())
		{
			System.out.println(file);
		}
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
