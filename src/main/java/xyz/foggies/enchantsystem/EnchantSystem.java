package xyz.foggies.enchantsystem;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.foggies.enchantsystem.examples.FlameMining;
import xyz.foggies.enchantsystem.listeners.BlockBreakListener;
import xyz.foggies.enchantsystem.manager.EnchantManager;
import xyz.foggies.enchantsystem.model.Enchantment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class EnchantSystem extends JavaPlugin {

	private static EnchantSystem plugin;
	private static List<Enchantment> enchantmentList;
	private static EnchantManager enchantManager;

	@Override
	public void onEnable() {
		plugin = this;
		enchantmentList = new ArrayList<>();

		registerEnchantments(new FlameMining());
		registerListeners(new BlockBreakListener());

		enchantManager = new EnchantManager();
	}

	@Override
	public void onDisable() {
		// Plugin shutdown logic
	}

	public void registerListeners(Listener... listeners){
		Arrays.stream(listeners).forEach(listener -> getServer().getPluginManager().registerEvents(listener, this));
	}

	public void registerEnchantments(Enchantment... enchantments){
		enchantmentList.addAll(Arrays.asList(enchantments));
	}

	public static EnchantManager getEnchantManager() {
		return enchantManager;
	}

	public static List<Enchantment> getEnchantmentList() {
		return enchantmentList;
	}

	public static EnchantSystem getPlugin() {
		return plugin;
	}
}
