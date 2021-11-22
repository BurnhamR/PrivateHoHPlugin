package com.HideOrHunt.listeners;

import com.HideOrHunt.HideOrHunt;
import com.HideOrHunt.config.ConfigRegistery;
import com.HideOrHunt.config.PlayerRegistery;
import com.HideOrHunt.config.TeamRegistery;
import com.HideOrHunt.utilities.TeamUtilities;
import java.io.File;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

public class BlockPlacementEvent implements Listener {
  @EventHandler
  public void onBlockPlace(BlockPlaceEvent event) {
    Player player = event.getPlayer();
    ItemStack item = event.getItemInHand();
    ItemMeta meta = item.getItemMeta();
    Block block = event.getBlock();
    NamespacedKey teamBeaconUUID = new NamespacedKey((Plugin)HideOrHunt.getInstance(), "teambeaconuuid");
    NamespacedKey isCustomBeacon = new NamespacedKey((Plugin)HideOrHunt.getInstance(), "iscustombeacon");
    PersistentDataContainer itemTags = meta.getPersistentDataContainer();
    File configFile = new File("plugins/PrivateHideOrHunt/", "gamesettings.yml");
    YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(configFile);
    if (block.getType() == Material.BEACON && 
      itemTags.has(isCustomBeacon, PersistentDataType.STRING) && itemTags.has(teamBeaconUUID, PersistentDataType.STRING) && (
      (String)itemTags.get(isCustomBeacon, PersistentDataType.STRING)).equals("true") && 
      PlayerRegistery.isOnTeam(HideOrHunt.getInstance(), player)) {
      String beaconUUID = (String)itemTags.get(teamBeaconUUID, PersistentDataType.STRING);
      boolean isLeader = TeamRegistery.isTeamLeader(HideOrHunt.getInstance(), beaconUUID, player);
      if (yamlConfiguration.getBoolean("gameSettings.beacon.leaderOnlyPlace")) {
        if (isLeader) {
          if (TeamRegistery.getTeamBeaconStatus(HideOrHunt.getInstance(), beaconUUID).equals("pending")) {
            TeamRegistery.updateTeamBeaconStatus(HideOrHunt.getInstance(), beaconUUID);
            ConfigRegistery.registerBeacon(HideOrHunt.getInstance(), player, beaconUUID, block.getLocation());
            Bukkit.broadcastMessage("§a§n" + TeamRegistery.getTeamName(HideOrHunt.getInstance(), beaconUUID) + "§a placed their Beacon!");
            if (player.getGameMode().equals(GameMode.CREATIVE))
              player.getInventory().remove(item); 
            TeamUtilities.updateTeamSpawnpoint(beaconUUID, player.getLocation());
          } else {
            event.setCancelled(true);
            player.sendMessage("§cThis Team has already placed a Beacon!");
          } 
        } else {
          event.setCancelled(true);
          player.sendMessage("§cYou must be the Team Leader to place the Team Beacon!");
        } 
      } else if (TeamRegistery.getTeamBeaconStatus(HideOrHunt.getInstance(), beaconUUID).equals("pending")) {
        TeamRegistery.updateTeamBeaconStatus(HideOrHunt.getInstance(), beaconUUID);
        ConfigRegistery.registerBeacon(HideOrHunt.getInstance(), player, beaconUUID, block.getLocation());
        Bukkit.broadcastMessage("§a§n" + TeamRegistery.getTeamName(HideOrHunt.getInstance(), beaconUUID) + "§a placed their Beacon!");
        if (player.getGameMode().equals(GameMode.CREATIVE))
          player.getInventory().remove(item); 
        TeamUtilities.updateTeamSpawnpoint(beaconUUID, player.getLocation());
      } else {
        event.setCancelled(true);
        player.sendMessage("§cThis Team has already placed a Beacon!");
      } 
    } 
  }
}
