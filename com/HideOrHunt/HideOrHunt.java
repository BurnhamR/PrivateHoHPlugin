package com.HideOrHunt;

import com.HideOrHunt.config.ConfigRegistery;
import com.HideOrHunt.config.PlayerRegistery;
import com.HideOrHunt.config.TeamRegistery;
import com.HideOrHunt.gamemenus.CustomGamemodeMenu;
import com.HideOrHunt.gamemenus.TimerDeathSwapMenu;
import com.HideOrHunt.listeners.BlockDestroyEvent;
import com.HideOrHunt.listeners.BlockPlacementEvent;
import com.HideOrHunt.listeners.ChatEvent;
import com.HideOrHunt.listeners.EntityBlowupEvent;
import com.HideOrHunt.listeners.EntityDamageEntityEvent;
import com.HideOrHunt.listeners.MobBreedEvent;
import com.HideOrHunt.listeners.MobLoveModeEvent;
import com.HideOrHunt.listeners.MobSpawnEvent;
import com.HideOrHunt.listeners.MobTameEvent;
import com.HideOrHunt.listeners.PlayerDamageEvent;
import com.HideOrHunt.listeners.PlayerJoinServerEvent;
import com.HideOrHunt.listeners.PlayerKickedServerEvent;
import com.HideOrHunt.listeners.PlayerKilledEvent;
import com.HideOrHunt.listeners.PlayerMovementEvent;
import com.HideOrHunt.listeners.PlayerQuitServerEvent;
import com.HideOrHunt.listeners.PlayerTouchEvent;
import com.HideOrHunt.listeners.RespawnEvent;
import com.HideOrHunt.listeners.WeatherUpdateEvent;
import com.HideOrHunt.menus.BeaconSettingsMenu;
import com.HideOrHunt.menus.BossBarSettingsMenu;
import com.HideOrHunt.menus.DeathChanceMenu;
import com.HideOrHunt.menus.DeathMessagesSettingsMenu;
import com.HideOrHunt.menus.DeathSettingsMenu;
import com.HideOrHunt.menus.EntryMessageSettingsMenu;
import com.HideOrHunt.menus.ExplodeEditMenu;
import com.HideOrHunt.menus.GameKitsMenu;
import com.HideOrHunt.menus.GameSettingsMenu;
import com.HideOrHunt.menus.InvisibilityDurationMenu;
import com.HideOrHunt.menus.InvisibilitySettingsMenu;
import com.HideOrHunt.menus.KitEditMenu;
import com.HideOrHunt.menus.MobBreedingSettingsMenu;
import com.HideOrHunt.menus.MobSettingsMenu;
import com.HideOrHunt.menus.MobSpawnSettingsMenu;
import com.HideOrHunt.menus.MobTamingSettingsMenu;
import com.HideOrHunt.menus.PeaceDurationMenu;
import com.HideOrHunt.menus.PeaceSettingsMenu;
import com.HideOrHunt.menus.ServerSettingsMenu;
import com.HideOrHunt.menus.SlowfallDurationMenu;
import com.HideOrHunt.menus.SlowfallSettingsMenu;
import com.HideOrHunt.menus.SpecificGameSettingsMenu;
import com.HideOrHunt.menus.TeamCountMenu;
import com.HideOrHunt.menus.TeamNameCharacterMenu;
import com.HideOrHunt.menus.TeamNameSettingsMenu;
import com.HideOrHunt.menus.TeamTypeMenu;
import com.HideOrHunt.menus.WeatherSettingsMenu;
import com.HideOrHunt.utilities.Tab;
import java.io.File;
import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class HideOrHunt extends JavaPlugin {
  private static HideOrHunt instance;
  
  public static BossBar header = Bukkit.createBossBar("§f§lJoin: §b§lHide§f§lor§c§lHunt§f§l.com", BarColor.GREEN, BarStyle.SOLID, new org.bukkit.boss.BarFlag[0]);
  
  public static HideOrHunt getInstance() {
    return instance;
  }
  
  public static BossBar getBossBar() {
    return header;
  }
  
  public void onEnable() {
    instance = this;
    Bukkit.getLogger().info("Hide or Hunt > Starting the Plugin.");
    ConfigRegistery.createPluginFiles(instance);
    ConfigRegistery.loadPluginFiles(instance);
    PlayerRegistery.createUsersFolder(instance);
    TeamRegistery.createTeamsFolder(instance);
    getServer().getPluginManager().registerEvents((Listener)new MobSpawnEvent(), (Plugin)this);
    getServer().getPluginManager().registerEvents((Listener)new MobBreedEvent(), (Plugin)this);
    getServer().getPluginManager().registerEvents((Listener)new MobLoveModeEvent(), (Plugin)this);
    getServer().getPluginManager().registerEvents((Listener)new MobTameEvent(), (Plugin)this);
    getServer().getPluginManager().registerEvents((Listener)new WeatherUpdateEvent(), (Plugin)this);
    getServer().getPluginManager().registerEvents((Listener)new PlayerDamageEvent(), (Plugin)this);
    getServer().getPluginManager().registerEvents((Listener)new PlayerTouchEvent(), (Plugin)this);
    getServer().getPluginManager().registerEvents((Listener)new PlayerMovementEvent(), (Plugin)this);
    getServer().getPluginManager().registerEvents((Listener)new PlayerJoinServerEvent(), (Plugin)this);
    getServer().getPluginManager().registerEvents((Listener)new PlayerQuitServerEvent(), (Plugin)this);
    getServer().getPluginManager().registerEvents((Listener)new PlayerKickedServerEvent(), (Plugin)this);
    getServer().getPluginManager().registerEvents((Listener)new PlayerKilledEvent(), (Plugin)this);
    getServer().getPluginManager().registerEvents((Listener)new ChatEvent(), (Plugin)this);
    getServer().getPluginManager().registerEvents((Listener)new EntityBlowupEvent(), (Plugin)this);
    getServer().getPluginManager().registerEvents((Listener)new EntityDamageEntityEvent(), (Plugin)this);
    getServer().getPluginManager().registerEvents((Listener)new RespawnEvent(), (Plugin)this);
    getServer().getPluginManager().registerEvents((Listener)new BlockPlacementEvent(), (Plugin)this);
    getServer().getPluginManager().registerEvents((Listener)new BlockDestroyEvent(), (Plugin)this);
    getServer().getPluginManager().registerEvents((Listener)new GameSettingsMenu(), (Plugin)this);
    getServer().getPluginManager().registerEvents((Listener)new TeamTypeMenu(), (Plugin)this);
    getServer().getPluginManager().registerEvents((Listener)new TeamCountMenu(), (Plugin)this);
    getServer().getPluginManager().registerEvents((Listener)new TeamNameSettingsMenu(), (Plugin)this);
    getServer().getPluginManager().registerEvents((Listener)new TeamNameCharacterMenu(), (Plugin)this);
    getServer().getPluginManager().registerEvents((Listener)new MobSettingsMenu(), (Plugin)this);
    getServer().getPluginManager().registerEvents((Listener)new MobSpawnSettingsMenu(), (Plugin)this);
    getServer().getPluginManager().registerEvents((Listener)new MobBreedingSettingsMenu(), (Plugin)this);
    getServer().getPluginManager().registerEvents((Listener)new MobTamingSettingsMenu(), (Plugin)this);
    getServer().getPluginManager().registerEvents((Listener)new ServerSettingsMenu(), (Plugin)this);
    getServer().getPluginManager().registerEvents((Listener)new WeatherSettingsMenu(), (Plugin)this);
    getServer().getPluginManager().registerEvents((Listener)new EntryMessageSettingsMenu(), (Plugin)this);
    getServer().getPluginManager().registerEvents((Listener)new DeathMessagesSettingsMenu(), (Plugin)this);
    getServer().getPluginManager().registerEvents((Listener)new GameKitsMenu(), (Plugin)this);
    getServer().getPluginManager().registerEvents((Listener)new KitEditMenu(), (Plugin)this);
    getServer().getPluginManager().registerEvents((Listener)new SpecificGameSettingsMenu(), (Plugin)this);
    getServer().getPluginManager().registerEvents((Listener)new BeaconSettingsMenu(), (Plugin)this);
    getServer().getPluginManager().registerEvents((Listener)new BossBarSettingsMenu(), (Plugin)this);
    getServer().getPluginManager().registerEvents((Listener)new DeathSettingsMenu(), (Plugin)this);
    getServer().getPluginManager().registerEvents((Listener)new DeathChanceMenu(), (Plugin)this);
    getServer().getPluginManager().registerEvents((Listener)new SlowfallSettingsMenu(), (Plugin)this);
    getServer().getPluginManager().registerEvents((Listener)new SlowfallDurationMenu(), (Plugin)this);
    getServer().getPluginManager().registerEvents((Listener)new InvisibilitySettingsMenu(), (Plugin)this);
    getServer().getPluginManager().registerEvents((Listener)new InvisibilityDurationMenu(), (Plugin)this);
    getServer().getPluginManager().registerEvents((Listener)new PeaceSettingsMenu(), (Plugin)this);
    getServer().getPluginManager().registerEvents((Listener)new PeaceDurationMenu(), (Plugin)this);
    getServer().getPluginManager().registerEvents((Listener)new ExplodeEditMenu(), (Plugin)this);
    getServer().getPluginManager().registerEvents((Listener)new CustomGamemodeMenu(), (Plugin)this);
    getServer().getPluginManager().registerEvents((Listener)new TimerDeathSwapMenu(), (Plugin)this);
    Bukkit.getLogger().info("Hide or Hunt > Loading the Commands.");
    Bukkit.getLogger().info("Hide or Hunt > Starting the Tab.");
    Tab.runTab();
    Bukkit.getLogger().info("Hide or Hunt > Finishing Loading the Plugin.");
    File configFile = new File("plugins/PrivateHideOrHunt/", "gamesettings.yml");
    YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(configFile);
    if (yamlConfiguration.getBoolean("gameSettings.active") && 
      yamlConfiguration.getBoolean("gameSettings.bossbar.enabled")) {
      BossBar header = getBossBar();
      for (Player oPlayer : Bukkit.getOnlinePlayers()) {
        if (PlayerRegistery.getPlayerBossBar(getInstance(), oPlayer))
          header.addPlayer(oPlayer); 
      } 
      ConfigRegistery.updateBossBar(getInstance(), true);
      Bukkit.getLogger().info("Hide or Hunt > Reloading Mid-Game Boss Bar.");
    } 
  }
  
  public void onDisable() {
    Bukkit.getScheduler().cancelTasks((Plugin)getInstance());
    instance = null;
    for (Player player : Bukkit.getOnlinePlayers()) {
      BossBar header = getBossBar();
      for (Player listedPlayer : header.getPlayers()) {
        if (listedPlayer.getUniqueId().toString().equals(player.getUniqueId().toString()))
          header.removePlayer(player); 
      } 
    } 
  }
}
