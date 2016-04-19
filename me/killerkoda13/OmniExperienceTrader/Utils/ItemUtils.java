package me.killerkoda13.OmniExperienceTrader.Utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

/***
 *		---------------------------------
 *		@Author Killerkoda13 (Alex Jones)
 *		@date Apr 16, 2016
 *		---------------------------------
 */
public class ItemUtils {

	public static int getEmptySlots(final Inventory inventory) {
		int amount = 0;
		for (ItemStack stack : inventory.getContents()) {
			if (stack == null) {
				amount++;
			}
		}
		return amount;
	}

		public static String itemTo64(ItemStack stack) throws IllegalStateException {
			try {
				ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
				BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(outputStream);
				dataOutput.writeObject(stack);

				// Serialize that array
				dataOutput.close();
				return Base64Coder.encodeLines(outputStream.toByteArray());
			}
			catch (Exception e) {
				throw new IllegalStateException("Unable to save item stack.", e);
			}
		}

		public static ItemStack itemFrom64(String data) throws IOException {
			try {
				ByteArrayInputStream inputStream = new ByteArrayInputStream(Base64Coder.decodeLines(data));
				BukkitObjectInputStream dataInput = new BukkitObjectInputStream(inputStream);
				try {
					return (ItemStack) dataInput.readObject();
				} finally {
					dataInput.close();
				}
			}
			catch (ClassNotFoundException e) {
				throw new IOException("Class not found exception.", e);
			}
		}

	}

