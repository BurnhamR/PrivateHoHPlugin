package com.HideOrHunt.listeners;

import com.HideOrHunt.HideOrHunt;
import com.HideOrHunt.config.PlayerRegistery;
import com.HideOrHunt.config.TeamRegistery;
import com.HideOrHunt.utilities.TeamUtilities;
import java.io.File;
import java.util.Random;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.Plugin;

public class PlayerKilledEvent implements Listener {
  @EventHandler
  public void onPlayerDeath(PlayerDeathEvent event) {
    Player player = event.getEntity();
    File configFile = new File("plugins/PrivateHideOrHunt/", "gamesettings.yml");
    YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(configFile);
    if (yamlConfiguration.getString("serverSettings.deathMessages").equals("off")) {
      event.setDeathMessage("");
    } else if (yamlConfiguration.getString("serverSettings.deathMessages").equals("on")) {
      event.setDeathMessage("§e" + event.getDeathMessage());
    } else if (yamlConfiguration.getString("serverSettings.deathMessages").equals("op")) {
      for (Player oplayer : Bukkit.getOnlinePlayers()) {
        if (oplayer.isOp())
          oplayer.sendMessage("§e" + event.getDeathMessage()); 
      } 
      event.setDeathMessage("");
    } 
    Random random = new Random();
    if (yamlConfiguration.getBoolean("gameSettings.death.keepXPOnDeath")) {
      float chance = random.nextFloat();
      float chanceToKeep = yamlConfiguration.getInt("gameSettings.death.chanceXPSave");
      if (chance <= chanceToKeep)
        event.setKeepLevel(true); 
    } 
    if (yamlConfiguration.getBoolean("gameSettings.death.keepItemsOnDeath")) {
      float chance = random.nextFloat();
      float chanceToKeep = yamlConfiguration.getInt("gameSettings.death.chanceItemSave");
      if (chance <= chanceToKeep) {
        event.setKeepInventory(true);
        player.sendMessage("§a§lLucky!§a You kept your inventory on death!");
      } 
    } 
    if (TeamRegistery.getPlayerLastLife(HideOrHunt.getInstance(), PlayerRegistery.getPlayerTeamUUID(HideOrHunt.getInstance(), player), player.getUniqueId().toString())) {
      player.getWorld().strikeLightningEffect(player.getLocation());
      TeamRegistery.updatePlayerEliminated(HideOrHunt.getInstance(), PlayerRegistery.getPlayerTeamUUID(HideOrHunt.getInstance(), player), player.getUniqueId().toString(), true);
      if (player.getKiller() instanceof Player) {
        Player killer = player.getKiller();
        Bukkit.broadcastMessage("§a§n" + PlayerRegistery.getNickname(HideOrHunt.getInstance(), player) + "§a was Eliminated by §n" + PlayerRegistery.getNickname(HideOrHunt.getInstance(), killer) + "§a.");
        TeamRegistery.updatePlayerFinalKills(HideOrHunt.getInstance(), PlayerRegistery.getPlayerTeamUUID(HideOrHunt.getInstance(), killer), killer.getUniqueId().toString(), TeamRegistery.getPlayerFinalKills(HideOrHunt.getInstance(), PlayerRegistery.getPlayerTeamUUID(HideOrHunt.getInstance(), killer), killer.getUniqueId().toString()) + 1);
        TeamRegistery.updateTeamFinalKills(HideOrHunt.getInstance(), PlayerRegistery.getPlayerTeamUUID(HideOrHunt.getInstance(), killer), TeamRegistery.getTeamFinalKills(HideOrHunt.getInstance(), PlayerRegistery.getPlayerTeamUUID(HideOrHunt.getInstance(), killer)) + 1);
      } else {
        Bukkit.broadcastMessage("§a§n" + PlayerRegistery.getNickname(HideOrHunt.getInstance(), player) + "§a was Eliminated.");
      } 
      if (yamlConfiguration.getBoolean("gameSettings.death.autokick"))
        if (player.isOp()) {
          player.setGameMode(GameMode.SPECTATOR);
        } else {
          player.kickPlayer("Eliminated from the Game");
          if (yamlConfiguration.getBoolean("gameSettings.death.autounwhitelist"))
            player.setWhitelisted(false); 
        }  
      if (!TeamUtilities.arePlayersRemaining(PlayerRegistery.getPlayerTeamUUID(HideOrHunt.getInstance(), player))) {
        if (player.getKiller() instanceof Player) {
          Player killer = player.getKiller();
          Bukkit.broadcastMessage("§a§n" + TeamRegistery.getTeamName(HideOrHunt.getInstance(), PlayerRegistery.getPlayerTeamUUID(HideOrHunt.getInstance(), player)) + "§a was Eliminated by §n" + PlayerRegistery.getNickname(HideOrHunt.getInstance(), killer) + "§a.");
        } else {
          Bukkit.broadcastMessage("§a§n" + TeamRegistery.getTeamName(HideOrHunt.getInstance(), PlayerRegistery.getPlayerTeamUUID(HideOrHunt.getInstance(), player)) + "§a was Eliminated.");
        } 
        TeamRegistery.updateTeamEliminated(HideOrHunt.getInstance(), PlayerRegistery.getPlayerTeamUUID(HideOrHunt.getInstance(), player), true);
        if (TeamUtilities.getTeamsRemaining() == 1) {
          String teamUUID = TeamUtilities.getLastTeamUUID();
          TeamUtilities.sendTeamCommandUUID(teamUUID, "title %player% title {\"text\":\"Winner\",\"color\":\"green\",\"bold\":true}");
          TeamUtilities.spawnTeamFireworks(teamUUID, 3);
          Bukkit.getScheduler().cancelTasks((Plugin)HideOrHunt.getInstance());
        } else {
          Bukkit.broadcastMessage("§aThere are §n" + TeamUtilities.getTeamsRemaining() + "§a Teams Remaining!");
        } 
      } 
    } else if (player.getKiller() instanceof Player) {
      Player killer = player.getKiller();
      TeamRegistery.updatePlayerGeneralKills(HideOrHunt.getInstance(), PlayerRegistery.getPlayerTeamUUID(HideOrHunt.getInstance(), killer), killer.getUniqueId().toString(), TeamRegistery.getPlayerGeneralKills(HideOrHunt.getInstance(), PlayerRegistery.getPlayerTeamUUID(HideOrHunt.getInstance(), killer), killer.getUniqueId().toString()) + 1);
      TeamRegistery.updateTeamGeneralKills(HideOrHunt.getInstance(), PlayerRegistery.getPlayerTeamUUID(HideOrHunt.getInstance(), killer), TeamRegistery.getTeamGeneralKills(HideOrHunt.getInstance(), PlayerRegistery.getPlayerTeamUUID(HideOrHunt.getInstance(), killer)) + 1);
    } 
  }
}
