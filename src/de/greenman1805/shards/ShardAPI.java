package de.greenman1805.shards;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.TreeMap;
import java.util.UUID;

import org.bukkit.Bukkit;

public class ShardAPI {

	private static double round(double value, int precision) {
		int scale = (int) Math.pow(10, precision);
		return (double) Math.round(value * scale) / scale;
	}

	public static boolean hasAccount(UUID uuid) {
		MySQL.checkConnection();
		try {
			PreparedStatement ps = MySQL.getConnection().prepareStatement("SELECT money FROM money WHERE UUID = ?");
			ps.setString(1, uuid.toString());
			ResultSet rs = ps.executeQuery();
			return rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static double getShards(UUID uuid) {
		MySQL.checkConnection();
		try {
			PreparedStatement ps = MySQL.getConnection().prepareStatement("SELECT money FROM money WHERE UUID = ?");
			ps.setString(1, uuid.toString());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				double money = rs.getDouble("money");
				money = round(money, 1);
				return money;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static void createAccount(UUID uuid) {
		MySQL.checkConnection();
		try {
			PreparedStatement ps = MySQL.getConnection().prepareStatement("INSERT INTO money (UUID, money) VALUES (?,?)");
			ps.setString(1, uuid.toString());
			ps.setString(2, String.valueOf(Main.start_money));
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static TreeMap<Double, UUID> getTopShards() {
		MySQL.checkConnection();
		String sql = "SELECT * FROM money ORDER BY money DESC LIMIT 10;";
		TreeMap<Double, UUID> top = new TreeMap<Double, UUID>(Collections.reverseOrder());

		try {
			Statement statement = MySQL.getConnection().createStatement();
			ResultSet resultset = statement.executeQuery(sql);
			while (resultset.next()) {

				UUID uuid = UUID.fromString(resultset.getString("UUID"));
				double money = resultset.getDouble("money");
				top.put(money, uuid);
			}

			resultset.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return top;
	}

	public static void setShards(UUID uuid, double money) {
		MySQL.checkConnection();
		if (!hasAccount(uuid)) {
			createAccount(uuid);
		}
		try {
			PreparedStatement ps = MySQL.getConnection().prepareStatement("UPDATE money SET money = ? WHERE UUID = ?");
			ps.setString(1, String.valueOf(round(money, 1)));
			ps.setString(2, uuid.toString());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Bukkit.getPluginManager().callEvent(new MoneyChangeEvent(uuid, money));
	}
}
