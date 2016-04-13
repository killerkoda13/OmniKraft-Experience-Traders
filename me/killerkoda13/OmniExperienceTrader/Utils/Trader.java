package me.killerkoda13.OmniExperienceTrader.Utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import me.killerkoda13.OmniExperienceTrader.OmniExperienceTrader;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.Plugin;
import org.json.simple.JSONObject;

/***
 *		---------------------------------
 *		@Author Killerkoda13 (Alex Jones)
 *		@date Apr 11, 2016
 *		---------------------------------
 */

public class Trader {	


	Plugin plugin;		//Instance of plugin

	String line1;			//First line of trader
	String line2;			//Second line of trader
	int price;				//Price of item in trader
	int amount;				//Amount of item trader is selling
	boolean gravity;		//If trader entity should have gravity
	ItemStack hand;			//ItemStack object of item in players hand. Only used if item id is absent
	World world;			//World the trader exists in
	Location location;		//Location where the trader exists
	ArmorStand trader;
	Item item;
	UUID uuid;
	UUID baseUID;
	/**
	 * @param firstline first line of trader
	 * @param secondline second line of trader
	 * @param price price of trader in experience
	 * @param amount amount of item trader is selling
	 * @param itemid id of item trader is selling
	 * @param hand ItemStack in players hand
	 * @param world world the trader exists in
	 * @param location location of trader
	 */
	public Trader(String firstline, String secondline, int price, int amount, ItemStack hand, World world, Location location)
	{
		this.line1 = firstline;
		this.line2 = secondline;
		this.price = price;
		this.amount = amount;
		hand.setAmount(1);
		this.hand = hand;
		this.world = world;
		this.location = location;
		this.uuid = UUID.randomUUID();
		this.plugin = OmniExperienceTrader.getInstance();	
		
	}

	/**
	 * @param firstline first line of trader
	 * @param secondline second line of trader
	 * @param price price of trader in experience
	 * @param amount amount of item trader is selling
	 * @param hand ItemStack in players hand
	 * @param gravity if trader has gravity
	 * @param world world the trader exists in
	 * @param location location of trader
	 */
	public Trader(String firstline, String secondline, int price, int amount, ItemStack hand, boolean gravity, World world, Location location)
	{
		this.line1 = firstline;
		this.line2 = secondline;
		this.price = price;
		this.amount = amount;
		hand.setAmount(1);
		this.hand = hand;
		this.gravity = gravity;
		this.world = world;
		Location locations = new Location(world,location.getBlockX()+0.5,location.getBlockY(),location.getBlockZ()+0.5);
		this.location = locations;
		this.uuid = UUID.randomUUID();
		this.plugin = OmniExperienceTrader.getInstance();	

	}
	
	public void setline1(String string)
	{
		this.line2 = string;
	}

	//WIP 4/12/2016 - 9:27 AM
	public Trader(JSONObject traderJSON)
	{
		this.line2 = (String) traderJSON.get("hitbox.CustomName");
		this.price = Math.toIntExact((long) traderJSON.get("hitbox.price"));
		this.amount = Math.toIntExact((long) traderJSON.get("hitbox.amount"));
		int x = Math.toIntExact((long) traderJSON.get("hitbox.location.x"));
		int y = Math.toIntExact((long) traderJSON.get("hitbox.location.y"));
		int z = Math.toIntExact((long) traderJSON.get("hitbox.location.z"));
		this.plugin = OmniExperienceTrader.getInstance();	
		World world = (World) Bukkit.getWorld((String) traderJSON.get("hitbox.location.world"));
		this.world = world;
		Location location = new Location(world,x+0.5,y,z+0.5);
		this.location = location;
		this.gravity = (boolean) traderJSON.get("hitbox.gravity");
		this.line1 = (String) traderJSON.get("item.CustomName");
		ItemStack stack = null;
		try {
			stack = ItemUtils.itemFrom64((String) traderJSON.get("item.base64"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.hand = stack;
	}
	

	
	public void removeTrader()
	{
		for(Entity e : location.getChunk().getEntities())
		{
			if(e.getUniqueId().equals(this.baseUID))
			{
				e.getPassenger().remove();
				e.remove();
			}
		}
	}
	
	/**
	 * Creates trader
	 * @return true if trader was created successfully. returns false if trader was not created successfully.
	 */
	public boolean createTrader()
	{
		
		ArmorStand hitbox = (ArmorStand) world.spawnEntity(location, EntityType.ARMOR_STAND);

		hitbox.setMetadata("xptrader.price", new FixedMetadataValue(OmniExperienceTrader.getInstance(), price));
		hitbox.setCustomName(line1);
		hitbox.setCustomNameVisible(true);
		hitbox.setMetadata("xptrader.amount", new FixedMetadataValue(OmniExperienceTrader.getInstance(), amount));
		hitbox.setMetadata("xptrader.hand", new FixedMetadataValue(OmniExperienceTrader.getInstance(), hand));
		hitbox.setVisible(false);
		hitbox.setSmall(true);
		hitbox.setBasePlate(false);
		hitbox.setGravity(gravity);

		Item display = (Item) world.dropItem(location, hand);
		display.setCustomName(line2);
		display.setPickupDelay(Integer.MAX_VALUE);
		display.setCustomNameVisible(true);
		hitbox.setPassenger(display);
		this.item = display;
		this.trader = hitbox;
		this.baseUID = hitbox.getUniqueId();
		return true;
	}

	/**
	 * Saves the trader to /plugins/ExpTraders/traders/
	 * Saves as UUID'd JSON form
	 */
	@SuppressWarnings("unchecked")
	public boolean save()
	{
		File pluginDirectory = plugin.getDataFolder();
		File traderDirectory = new File(pluginDirectory+"/traders/");
		String JSON = null;
		boolean ret = true;
		JSONObject obj = new JSONObject();
		//Check if hand object is initialized.
		if(hand !=null)
		{
			obj.put("hitbox.CustomName", line2);
			obj.put("hitbox.price", price);
			obj.put("hitbox.amount",amount);
			obj.put("hitbox.location.x", location.getBlock().getX());
			obj.put("hitbox.location.y", location.getBlock().getY());
			obj.put("hitbox.location.z", location.getBlock().getZ());
			obj.put("hitbox.location.world", world.getName());
			obj.put("hitbox.gravity", gravity);
			obj.put("item.CustomName", line1);
			obj.put("item.base64", ItemUtils.itemTo64(hand));
			JSON = obj.toJSONString();
		}else
		{
			ret = false;
		}
		
		//Check if tradering directoy exists if not make one.
		if(!traderDirectory.exists())
		{
			traderDirectory.mkdirs();
		}else
		{
			File traderDoc = new File(traderDirectory+"/"+uuid.toString()+".json");
			if(!traderDoc.exists())
			{
				try {
					FileWriter writer = new FileWriter(traderDoc);
					if(JSON !=null)
					{
					writer.write(JSON);
					writer.flush();
					writer.close();
					}else
					{
						ret = false;
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else
			{
				ret = false;
			}
		}
		return ret;
	}
}
