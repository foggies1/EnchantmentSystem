package xyz.foggies.enchantsystem.model;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.List;

/**
 * @author foggies on 09/10/2021.
 * @project EnchantmentSystem
 */

public interface Enchantment {

	long getStartLevel();
	long getMaximumLevel();
	long getAdminMaximumLevel();
	NamespacedKey getEnchantmentKey();
	String getIdentifier();
	String getDisplayName();
	List<String> getDescription();
	List<Material> getApplicable();
	void blockBreakHandler(BlockBreakEvent e);

}
