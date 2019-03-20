package de.greenman1805.shards;

import java.util.UUID;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class MoneyChangeEvent extends Event {

    private final UUID uuid;
    private final double money;

    public MoneyChangeEvent(UUID uuid, double money) {
        this.uuid = uuid;
        this.money = money;
    }

    private static final HandlerList HANDLERS = new HandlerList();

    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    public UUID getUniqueId() {
        return this.uuid;
    }
    
    public double getMoney() {
    	return this.money;
    }

}