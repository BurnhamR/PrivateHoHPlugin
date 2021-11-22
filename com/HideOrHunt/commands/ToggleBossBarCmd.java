package com.HideOrHunt.commands;

import com.HideOrHunt.HideOrHunt;
import com.HideOrHunt.config.PlayerRegistery;
import java.io.File;
import org.bukkit.boss.BossBar;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class ToggleBossBarCmd implements CommandExecutor {
  private HideOrHunt plugin;
  
  public ToggleBossBarCmd(HideOrHunt plugin) {
    this.plugin = plugin;
    plugin.getCommand("togglebossbar").setExecutor(this);
  }
  
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    Player player = (Player)sender;
    if (!(sender instanceof Player)) {
      sender.sendMessage("Only players may execute this command!");
      return true;
    } 
    if (args.length > 0) {
      player.sendMessage("§cOptions for /ToggleBossBar");
      player.sendMessage("§c/togglebossbar");
      return true;
    } 
    File configFile = new File("plugins/PrivateHideOrHunt/", "gamesettings.yml");
    YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(configFile);
    if (yamlConfiguration.getBoolean("gameSettings.bossbar.allowDisableForAll")) {
      updateBossBar((FileConfiguration)yamlConfiguration, player);
    } else if (player.isOp()) {
      updateBossBar((FileConfiguration)yamlConfiguration, player);
    } else {
      player.sendMessage("§cYou cannot use this command!");
      return true;
    } 
    return true;
  }
  
  public static void updateBossBar(FileConfiguration config, Player player) {
    if (!config.getBoolean("gameSettings.bossbar.enabled")) {
      player.sendMessage("§cThe Boss Bar is not Enabled!");
      return;
    } 
    if (PlayerRegistery.getPlayerBossBar(HideOrHunt.getInstance(), player)) {
      PlayerRegistery.setPlayerBossBar(HideOrHunt.getInstance(), player, false);
      player.sendMessage("§aYour Boss Bar has been §nDisabled§a.");
      if (config.getBoolean("gameSettings.bossbar.isShowing")) {
        BossBar header = HideOrHunt.getBossBar();
        for (Player listedPlayer : header.getPlayers()) {
          if (listedPlayer.getUniqueId().toString().equals(player.getUniqueId().toString())) {
            header.removePlayer(player);
            break;
          } 
        } 
      } 
    } else {
      PlayerRegistery.setPlayerBossBar(HideOrHunt.getInstance(), player, true);
      player.sendMessage("§aYour Boss Bar has been §nEnabled§a.");
      if (config.getBoolean("gameSettings.bossbar.isShowing")) {
        BossBar header = HideOrHunt.getBossBar();
        header.addPlayer(player);
      } 
    } 
  }
}
