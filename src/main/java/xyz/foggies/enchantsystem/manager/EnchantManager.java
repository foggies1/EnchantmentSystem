package xyz.foggies.enchantsystem.manager;

import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import xyz.foggies.enchantsystem.EnchantSystem;
import xyz.foggies.enchantsystem.model.Enchantment;
import xyz.foggies.enchantsystem.utils.PersistentDataUtilities;

import java.util.ArrayList;
import java.util.List;

/**
 * @author foggies on 09/10/2021.
 * @project EnchantmentSystem
 */
public class EnchantManager {

	/*
		Simulates an enchantment by looping through
		all possible enchantments and running the
		blockBreakHandler within the enchantment object.
	 */
	public void simulateEnchantment(BlockBreakEvent e) {
		Player player = e.getPlayer();
		ItemStack itemInHand = player.getInventory().getItemInMainHand();

		for (Enchantment enchantment : EnchantSystem.getEnchantmentList()) {
			if (itemHasEnchantment(itemInHand, enchantment))
				enchantment.blockBreakHandler(e);
		}

	}

	/*
		Generates a new lore with all enchantments
		that are on the current item-stack.

		Example: Flame Mining - 405
	 */
	public List<String> generateEnchantmentLore(ItemStack itemStack) {
		List<String> lore = new ArrayList<>();
		for (Enchantment enchantment : EnchantSystem.getEnchantmentList()) {
			if (itemHasEnchantment(itemStack, enchantment))
				lore.add(enchantment.getDisplayName() + " - " + getEnchantmentLevel(itemStack, enchantment));
		}
		return lore;
	}


	/*
		Attempts to take levels from an enchantment,
		If the (level - currentLevel) is less than 0 it'll
		set the level to the current level.
	 */
	public void takeEnchantmentLevel(ItemStack itemStack, Enchantment enchantment, long level) {
		if (getEnchantmentLevel(itemStack, enchantment) - level < 0)
			level = getEnchantmentLevel(itemStack, enchantment);
		setEnchantmentLevel(itemStack, enchantment, getEnchantmentLevel(itemStack, enchantment) - level);
	}

	/*
		Add levels onto an enchantment on an item-stack.
	 */
	public void addEnchantmentLevel(ItemStack itemStack, Enchantment enchantment, long level) {
		setEnchantmentLevel(itemStack, enchantment, getEnchantmentLevel(itemStack, enchantment) + level);
	}

	/*
		Attempts to set an enchantment level on an item-stack.
		It will not let them apply it if the enchantment
		isn't applicable to the item-type.
	 */
	public void setEnchantmentLevel(ItemStack itemStack, Enchantment enchantment, long level) {
		if (!enchantment.getApplicable().contains(itemStack.getType())) return;

		// Adds the enchantment key to the item-stack's persistent data
		new PersistentDataUtilities(itemStack, enchantment.getEnchantmentKey()).setLong(level);
	}

	/*
		Attempts to remove an enchantment from an item-stack.
		It will not let them remove it if the enchantment
		isn't on the item.
	 */
	public void removeEnchantmentFromItem(ItemStack itemStack, Enchantment enchantment, long level) {
		if (!itemHasEnchantment(itemStack, enchantment)) return;

		// Adds the enchantment key to the item-stack's persistent data
		new PersistentDataUtilities(itemStack, enchantment.getEnchantmentKey()).removeKey();
	}

	/*
		Gets the enchantment level on the item-stack.
		If the item-stack doesn't  contain the enchantment
		it'll return -1;
	 */
	public long getEnchantmentLevel(ItemStack itemStack, Enchantment enchantment) {
		if (!itemHasEnchantment(itemStack, enchantment)) return -1;
		return new PersistentDataUtilities(itemStack, enchantment.getEnchantmentKey()).getLong();
	}

	/*
		Checks the item-stacks persistent data to find whether it
		contains the enchantment key.
	 */
	private boolean itemHasEnchantment(ItemStack itemStack, Enchantment enchantment) {
		return new PersistentDataUtilities(itemStack, enchantment.getEnchantmentKey()).containsKey();
	}

}
