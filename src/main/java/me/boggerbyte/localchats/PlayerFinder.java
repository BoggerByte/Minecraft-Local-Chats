package me.boggerbyte.localchats;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.List;

public interface PlayerFinder {
    List<Player> findPlayersAround(Location location);
}
