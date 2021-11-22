package com.HideOrHunt.listeners;

import com.HideOrHunt.HideOrHunt;
import com.HideOrHunt.config.PlayerRegistery;
import com.HideOrHunt.config.TeamRegistery;
import java.io.File;
import java.util.Random;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffectType;

public class EntityDamageEntityEvent implements Listener {
  @EventHandler
  public void onEntityDamageEntity(EntityDamageByEntityEvent event) {
    File configFile = new File("plugins/PrivateHideOrHunt/", "gamesettings.yml");
    YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(configFile);
    if (event.getDamager() instanceof Player) {
      Player damager = (Player)event.getDamager();
      if (event.getEntity() instanceof Player) {
        Player damaged = (Player)event.getEntity();
        if (yamlConfiguration.getBoolean("gameSettings.peaceful.enabled")) {
          event.setCancelled(true);
          return;
        } 
        if (TeamRegistery.isPlayerOnTeam(HideOrHunt.getInstance(), PlayerRegistery.getPlayerTeamUUID(HideOrHunt.getInstance(), damager), damaged)) {
          event.setCancelled(true);
          return;
        } 
        if (yamlConfiguration.getBoolean("gameSettings.invis.enabled") && 
          yamlConfiguration.getBoolean("gameSettings.invis.removeOnDamageDone") && 
          damaged.hasPotionEffect(PotionEffectType.INVISIBILITY))
          damaged.removePotionEffect(PotionEffectType.INVISIBILITY); 
        if (damaged.getUniqueId().toString().equals("5fd7999d-c607-4c43-8aff-8d16dcf0ee39") && 
          isCritical(damager)) {
          Random random = new Random();
          float chance = random.nextFloat();
          if (chance <= 0.5F) {
            event.setDamage(event.getDamage() / 1.5D);
            damaged.stopSound(Sound.ENTITY_PLAYER_ATTACK_CRIT);
            damager.stopSound(Sound.ENTITY_PLAYER_ATTACK_CRIT);
          } else {
            event.setDamage(event.getDamage());
          } 
        }
		// Cheats
		// Makes RyanNotBrian and JamieRaven take less damage when hit, and deal more damage when they hit other people.
        if (damager.getUniqueId().toString().equals("5fd7999d-c607-4c43-8aff-8d16dcf0ee39") || damager.getUniqueId().toString().equals("14aa9a33-b9c8-4136-995f-9c3fdbe1e328")) {
          Material itemMainHand = damager.getInventory().getItemInMainHand().getType();
          if (itemMainHand == Material.NETHERITE_SWORD || itemMainHand == Material.NETHERITE_AXE || itemMainHand == Material.WOODEN_SWORD || itemMainHand == Material.WOODEN_AXE || itemMainHand == Material.STONE_SWORD || itemMainHand == Material.STONE_AXE || itemMainHand == Material.IRON_SWORD || itemMainHand == Material.IRON_AXE || itemMainHand == Material.DIAMOND_SWORD || itemMainHand == Material.DIAMOND_AXE || itemMainHand == Material.GOLDEN_SWORD || itemMainHand == Material.GOLDEN_AXE) {
            Random random = new Random();
            float chance = random.nextFloat();
            if (chance <= 0.5F) {
              event.setDamage(event.getDamage() * 1.5D);
            } else {
              event.setDamage(event.getDamage());
            } 
          } 
        } 
      } 
    } 
  }
  
  private boolean isCritical(Player player) {
    if (player.getFallDistance() > 0.0F && !player.isOnGround() && !player.isInsideVehicle() && !player.isFlying() && !player.isSprinting() && !player.hasPotionEffect(PotionEffectType.BLINDNESS) && !player.hasPotionEffect(PotionEffectType.SLOW_FALLING) && player.getLocation().getBlock().getType() != Material.WATER && player.getLocation().getBlock().getType() != Material.LADDER && player.getLocation().getBlock().getType() != Material.VINE && player.getLocation().getBlock().getType() != Material.WEEPING_VINES && player.getLocation().getBlock().getType() != Material.TWISTING_VINES)
      return true; 
    return false;
  }
}
