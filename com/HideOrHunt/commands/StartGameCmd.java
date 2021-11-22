package com.HideOrHunt.commands;

import com.HideOrHunt.HideOrHunt;
import com.HideOrHunt.config.ConfigRegistery;
import com.HideOrHunt.config.PlayerRegistery;
import com.HideOrHunt.config.TeamRegistery;
import com.HideOrHunt.customgames.TimerDeathSwap;
import com.HideOrHunt.utilities.BeaconUtilities;
import com.HideOrHunt.utilities.InvisUtilities;
import com.HideOrHunt.utilities.KitUtilities;
import java.io.File;
import java.io.IOException;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.boss.BossBar;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class StartGameCmd implements CommandExecutor {
  private HideOrHunt plugin;
  
  public StartGameCmd(HideOrHunt plugin) {
    this.plugin = plugin;
    plugin.getCommand("startgame").setExecutor(this);
  }
  
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    Player player = (Player)sender;
    if (!(sender instanceof Player)) {
      sender.sendMessage("Only players may execute this command!");
      return true;
    } 
    if (!player.isOp()) {
      player.sendMessage("§cYou cannot use this command!");
      return true;
    } 
    final File configFile = new File("plugins/PrivateHideOrHunt/", "gamesettings.yml");
    final YamlConfiguration config = YamlConfiguration.loadConfiguration(configFile);
    File customConfigFile = new File("plugins/PrivateHideOrHunt/", "custom.yml");
    YamlConfiguration yamlConfiguration2 = YamlConfiguration.loadConfiguration(customConfigFile);
    Bukkit.dispatchCommand(sender, "title @a title {\"text\":\"Game Started\",\"color\":\"green\",\"bold\":true}");
    Bukkit.dispatchCommand(sender, "title @a subtitle {\"text\":\"Leaders have received the team beacons.\",\"color\":\"white\",\"bold\":true}");
    for (Player oPlayer : Bukkit.getOnlinePlayers()) {
      if (PlayerRegistery.isOnTeam(this.plugin, oPlayer)) {
        KitUtilities.giveKits("startinghoh", oPlayer);
        if (yamlConfiguration1.getBoolean("gameSettings.slowfall.enabled"))
          oPlayer.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, yamlConfiguration1.getInt("gameSettings.slowfall.duration") * 20, 0)); 
        if (TeamRegistery.isTeamLeader(this.plugin, PlayerRegistery.getPlayerTeamUUID(this.plugin, oPlayer), oPlayer)) {
          PlayerInventory playerInventory = oPlayer.getInventory();
          int emptySpace = 0;
          byte b;
          int i;
          ItemStack[] arrayOfItemStack;
          for (i = (arrayOfItemStack = playerInventory.getStorageContents()).length, b = 0; b < i; ) {
            ItemStack item = arrayOfItemStack[b];
            if (item == null || item.getType() == Material.AIR)
              emptySpace++; 
            b++;
          } 
          if (emptySpace <= 0) {
            oPlayer.getWorld().dropItem(oPlayer.getLocation(), BeaconUtilities.giveBeacon((FileConfiguration)yamlConfiguration1, oPlayer));
            continue;
          } 
          playerInventory.addItem(new ItemStack[] { BeaconUtilities.giveBeacon((FileConfiguration)yamlConfiguration1, oPlayer) });
        } 
      } 
    } 
    Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)this.plugin, new Runnable() {
          public void run() {
            InvisUtilities.invisPotion(config);
          }
        },  (yamlConfiguration1.getInt("gameSettings.invis.timeBeforeGive") * 20));
    try {
      yamlConfiguration1.set("gameSettings.active", Boolean.valueOf(true));
      yamlConfiguration1.set("gameSettings.peaceful.enabled", Boolean.valueOf(true));
      yamlConfiguration1.save(configFile);
    } catch (IOException e) {
      e.printStackTrace();
    } 
    Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)this.plugin, new Runnable() {
          public void run() {
            Bukkit.broadcastMessage("§aGrace Period has §nEnded§a! Players can now be Killed and Eliminated!");
            try {
              config.set("gameSettings.peaceful.enabled", Boolean.valueOf(false));
              config.save(configFile);
            } catch (IOException e) {
              e.printStackTrace();
            } 
          }
        }(yamlConfiguration1.getInt("gameSettings.peaceful.delayToRemove") * 20));
    if (yamlConfiguration1.getBoolean("gameSettings.bossbar.enabled")) {
      BossBar header = HideOrHunt.getBossBar();
      for (Player oPlayer : Bukkit.getOnlinePlayers()) {
        if (PlayerRegistery.getPlayerBossBar(this.plugin, oPlayer))
          header.addPlayer(oPlayer); 
      } 
      ConfigRegistery.updateBossBar(this.plugin, true);
    } 
    if (ConfigRegistery.getCustomGamemodesStatus(this.plugin, "timerdeathswap"))
      if (yamlConfiguration2.getBoolean("timerdeathswap.swapDuringPeace")) {
        TimerDeathSwap.timerSwap(this.plugin, true, (FileConfiguration)yamlConfiguration1, (FileConfiguration)yamlConfiguration2);
      } else {
        TimerDeathSwap.timerSwap(this.plugin, false, (FileConfiguration)yamlConfiguration1, (FileConfiguration)yamlConfiguration2);
      }  
    return true;
  }
}
