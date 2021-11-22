package com.HideOrHunt.utilities;

import com.HideOrHunt.HideOrHunt;
import com.HideOrHunt.config.PlayerRegistery;
import com.HideOrHunt.config.TeamRegistery;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class Tab {
  public static void runTab() {
    (new BukkitRunnable() {
        public void run() {
          for (Player player : Bukkit.getOnlinePlayers()) {
            player.setPlayerListHeader("§f§lPrivate §b§lHide§f§lOr§c§lHunt");
            player.setPlayerListFooter("§fJoin §nPublic Hide or Hunt§f on §n1.8.9§f @ §b§lHide§f§lOr§c§lHunt§f§l.com");
            if (PlayerRegistery.isOnTeam(HideOrHunt.getInstance(), player)) {
              player.setPlayerListName("§a§l" + TeamRegistery.getTeamName(HideOrHunt.getInstance(), PlayerRegistery.getPlayerTeamUUID(HideOrHunt.getInstance(), player)) + " §r§f" + PlayerRegistery.getNickname(HideOrHunt.getInstance(), player));
              continue;
            } 
            if (player.isOp()) {
              player.setPlayerListName("§c§lAdmin §r§f" + PlayerRegistery.getNickname(HideOrHunt.getInstance(), player));
              continue;
            } 
            player.setPlayerListName("§7Default §r§f" + PlayerRegistery.getNickname(HideOrHunt.getInstance(), player));
          } 
        }
      }).runTaskTimer((Plugin)HideOrHunt.getInstance(), 0L, 20L);
  }
}
