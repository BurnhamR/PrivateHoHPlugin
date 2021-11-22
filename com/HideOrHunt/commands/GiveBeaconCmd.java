package com.HideOrHunt.commands;

import com.HideOrHunt.HideOrHunt;
import com.HideOrHunt.config.PlayerRegistery;
import com.HideOrHunt.utilities.BeaconUtilities;
import java.io.File;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class GiveBeaconCmd implements CommandExecutor {
  private HideOrHunt plugin;
  
  public GiveBeaconCmd(HideOrHunt plugin) {
    this.plugin = plugin;
    plugin.getCommand("givebeacon").setExecutor(this);
  }
  
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    Player player = (Player)sender;
    if (!(sender instanceof Player)) {
      sender.sendMessage("Only players may execute this command!");
      return true;
    } 
    File configFile = new File("plugins/PrivateHideOrHunt/", "gamesettings.yml");
    YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(configFile);
    if (args.length <= 0 || args.length > 1) {
      player.sendMessage("§cOptions for /GiveBeacon");
      player.sendMessage("§c/givebeacon <player>");
      return true;
    } 
    if (!player.isOp()) {
      player.sendMessage("§cYou cannot use this command!");
      return true;
    } 
    Player target = Bukkit.getPlayer(args[0]);
    if (target == null || !target.isOnline()) {
      player.sendMessage("§c§n\"" + args[0] + "\"§c is not Valid or is not Online!");
      return true;
    } 
    if (PlayerRegistery.isOnTeam(this.plugin, target)) {
      PlayerInventory playerInventory = target.getInventory();
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
        target.getWorld().dropItem(target.getLocation(), BeaconUtilities.giveBeacon((FileConfiguration)yamlConfiguration, target));
      } else {
        playerInventory.addItem(new ItemStack[] { BeaconUtilities.giveBeacon((FileConfiguration)yamlConfiguration, target) });
      } 
    } else {
      player.sendMessage("§c§n\"" + args[0] + "\"§c is not on a Team!");
      return true;
    } 
    return true;
  }
}
