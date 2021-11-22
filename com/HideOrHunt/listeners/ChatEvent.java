package com.HideOrHunt.listeners;

import com.HideOrHunt.HideOrHunt;
import com.HideOrHunt.config.PlayerRegistery;
import com.HideOrHunt.config.TeamRegistery;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatEvent implements Listener {
  @EventHandler
  public void onPlayerChat(AsyncPlayerChatEvent event) {
    Player player = event.getPlayer();
    if (PlayerRegistery.isOnTeam(HideOrHunt.getInstance(), player)) {
      event.setFormat("§a" + TeamRegistery.getTeamName(HideOrHunt.getInstance(), PlayerRegistery.getPlayerTeamUUID(HideOrHunt.getInstance(), player)) + " §r§f" + PlayerRegistery.getNickname(HideOrHunt.getInstance(), player) + " §8> §f" + event.getMessage());
    } else if (player.isOp()) {
      event.setFormat("§c§lAdmin §r§f" + PlayerRegistery.getNickname(HideOrHunt.getInstance(), player) + " §8> §f" + event.getMessage());
    } else {
      event.setFormat("§7Default §r§f" + PlayerRegistery.getNickname(HideOrHunt.getInstance(), player) + " §8> §f" + event.getMessage());
    } 
  }
}
