package com.HideOrHunt.commands;

import com.HideOrHunt.HideOrHunt;
import com.HideOrHunt.menus.GameSettingsMenu;
import java.io.File;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class SetKitsCmd implements CommandExecutor {
  private HideOrHunt plugin;
  
  public SetKitsCmd(HideOrHunt plugin) {
    this.plugin = plugin;
    plugin.getCommand("setkits").setExecutor(this);
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
    File configFile = new File("plugins/PrivateHideOrHunt/", "gamesettings.yml");
    YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(configFile);
    player.openInventory(GameSettingsMenu.inventoryGameKits((FileConfiguration)yamlConfiguration));
    return true;
  }
}
