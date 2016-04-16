package me.killerkoda13.OmniExperienceTrader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import me.killerkoda13.OmniExperienceTrader.Utils.ItemUtils;
import me.killerkoda13.OmniExperienceTrader.Utils.Trader;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class OmniExperienceTrader extends JavaPlugin implements Listener{

	ArrayList<Player> remover = new ArrayList<Player>();
	static Plugin plugin;
	ArrayList<Trader> traders = new ArrayList<Trader>();

	@Override
	public void onEnable()
	{
		getServer().getPluginManager().registerEvents(this,this);

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

	@EventHandler
	public void InteractAtEntity(PlayerInteractAtEntityEvent e)
	{
		if(e.getRightClicked().getType().equals(EntityType.ARMOR_STAND))
		{
			if(e.getRightClicked().hasMetadata("xptrader.UUID"))
			{
				if(remover.contains(e.getPlayer()))
				{
					String UUID = e.getRightClicked().getMetadata("xptrader.UUID").get(0).asString();
					try {
						Trader trader = Trader.getTrader(UUID);
						trader.removeTrader();
						e.getPlayer().sendMessage(ChatColor.GREEN+"Removed trader with UUID: "+UUID);
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}else
				{
					if(e.getRightClicked().hasMetadata("xptrader.price"))
					{
						if(e.getPlayer().getLevel() >= e.getRightClicked().getMetadata("xptrader.price").get(0).asInt())
						{
							if(ItemUtils.getEmptySlots(e.getPlayer().getInventory()) < 36)
							{
								e.getPlayer().setLevel(e.getPlayer().getLevel()-e.getRightClicked().getMetadata("xptrader.price").get(0).asInt());
								ItemStack item = null;
								try {
									item = ItemUtils.itemFrom64(e.getRightClicked().getMetadata("xptrader.base64").get(0).asString());
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								item.setAmount(e.getPlayer().getMetadata("xptrader.amount").get(0).asInt());
								e.getPlayer().getInventory().addItem(item);
								e.getPlayer().sendMessage(ChatColor.GREEN+"Item successfully purchased for "+e.getRightClicked().getMetadata("xptrader.price").get(0).asInt()+" XP");
								
							}

						}else
						{
							e.getPlayer().sendMessage(ChatColor.RED+"Not enough EXP!");
							int required = e.getRightClicked().getMetadata("xptrader.price").get(0).asInt() - e.getPlayer().getLevel();
							e.getPlayer().sendMessage(ChatColor.RED+"An additional: "+ required+" level(s) is required.");
						}
					}
				}
			}
		}
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
				}else
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
						p.sendMessage(ChatColor.RED+"Please enter all arguments!");
						p.sendMessage(ChatColor.RED+"/xptrader create <firstline> <secondline> <xpcost> <amount> <gravity>");
						p.sendMessage(ChatColor.RED+"NOTE: <gavity> is optional!");
						p.sendMessage(ChatColor.RED+"EXAMPLE command: /xptrader create 20_Sponge 10_Levels 10 20 false");
					}
			}else if(args[0].equalsIgnoreCase("remove") || args[0].equalsIgnoreCase("r") || args[0].equalsIgnoreCase("destroy"))
			{
				if(remover.contains(p))
				{
					remover.remove(p);
					p.sendMessage(ChatColor.GREEN+"Remover tool no longer active.");
				}else
				{
					remover.add(p);
					p.sendMessage(ChatColor.GREEN+"Remover tool now active. Right click on the trader you wish to remove.");
				}
			}
		}
		{

		}
		return true;

	}
}
