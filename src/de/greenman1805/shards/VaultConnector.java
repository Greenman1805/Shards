package de.greenman1805.shards;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.OfflinePlayer;

import de.greenman1805.uuids.UUIDs;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import net.milkbowl.vault.economy.EconomyResponse.ResponseType;

public class VaultConnector implements Economy {

	@Override
	public EconomyResponse bankBalance(String arg0) {
		return new EconomyResponse(0, 0, ResponseType.NOT_IMPLEMENTED, "not implemented");
	}

	@Override
	public EconomyResponse bankDeposit(String arg0, double arg1) {
		return new EconomyResponse(0, 0, ResponseType.NOT_IMPLEMENTED, "not implemented");
	}

	@Override
	public EconomyResponse bankHas(String arg0, double arg1) {
		return new EconomyResponse(0, 0, ResponseType.NOT_IMPLEMENTED, "not implemented");
	}

	@Override
	public EconomyResponse bankWithdraw(String arg0, double arg1) {
		return new EconomyResponse(0, 0, ResponseType.NOT_IMPLEMENTED, "not implemented");
	}

	@Override
	public EconomyResponse createBank(String arg0, String arg1) {
		return new EconomyResponse(0, 0, ResponseType.NOT_IMPLEMENTED, "not implemented");
	}

	@Override
	public EconomyResponse createBank(String arg0, OfflinePlayer arg1) {
		return new EconomyResponse(0, 0, ResponseType.NOT_IMPLEMENTED, "not implemented");
	}

	@Override
	public boolean createPlayerAccount(String arg0) {
		UUID uuid = UUIDs.getUUID(arg0);
		if (ShardAPI.hasAccount(uuid)) {
			return false;
		} else {
			ShardAPI.createAccount(uuid);
			return true;
		}
	}

	@Override
	public boolean createPlayerAccount(OfflinePlayer arg0) {
		UUID uuid = arg0.getUniqueId();
		if (ShardAPI.hasAccount(uuid)) {
			return false;
		} else {
			ShardAPI.createAccount(uuid);
			return true;
		}
	}

	@Override
	public boolean createPlayerAccount(String arg0, String arg1) {
		UUID uuid = UUIDs.getUUID(arg0);
		if (ShardAPI.hasAccount(uuid)) {
			return false;
		} else {
			ShardAPI.createAccount(uuid);
			return true;
		}
	}

	@Override
	public boolean createPlayerAccount(OfflinePlayer arg0, String arg1) {
		UUID uuid = arg0.getUniqueId();
		if (ShardAPI.hasAccount(uuid)) {
			return false;
		} else {
			ShardAPI.createAccount(uuid);
			return true;
		}
	}

	@Override
	public String currencyNamePlural() {
		return "Shards";
	}

	@Override
	public String currencyNameSingular() {
		return "Shard";
	}

	@Override
	public EconomyResponse deleteBank(String arg0) {
		return new EconomyResponse(0, 0, ResponseType.NOT_IMPLEMENTED, "not implemented");
	}

	@Override
	public EconomyResponse depositPlayer(String arg0, double arg1) {
		UUID uuid = UUIDs.getUUID(arg0);
		double money = ShardAPI.getShards(uuid);
		money += arg1;
		ShardAPI.setShards(uuid, money);
		return new EconomyResponse(arg1, money, ResponseType.SUCCESS, null);
	}

	@Override
	public EconomyResponse depositPlayer(OfflinePlayer arg0, double arg1) {
		UUID uuid = arg0.getUniqueId();
		double money = ShardAPI.getShards(uuid);
		money += arg1;
		ShardAPI.setShards(uuid, money);
		return new EconomyResponse(arg1, money, ResponseType.SUCCESS, null);
	}

	@Override
	public EconomyResponse depositPlayer(String arg0, String arg1, double arg2) {
		UUID uuid = UUIDs.getUUID(arg0);
		double money = ShardAPI.getShards(uuid);
		money += arg2;
		ShardAPI.setShards(uuid, money);
		return new EconomyResponse(arg2, money, ResponseType.SUCCESS, null);
	}

	@Override
	public EconomyResponse depositPlayer(OfflinePlayer arg0, String arg1, double arg2) {
		UUID uuid = arg0.getUniqueId();
		double money = ShardAPI.getShards(uuid);
		money += arg2;
		ShardAPI.setShards(uuid, money);
		return new EconomyResponse(arg2, money, ResponseType.SUCCESS, null);
	}

	@Override
	public String format(double arg0) {
		return arg0 + "";
	}

	@Override
	public int fractionalDigits() {
		return 1;
	}

	@Override
	public double getBalance(String arg0) {
		UUID uuid = UUIDs.getUUID(arg0);
		return ShardAPI.getShards(uuid);
	}

	@Override
	public double getBalance(OfflinePlayer arg0) {
		UUID uuid = arg0.getUniqueId();
		return ShardAPI.getShards(uuid);
	}

	@Override
	public double getBalance(String arg0, String arg1) {
		UUID uuid = UUIDs.getUUID(arg0);
		return ShardAPI.getShards(uuid);
	}

	@Override
	public double getBalance(OfflinePlayer arg0, String arg1) {
		UUID uuid = arg0.getUniqueId();
		return ShardAPI.getShards(uuid);
	}

	@Override
	public List<String> getBanks() {
		return new ArrayList<>();
	}

	@Override
	public String getName() {
		return "Shards";
	}

	@Override
	public boolean has(String arg0, double arg1) {
		UUID uuid = UUIDs.getUUID(arg0);
		double money = ShardAPI.getShards(uuid);
		money -= arg1;
		if (money >= 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean has(OfflinePlayer arg0, double arg1) {
		UUID uuid = arg0.getUniqueId();
		double money = ShardAPI.getShards(uuid);
		money -= arg1;
		if (money >= 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean has(String arg0, String arg1, double arg2) {
		UUID uuid = UUIDs.getUUID(arg0);
		double money = ShardAPI.getShards(uuid);
		money -= arg2;
		if (money >= 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean has(OfflinePlayer arg0, String arg1, double arg2) {
		UUID uuid = arg0.getUniqueId();
		double money = ShardAPI.getShards(uuid);
		money -= arg2;
		if (money >= 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean hasAccount(String arg0) {
		if (ShardAPI.hasAccount(UUIDs.getUUID(arg0))) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean hasAccount(OfflinePlayer arg0) {
		if (ShardAPI.hasAccount(arg0.getUniqueId())) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean hasAccount(String arg0, String arg1) {
		if (ShardAPI.hasAccount(UUIDs.getUUID(arg0))) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean hasAccount(OfflinePlayer arg0, String arg1) {
		if (ShardAPI.hasAccount(arg0.getUniqueId())) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean hasBankSupport() {
		return false;
	}

	@Override
	public EconomyResponse isBankMember(String arg0, String arg1) {
		return new EconomyResponse(0, 0, ResponseType.NOT_IMPLEMENTED, "not implemented");
	}

	@Override
	public EconomyResponse isBankMember(String arg0, OfflinePlayer arg1) {
		return new EconomyResponse(0, 0, ResponseType.NOT_IMPLEMENTED, "not implemented");
	}

	@Override
	public EconomyResponse isBankOwner(String arg0, String arg1) {
		return new EconomyResponse(0, 0, ResponseType.NOT_IMPLEMENTED, "not implemented");
	}

	@Override
	public EconomyResponse isBankOwner(String arg0, OfflinePlayer arg1) {
		return new EconomyResponse(0, 0, ResponseType.NOT_IMPLEMENTED, "not implemented");
	}

	@Override
	public boolean isEnabled() {
		return Main.plugin.isEnabled();
	}

	@Override
	public EconomyResponse withdrawPlayer(String arg0, double arg1) {
		UUID uuid = UUIDs.getUUID(arg0);
		double money = ShardAPI.getShards(uuid);
		money -= arg1;
		if (money >= 0) {
			ShardAPI.setShards(uuid, money);
			return new EconomyResponse(arg1, money, ResponseType.SUCCESS, null);
		} else {
			return new EconomyResponse(0, ShardAPI.getShards(uuid), ResponseType.FAILURE, "not enough money");
		}
	}

	@Override
	public EconomyResponse withdrawPlayer(OfflinePlayer arg0, double arg1) {
		UUID uuid = arg0.getUniqueId();
		double money = ShardAPI.getShards(uuid);
		money -= arg1;
		if (money >= 0) {
			ShardAPI.setShards(uuid, money);
			return new EconomyResponse(arg1, money, ResponseType.SUCCESS, null);
		} else {
			return new EconomyResponse(0, ShardAPI.getShards(uuid), ResponseType.FAILURE, "not enough money");
		}
	}

	@Override
	public EconomyResponse withdrawPlayer(String arg0, String arg1, double arg2) {
		UUID uuid = UUIDs.getUUID(arg0);
		double money = ShardAPI.getShards(uuid);
		money -= arg2;
		if (money >= 0) {
			ShardAPI.setShards(uuid, money);
			return new EconomyResponse(arg2, money, ResponseType.SUCCESS, null);
		} else {
			return new EconomyResponse(0, ShardAPI.getShards(uuid), ResponseType.FAILURE, "not enough money");
		}
	}

	@Override
	public EconomyResponse withdrawPlayer(OfflinePlayer arg0, String arg1, double arg2) {
		UUID uuid = arg0.getUniqueId();
		double money = ShardAPI.getShards(uuid);
		money -= arg2;
		if (money >= 0) {
			ShardAPI.setShards(uuid, money);
			return new EconomyResponse(arg2, money, ResponseType.SUCCESS, null);
		} else {
			return new EconomyResponse(0, ShardAPI.getShards(uuid), ResponseType.FAILURE, "not enough money");
		}
	}

}
