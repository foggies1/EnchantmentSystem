package xyz.foggies.enchantsystem.examples;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.event.block.BlockBreakEvent;
import xyz.foggies.enchantsystem.EnchantSystem;
import xyz.foggies.enchantsystem.model.Enchantment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author foggies on 09/10/2021.
 * @project EnchantmentSystem
 */
public class FlameMining implements Enchantment {

	@Override
	public long getStartLevel() {
		return 1;
	}

	@Override
	public long getMaximumLevel() {
		return 10;
	}

	@Override
	public long getAdminMaximumLevel() {
		return 9999;
	}

	@Override
	public NamespacedKey getEnchantmentKey() {
		return new NamespacedKey(EnchantSystem.getPlugin(), getIdentifier());
	}

	@Override
	public String getIdentifier() {
		return "FLAME_MINING";
	}

	@Override
	public String getDisplayName() {
		return "&6Flame Mining";
	}

	@Override
	public List<String> getDescription() {
		return new ArrayList<>(
				Arrays.asList(
						"&7Each time you mine a block with this",
						"&7enchantment on your item, a flame will",
						"&7spawn at the block."
				)
		);
	}

	@Override
	public List<Material> getApplicable() {
		return new ArrayList<>(
				Collections.singletonList(Material.DIAMOND_PICKAXE)
		);
	}

	@Override
	public void blockBreakHandler(BlockBreakEvent e) {
		Block block = e.getBlock();
		Location location = block.getLocation();
		location.getWorld().spawnParticle(Particle.FLAME, location, 10);
	}

}
