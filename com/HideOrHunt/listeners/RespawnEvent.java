package com.HideOrHunt.listeners;

import com.HideOrHunt.HideOrHunt;
import com.HideOrHunt.config.PlayerRegistery;
import com.HideOrHunt.config.TeamRegistery;
import com.HideOrHunt.utilities.KitUtilities;
import com.HideOrHunt.utilities.SpawnUtilities;
import java.io.File;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class RespawnEvent implements Listener {
  @EventHandler
  public void onPlayerRespawn(PlayerRespawnEvent event) {
    Player player = event.getPlayer();
    File configFile = new File("plugins/PrivateHideOrHunt/", "gamesettings.yml");
    YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(configFile);
    if (PlayerRegistery.isOnTeam(HideOrHunt.getInstance(), player)) {
      KitUtilities.giveKits("deathhoh", player);
      if (yamlConfiguration.getBoolean("gameSettings.beacon.respawn")) {
        if (TeamRegistery.getTeamBeaconStatus(HideOrHunt.getInstance(), PlayerRegistery.getPlayerTeamUUID(HideOrHunt.getInstance(), player)).equals("placed")) {
          Location respawn = PlayerRegistery.getSpawnPoint(HideOrHunt.getInstance(), player);
          World world = respawn.getWorld();
          Block bottomBlock = world.getBlockAt(respawn.getBlockX(), respawn.getBlockY(), respawn.getBlockZ());
          Block topBlock = world.getBlockAt(respawn.getBlockX(), respawn.getBlockY() + 1, respawn.getBlockZ());
          Location safeRespawn = new Location(world, respawn.getBlockX() + 0.5D, respawn.getBlockY(), respawn.getBlockZ() + 0.5D);
          if (bottomBlock.isEmpty() && topBlock.isEmpty()) {
            event.setRespawnLocation(safeRespawn);
          } else if (!SpawnUtilities.isImpactingBlock(bottomBlock, topBlock)) {
            bottomBlock.setType(Material.AIR);
            topBlock.setType(Material.AIR);
            event.setRespawnLocation(safeRespawn);
          } else {
            bottomBlock = world.getBlockAt(respawn.getBlockX(), respawn.getBlockY(), respawn.getBlockZ() + 1);
            topBlock = world.getBlockAt(respawn.getBlockX(), respawn.getBlockY() + 1, respawn.getBlockZ() + 1);
            if (!SpawnUtilities.isImpactingBlock(bottomBlock, topBlock)) {
              bottomBlock.setType(Material.AIR);
              topBlock.setType(Material.AIR);
              event.setRespawnLocation(new Location(world, safeRespawn.getX(), safeRespawn.getY(), safeRespawn.getZ() + 1.0D));
            } else {
              bottomBlock = world.getBlockAt(respawn.getBlockX() + 1, respawn.getBlockY(), respawn.getBlockZ() + 1);
              topBlock = world.getBlockAt(respawn.getBlockX() + 1, respawn.getBlockY() + 1, respawn.getBlockZ() + 1);
              if (!SpawnUtilities.isImpactingBlock(bottomBlock, topBlock)) {
                bottomBlock.setType(Material.AIR);
                topBlock.setType(Material.AIR);
                event.setRespawnLocation(new Location(world, safeRespawn.getX() + 1.0D, safeRespawn.getY(), safeRespawn.getZ() + 1.0D));
              } else {
                bottomBlock = world.getBlockAt(respawn.getBlockX() + 1, respawn.getBlockY(), respawn.getBlockZ());
                topBlock = world.getBlockAt(respawn.getBlockX() + 1, respawn.getBlockY() + 1, respawn.getBlockZ());
                if (!SpawnUtilities.isImpactingBlock(bottomBlock, topBlock)) {
                  bottomBlock.setType(Material.AIR);
                  topBlock.setType(Material.AIR);
                  event.setRespawnLocation(new Location(world, safeRespawn.getX() + 1.0D, safeRespawn.getY(), safeRespawn.getZ()));
                } else {
                  bottomBlock = world.getBlockAt(respawn.getBlockX() + 1, respawn.getBlockY(), respawn.getBlockZ() - 1);
                  topBlock = world.getBlockAt(respawn.getBlockX() + 1, respawn.getBlockY() + 1, respawn.getBlockZ() - 1);
                  if (!SpawnUtilities.isImpactingBlock(bottomBlock, topBlock)) {
                    bottomBlock.setType(Material.AIR);
                    topBlock.setType(Material.AIR);
                    event.setRespawnLocation(new Location(world, safeRespawn.getX() + 1.0D, safeRespawn.getY(), safeRespawn.getZ() - 1.0D));
                  } else {
                    bottomBlock = world.getBlockAt(respawn.getBlockX(), respawn.getBlockY(), respawn.getBlockZ() - 1);
                    topBlock = world.getBlockAt(respawn.getBlockX(), respawn.getBlockY() + 1, respawn.getBlockZ() - 1);
                    if (!SpawnUtilities.isImpactingBlock(bottomBlock, topBlock)) {
                      bottomBlock.setType(Material.AIR);
                      topBlock.setType(Material.AIR);
                      event.setRespawnLocation(new Location(world, safeRespawn.getX(), safeRespawn.getY(), safeRespawn.getZ() - 1.0D));
                    } else {
                      bottomBlock = world.getBlockAt(respawn.getBlockX() - 1, respawn.getBlockY(), respawn.getBlockZ() - 1);
                      topBlock = world.getBlockAt(respawn.getBlockX() - 1, respawn.getBlockY() + 1, respawn.getBlockZ() - 1);
                      if (!SpawnUtilities.isImpactingBlock(bottomBlock, topBlock)) {
                        bottomBlock.setType(Material.AIR);
                        topBlock.setType(Material.AIR);
                        event.setRespawnLocation(new Location(world, safeRespawn.getX() - 1.0D, safeRespawn.getY(), safeRespawn.getZ() - 1.0D));
                      } else {
                        bottomBlock = world.getBlockAt(respawn.getBlockX() - 1, respawn.getBlockY(), respawn.getBlockZ());
                        topBlock = world.getBlockAt(respawn.getBlockX() - 1, respawn.getBlockY() + 1, respawn.getBlockZ());
                        if (!SpawnUtilities.isImpactingBlock(bottomBlock, topBlock)) {
                          bottomBlock.setType(Material.AIR);
                          topBlock.setType(Material.AIR);
                          event.setRespawnLocation(new Location(world, safeRespawn.getX() - 1.0D, safeRespawn.getY(), safeRespawn.getZ()));
                        } else {
                          bottomBlock = world.getBlockAt(respawn.getBlockX() - 1, respawn.getBlockY(), respawn.getBlockZ() + 1);
                          topBlock = world.getBlockAt(respawn.getBlockX() - 1, respawn.getBlockY() + 1, respawn.getBlockZ() + 1);
                          if (!SpawnUtilities.isImpactingBlock(bottomBlock, topBlock)) {
                            bottomBlock.setType(Material.AIR);
                            topBlock.setType(Material.AIR);
                            event.setRespawnLocation(new Location(world, safeRespawn.getX() - 1.0D, safeRespawn.getY(), safeRespawn.getZ() + 1.0D));
                          } else {
                            event.setRespawnLocation(Bukkit.getWorld("world").getSpawnLocation());
                          } 
                        } 
                      } 
                    } 
                  } 
                } 
              } 
            } 
          } 
          return;
        } 
        event.setRespawnLocation(Bukkit.getWorld("world").getSpawnLocation());
        return;
      } 
      event.setRespawnLocation(player.getBedSpawnLocation());
    } 
    event.setRespawnLocation(Bukkit.getWorld("HoH_2").getSpawnLocation());
  }
}
