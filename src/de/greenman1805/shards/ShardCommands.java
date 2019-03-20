package de.greenman1805.shards;

import java.util.Map.Entry;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.greenman1805.uuids.UUIDs;

public class ShardCommands implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = null;
		if (sender instanceof Player) {
			p = (Player) sender;
		}

		if (cmd.getName().equalsIgnoreCase("pay")) {
			if (args.length == 2) {
				if (p != null) {
					if (UUIDs.hasEntry(args[0])) {
						OfflinePlayer t = Bukkit.getOfflinePlayer(UUIDs.getUUID(args[0]));
						try {
							int shards = Integer.parseInt(args[1]);
							int account_after = (int) (Main.econ.getBalance(p) - shards);
							if (account_after >= 0) {
								Main.econ.depositPlayer(t, shards);
								Main.econ.withdrawPlayer(p, shards);
								sender.sendMessage(Main.prefix + "§7Du hast §a" + t.getName() + " §9" + shards + " Shards §7gegeben.");
								if (t.isOnline()) {
									Player ot = (Player) t;
									ot.sendMessage(Main.prefix + p.getDisplayName() + " §7hat dir §9" + shards + " Shards §7gegeben.");
								}
							} else {
								sender.sendMessage(Main.prefix + "§cDu besitzt diese Summe an Shards nicht!");
							}
						} catch (NumberFormatException exc) {
							sender.sendMessage(Main.prefix + "/shards pay <spieler> <shards>");
						}
					} else {
						sender.sendMessage(Main.prefix + "§cSpieler nicht gefunden!");
					}
				} else {
					sender.sendMessage(Main.prefix + "§cDiesen Befehl kannst du nur als Spieler nutzen.");
				}
			}
		}

		if (cmd.getName().equalsIgnoreCase("money")) {
			if (args.length == 0) {
				if (p != null) {
					double shards = Main.econ.getBalance(p);
					p.sendMessage("§9Shards: §f" + shards);
				} else {
					sender.sendMessage(Main.prefix + "§cDiesen Befehl kannst du nur als Spieler nutzen.");
				}
			}

			else if (args.length == 1) {
				if (args[0].equalsIgnoreCase("help")) {
					sender.sendMessage("");
					sender.sendMessage("§7- §2/shards §r- §aDeine Shards.");
					sender.sendMessage("§7- §2/shards pay <spieler> <shards> §r- §aGibt einem Spieler Shards.");
					sender.sendMessage("§7- §2/shards top §r- §aZeigt Spieler mit den meisten Shards.");
					sender.sendMessage("§7- §c/shards admin §r- §aAdmin Befehle.");
					sender.sendMessage("");
				}

				else if (args[0].equalsIgnoreCase("admin")) {
					if (sender.hasPermission("shards.admin")) {
						sender.sendMessage("");
						sender.sendMessage("§7- §2/shards <spieler>");
						sender.sendMessage("§7- §2/shards give <spieler> <shards>");
						sender.sendMessage("§7- §2/shards take <spieler> <shards>");
						sender.sendMessage("§7- §2/shards set <spieler> <shards>");
						sender.sendMessage("");
					} else {
						sender.sendMessage(Main.prefix + "§cKeine Permissions.");
					}
				}

				else if (args[0].equalsIgnoreCase("top")) {
					p.sendMessage("§2Spieler mit den meisten Shards:");

					for (Entry<Double, UUID> entry : ShardAPI.getTopShards().entrySet()) {
						UUID uuid = entry.getValue();
						double money = entry.getKey();
						p.sendMessage("§a" + UUIDs.getName(uuid) + ": §f" + money);
					}
				}

				else if (sender.hasPermission("shards.admin")) {
					if (UUIDs.hasEntry(args[0])) {
						OfflinePlayer t = Bukkit.getServer().getOfflinePlayer(UUIDs.getUUID(args[0]));
						if (Main.econ.hasAccount(t)) {
							double shards = Main.econ.getBalance(t);
							sender.sendMessage("§a" + t.getName() + ": §f" + shards + " Shards");
						} else {
							sender.sendMessage(Main.prefix + "§cSpieler hat keinen Account!");
						}
					} else {
						sender.sendMessage(Main.prefix + "§cSpieler nicht gefunden!");
					}
				} else {
					sender.sendMessage(Main.prefix + "§cKeine Permissions.");
				}

			} else if (args.length == 3) {
				if (args[0].equalsIgnoreCase("pay")) {
					if (p != null) {
						if (UUIDs.hasEntry(args[1])) {
							OfflinePlayer t = Bukkit.getOfflinePlayer(UUIDs.getUUID(args[1]));
							try {
								int shards = Integer.parseInt(args[2]);
								int account_after = (int) (Main.econ.getBalance(p) - shards);
								if (account_after >= 0) {
									Main.econ.depositPlayer(t, shards);
									Main.econ.withdrawPlayer(p, shards);
									sender.sendMessage(Main.prefix + "§7Du hast §a" + t.getName() + " §9" + shards + " Shards §7gegeben.");
									if (t.isOnline()) {
										Player ot = (Player) t;
										ot.sendMessage(Main.prefix + p.getDisplayName() + " §7hat dir §9" + shards + " Shards §7gegeben.");
									}
								} else {
									sender.sendMessage(Main.prefix + "§cDu besitzt diese Summe an Shards nicht!");
								}
							} catch (NumberFormatException exc) {
								sender.sendMessage(Main.prefix + "/shards pay <spieler> <shards>");
							}
						} else {
							sender.sendMessage(Main.prefix + "§cSpieler nicht gefunden!");
						}
					} else {
						sender.sendMessage(Main.prefix + "§cDiesen Befehl kannst du nur als Spieler nutzen.");
					}
				}

				if (args[0].equalsIgnoreCase("give")) {
					if (sender.hasPermission("shards.admin")) {
						if (UUIDs.hasEntry(args[1])) {
							OfflinePlayer t = Bukkit.getOfflinePlayer(UUIDs.getUUID(args[1]));
							try {
								int shards = Integer.parseInt(args[2]);
								Main.econ.depositPlayer(t, shards);
								sender.sendMessage(Main.prefix + "§7Du hast §a" + t.getName() + " §9" + shards + " Shards §7gegeben.");
							} catch (NumberFormatException exc) {
								sender.sendMessage(Main.prefix + "/shards give <spieler> <shards>");
							}
						} else {
							sender.sendMessage(Main.prefix + "§cSpieler nicht gefunden!");
						}
					} else {
						sender.sendMessage(Main.prefix + "§cKeine Permissions.");
					}
				}

				if (args[0].equalsIgnoreCase("take")) {
					if (sender.hasPermission("shards.admin")) {
						if (UUIDs.hasEntry(args[1])) {
							OfflinePlayer t = Bukkit.getOfflinePlayer(UUIDs.getUUID(args[1]));
							try {
								int shards = Integer.parseInt(args[2]);
								Main.econ.withdrawPlayer(t, shards);
								sender.sendMessage(Main.prefix + "§7Du hast §a" + t.getName() + " §9" + shards + " Shards §7abgezogen.");
							} catch (NumberFormatException exc) {
								sender.sendMessage(Main.prefix + "/shards take <spieler> <shards>");
							}
						} else {
							sender.sendMessage(Main.prefix + "§cSpieler nicht gefunden!");
						}
					} else {
						sender.sendMessage(Main.prefix + "§cKeine Permissions.");
					}
				}

				if (args[0].equalsIgnoreCase("set")) {
					if (sender.hasPermission("shards.admin")) {
						if (UUIDs.hasEntry(args[1])) {
							OfflinePlayer t = Bukkit.getOfflinePlayer(UUIDs.getUUID(args[1]));
							try {
								double shards = Main.econ.getBalance(t);
								Main.econ.withdrawPlayer(t, shards);
								shards = Integer.parseInt(args[2]);
								Main.econ.depositPlayer(t, shards);
								sender.sendMessage(Main.prefix + "§a" + t.getName() + "§7 hat jetzt §9" + (int) shards + " Shards§7.");
							} catch (NumberFormatException exc) {
								sender.sendMessage(Main.prefix + "/shards set <spieler> <shards>");
							}
						} else {
							sender.sendMessage(Main.prefix + "§cSpieler nicht gefunden!");
						}
					} else {
						sender.sendMessage(Main.prefix + "§cKeine Permissions.");
					}
				}

			} else {
				sender.sendMessage("");
				sender.sendMessage("§7- §2/shards §r- §aDeine Shards.");
				sender.sendMessage("§7- §2/shards pay <spieler> <shards> §r- §aGibt einem Spieler Shards.");
				sender.sendMessage("§7- §2/shards top §r- §aZeigt Spieler mit den meisten Shards.");
				sender.sendMessage("§7- §c/shards admin §r- §aAdmin Befehle.");
				sender.sendMessage("");
			}
		}
		return false;
	}

}
