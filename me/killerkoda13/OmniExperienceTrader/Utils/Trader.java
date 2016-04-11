package me.killerkoda13.OmniExperienceTrader.Utils;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.inventory.ItemStack;

/***
 *		---------------------------------
 *		@Author Killerkoda13 (Alex Jones)
 *		@date Apr 11, 2016
 *		---------------------------------
 */


public class Trader {	
	
	
	String line1;			//First line of trader
	String line2;			//Second line of trader
	int price;				//Price of item in trader
	int amount;				//Amount of item trader is selling
	int itemid;				//ID of item trader is selling
	short data;				//Data of item being sold. Defaulted to 0 if none.
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
	 * @param data data of item trader is selling
	 * @param gravity if trader has gravity
	 * @param world world the trader exists in
	 * @param location location of trader
	 */
	public Trader(String firstline, String secondline, int price, int amount, int itemid, short data, boolean gravity, World world, Location location)
	{
		this.line1 = firstline;
		this.line2 = secondline;
		this.price = price;
		this.amount = amount;
		this.itemid = itemid;
		this.data = data;
		this.gravity = gravity;
		this.world = world;
		this.location = location;
	}

	/**
	 * @param firstline first line of trader
	 * @param secondline second line of trader
	 * @param price price of trader in experience
	 * @param amount amount of item trader is selling
	 * @param itemid id of item trader is selling
	 * @param gravity if trader has gravity
	 * @param world world the trader exists in
	 * @param location location of trader
	 */
	public Trader(String firstline, String secondline, int price, int amount, int itemid, boolean gravity, World world, Location location)
	{
		this.line1 = firstline;
		this.line2 = secondline;
		this.price = price;
		this.amount = amount;
		this.itemid = itemid;
		this.gravity = gravity;
		this.world = world;
		this.location = location;
	}
	
	/**
	 * @param firstline first line of trader
	 * @param secondline second line of trader
	 * @param price price of trader in experience
	 * @param amount amount of item trader is selling
	 * @param itemid id of item trader is selling
	 * @param data data of item trader is selling
	 * @param world world the trader exists in
	 * @param location location of trader
	 */	
	public Trader(String firstline, String secondline, int price, int amount, int itemid, short data, World world, Location location)
	{
		this.line1 = firstline;
		this.line2 = secondline;
		this.price = price;
		this.amount = amount;
		this.itemid = itemid;
		this.data = data;
		this.world = world;
		this.location = location;

	}
	
	/**
	 * @param firstline first line of trader
	 * @param secondline second line of trader
	 * @param price price of trader in experience
	 * @param amount amount of item trader is selling
	 * @param itemid id of item trader is selling
	 * @param data data of item trader is selling
	 * @param world world the trader exists in
	 * @param location location of trader
	 */
	public Trader(String firstline, String secondline, int price, int amount, int itemid, World world, Location location)
	{
		this.line1 = firstline;
		this.line2 = secondline;
		this.price = price;
		this.amount = amount;
		this.itemid = itemid;
		this.world = world;
		this.location = location;

	}
	
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
}
