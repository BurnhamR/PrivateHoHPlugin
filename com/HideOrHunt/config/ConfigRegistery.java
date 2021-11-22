package com.HideOrHunt.config;

import com.HideOrHunt.HideOrHunt;
import java.io.File;
import java.io.IOException;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class ConfigRegistery {
  private HideOrHunt plugin;
  
  public ConfigRegistery(HideOrHunt plugin) {
    this.plugin = plugin;
  }
  
  public static void createPluginFiles(HideOrHunt plugin) {
    Bukkit.getLogger().info("Hide or Hunt > Checking for Configuration Files.");
    File configFile = new File("plugins/PrivateHideOrHunt/", "gamesettings.yml");
    if (!configFile.exists())
      try {
        configFile.createNewFile();
        Bukkit.getLogger().info("Hide or Hunt > Creating gamesettings.yml File.");
      } catch (IOException e) {
        Bukkit.getLogger().info("Hide or Hunt > Error! gamesettings.yml File Missing.");
      }  
    File kitsFile = new File("plugins/PrivateHideOrHunt/", "kits.yml");
    if (!kitsFile.exists())
      try {
        kitsFile.createNewFile();
        Bukkit.getLogger().info("Hide or Hunt > Creating kits.yml File.");
      } catch (IOException e) {
        Bukkit.getLogger().info("Hide or Hunt > Error! kits.yml File Missing.");
      }  
    File beaconsFile = new File("plugins/PrivateHideOrHunt/", "beacons.yml");
    if (!beaconsFile.exists())
      try {
        beaconsFile.createNewFile();
        Bukkit.getLogger().info("Hide or Hunt > Creating beacons.yml File.");
      } catch (IOException e) {
        Bukkit.getLogger().info("Hide or Hunt > Error! beacons.yml File Missing.");
      }  
    File customFile = new File("plugins/PrivateHideOrHunt/", "custom.yml");
    if (!customFile.exists())
      try {
        customFile.createNewFile();
        Bukkit.getLogger().info("Hide or Hunt > Creating custom.yml File.");
      } catch (IOException e) {
        Bukkit.getLogger().info("Hide or Hunt > Error! custom.yml File Missing.");
      }  
    File dataFile = new File("plugins/PrivateHideOrHunt/", "data.yml");
    if (!dataFile.exists())
      try {
        dataFile.createNewFile();
        Bukkit.getLogger().info("Hide or Hunt > Creating data.yml File.");
      } catch (IOException e) {
        Bukkit.getLogger().info("Hide or Hunt > Error! data.yml File Missing.");
      }  
  }
  
  public static void listPluginFiles(HideOrHunt plugin, Player player) {
    byte b;
    int i;
    File[] arrayOfFile;
    for (i = (arrayOfFile = (new File("plugins/PrivateHideOrHunt/")).listFiles()).length, b = 0; b < i; ) {
      File config = arrayOfFile[b];
      if (config.isFile())
        player.sendMessage("§a" + config.getName()); 
      b++;
    } 
  }
  
  public static void loadPluginFiles(HideOrHunt plugin) {
    loadTeamSettings(plugin, "gamesettings");
    loadMobSpawningSettings(plugin, "gamesettings");
    loadMobBreedingSettings(plugin, "gamesettings");
    loadMobTamingSettings(plugin, "gamesettings");
    loadServerSettings(plugin, "gamesettings");
    loadGameSettings(plugin, "gamesettings");
    loadExplodeBlacklist(plugin, "explode");
    loadGameKits(plugin, "kits");
    loadBeacons(plugin, "beacons");
    loadCustomGamemodes(plugin, "custom");
  }
  
  public static void registerBeacon(HideOrHunt plugin, Player player, String teamID, Location beaconLocation) {
    File configFile = new File("plugins/PrivateHideOrHunt/", "beacons.yml");
    YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(configFile);
    Bukkit.getLogger().info("Hide or Hunt > Registering a New Beacon - " + teamID);
    if (!yamlConfiguration.isSet("beacons." + teamID)) {
      yamlConfiguration.set("beacons." + teamID + ".name", TeamRegistery.getTeamName(plugin, teamID));
      yamlConfiguration.set("beacons." + teamID + ".status", "placed");
      yamlConfiguration.set("beacons." + teamID + ".world", beaconLocation.getWorld().getName());
      yamlConfiguration.set("beacons." + teamID + ".x", Double.valueOf(beaconLocation.getX()));
      yamlConfiguration.set("beacons." + teamID + ".y", Double.valueOf(beaconLocation.getY()));
      yamlConfiguration.set("beacons." + teamID + ".z", Double.valueOf(beaconLocation.getZ()));
    } 
    try {
      yamlConfiguration.save(configFile);
      Bukkit.getLogger().info("Hide or Hunt > Saving Configuration Files [Beacon].");
    } catch (IOException e) {
      e.printStackTrace();
    } 
  }
  
  public static void loadTeamSettings(HideOrHunt plugin, String fileName) {
    File configFile = new File("plugins/PrivateHideOrHunt/", String.valueOf(fileName) + ".yml");
    YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(configFile);
    Bukkit.getLogger().info("Hide or Hunt > Loading Configuration Files [TeamSettings].");
    if (!yamlConfiguration.isSet("teamsSettings.type")) {
      yamlConfiguration.set("teamsSettings.type", "SOLO");
      Bukkit.getLogger().info("Hide or Hunt > Setting the Default Team Type.");
    } 
    if (!yamlConfiguration.isSet("teamsSettings.maximumTeamCount")) {
      yamlConfiguration.set("teamsSettings.maximumTeamCount", Integer.valueOf(12));
      Bukkit.getLogger().info("Hide or Hunt > Setting the Default Maximum Team Count.");
    } 
    if (!yamlConfiguration.isSet("teamsSettings.currentTeamCount")) {
      yamlConfiguration.set("teamsSettings.currentTeamCount", Integer.valueOf(0));
      Bukkit.getLogger().info("Hide or Hunt > Setting the Default Current Team Count.");
    } 
    if (!yamlConfiguration.isSet("teamsSettings.minTeamNameCharacters")) {
      yamlConfiguration.set("teamsSettings.minTeamNameCharacters", Integer.valueOf(3));
      Bukkit.getLogger().info("Hide or Hunt > Setting the Default Minimum Team Name Character.");
    } 
    if (!yamlConfiguration.isSet("teamsSettings.maxTeamNameCharacters")) {
      yamlConfiguration.set("teamsSettings.maxTeamNameCharacters", Integer.valueOf(20));
      Bukkit.getLogger().info("Hide or Hunt > Setting the Default Maximum Team Name Character.");
    } 
    if (!yamlConfiguration.isSet("teamsSettings.canTeamNameContainNumber")) {
      yamlConfiguration.set("teamsSettings.canTeamNameContainNumber", Boolean.valueOf(true));
      Bukkit.getLogger().info("Hide or Hunt > Setting the Default \"CanTeamNameContainNumber\".");
    } 
    if (!yamlConfiguration.isSet("teamsSettings.canTeamNameContainRandomCharacters")) {
      yamlConfiguration.set("teamsSettings.canTeamNameContainRandomCharacters", Boolean.valueOf(false));
      Bukkit.getLogger().info("Hide or Hunt > Setting the Default \"CanTeamNameContainRandomCharacters\".");
    } 
    try {
      yamlConfiguration.save(configFile);
      Bukkit.getLogger().info("Hide or Hunt > Saving Configuration Files [TeamSettings].");
    } catch (IOException e) {
      e.printStackTrace();
    } 
  }
  
  public static void updateTeamCount(HideOrHunt plugin, int increaseBy) {
    File configFile = new File("plugins/PrivateHideOrHunt/", "gamesettings.yml");
    YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(configFile);
    loadTeamSettings(plugin, "gamesettings");
    int currentCount = yamlConfiguration.getInt("teamsSettings.currentTeamCount");
    yamlConfiguration.set("teamsSettings.currentTeamCount", Integer.valueOf(currentCount + increaseBy));
    Bukkit.getLogger().info("Hide or Hunt > Updating the Current Team Count to " + (currentCount + increaseBy) + ".");
    try {
      yamlConfiguration.save(configFile);
      Bukkit.getLogger().info("Hide or Hunt > Saving Configuration Files [TeamSettings].");
    } catch (IOException e) {
      e.printStackTrace();
    } 
  }
  
  public static void loadMobSpawningSettings(HideOrHunt plugin, String fileName) {
    File configFile = new File("plugins/PrivateHideOrHunt/", String.valueOf(fileName) + ".yml");
    YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(configFile);
    Bukkit.getLogger().info("Hide or Hunt > Loading Configuration Files [MobSpawningSettings].");
    if (!yamlConfiguration.isSet("mobSettings.mobSpawn.bat")) {
      yamlConfiguration.set("mobSettings.mobSpawn.bat", Boolean.valueOf(false));
      Bukkit.getLogger().info("Hide or Hunt > Setting the BAT Spawn.");
    } 
    if (!yamlConfiguration.isSet("mobSettings.mobSpawn.bee")) {
      yamlConfiguration.set("mobSettings.mobSpawn.bee", Boolean.valueOf(true));
      Bukkit.getLogger().info("Hide or Hunt > Setting the BEE Spawn.");
    } 
    if (!yamlConfiguration.isSet("mobSettings.mobSpawn.blaze")) {
      yamlConfiguration.set("mobSettings.mobSpawn.blaze", Boolean.valueOf(false));
      Bukkit.getLogger().info("Hide or Hunt > Setting the BLAZE Spawn.");
    } 
    if (!yamlConfiguration.isSet("mobSettings.mobSpawn.cat")) {
      yamlConfiguration.set("mobSettings.mobSpawn.cat", Boolean.valueOf(false));
      Bukkit.getLogger().info("Hide or Hunt > Setting the CAT Spawn.");
    } 
    if (!yamlConfiguration.isSet("mobSettings.mobSpawn.cavespider")) {
      yamlConfiguration.set("mobSettings.mobSpawn.cavespider", Boolean.valueOf(true));
      Bukkit.getLogger().info("Hide or Hunt > Setting the CAVE SPIDER Spawn.");
    } 
    if (!yamlConfiguration.isSet("mobSettings.mobSpawn.chicken")) {
      yamlConfiguration.set("mobSettings.mobSpawn.chicken", Boolean.valueOf(true));
      Bukkit.getLogger().info("Hide or Hunt > Setting the CHICKEN Spawn.");
    } 
    if (!yamlConfiguration.isSet("mobSettings.mobSpawn.cod")) {
      yamlConfiguration.set("mobSettings.mobSpawn.cod", Boolean.valueOf(true));
      Bukkit.getLogger().info("Hide or Hunt > Setting the COD Spawn.");
    } 
    if (!yamlConfiguration.isSet("mobSettings.mobSpawn.creeper")) {
      yamlConfiguration.set("mobSettings.mobSpawn.creeper.normal", Boolean.valueOf(true));
      yamlConfiguration.set("mobSettings.mobSpawn.creeper.charged", Boolean.valueOf(false));
      Bukkit.getLogger().info("Hide or Hunt > Setting the CREEPER Spawn.");
    } 
    if (!yamlConfiguration.isSet("mobSettings.mobSpawn.dolphin")) {
      yamlConfiguration.set("mobSettings.mobSpawn.dolphin", Boolean.valueOf(true));
      Bukkit.getLogger().info("Hide or Hunt > Setting the DOLPHIN Spawn.");
    } 
    if (!yamlConfiguration.isSet("mobSettings.mobSpawn.donkey")) {
      yamlConfiguration.set("mobSettings.mobSpawn.donkey", Boolean.valueOf(false));
      Bukkit.getLogger().info("Hide or Hunt > Setting the DONKEY Spawn.");
    } 
    if (!yamlConfiguration.isSet("mobSettings.mobSpawn.drowned")) {
      yamlConfiguration.set("mobSettings.mobSpawn.drowned", Boolean.valueOf(true));
      Bukkit.getLogger().info("Hide or Hunt > Setting the DROWNED Spawn.");
    } 
    if (!yamlConfiguration.isSet("mobSettings.mobSpawn.elderguardian")) {
      yamlConfiguration.set("mobSettings.mobSpawn.elderguardian", Boolean.valueOf(false));
      Bukkit.getLogger().info("Hide or Hunt > Setting the ELDER GUARDIAN Spawn.");
    } 
    if (!yamlConfiguration.isSet("mobSettings.mobSpawn.enderdragon")) {
      yamlConfiguration.set("mobSettings.mobSpawn.enderdragon", Boolean.valueOf(false));
      Bukkit.getLogger().info("Hide or Hunt > Setting the ENDER DRAGON Spawn.");
    } 
    if (!yamlConfiguration.isSet("mobSettings.mobSpawn.enderman")) {
      yamlConfiguration.set("mobSettings.mobSpawn.enderman", Boolean.valueOf(true));
      Bukkit.getLogger().info("Hide or Hunt > Setting the ENDERMAN Spawn.");
    } 
    if (!yamlConfiguration.isSet("mobSettings.mobSpawn.endermite")) {
      yamlConfiguration.set("mobSettings.mobSpawn.endermite", Boolean.valueOf(true));
      Bukkit.getLogger().info("Hide or Hunt > Setting the ENDERMITE Spawn.");
    } 
    if (!yamlConfiguration.isSet("mobSettings.mobSpawn.evoker")) {
      yamlConfiguration.set("mobSettings.mobSpawn.evoker", Boolean.valueOf(false));
      Bukkit.getLogger().info("Hide or Hunt > Setting the EVOKER Spawn.");
    } 
    if (!yamlConfiguration.isSet("mobSettings.mobSpawn.fox")) {
      yamlConfiguration.set("mobSettings.mobSpawn.fox", Boolean.valueOf(true));
      Bukkit.getLogger().info("Hide or Hunt > Setting the FOX Spawn.");
    } 
    if (!yamlConfiguration.isSet("mobSettings.mobSpawn.ghast")) {
      yamlConfiguration.set("mobSettings.mobSpawn.ghast", Boolean.valueOf(false));
      Bukkit.getLogger().info("Hide or Hunt > Setting the GHAST Spawn.");
    } 
    if (!yamlConfiguration.isSet("mobSettings.mobSpawn.giant")) {
      yamlConfiguration.set("mobSettings.mobSpawn.giant", Boolean.valueOf(false));
      Bukkit.getLogger().info("Hide or Hunt > Setting the GIANT Spawn.");
    } 
    if (!yamlConfiguration.isSet("mobSettings.mobSpawn.guardian")) {
      yamlConfiguration.set("mobSettings.mobSpawn.guardian", Boolean.valueOf(true));
      Bukkit.getLogger().info("Hide or Hunt > Setting the GUARDIAN Spawn.");
    } 
    if (!yamlConfiguration.isSet("mobSettings.mobSpawn.hoglin")) {
      yamlConfiguration.set("mobSettings.mobSpawn.hoglin", Boolean.valueOf(false));
      Bukkit.getLogger().info("Hide or Hunt > Setting the HOGLIN Spawn.");
    } 
    if (!yamlConfiguration.isSet("mobSettings.mobSpawn.horse")) {
      yamlConfiguration.set("mobSettings.mobSpawn.horse", Boolean.valueOf(true));
      Bukkit.getLogger().info("Hide or Hunt > Setting the HORSE Spawn.");
    } 
    if (!yamlConfiguration.isSet("mobSettings.mobSpawn.husk")) {
      yamlConfiguration.set("mobSettings.mobSpawn.husk", Boolean.valueOf(true));
      Bukkit.getLogger().info("Hide or Hunt > Setting the HUSK Spawn.");
    } 
    if (!yamlConfiguration.isSet("mobSettings.mobSpawn.illusioner")) {
      yamlConfiguration.set("mobSettings.mobSpawn.illusioner", Boolean.valueOf(false));
      Bukkit.getLogger().info("Hide or Hunt > Setting the ILLUSIONER Spawn.");
    } 
    if (!yamlConfiguration.isSet("mobSettings.mobSpawn.irongolem")) {
      yamlConfiguration.set("mobSettings.mobSpawn.irongolem", Boolean.valueOf(true));
      Bukkit.getLogger().info("Hide or Hunt > Setting the IRON GOLEM Spawn.");
    } 
    if (!yamlConfiguration.isSet("mobSettings.mobSpawn.llama")) {
      yamlConfiguration.set("mobSettings.mobSpawn.llama", Boolean.valueOf(true));
      Bukkit.getLogger().info("Hide or Hunt > Setting the LLAMA Spawn.");
    } 
    if (!yamlConfiguration.isSet("mobSettings.mobSpawn.magmacube")) {
      yamlConfiguration.set("mobSettings.mobSpawn.magmacube.small", Boolean.valueOf(false));
      yamlConfiguration.set("mobSettings.mobSpawn.magmacube.mid", Boolean.valueOf(false));
      yamlConfiguration.set("mobSettings.mobSpawn.magmacube.large", Boolean.valueOf(false));
      Bukkit.getLogger().info("Hide or Hunt > Setting the MAGMA CUBE Spawn.");
    } 
    if (!yamlConfiguration.isSet("mobSettings.mobSpawn.mule")) {
      yamlConfiguration.set("mobSettings.mobSpawn.mule", Boolean.valueOf(true));
      Bukkit.getLogger().info("Hide or Hunt > Setting the MULE Spawn.");
    } 
    if (!yamlConfiguration.isSet("mobSettings.mobSpawn.mushroomcow")) {
      yamlConfiguration.set("mobSettings.mobSpawn.mushroomcow", Boolean.valueOf(true));
      Bukkit.getLogger().info("Hide or Hunt > Setting the MOOSHROOM Spawn.");
    } 
    if (!yamlConfiguration.isSet("mobSettings.mobSpawn.ocelot")) {
      yamlConfiguration.set("mobSettings.mobSpawn.ocelot", Boolean.valueOf(true));
      Bukkit.getLogger().info("Hide or Hunt > Setting the OCELOT Spawn.");
    } 
    if (!yamlConfiguration.isSet("mobSettings.mobSpawn.panda")) {
      yamlConfiguration.set("mobSettings.mobSpawn.panda", Boolean.valueOf(true));
      Bukkit.getLogger().info("Hide or Hunt > Setting the PANDA Spawn.");
    } 
    if (!yamlConfiguration.isSet("mobSettings.mobSpawn.parrot")) {
      yamlConfiguration.set("mobSettings.mobSpawn.parrot", Boolean.valueOf(false));
      Bukkit.getLogger().info("Hide or Hunt > Setting the PARROT Spawn.");
    } 
    if (!yamlConfiguration.isSet("mobSettings.mobSpawn.phantom")) {
      yamlConfiguration.set("mobSettings.mobSpawn.phantom", Boolean.valueOf(false));
      Bukkit.getLogger().info("Hide or Hunt > Setting the PHANTOM Spawn.");
    } 
    if (!yamlConfiguration.isSet("mobSettings.mobSpawn.pig")) {
      yamlConfiguration.set("mobSettings.mobSpawn.pig", Boolean.valueOf(false));
      Bukkit.getLogger().info("Hide or Hunt > Setting the PIG Spawn.");
    } 
    if (!yamlConfiguration.isSet("mobSettings.mobSpawn.piglin")) {
      yamlConfiguration.set("mobSettings.mobSpawn.piglin", Boolean.valueOf(false));
      Bukkit.getLogger().info("Hide or Hunt > Setting the PIGLIN Spawn.");
    } 
    if (!yamlConfiguration.isSet("mobSettings.mobSpawn.pillager")) {
      yamlConfiguration.set("mobSettings.mobSpawn.pillager", Boolean.valueOf(false));
      Bukkit.getLogger().info("Hide or Hunt > Setting the PILLAGER Spawn.");
    } 
    if (!yamlConfiguration.isSet("mobSettings.mobSpawn.polarbear")) {
      yamlConfiguration.set("mobSettings.mobSpawn.polarbear", Boolean.valueOf(false));
      Bukkit.getLogger().info("Hide or Hunt > Setting the POLAR BEAR Spawn.");
    } 
    if (!yamlConfiguration.isSet("mobSettings.mobSpawn.pufferfish")) {
      yamlConfiguration.set("mobSettings.mobSpawn.pufferfish", Boolean.valueOf(false));
      Bukkit.getLogger().info("Hide or Hunt > Setting the PUFFERFISH Spawn.");
    } 
    if (!yamlConfiguration.isSet("mobSettings.mobSpawn.rabbit")) {
      yamlConfiguration.set("mobSettings.mobSpawn.rabbit", Boolean.valueOf(false));
      Bukkit.getLogger().info("Hide or Hunt > Setting the RABBIT Spawn.");
    } 
    if (!yamlConfiguration.isSet("mobSettings.mobSpawn.ravager")) {
      yamlConfiguration.set("mobSettings.mobSpawn.ravager", Boolean.valueOf(false));
      Bukkit.getLogger().info("Hide or Hunt > Setting the RAVAGER Spawn.");
    } 
    if (!yamlConfiguration.isSet("mobSettings.mobSpawn.salmon")) {
      yamlConfiguration.set("mobSettings.mobSpawn.salmon", Boolean.valueOf(false));
      Bukkit.getLogger().info("Hide or Hunt > Setting the SALMON Spawn.");
    } 
    if (!yamlConfiguration.isSet("mobSettings.mobSpawn.sheep")) {
      yamlConfiguration.set("mobSettings.mobSpawn.sheep", Boolean.valueOf(false));
      Bukkit.getLogger().info("Hide or Hunt > Setting the SHEEP Spawn.");
    } 
    if (!yamlConfiguration.isSet("mobSettings.mobSpawn.shulker")) {
      yamlConfiguration.set("mobSettings.mobSpawn.shulker", Boolean.valueOf(false));
      Bukkit.getLogger().info("Hide or Hunt > Setting the SHULKER Spawn.");
    } 
    if (!yamlConfiguration.isSet("mobSettings.mobSpawn.silverfish")) {
      yamlConfiguration.set("mobSettings.mobSpawn.silverfish", Boolean.valueOf(false));
      Bukkit.getLogger().info("Hide or Hunt > Setting the SILVERFISH Spawn.");
    } 
    if (!yamlConfiguration.isSet("mobSettings.mobSpawn.skeleton")) {
      yamlConfiguration.set("mobSettings.mobSpawn.skeleton", Boolean.valueOf(false));
      Bukkit.getLogger().info("Hide or Hunt > Setting the SKELETON Spawn.");
    } 
    if (!yamlConfiguration.isSet("mobSettings.mobSpawn.skeletonhorse")) {
      yamlConfiguration.set("mobSettings.mobSpawn.skeletonhorse", Boolean.valueOf(false));
      Bukkit.getLogger().info("Hide or Hunt > Setting the SKELETON HORSE Spawn.");
    } 
    if (!yamlConfiguration.isSet("mobSettings.mobSpawn.slime")) {
      yamlConfiguration.set("mobSettings.mobSpawn.slime.small", Boolean.valueOf(false));
      yamlConfiguration.set("mobSettings.mobSpawn.slime.mid", Boolean.valueOf(false));
      yamlConfiguration.set("mobSettings.mobSpawn.slime.large", Boolean.valueOf(false));
      Bukkit.getLogger().info("Hide or Hunt > Setting the SLIME Spawn.");
    } 
    if (!yamlConfiguration.isSet("mobSettings.mobSpawn.snowman")) {
      yamlConfiguration.set("mobSettings.mobSpawn.snowman", Boolean.valueOf(false));
      Bukkit.getLogger().info("Hide or Hunt > Setting the SNOWMAN Spawn.");
    } 
    if (!yamlConfiguration.isSet("mobSettings.mobSpawn.spider")) {
      yamlConfiguration.set("mobSettings.mobSpawn.spider", Boolean.valueOf(false));
      Bukkit.getLogger().info("Hide or Hunt > Setting the SPIDER Spawn.");
    } 
    if (!yamlConfiguration.isSet("mobSettings.mobSpawn.squid")) {
      yamlConfiguration.set("mobSettings.mobSpawn.squid", Boolean.valueOf(false));
      Bukkit.getLogger().info("Hide or Hunt > Setting the SQUID Spawn.");
    } 
    if (!yamlConfiguration.isSet("mobSettings.mobSpawn.stray")) {
      yamlConfiguration.set("mobSettings.mobSpawn.stray", Boolean.valueOf(false));
      Bukkit.getLogger().info("Hide or Hunt > Setting the STRAY Spawn.");
    } 
    if (!yamlConfiguration.isSet("mobSettings.mobSpawn.strider")) {
      yamlConfiguration.set("mobSettings.mobSpawn.strider", Boolean.valueOf(false));
      Bukkit.getLogger().info("Hide or Hunt > Setting the STRIDER Spawn.");
    } 
    if (!yamlConfiguration.isSet("mobSettings.mobSpawn.traderllama")) {
      yamlConfiguration.set("mobSettings.mobSpawn.traderllama", Boolean.valueOf(false));
      Bukkit.getLogger().info("Hide or Hunt > Setting the TRADER LLAMA Spawn.");
    } 
    if (!yamlConfiguration.isSet("mobSettings.mobSpawn.tropicalfish")) {
      yamlConfiguration.set("mobSettings.mobSpawn.tropicalfish", Boolean.valueOf(false));
      Bukkit.getLogger().info("Hide or Hunt > Setting the TROPICAL FISH Spawn.");
    } 
    if (!yamlConfiguration.isSet("mobSettings.mobSpawn.turtle")) {
      yamlConfiguration.set("mobSettings.mobSpawn.turtle", Boolean.valueOf(false));
      Bukkit.getLogger().info("Hide or Hunt > Setting the TURTLE Spawn.");
    } 
    if (!yamlConfiguration.isSet("mobSettings.mobSpawn.vex")) {
      yamlConfiguration.set("mobSettings.mobSpawn.vex", Boolean.valueOf(false));
      Bukkit.getLogger().info("Hide or Hunt > Setting the VEX Spawn.");
    } 
    if (!yamlConfiguration.isSet("mobSettings.mobSpawn.villager")) {
      yamlConfiguration.set("mobSettings.mobSpawn.villager", Boolean.valueOf(false));
      Bukkit.getLogger().info("Hide or Hunt > Setting the VILLAGER Spawn.");
    } 
    if (!yamlConfiguration.isSet("mobSettings.mobSpawn.vindicator")) {
      yamlConfiguration.set("mobSettings.mobSpawn.vindicator", Boolean.valueOf(false));
      Bukkit.getLogger().info("Hide or Hunt > Setting the VINDICATOR Spawn.");
    } 
    if (!yamlConfiguration.isSet("mobSettings.mobSpawn.wanderingtrader")) {
      yamlConfiguration.set("mobSettings.mobSpawn.wanderingtrader", Boolean.valueOf(false));
      Bukkit.getLogger().info("Hide or Hunt > Setting the WANDERING TRADER Spawn.");
    } 
    if (!yamlConfiguration.isSet("mobSettings.mobSpawn.witch")) {
      yamlConfiguration.set("mobSettings.mobSpawn.witch", Boolean.valueOf(false));
      Bukkit.getLogger().info("Hide or Hunt > Setting the WITCH Spawn.");
    } 
    if (!yamlConfiguration.isSet("mobSettings.mobSpawn.wither")) {
      yamlConfiguration.set("mobSettings.mobSpawn.wither", Boolean.valueOf(false));
      Bukkit.getLogger().info("Hide or Hunt > Setting the WITHER Spawn.");
    } 
    if (!yamlConfiguration.isSet("mobSettings.mobSpawn.witherskeleton")) {
      yamlConfiguration.set("mobSettings.mobSpawn.witherskeleton", Boolean.valueOf(false));
      Bukkit.getLogger().info("Hide or Hunt > Setting the WITHER SKELETON Spawn.");
    } 
    if (!yamlConfiguration.isSet("mobSettings.mobSpawn.wolf")) {
      yamlConfiguration.set("mobSettings.mobSpawn.wolf", Boolean.valueOf(false));
      Bukkit.getLogger().info("Hide or Hunt > Setting the WOLF Spawn.");
    } 
    if (!yamlConfiguration.isSet("mobSettings.mobSpawn.zoglin")) {
      yamlConfiguration.set("mobSettings.mobSpawn.zoglin", Boolean.valueOf(false));
      Bukkit.getLogger().info("Hide or Hunt > Setting the ZOGLIN Spawn.");
    } 
    if (!yamlConfiguration.isSet("mobSettings.mobSpawn.zombie")) {
      yamlConfiguration.set("mobSettings.mobSpawn.zombie", Boolean.valueOf(false));
      Bukkit.getLogger().info("Hide or Hunt > Setting the ZOMBIE Spawn.");
    } 
    if (!yamlConfiguration.isSet("mobSettings.mobSpawn.zombiehorse")) {
      yamlConfiguration.set("mobSettings.mobSpawn.zombiehorse", Boolean.valueOf(false));
      Bukkit.getLogger().info("Hide or Hunt > Setting the ZOMBIE HORSE Spawn.");
    } 
    if (!yamlConfiguration.isSet("mobSettings.mobSpawn.zombievillager")) {
      yamlConfiguration.set("mobSettings.mobSpawn.zombievillager", Boolean.valueOf(false));
      Bukkit.getLogger().info("Hide or Hunt > Setting the ZOMBIE VILLAGER Spawn.");
    } 
    if (!yamlConfiguration.isSet("mobSettings.mobSpawn.zombifiedpiglin")) {
      yamlConfiguration.set("mobSettings.mobSpawn.zombifiedpiglin", Boolean.valueOf(false));
      Bukkit.getLogger().info("Hide or Hunt > Setting the ZOMBIFIED PIGLIN Spawn.");
    } 
    try {
      yamlConfiguration.save(configFile);
      Bukkit.getLogger().info("Hide or Hunt > Saving Configuration Files [MobSpawningSettings].");
    } catch (IOException e) {
      e.printStackTrace();
    } 
  }
  
  public static void loadMobBreedingSettings(HideOrHunt plugin, String fileName) {
    File configFile = new File("plugins/PrivateHideOrHunt/", String.valueOf(fileName) + ".yml");
    YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(configFile);
    Bukkit.getLogger().info("Hide or Hunt > Loading Configuration Files [MobBreedingSettings].");
    if (!yamlConfiguration.isSet("mobSettings.mobBreed.bee")) {
      yamlConfiguration.set("mobSettings.mobBreed.bee", Boolean.valueOf(true));
      Bukkit.getLogger().info("Hide or Hunt > Setting the BEE Breedable.");
    } 
    if (!yamlConfiguration.isSet("mobSettings.mobBreed.cat")) {
      yamlConfiguration.set("mobSettings.mobBreed.cat", Boolean.valueOf(false));
      Bukkit.getLogger().info("Hide or Hunt > Setting the CAT Breedable.");
    } 
    if (!yamlConfiguration.isSet("mobSettings.mobBreed.chicken")) {
      yamlConfiguration.set("mobSettings.mobBreed.chicken", Boolean.valueOf(true));
      Bukkit.getLogger().info("Hide or Hunt > Setting the CHICKEN Breedable.");
    } 
    if (!yamlConfiguration.isSet("mobSettings.mobBreed.donkey")) {
      yamlConfiguration.set("mobSettings.mobBreed.donkey", Boolean.valueOf(false));
      Bukkit.getLogger().info("Hide or Hunt > Setting the DONKEY Breedable.");
    } 
    if (!yamlConfiguration.isSet("mobSettings.mobBreed.fox")) {
      yamlConfiguration.set("mobSettings.mobBreed.fox", Boolean.valueOf(true));
      Bukkit.getLogger().info("Hide or Hunt > Setting the FOX Breedable.");
    } 
    if (!yamlConfiguration.isSet("mobSettings.mobBreed.hoglin")) {
      yamlConfiguration.set("mobSettings.mobBreed.hoglin", Boolean.valueOf(false));
      Bukkit.getLogger().info("Hide or Hunt > Setting the HOGLIN Breedable.");
    } 
    if (!yamlConfiguration.isSet("mobSettings.mobBreed.horse")) {
      yamlConfiguration.set("mobSettings.mobBreed.horse", Boolean.valueOf(true));
      Bukkit.getLogger().info("Hide or Hunt > Setting the HORSE Breedable.");
    } 
    if (!yamlConfiguration.isSet("mobSettings.mobBreed.llama")) {
      yamlConfiguration.set("mobSettings.mobBreed.llama", Boolean.valueOf(true));
      Bukkit.getLogger().info("Hide or Hunt > Setting the LLAMA Breedable.");
    } 
    if (!yamlConfiguration.isSet("mobSettings.mobBreed.mule")) {
      yamlConfiguration.set("mobSettings.mobBreed.mule", Boolean.valueOf(true));
      Bukkit.getLogger().info("Hide or Hunt > Setting the MULE Breedable.");
    } 
    if (!yamlConfiguration.isSet("mobSettings.mobBreed.mushroomcow")) {
      yamlConfiguration.set("mobSettings.mobBreed.mushroomcow", Boolean.valueOf(true));
      Bukkit.getLogger().info("Hide or Hunt > Setting the MOOSHROOM Breedable.");
    } 
    if (!yamlConfiguration.isSet("mobSettings.mobBreed.ocelot")) {
      yamlConfiguration.set("mobSettings.mobBreed.ocelot", Boolean.valueOf(true));
      Bukkit.getLogger().info("Hide or Hunt > Setting the OCELOT Breedable.");
    } 
    if (!yamlConfiguration.isSet("mobSettings.mobBreed.panda")) {
      yamlConfiguration.set("mobSettings.mobBreed.panda", Boolean.valueOf(true));
      Bukkit.getLogger().info("Hide or Hunt > Setting the PANDA Breedable.");
    } 
    if (!yamlConfiguration.isSet("mobSettings.mobBreed.pig")) {
      yamlConfiguration.set("mobSettings.mobBreed.pig", Boolean.valueOf(false));
      Bukkit.getLogger().info("Hide or Hunt > Setting the PIG Breedable.");
    } 
    if (!yamlConfiguration.isSet("mobSettings.mobBreed.rabbit")) {
      yamlConfiguration.set("mobSettings.mobBreed.rabbit", Boolean.valueOf(false));
      Bukkit.getLogger().info("Hide or Hunt > Setting the RABBIT Breedable.");
    } 
    if (!yamlConfiguration.isSet("mobSettings.mobBreed.sheep")) {
      yamlConfiguration.set("mobSettings.mobBreed.sheep", Boolean.valueOf(false));
      Bukkit.getLogger().info("Hide or Hunt > Setting the SHEEP Breedable.");
    } 
    if (!yamlConfiguration.isSet("mobSettings.mobBreed.strider")) {
      yamlConfiguration.set("mobSettings.mobBreed.strider", Boolean.valueOf(false));
      Bukkit.getLogger().info("Hide or Hunt > Setting the STRIDER Breedable.");
    } 
    if (!yamlConfiguration.isSet("mobSettings.mobBreed.turtle")) {
      yamlConfiguration.set("mobSettings.mobBreed.turtle", Boolean.valueOf(false));
      Bukkit.getLogger().info("Hide or Hunt > Setting the TURTLE Breedable.");
    } 
    if (!yamlConfiguration.isSet("mobSettings.mobBreed.wolf")) {
      yamlConfiguration.set("mobSettings.mobBreed.wolf", Boolean.valueOf(false));
      Bukkit.getLogger().info("Hide or Hunt > Setting the WOLF Breedable.");
    } 
    try {
      yamlConfiguration.save(configFile);
      Bukkit.getLogger().info("Hide or Hunt > Saving Configuration Files [MobBreedingSettings].");
    } catch (IOException e) {
      e.printStackTrace();
    } 
  }
  
  public static void loadMobTamingSettings(HideOrHunt plugin, String fileName) {
    File configFile = new File("plugins/PrivateHideOrHunt/", String.valueOf(fileName) + ".yml");
    YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(configFile);
    Bukkit.getLogger().info("Hide or Hunt > Loading Configuration Files [MobTamingSettings].");
    if (!yamlConfiguration.isSet("mobSettings.mobTame.cat")) {
      yamlConfiguration.set("mobSettings.mobTame.cat", Boolean.valueOf(false));
      Bukkit.getLogger().info("Hide or Hunt > Setting the CAT Tamable.");
    } 
    if (!yamlConfiguration.isSet("mobSettings.mobTame.donkey")) {
      yamlConfiguration.set("mobSettings.mobTame.donkey", Boolean.valueOf(false));
      Bukkit.getLogger().info("Hide or Hunt > Setting the DONKEY Tamable.");
    } 
    if (!yamlConfiguration.isSet("mobSettings.mobTame.horse")) {
      yamlConfiguration.set("mobSettings.mobTame.horse", Boolean.valueOf(true));
      Bukkit.getLogger().info("Hide or Hunt > Setting the HORSE Tamable.");
    } 
    if (!yamlConfiguration.isSet("mobSettings.mobTame.llama")) {
      yamlConfiguration.set("mobSettings.mobTame.llama", Boolean.valueOf(true));
      Bukkit.getLogger().info("Hide or Hunt > Setting the LLAMA Tamable.");
    } 
    if (!yamlConfiguration.isSet("mobSettings.mobTame.mule")) {
      yamlConfiguration.set("mobSettings.mobTame.mule", Boolean.valueOf(true));
      Bukkit.getLogger().info("Hide or Hunt > Setting the MULE Tamable.");
    } 
    if (!yamlConfiguration.isSet("mobSettings.mobTame.ocelot")) {
      yamlConfiguration.set("mobSettings.mobTame.ocelot", Boolean.valueOf(true));
      Bukkit.getLogger().info("Hide or Hunt > Setting the OCELOT Tamable.");
    } 
    if (!yamlConfiguration.isSet("mobSettings.mobTame.parrot")) {
      yamlConfiguration.set("mobSettings.mobTame.parrot", Boolean.valueOf(false));
      Bukkit.getLogger().info("Hide or Hunt > Setting the PARROT Tamable.");
    } 
    if (!yamlConfiguration.isSet("mobSettings.mobTame.skeletonhorse")) {
      yamlConfiguration.set("mobSettings.mobTame.skeletonhorse", Boolean.valueOf(false));
      Bukkit.getLogger().info("Hide or Hunt > Setting the SKELETON HORSE Tamable.");
    } 
    if (!yamlConfiguration.isSet("mobSettings.mobTame.wolf")) {
      yamlConfiguration.set("mobSettings.mobTame.wolf", Boolean.valueOf(false));
      Bukkit.getLogger().info("Hide or Hunt > Setting the WOLF Tamable.");
    } 
    try {
      yamlConfiguration.save(configFile);
      Bukkit.getLogger().info("Hide or Hunt > Saving Configuration Files [MobTamingSettings].");
    } catch (IOException e) {
      e.printStackTrace();
    } 
  }
  
  public static void loadServerSettings(HideOrHunt plugin, String fileName) {
    File configFile = new File("plugins/PrivateHideOrHunt/", String.valueOf(fileName) + ".yml");
    YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(configFile);
    Bukkit.getLogger().info("Hide or Hunt > Loading Configuration Files [ServerSettings].");
    if (!yamlConfiguration.isSet("serverSettings.weather")) {
      yamlConfiguration.set("serverSettings.weather", "rotate");
      Bukkit.getLogger().info("Hide or Hunt > Setting the Default Weather");
    } 
    if (!yamlConfiguration.isSet("serverSettings.entryMessages")) {
      yamlConfiguration.set("serverSettings.entryMessages", "off");
      Bukkit.getLogger().info("Hide or Hunt > Setting the Default Entry Message Ability");
    } 
    if (!yamlConfiguration.isSet("serverSettings.deathMessages")) {
      yamlConfiguration.set("serverSettings.deathMessages", "off");
      Bukkit.getLogger().info("Hide or Hunt > Setting the Default Death Message Ability");
    } 
    try {
      yamlConfiguration.save(configFile);
      Bukkit.getLogger().info("Hide or Hunt > Saving Configuration Files [ServerSettings].");
    } catch (IOException e) {
      e.printStackTrace();
    } 
  }
  
  public static void loadGameSettings(HideOrHunt plugin, String fileName) {
    File configFile = new File("plugins/PrivateHideOrHunt/", String.valueOf(fileName) + ".yml");
    YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(configFile);
    Bukkit.getLogger().info("Hide or Hunt > Loading Configuration Files [GameSettings].");
    if (!yamlConfiguration.isSet("gameSettings.slowfall")) {
      yamlConfiguration.set("gameSettings.slowfall.enabled", Boolean.valueOf(true));
      yamlConfiguration.set("gameSettings.slowfall.duration", Integer.valueOf(60));
      yamlConfiguration.set("gameSettings.slowfall.removeOnContact", Boolean.valueOf(true));
      Bukkit.getLogger().info("Hide or Hunt > Setting the Default Slowfall Settings");
    } 
    if (!yamlConfiguration.isSet("gameSettings.invis")) {
      yamlConfiguration.set("gameSettings.invis.enabled", Boolean.valueOf(true));
      yamlConfiguration.set("gameSettings.invis.given", "potion");
      yamlConfiguration.set("gameSettings.invis.timeBeforeGive", Integer.valueOf(60));
      yamlConfiguration.set("gameSettings.invis.duration", Integer.valueOf(600));
      yamlConfiguration.set("gameSettings.invis.removeOnDamageDone", Boolean.valueOf(true));
      Bukkit.getLogger().info("Hide or Hunt > Setting the Default Invisibility Settings");
    } 
    if (!yamlConfiguration.isSet("gameSettings.beacon")) {
      yamlConfiguration.set("gameSettings.beacon.crafting", Boolean.valueOf(true));
      yamlConfiguration.set("gameSettings.beacon.respawn", Boolean.valueOf(true));
      yamlConfiguration.set("gameSettings.beacon.leaderOnlyPlace", Boolean.valueOf(true));
      Bukkit.getLogger().info("Hide or Hunt > Setting the Default Beacon Settings");
    } 
    if (!yamlConfiguration.isSet("gameSettings.craftingTablesEnabled")) {
      yamlConfiguration.set("gameSettings.craftingTablesEnabled", Boolean.valueOf(false));
      Bukkit.getLogger().info("Hide or Hunt > Setting the Default Crafting Table Settings");
    } 
    if (!yamlConfiguration.isSet("gameSettings.active")) {
      yamlConfiguration.set("gameSettings.active", Boolean.valueOf(false));
      Bukkit.getLogger().info("Hide or Hunt > Setting the Default Active Settings");
    } 
    if (!yamlConfiguration.isSet("gameSettings.peaceful")) {
      yamlConfiguration.set("gameSettings.peaceful.enabled", Boolean.valueOf(false));
      yamlConfiguration.set("gameSettings.peaceful.delayToRemove", Integer.valueOf(60));
      Bukkit.getLogger().info("Hide or Hunt > Setting the Default Peace Settings");
    } 
    if (!yamlConfiguration.isSet("gameSettings.death")) {
      yamlConfiguration.set("gameSettings.death.keepXPOnDeath", Boolean.valueOf(true));
      yamlConfiguration.set("gameSettings.death.chanceXPSave", Double.valueOf(0.25D));
      yamlConfiguration.set("gameSettings.death.keepItemsOnDeath", Boolean.valueOf(true));
      yamlConfiguration.set("gameSettings.death.chanceItemSave", Double.valueOf(0.25D));
      yamlConfiguration.set("gameSettings.death.autokick", Boolean.valueOf(true));
      yamlConfiguration.set("gameSettings.death.autounwhitelist", Boolean.valueOf(true));
      Bukkit.getLogger().info("Hide or Hunt > Setting the Default Death Settings");
    } 
    if (!yamlConfiguration.isSet("gameSettings.bossbar")) {
      yamlConfiguration.set("gameSettings.bossbar.enabled", Boolean.valueOf(true));
      yamlConfiguration.set("gameSettings.bossbar.isShowing", Boolean.valueOf(false));
      yamlConfiguration.set("gameSettings.bossbar.allowDisableForAll", Boolean.valueOf(false));
      Bukkit.getLogger().info("Hide or Hunt > Setting the Default Boss Bar Settings");
    } 
    try {
      yamlConfiguration.save(configFile);
      Bukkit.getLogger().info("Hide or Hunt > Saving Configuration Files [GameSettings].");
    } catch (IOException e) {
      e.printStackTrace();
    } 
  }
  
  public static void updateBossBar(HideOrHunt plugin, boolean isShowing) {
    File configFile = new File("plugins/PrivateHideOrHunt/", "gamesettings.yml");
    YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(configFile);
    loadTeamSettings(plugin, "gamesettings");
    yamlConfiguration.set("gameSettings.bossbar.isShowing", Boolean.valueOf(isShowing));
    Bukkit.getLogger().info("Hide or Hunt > Updating the Boss Bar to.");
    try {
      yamlConfiguration.save(configFile);
      Bukkit.getLogger().info("Hide or Hunt > Saving Configuration Files [GameSettings].");
    } catch (IOException e) {
      e.printStackTrace();
    } 
  }
  
  public static void loadExplodeBlacklist(HideOrHunt plugin, String fileName) {
    File configFile = new File("plugins/PrivateHideOrHunt/", String.valueOf(fileName) + ".yml");
    YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(configFile);
    Bukkit.getLogger().info("Hide or Hunt > Loading Configuration Files [ExplodeSettings].");
    if (!yamlConfiguration.isSet("blacklist")) {
      yamlConfiguration.set("blacklist", "");
      Bukkit.getLogger().info("Hide or Hunt > Setting the Default Explosion Blacklist");
    } 
    try {
      yamlConfiguration.save(configFile);
      Bukkit.getLogger().info("Hide or Hunt > Saving Configuration Files [ExplodeSettings].");
    } catch (IOException e) {
      e.printStackTrace();
    } 
  }
  
  public static void loadGameKits(HideOrHunt plugin, String fileName) {
    File configFile = new File("plugins/PrivateHideOrHunt/", String.valueOf(fileName) + ".yml");
    YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(configFile);
    Bukkit.getLogger().info("Hide or Hunt > Loading Configuration Files [Kits].");
    if (!yamlConfiguration.isSet("startinghoh")) {
      yamlConfiguration.set("startinghoh", "");
      Bukkit.getLogger().info("Hide or Hunt > Setting the Hide or Hunt Starting Kit");
    } 
    if (!yamlConfiguration.isSet("deathhoh")) {
      yamlConfiguration.set("deathhoh", "");
      Bukkit.getLogger().info("Hide or Hunt > Setting the Hide or Hunt Death Kit");
    } 
    try {
      yamlConfiguration.save(configFile);
      Bukkit.getLogger().info("Hide or Hunt > Saving Configuration Files [Kits].");
    } catch (IOException e) {
      e.printStackTrace();
    } 
  }
  
  public static void loadBeacons(HideOrHunt plugin, String fileName) {
    File configFile = new File("plugins/PrivateHideOrHunt/", String.valueOf(fileName) + ".yml");
    YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(configFile);
    Bukkit.getLogger().info("Hide or Hunt > Loading Configuration Files [Beacons].");
    if (!yamlConfiguration.isSet("beacons")) {
      yamlConfiguration.set("beacons", "");
      Bukkit.getLogger().info("Hide or Hunt > Setting the Beacons");
    } 
    try {
      yamlConfiguration.save(configFile);
      Bukkit.getLogger().info("Hide or Hunt > Saving Configuration Files [Beacons].");
    } catch (IOException e) {
      e.printStackTrace();
    } 
  }
  
  public static void loadCustomGamemodes(HideOrHunt plugin, String fileName) {
    File configFile = new File("plugins/PrivateHideOrHunt/", String.valueOf(fileName) + ".yml");
    YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(configFile);
    Bukkit.getLogger().info("Hide or Hunt > Loading Configuration Files [CustomGamemodes].");
    if (!yamlConfiguration.isSet("timerdeathswap")) {
      yamlConfiguration.set("timerdeathswap.enabled", Boolean.valueOf(false));
      yamlConfiguration.set("timerdeathswap.swapDuringPeace", Boolean.valueOf(false));
      yamlConfiguration.set("timerdeathswap.notifyOfSwapper", Boolean.valueOf(false));
      yamlConfiguration.set("timerdeathswap.fixedSwapTime", Boolean.valueOf(false));
      yamlConfiguration.set("timerdeathswap.timeBetweenSwaps", Integer.valueOf(60));
      Bukkit.getLogger().info("Hide or Hunt > Setting the Death Swap");
    } 
    try {
      yamlConfiguration.save(configFile);
      Bukkit.getLogger().info("Hide or Hunt > Saving Configuration Files [CustomGamemodes].");
    } catch (IOException e) {
      e.printStackTrace();
    } 
  }
  
  public static boolean getCustomGamemodesStatus(HideOrHunt plugin, String gamemodeName) {
    File configFile = new File("plugins/PrivateHideOrHunt/", "custom.yml");
    YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(configFile);
    return yamlConfiguration.getBoolean(String.valueOf(gamemodeName) + ".enabled");
  }
  
  public static int getTaskID(HideOrHunt plugin, String gamemodeName, String task) {
    File configFile = new File("plugins/PrivateHideOrHunt/", "data.yml");
    YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(configFile);
    return yamlConfiguration.getInt(String.valueOf(gamemodeName) + "." + task);
  }
  
  public static void setTaskID(HideOrHunt plugin, String gamemodeName, String task, int taskID) {
    File configFile = new File("plugins/PrivateHideOrHunt/", "data.yml");
    YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(configFile);
    if (gamemodeName.equals("timerdeathswap"))
      yamlConfiguration.set("timerdeathswap." + task, Integer.valueOf(taskID)); 
    try {
      yamlConfiguration.save(configFile);
      Bukkit.getLogger().info("Hide or Hunt > Saving Configuration Files [CustomGamemodesData].");
    } catch (IOException e) {
      e.printStackTrace();
    } 
  }
}
