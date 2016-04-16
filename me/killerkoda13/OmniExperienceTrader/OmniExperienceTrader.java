package me.killerkoda13.OmniExperienceTrader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import me.killerkoda13.OmniExperienceTrader.Utils.Trader;

import org.bukkit.ChatColor;
import org.bukkit.Material;
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
	ArrayList<Trader> traders = new ArrayList<Trader>();

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
				trader.createTrader();
				traders.add(trader);
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
		for(Trader trader : traders)
		{
			System.out.println(trader);
		}
	}

	@Override
	public void onDisable()
	{		
		for(Trader trader : traders)
		{
			trader.removeTrader();
		}


	}

	public static Plugin getInstance()
	{
		return plugin;
	}


	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{

		Player p = (Player) sender;

		if(cmd.getName().equalsIgnoreCase("xptrader") || cmd.getName().equalsIgnoreCase("xpt"))
		{
			if(args[0].equalsIgnoreCase("create") || args[0].equalsIgnoreCase("c") || args[0].equalsIgnoreCase("spawn") || args[0].equalsIgnoreCase("make"))
			{
				if(p.getItemInHand().getType().equals(Material.AIR))
				{
					p.sendMessage(ChatColor.RED+"Can not make trader that sells AIR");
				}
				if(args.length == 6)
				{					
					try
					{
						String first = args[1].replaceAll("_", " ");
						String second = args[2].replaceAll("_", " ");
						int cost = Integer.parseInt(args[3]);
						int amount = Integer.parseInt(args[4]);
						boolean grav = false;
						if(args[5].equalsIgnoreCase("t") || args[5].equalsIgnoreCase("true"))
						{
							grav = true;
						}else if(args[5].equalsIgnoreCase("f") || args[5].equalsIgnoreCase("false"))
						{
							grav = false;
						}
						Trader trader = new Trader(first,second,cost,amount,p.getItemInHand(),grav,p.getWorld(),p.getLocation());
						trader.createTrader();
						trader.save();
					}catch(Exception e)
					{
						System.out.println(e);
					}
				}else if(args.length == 5)
				{
					try
					{
						String first = args[1].replaceAll("_", " ");
						String second = args[2].replaceAll("_", " ");
						int cost = Integer.parseInt(args[3]);
						int amount = Integer.parseInt(args[4]);
						Trader trader = new Trader(first,second,cost,amount,p.getItemInHand(),p.getWorld(),p.getLocation());
						trader.createTrader();
						trader.save();
					}catch(Exception e)
					{
						System.out.println(e);
					}
				}else
				{

				}
			}else 
			{

			}
		}
		{

		}
		return false;

	}
}
