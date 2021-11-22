package com.HideOrHunt.menus;

import com.HideOrHunt.HideOrHunt;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

public class MobSettingsMenu implements Listener, InventoryHolder {
  public Inventory getInventory() {
    return null;
  }
  
  @EventHandler
  public void InventoryClick(InventoryClickEvent event) {
    Player player = (Player)event.getWhoClicked();
    UUID playerUUID = player.getUniqueId();
    ClickType click = event.getClick();
    ItemStack item = event.getCurrentItem();
    InventoryView menu = event.getView();
    int slotClicked = event.getSlot();
    File configFile = new File("plugins/PrivateHideOrHunt/", "gamesettings.yml");
    YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(configFile);
    if (menu.getTitle().equals("Mob Settings")) {
      event.setCancelled(true);
      if (menu.getItem(slotClicked) == null)
        return; 
      if (item.getItemMeta().getDisplayName().equals("§cMob Spawn Settings")) {
        player.openInventory(inventoryMobSpawnPage1((FileConfiguration)yamlConfiguration));
      } else if (item.getItemMeta().getDisplayName().equals("§cMob Breeding Settings")) {
        player.openInventory(inventoryMobBreed((FileConfiguration)yamlConfiguration));
      } else if (item.getItemMeta().getDisplayName().equals("§cMob Taming Settings")) {
        player.openInventory(inventoryMobTame((FileConfiguration)yamlConfiguration));
      } 
    } 
  }
  
  public static Inventory inventoryMobSpawnPage1(FileConfiguration config) {
    Inventory mobSpawnMenu = Bukkit.getServer().createInventory(null, 54, "Mob Spawns #1");
    mobSpawnMenu.setItem(0, mobEggSpawn(Material.BAT_SPAWN_EGG, "Bat", "bat", config));
    mobSpawnMenu.setItem(1, mobEggSpawn(Material.BEE_SPAWN_EGG, "Bee", "bee", config));
    mobSpawnMenu.setItem(2, mobEggSpawn(Material.BLAZE_SPAWN_EGG, "Blaze", "blaze", config));
    mobSpawnMenu.setItem(3, mobEggSpawn(Material.CAT_SPAWN_EGG, "Cat", "cat", config));
    mobSpawnMenu.setItem(4, mobEggSpawn(Material.CAVE_SPIDER_SPAWN_EGG, "Cave Spider", "cavespider", config));
    mobSpawnMenu.setItem(5, mobEggSpawn(Material.CHICKEN_SPAWN_EGG, "Chicken", "chicken", config));
    mobSpawnMenu.setItem(6, mobEggSpawn(Material.COD_SPAWN_EGG, "Cod", "cod", config));
    mobSpawnMenu.setItem(7, mobEggSpawn(Material.CREEPER_SPAWN_EGG, "Creeper", "creeper.normal", config));
    mobSpawnMenu.setItem(8, mobEggSpawn(Material.CREEPER_SPAWN_EGG, "Charged Creeper", "creeper.charged", config));
    mobSpawnMenu.setItem(9, mobEggSpawn(Material.DOLPHIN_SPAWN_EGG, "Dolphin", "dolphin", config));
    mobSpawnMenu.setItem(10, mobEggSpawn(Material.DONKEY_SPAWN_EGG, "Donkey", "donkey", config));
    mobSpawnMenu.setItem(11, mobEggSpawn(Material.DROWNED_SPAWN_EGG, "Drowned", "drowned", config));
    mobSpawnMenu.setItem(12, mobEggSpawn(Material.GUARDIAN_SPAWN_EGG, "Elder Guardian", "elderguardian", config));
    mobSpawnMenu.setItem(13, mobEggSpawn(Material.DRAGON_HEAD, "Ender Dragon", "enderdragon", config));
    mobSpawnMenu.setItem(14, mobEggSpawn(Material.ENDERMAN_SPAWN_EGG, "Enderman", "enderman", config));
    mobSpawnMenu.setItem(15, mobEggSpawn(Material.ENDERMITE_SPAWN_EGG, "Endermite", "endermite", config));
    mobSpawnMenu.setItem(16, mobEggSpawn(Material.EVOKER_SPAWN_EGG, "Evoker", "evoker", config));
    mobSpawnMenu.setItem(17, mobEggSpawn(Material.FOX_SPAWN_EGG, "Fox", "fox", config));
    mobSpawnMenu.setItem(18, mobEggSpawn(Material.GHAST_SPAWN_EGG, "Ghast", "ghast", config));
    mobSpawnMenu.setItem(19, mobEggSpawn(Material.ZOMBIE_SPAWN_EGG, "Giant", "giant", config));
    mobSpawnMenu.setItem(20, mobEggSpawn(Material.GUARDIAN_SPAWN_EGG, "Guardian", "guardian", config));
    mobSpawnMenu.setItem(21, mobEggSpawn(Material.HOGLIN_SPAWN_EGG, "Hoglin", "hoglin", config));
    mobSpawnMenu.setItem(22, mobEggSpawn(Material.HORSE_SPAWN_EGG, "Horse", "horse", config));
    mobSpawnMenu.setItem(23, mobEggSpawn(Material.HUSK_SPAWN_EGG, "Husk", "husk", config));
    mobSpawnMenu.setItem(24, mobEggSpawn(Material.POTION, "Illusioner", "illusioner", config));
    mobSpawnMenu.setItem(25, mobEggSpawn(Material.IRON_INGOT, "Iron Golem", "irongolem", config));
    mobSpawnMenu.setItem(26, mobEggSpawn(Material.LLAMA_SPAWN_EGG, "Llama", "llama", config));
    mobSpawnMenu.setItem(27, mobEggSpawn(Material.MAGMA_CUBE_SPAWN_EGG, "Magma Cube [Small]", "magmacube.small", config));
    mobSpawnMenu.setItem(28, mobEggSpawn(Material.MAGMA_CUBE_SPAWN_EGG, "Magma Cube [Medium]", "magmacube.mid", config));
    mobSpawnMenu.setItem(29, mobEggSpawn(Material.MAGMA_CUBE_SPAWN_EGG, "Magma Cube [Large]", "magmacube.large", config));
    mobSpawnMenu.setItem(30, mobEggSpawn(Material.MULE_SPAWN_EGG, "Mule", "mule", config));
    mobSpawnMenu.setItem(31, mobEggSpawn(Material.MOOSHROOM_SPAWN_EGG, "Mooshroom", "mushroomcow", config));
    mobSpawnMenu.setItem(32, mobEggSpawn(Material.OCELOT_SPAWN_EGG, "Ocelot", "ocelot", config));
    mobSpawnMenu.setItem(33, mobEggSpawn(Material.PANDA_SPAWN_EGG, "Panda", "panda", config));
    mobSpawnMenu.setItem(34, mobEggSpawn(Material.PARROT_SPAWN_EGG, "Parrot", "parrot", config));
    mobSpawnMenu.setItem(35, mobEggSpawn(Material.PHANTOM_SPAWN_EGG, "Phantom", "phantom", config));
    mobSpawnMenu.setItem(36, mobEggSpawn(Material.PIG_SPAWN_EGG, "Pig", "pig", config));
    mobSpawnMenu.setItem(37, mobEggSpawn(Material.PIGLIN_SPAWN_EGG, "Piglin", "piglin", config));
    mobSpawnMenu.setItem(38, mobEggSpawn(Material.PILLAGER_SPAWN_EGG, "Pillager", "pillager", config));
    mobSpawnMenu.setItem(39, mobEggSpawn(Material.POLAR_BEAR_SPAWN_EGG, "Polar Bear", "polarbear", config));
    mobSpawnMenu.setItem(40, mobEggSpawn(Material.PUFFERFISH_SPAWN_EGG, "Pufferfish", "pufferfish", config));
    mobSpawnMenu.setItem(41, mobEggSpawn(Material.RABBIT_SPAWN_EGG, "Rabbit", "rabbit", config));
    mobSpawnMenu.setItem(42, mobEggSpawn(Material.RAVAGER_SPAWN_EGG, "Ravager", "ravager", config));
    mobSpawnMenu.setItem(43, mobEggSpawn(Material.SALMON_SPAWN_EGG, "Salmon", "salmon", config));
    mobSpawnMenu.setItem(44, mobEggSpawn(Material.SHEEP_SPAWN_EGG, "Sheep", "sheep", config));
    ItemStack lastPage = new ItemStack(Material.RED_STAINED_GLASS_PANE, 1);
    ItemMeta lastPageMeta = lastPage.getItemMeta();
    lastPageMeta.setDisplayName("§cLast Page §8[§7Click§8]");
    lastPage.setItemMeta(lastPageMeta);
    mobSpawnMenu.setItem(48, lastPage);
    ItemStack nextPage = new ItemStack(Material.LIME_STAINED_GLASS_PANE, 1);
    ItemMeta nextPageMeta = nextPage.getItemMeta();
    nextPageMeta.setDisplayName("§aNext Page §8[§7Click§8]");
    nextPage.setItemMeta(nextPageMeta);
    mobSpawnMenu.setItem(50, nextPage);
    return mobSpawnMenu;
  }
  
  public static Inventory inventoryMobSpawnPage2(FileConfiguration config) {
    Inventory mobSpawnMenu = Bukkit.getServer().createInventory(null, 54, "Mob Spawns #2");
    mobSpawnMenu.setItem(0, mobEggSpawn(Material.SHULKER_SPAWN_EGG, "Shulker", "shulker", config));
    mobSpawnMenu.setItem(1, mobEggSpawn(Material.SILVERFISH_SPAWN_EGG, "Silverfish", "silverfish", config));
    mobSpawnMenu.setItem(2, mobEggSpawn(Material.SKELETON_SPAWN_EGG, "Skeleton", "skeleton", config));
    mobSpawnMenu.setItem(3, mobEggSpawn(Material.SKELETON_HORSE_SPAWN_EGG, "Skeleton Horse", "skeletonhorse", config));
    mobSpawnMenu.setItem(4, mobEggSpawn(Material.SLIME_SPAWN_EGG, "Slime [Small]", "slime.small", config));
    mobSpawnMenu.setItem(5, mobEggSpawn(Material.SLIME_SPAWN_EGG, "Slime [Medium]", "slime.mid", config));
    mobSpawnMenu.setItem(6, mobEggSpawn(Material.SLIME_SPAWN_EGG, "Slime [Large]", "slime.large", config));
    mobSpawnMenu.setItem(7, mobEggSpawn(Material.SNOWBALL, "Snowman", "snowman", config));
    mobSpawnMenu.setItem(8, mobEggSpawn(Material.SPIDER_SPAWN_EGG, "Spider", "spider", config));
    mobSpawnMenu.setItem(9, mobEggSpawn(Material.SQUID_SPAWN_EGG, "Squid", "squid", config));
    mobSpawnMenu.setItem(10, mobEggSpawn(Material.STRAY_SPAWN_EGG, "Stray", "stray", config));
    mobSpawnMenu.setItem(11, mobEggSpawn(Material.STRIDER_SPAWN_EGG, "Strider", "strider", config));
    mobSpawnMenu.setItem(12, mobEggSpawn(Material.WANDERING_TRADER_SPAWN_EGG, "Wandering Trader [Llama]", "traderllama", config));
    mobSpawnMenu.setItem(13, mobEggSpawn(Material.TROPICAL_FISH_SPAWN_EGG, "Tropical Fish", "tropicalfish", config));
    mobSpawnMenu.setItem(14, mobEggSpawn(Material.TURTLE_SPAWN_EGG, "Turtle", "turtle", config));
    mobSpawnMenu.setItem(15, mobEggSpawn(Material.VEX_SPAWN_EGG, "Vex", "vex", config));
    mobSpawnMenu.setItem(16, mobEggSpawn(Material.VILLAGER_SPAWN_EGG, "Villager", "villager", config));
    mobSpawnMenu.setItem(17, mobEggSpawn(Material.VINDICATOR_SPAWN_EGG, "Vindicator", "vindicator", config));
    mobSpawnMenu.setItem(18, mobEggSpawn(Material.WANDERING_TRADER_SPAWN_EGG, "Wandering Trader [Villager]", "wanderingtrader", config));
    mobSpawnMenu.setItem(19, mobEggSpawn(Material.WITCH_SPAWN_EGG, "Witch", "witch", config));
    mobSpawnMenu.setItem(20, mobEggSpawn(Material.WITHER_SKELETON_SKULL, "Wither", "wither", config));
    mobSpawnMenu.setItem(21, mobEggSpawn(Material.WITHER_SKELETON_SPAWN_EGG, "Wither Skeleton", "witherskeleton", config));
    mobSpawnMenu.setItem(22, mobEggSpawn(Material.WOLF_SPAWN_EGG, "Wolf", "wolf", config));
    mobSpawnMenu.setItem(23, mobEggSpawn(Material.ZOGLIN_SPAWN_EGG, "Zoglin", "zoglin", config));
    mobSpawnMenu.setItem(24, mobEggSpawn(Material.ZOMBIE_SPAWN_EGG, "Zombie", "zombie", config));
    mobSpawnMenu.setItem(25, mobEggSpawn(Material.ZOMBIE_HORSE_SPAWN_EGG, "Zombie Horse", "zombiehorse", config));
    mobSpawnMenu.setItem(26, mobEggSpawn(Material.ZOMBIE_VILLAGER_SPAWN_EGG, "Zombie Villager", "zombievillager", config));
    mobSpawnMenu.setItem(27, mobEggSpawn(Material.ZOMBIFIED_PIGLIN_SPAWN_EGG, "Zombified Piglin", "zombifiedpiglin", config));
    ItemStack lastPage = new ItemStack(Material.RED_STAINED_GLASS_PANE, 1);
    ItemMeta lastPageMeta = lastPage.getItemMeta();
    lastPageMeta.setDisplayName("§cLast Page §8[§7Click§8]");
    lastPage.setItemMeta(lastPageMeta);
    mobSpawnMenu.setItem(48, lastPage);
    ItemStack nextPage = new ItemStack(Material.LIME_STAINED_GLASS_PANE, 1);
    ItemMeta nextPageMeta = nextPage.getItemMeta();
    nextPageMeta.setDisplayName("§aNext Page §8[§7Click§8]");
    nextPage.setItemMeta(nextPageMeta);
    mobSpawnMenu.setItem(50, nextPage);
    return mobSpawnMenu;
  }
  
  public static Inventory inventoryMobBreed(FileConfiguration config) {
    Inventory mobBreedMenu = Bukkit.getServer().createInventory(null, 54, "Mob Breeds");
    mobBreedMenu.setItem(0, mobEggBreed(Material.BEE_SPAWN_EGG, "Bee", "bee", config));
    mobBreedMenu.setItem(1, mobEggBreed(Material.CAT_SPAWN_EGG, "Cat", "cat", config));
    mobBreedMenu.setItem(2, mobEggBreed(Material.CHICKEN_SPAWN_EGG, "Chicken", "chicken", config));
    mobBreedMenu.setItem(3, mobEggBreed(Material.DONKEY_SPAWN_EGG, "Donkey", "donkey", config));
    mobBreedMenu.setItem(4, mobEggBreed(Material.FOX_SPAWN_EGG, "Fox", "fox", config));
    mobBreedMenu.setItem(5, mobEggBreed(Material.HOGLIN_SPAWN_EGG, "Hoglin", "hoglin", config));
    mobBreedMenu.setItem(6, mobEggBreed(Material.HORSE_SPAWN_EGG, "Horse", "horse", config));
    mobBreedMenu.setItem(7, mobEggBreed(Material.LLAMA_SPAWN_EGG, "Llama", "llama", config));
    mobBreedMenu.setItem(8, mobEggBreed(Material.MULE_SPAWN_EGG, "Mule", "mule", config));
    mobBreedMenu.setItem(9, mobEggBreed(Material.MOOSHROOM_SPAWN_EGG, "Mooshroom", "mushroomcow", config));
    mobBreedMenu.setItem(10, mobEggBreed(Material.OCELOT_SPAWN_EGG, "Ocelot", "ocelot", config));
    mobBreedMenu.setItem(11, mobEggBreed(Material.PANDA_SPAWN_EGG, "Panda", "panda", config));
    mobBreedMenu.setItem(12, mobEggBreed(Material.PIG_SPAWN_EGG, "Pig", "pig", config));
    mobBreedMenu.setItem(13, mobEggBreed(Material.RABBIT_SPAWN_EGG, "Rabbit", "rabbit", config));
    mobBreedMenu.setItem(14, mobEggBreed(Material.SHEEP_SPAWN_EGG, "Sheep", "sheep", config));
    mobBreedMenu.setItem(15, mobEggBreed(Material.STRIDER_SPAWN_EGG, "Strider", "strider", config));
    mobBreedMenu.setItem(16, mobEggBreed(Material.TURTLE_SPAWN_EGG, "Turtle", "turtle", config));
    mobBreedMenu.setItem(17, mobEggBreed(Material.WOLF_SPAWN_EGG, "Wolf", "wolf", config));
    return mobBreedMenu;
  }
  
  public static Inventory inventoryMobTame(FileConfiguration config) {
    Inventory mobTameMenu = Bukkit.getServer().createInventory(null, 54, "Mob Taming");
    mobTameMenu.setItem(0, mobEggTame(Material.CAT_SPAWN_EGG, "Cat", "cat", config));
    mobTameMenu.setItem(1, mobEggTame(Material.DONKEY_SPAWN_EGG, "Donkey", "donkey", config));
    mobTameMenu.setItem(2, mobEggTame(Material.HORSE_SPAWN_EGG, "Horse", "horse", config));
    mobTameMenu.setItem(3, mobEggTame(Material.LLAMA_SPAWN_EGG, "Llama", "llama", config));
    mobTameMenu.setItem(4, mobEggTame(Material.MULE_SPAWN_EGG, "Mule", "mule", config));
    mobTameMenu.setItem(5, mobEggTame(Material.OCELOT_SPAWN_EGG, "Ocelot", "ocelot", config));
    mobTameMenu.setItem(6, mobEggTame(Material.PARROT_SPAWN_EGG, "Parrot", "parrot", config));
    mobTameMenu.setItem(7, mobEggTame(Material.SKELETON_HORSE_SPAWN_EGG, "Skeleton Horse", "skeletonhorse", config));
    mobTameMenu.setItem(8, mobEggTame(Material.WOLF_SPAWN_EGG, "Wolf", "wolf", config));
    return mobTameMenu;
  }
  
  public static ItemStack mobEggSpawn(Material egg, String mobType, String configName, FileConfiguration config) {
    ItemStack spawnEgg = new ItemStack(egg, 1);
    ItemMeta spawnEggMeta = spawnEgg.getItemMeta();
    List<String> spawnEggLore = new ArrayList<>();
    spawnEggLore.add("§f" + mobType + " Spawn Information:");
    if (config.getBoolean("mobSettings.mobSpawn." + configName)) {
      spawnEggMeta.setDisplayName("§a" + mobType);
      spawnEggLore.add("§f - §7Spawning: §a§nAllowed");
      spawnEggMeta.addEnchant(Enchantment.ARROW_DAMAGE, 1, false);
      spawnEggMeta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
    } else {
      spawnEggMeta.setDisplayName("§c" + mobType);
      spawnEggLore.add("§f - §7Spawning: §c§nDenied");
    } 
    spawnEggLore.add("§7");
    spawnEggLore.add("§f§l<!>§r §fClick to §nToggle§r §fthe §nSpawning§r §fof §n" + mobType);
    spawnEggMeta.setLore(spawnEggLore);
    spawnEggMeta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_POTION_EFFECTS });
    spawnEggMeta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });
    NamespacedKey mobspawnTypeConfig = new NamespacedKey((Plugin)HideOrHunt.getInstance(), "mobspawntypeconfig");
    spawnEggMeta.getPersistentDataContainer().set(mobspawnTypeConfig, PersistentDataType.STRING, configName);
    NamespacedKey mobspawnType = new NamespacedKey((Plugin)HideOrHunt.getInstance(), "mobspawntype");
    spawnEggMeta.getPersistentDataContainer().set(mobspawnType, PersistentDataType.STRING, mobType);
    spawnEgg.setItemMeta(spawnEggMeta);
    return spawnEgg;
  }
  
  public static ItemStack mobEggBreed(Material egg, String mobType, String configName, FileConfiguration config) {
    ItemStack breedEgg = new ItemStack(egg, 1);
    ItemMeta breedEggMeta = breedEgg.getItemMeta();
    List<String> breedEggLore = new ArrayList<>();
    breedEggLore.add("§f" + mobType + " Breed Information:");
    if (config.getBoolean("mobSettings.mobBreed." + configName)) {
      breedEggMeta.setDisplayName("§a" + mobType);
      breedEggLore.add("§f - §7Breeding: §a§nAllowed");
      breedEggMeta.addEnchant(Enchantment.ARROW_DAMAGE, 1, false);
      breedEggMeta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
    } else {
      breedEggMeta.setDisplayName("§c" + mobType);
      breedEggLore.add("§f - §7Breeding: §c§nDenied");
    } 
    breedEggLore.add("§7");
    breedEggLore.add("§f§l<!>§r §fClick to §nToggle§r §fthe §nBreeding§r §fof §n" + mobType);
    breedEggMeta.setLore(breedEggLore);
    breedEggMeta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_POTION_EFFECTS });
    breedEggMeta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });
    NamespacedKey mobbreedTypeConfig = new NamespacedKey((Plugin)HideOrHunt.getInstance(), "mobbreedtypeconfig");
    breedEggMeta.getPersistentDataContainer().set(mobbreedTypeConfig, PersistentDataType.STRING, configName);
    NamespacedKey mobbreedType = new NamespacedKey((Plugin)HideOrHunt.getInstance(), "mobbreedtype");
    breedEggMeta.getPersistentDataContainer().set(mobbreedType, PersistentDataType.STRING, mobType);
    breedEgg.setItemMeta(breedEggMeta);
    return breedEgg;
  }
  
  public static ItemStack mobEggTame(Material egg, String mobType, String configName, FileConfiguration config) {
    ItemStack tameEgg = new ItemStack(egg, 1);
    ItemMeta tameEggMeta = tameEgg.getItemMeta();
    List<String> tameEggLore = new ArrayList<>();
    tameEggLore.add("§f" + mobType + " Taming Information:");
    if (config.getBoolean("mobSettings.mobTame." + configName)) {
      tameEggMeta.setDisplayName("§a" + mobType);
      tameEggLore.add("§f - §7Taming: §a§nAllowed");
      tameEggMeta.addEnchant(Enchantment.ARROW_DAMAGE, 1, false);
      tameEggMeta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
    } else {
      tameEggMeta.setDisplayName("§c" + mobType);
      tameEggLore.add("§f - §7Taming: §c§nDenied");
    } 
    tameEggLore.add("§7");
    tameEggLore.add("§f§l<!>§r §fClick to §nToggle§r §fthe §nTaming§r §fof §n" + mobType);
    tameEggMeta.setLore(tameEggLore);
    tameEggMeta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_POTION_EFFECTS });
    tameEggMeta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });
    NamespacedKey mobtamingTypeConfig = new NamespacedKey((Plugin)HideOrHunt.getInstance(), "mobtametypeconfig");
    tameEggMeta.getPersistentDataContainer().set(mobtamingTypeConfig, PersistentDataType.STRING, configName);
    NamespacedKey mobtamingType = new NamespacedKey((Plugin)HideOrHunt.getInstance(), "mobtametype");
    tameEggMeta.getPersistentDataContainer().set(mobtamingType, PersistentDataType.STRING, mobType);
    tameEgg.setItemMeta(tameEggMeta);
    return tameEgg;
  }
}
