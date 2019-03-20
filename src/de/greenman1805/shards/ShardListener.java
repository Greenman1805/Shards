package de.greenman1805.shards;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class ShardListener implements Listener {

	@EventHandler
	public void onLoginEvent(PlayerJoinEvent e) {
		if (!ShardAPI.hasAccount(e.getPlayer().getUniqueId())) {
			ShardAPI.createAccount(e.getPlayer().getUniqueId());
		}
	}

}
