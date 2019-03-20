package de.greenman1805.shards;

import net.milkbowl.vault.economy.Economy;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

	public static Economy econ = null;
	public static String prefix = "§8[§9Shards§8] §f";
	public static Main plugin;

	public static int start_money;

	public static String host;
	public static String port;
	public static String user;
	public static String password;
	public static String database;

	@Override
	public void onEnable() {
		plugin = this;
		setupEconomy();
		registerEconomy();
		setupConfig();
		getValues();
		checkDatabase();

		getServer().getPluginManager().registerEvents(new ShardListener(), this);
		
		ShardCommands sc = new ShardCommands();
		
		registerCommands("money", sc);
		registerCommands("pay", sc);
	}

	private void checkDatabase() {
		if (!MySQL.openConnection()) {
			this.setEnabled(false);
			System.out.println("MySQL Verbindung konnte nicht hergestellt werden!");
			return;
		}
		try {
			PreparedStatement ps = MySQL.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS money (UUID VARCHAR(100), money DOUBLE);");
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void setupConfig() {
		reloadConfig();
		getConfig().addDefault("Money on first join", 50000);
		getConfig().addDefault("MySQL.host", "localhost");
		getConfig().addDefault("MySQL.port", "3306");
		getConfig().addDefault("MySQL.user", "user");
		getConfig().addDefault("MySQL.password", "user");
		getConfig().addDefault("MySQL.database", "database");
		getConfig().options().copyDefaults(true);
		saveConfig();
	}

	private void getValues() {
		start_money = getConfig().getInt("Money on first join");
		host = getConfig().getString("MySQL.host");
		port = getConfig().getString("MySQL.port");
		user = getConfig().getString("MySQL.user");
		password = getConfig().getString("MySQL.password");
		database = getConfig().getString("MySQL.database");
	}

	@Override
	public void onDisable() {
		MySQL.closeConnection();
	}

	public void registerCommands(String cmd, CommandExecutor exe) {
		getCommand(cmd).setExecutor(exe);
	}

	private void registerEconomy() {
            getServer().getServicesManager().register(Economy.class, new VaultConnector(), this, ServicePriority.Highest);
	}

	private boolean setupEconomy() {
		if (getServer().getPluginManager().getPlugin("Vault") == null) {
			return false;
		}
		RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
		if (rsp == null) {
			return false;
		}
		econ = rsp.getProvider();
		return econ != null;
	}

}
