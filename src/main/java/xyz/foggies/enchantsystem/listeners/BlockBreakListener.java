package xyz.foggies.enchantsystem.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import xyz.foggies.enchantsystem.EnchantSystem;
import xyz.foggies.enchantsystem.manager.EnchantManager;

/**
 * @author foggies on 09/10/2021.
 * @project EnchantmentSystem
 */
public class BlockBreakListener implements Listener {

	private final EnchantManager enchantManager = EnchantSystem.getEnchantManager();

	@EventHandler
	public void onBlockBreak(BlockBreakEvent e){
		enchantManager.simulateEnchantment(e);
	}

}
