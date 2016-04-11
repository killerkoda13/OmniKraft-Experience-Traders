package me.killerkoda13.OmniExperienceTrader.Utils;

import org.bukkit.inventory.ItemStack;

public class Trader {
	
	String line1;
	String line2;
	int price;
	int amount;
	int itemid;
	short data;
	boolean gravity;
	ItemStack hand;
	
	public Trader(String firstline, String secondline, int price, int amount, int itemid, short data, boolean gravity)
	{
		this.line1 = firstline;
		this.line2 = secondline;
		this.price = price;
		this.amount = amount;
		this.itemid = itemid;
		this.data = data;
		this.gravity = gravity;
	}

	public Trader(String firstline, String secondline, int price, int amount, int itemid, boolean gravity)
	{
		this.line1 = firstline;
		this.line2 = secondline;
		this.price = price;
		this.amount = amount;
		this.itemid = itemid;
		this.gravity = gravity;
	}
	
	public Trader(String firstline, String secondline, int price, int amount, int itemid, short data)
	{
		this.line1 = firstline;
		this.line2 = secondline;
		this.price = price;
		this.amount = amount;
		this.itemid = itemid;
		this.data = data;
	}
	
	public Trader(String firstline, String secondline, int price, int amount, int itemid)
	{
		this.line1 = firstline;
		this.line2 = secondline;
		this.price = price;
		this.amount = amount;
		this.itemid = itemid;

	}
	
	public Trader(String firstline, String secondline, int price, int amount, ItemStack hand)
	{
		this.line1 = firstline;
		this.line2 = secondline;
		this.price = price;
		this.amount = amount;
		this.hand = hand;
	}
	
	public Trader(String firstline, String secondline, int price, int amount, ItemStack hand, boolean gravity)
	{
		this.line1 = firstline;
		this.line2 = secondline;
		this.price = price;
		this.amount = amount;
		this.hand = hand;
		this.gravity = gravity;
	}
}
