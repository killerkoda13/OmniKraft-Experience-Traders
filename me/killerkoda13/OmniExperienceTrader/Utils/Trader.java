package me.killerkoda13.OmniExperienceTrader.Utils;

import me.killerkoda13.OmniExperienceTrader.OmniExperienceTrader;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.Plugin;

/***
 *		---------------------------------
 *		@Author Killerkoda13 (Alex Jones)
 *		@date Apr 11, 2016
 *		---------------------------------
 */

public class Trader {	
	
	
	Plugin plugin = OmniExperienceTrader.getInstance();			//Instance of plugin
	
	String line1;			//First line of trader
	String line2;			//Second line of trader
	int price;				//Price of item in trader
	int amount;				//Amount of item trader is selling
	boolean gravity;		//If trader entity should have gravity
	ItemStack hand;			//ItemStack object of item in players hand. Only used if item id is absent
	World world;			//World the trader exists in
	Location location;		//Location where the trader exists
	
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
		this.hand = hand;
		this.world = world;
		this.location = location;
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
		this.hand = hand;
		this.gravity = gravity;
		this.world = world;
		this.location = location;
	}

	/**
	 * Creates trader
	 * @return true if trader was created successfully. returns false if trader was not created successfully.
	 */
	public boolean createTrader()
	{

		
		
		ArmorStand hitbox = (ArmorStand) world.spawnEntity(location, EntityType.ARMOR_STAND);
		hitbox.setMetadata("xptrader.line1", new FixedMetadataValue(plugin, line1));
		hitbox.setMetadata("xptrader.line2", new FixedMetadataValue(plugin, line2));
		hitbox.setMetadata("xptrader.price", new FixedMetadataValue(plugin, price));
		hitbox.setMetadata("xptrader.data", new FixedMetadataValue(plugin, gravity));
		hitbox.setMetadata("xptrader.data", new FixedMetadataValue(plugin, hand));


	}
}
