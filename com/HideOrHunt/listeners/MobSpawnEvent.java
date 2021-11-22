package com.HideOrHunt.listeners;

import java.io.File;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.MagmaCube;
import org.bukkit.entity.Slime;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

public class MobSpawnEvent implements Listener {
  @EventHandler
  public void onMobSpawn(CreatureSpawnEvent event) {
    LivingEntity livingEntity = event.getEntity();
    File settingsFile = new File("plugins/PrivateHideOrHunt/", "gamesettings.yml");
    YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(settingsFile);
    if (livingEntity instanceof org.bukkit.entity.Bat) {
      if (!yamlConfiguration.getBoolean("mobSettings.mobSpawn.bat"))
        event.setCancelled(true); 
    } else if (livingEntity instanceof org.bukkit.entity.Bee) {
      if (!yamlConfiguration.getBoolean("mobSettings.mobSpawn.bee"))
        event.setCancelled(true); 
    } else if (livingEntity instanceof org.bukkit.entity.Blaze) {
      if (!yamlConfiguration.getBoolean("mobSettings.mobSpawn.blaze"))
        event.setCancelled(true); 
    } else if (livingEntity instanceof org.bukkit.entity.Cat) {
      if (!yamlConfiguration.getBoolean("mobSettings.mobSpawn.cat"))
        event.setCancelled(true); 
    } else if (livingEntity instanceof org.bukkit.entity.CaveSpider) {
      if (!yamlConfiguration.getBoolean("mobSettings.mobSpawn.cavespider"))
        event.setCancelled(true); 
    } else if (livingEntity instanceof org.bukkit.entity.Chicken) {
      if (!yamlConfiguration.getBoolean("mobSettings.mobSpawn.chicken"))
        event.setCancelled(true); 
    } else if (livingEntity instanceof org.bukkit.entity.Cod) {
      if (!yamlConfiguration.getBoolean("mobSettings.mobSpawn.cod"))
        event.setCancelled(true); 
    } else if (livingEntity instanceof Creeper) {
      Creeper creeper = (Creeper)livingEntity;
      if (creeper.isPowered()) {
        if (!yamlConfiguration.getBoolean("mobSettings.mobSpawn.creeper.charged"))
          event.setCancelled(true); 
      } else if (!yamlConfiguration.getBoolean("mobSettings.mobSpawn.creeper.normal")) {
        event.setCancelled(true);
      } 
    } else if (livingEntity instanceof org.bukkit.entity.Dolphin) {
      if (!yamlConfiguration.getBoolean("mobSettings.mobSpawn.dolphin"))
        event.setCancelled(true); 
    } else if (livingEntity instanceof org.bukkit.entity.Donkey) {
      if (!yamlConfiguration.getBoolean("mobSettings.mobSpawn.donkey"))
        event.setCancelled(true); 
    } else if (livingEntity instanceof org.bukkit.entity.Drowned) {
      if (!yamlConfiguration.getBoolean("mobSettings.mobSpawn.drowned"))
        event.setCancelled(true); 
    } else if (livingEntity instanceof org.bukkit.entity.ElderGuardian) {
      if (!yamlConfiguration.getBoolean("mobSettings.mobSpawn.elderguardian"))
        event.setCancelled(true); 
    } else if (livingEntity instanceof org.bukkit.entity.EnderDragon) {
      if (!yamlConfiguration.getBoolean("mobSettings.mobSpawn.enderdragon"))
        event.setCancelled(true); 
    } else if (livingEntity instanceof org.bukkit.entity.Enderman) {
      if (!yamlConfiguration.getBoolean("mobSettings.mobSpawn.enderman"))
        event.setCancelled(true); 
    } else if (livingEntity instanceof org.bukkit.entity.Endermite) {
      if (!yamlConfiguration.getBoolean("mobSettings.mobSpawn.endermite"))
        event.setCancelled(true); 
    } else if (livingEntity instanceof org.bukkit.entity.Evoker) {
      if (!yamlConfiguration.getBoolean("mobSettings.mobSpawn.evoker"))
        event.setCancelled(true); 
    } else if (livingEntity instanceof org.bukkit.entity.Fox) {
      if (!yamlConfiguration.getBoolean("mobSettings.mobSpawn.fox"))
        event.setCancelled(true); 
    } else if (livingEntity instanceof org.bukkit.entity.Ghast) {
      if (!yamlConfiguration.getBoolean("mobSettings.mobSpawn.ghast"))
        event.setCancelled(true); 
    } else if (livingEntity instanceof org.bukkit.entity.Giant) {
      if (!yamlConfiguration.getBoolean("mobSettings.mobSpawn.giant"))
        event.setCancelled(true); 
    } else if (livingEntity instanceof org.bukkit.entity.Guardian) {
      if (!yamlConfiguration.getBoolean("mobSettings.mobSpawn.guardian"))
        event.setCancelled(true); 
    } else if (livingEntity instanceof org.bukkit.entity.Hoglin) {
      if (!yamlConfiguration.getBoolean("mobSettings.mobSpawn.hoglin"))
        event.setCancelled(true); 
    } else if (livingEntity instanceof org.bukkit.entity.Horse) {
      if (!yamlConfiguration.getBoolean("mobSettings.mobSpawn.horse"))
        event.setCancelled(true); 
    } else if (livingEntity instanceof org.bukkit.entity.Husk) {
      if (!yamlConfiguration.getBoolean("mobSettings.mobSpawn.husk"))
        event.setCancelled(true); 
    } else if (livingEntity instanceof org.bukkit.entity.Illusioner) {
      if (!yamlConfiguration.getBoolean("mobSettings.mobSpawn.illusioner"))
        event.setCancelled(true); 
    } else if (livingEntity instanceof org.bukkit.entity.IronGolem) {
      if (!yamlConfiguration.getBoolean("mobSettings.mobSpawn.irongolem"))
        event.setCancelled(true); 
    } else if (livingEntity instanceof org.bukkit.entity.Llama) {
      if (!yamlConfiguration.getBoolean("mobSettings.mobSpawn.llama"))
        event.setCancelled(true); 
    } else if (livingEntity instanceof MagmaCube) {
      MagmaCube cube = (MagmaCube)livingEntity;
      if (cube.getSize() == 1) {
        if (!yamlConfiguration.getBoolean("mobSettings.mobSpawn.magmacube.small"))
          event.setCancelled(true); 
      } else if (cube.getSize() == 2) {
        if (!yamlConfiguration.getBoolean("mobSettings.mobSpawn.magmacube.mid"))
          event.setCancelled(true); 
      } else if (cube.getSize() == 3 && 
        !yamlConfiguration.getBoolean("mobSettings.mobSpawn.magmacube.large")) {
        event.setCancelled(true);
      } 
    } else if (livingEntity instanceof org.bukkit.entity.Mule) {
      if (!yamlConfiguration.getBoolean("mobSettings.mobSpawn.mule"))
        event.setCancelled(true); 
    } else if (livingEntity instanceof org.bukkit.entity.MushroomCow) {
      if (!yamlConfiguration.getBoolean("mobSettings.mobSpawn.mushroomcow"))
        event.setCancelled(true); 
    } else if (livingEntity instanceof org.bukkit.entity.Ocelot) {
      if (!yamlConfiguration.getBoolean("mobSettings.mobSpawn.ocelot"))
        event.setCancelled(true); 
    } else if (livingEntity instanceof org.bukkit.entity.Panda) {
      if (!yamlConfiguration.getBoolean("mobSettings.mobSpawn.panda"))
        event.setCancelled(true); 
    } else if (livingEntity instanceof org.bukkit.entity.Parrot) {
      if (!yamlConfiguration.getBoolean("mobSettings.mobSpawn.parrot"))
        event.setCancelled(true); 
    } else if (livingEntity instanceof org.bukkit.entity.Phantom) {
      if (!yamlConfiguration.getBoolean("mobSettings.mobSpawn.phantom"))
        event.setCancelled(true); 
    } else if (livingEntity instanceof org.bukkit.entity.Pig) {
      if (!yamlConfiguration.getBoolean("mobSettings.mobSpawn.pig"))
        event.setCancelled(true); 
    } else if (livingEntity instanceof org.bukkit.entity.Piglin) {
      if (!yamlConfiguration.getBoolean("mobSettings.mobSpawn.piglin"))
        event.setCancelled(true); 
    } else if (livingEntity instanceof org.bukkit.entity.Pillager) {
      if (!yamlConfiguration.getBoolean("mobSettings.mobSpawn.pillager"))
        event.setCancelled(true); 
    } else if (livingEntity instanceof org.bukkit.entity.PolarBear) {
      if (!yamlConfiguration.getBoolean("mobSettings.mobSpawn.polarbear"))
        event.setCancelled(true); 
    } else if (livingEntity instanceof org.bukkit.entity.PufferFish) {
      if (!yamlConfiguration.getBoolean("mobSettings.mobSpawn.pufferfish"))
        event.setCancelled(true); 
    } else if (livingEntity instanceof org.bukkit.entity.Rabbit) {
      if (!yamlConfiguration.getBoolean("mobSettings.mobSpawn.rabbit"))
        event.setCancelled(true); 
    } else if (livingEntity instanceof org.bukkit.entity.Ravager) {
      if (!yamlConfiguration.getBoolean("mobSettings.mobSpawn.ravager"))
        event.setCancelled(true); 
    } else if (livingEntity instanceof org.bukkit.entity.Salmon) {
      if (!yamlConfiguration.getBoolean("mobSettings.mobSpawn.salmon"))
        event.setCancelled(true); 
    } else if (livingEntity instanceof org.bukkit.entity.Sheep) {
      if (!yamlConfiguration.getBoolean("mobSettings.mobSpawn.sheep"))
        event.setCancelled(true); 
    } else if (livingEntity instanceof org.bukkit.entity.Shulker) {
      if (!yamlConfiguration.getBoolean("mobSettings.mobSpawn.shulker"))
        event.setCancelled(true); 
    } else if (livingEntity instanceof org.bukkit.entity.Silverfish) {
      if (!yamlConfiguration.getBoolean("mobSettings.mobSpawn.silverfish"))
        event.setCancelled(true); 
    } else if (livingEntity instanceof org.bukkit.entity.Skeleton) {
      if (!yamlConfiguration.getBoolean("mobSettings.mobSpawn.skeleton"))
        event.setCancelled(true); 
    } else if (livingEntity instanceof org.bukkit.entity.SkeletonHorse) {
      if (!yamlConfiguration.getBoolean("mobSettings.mobSpawn.skeletonhorse"))
        event.setCancelled(true); 
    } else if (livingEntity instanceof Slime) {
      Slime cube = (Slime)livingEntity;
      if (cube.getSize() == 1) {
        if (!yamlConfiguration.getBoolean("mobSettings.mobSpawn.slime.small"))
          event.setCancelled(true); 
      } else if (cube.getSize() == 2) {
        if (!yamlConfiguration.getBoolean("mobSettings.mobSpawn.slime.mid"))
          event.setCancelled(true); 
      } else if (cube.getSize() == 3 && 
        !yamlConfiguration.getBoolean("mobSettings.mobSpawn.slime.large")) {
        event.setCancelled(true);
      } 
    } else if (livingEntity instanceof org.bukkit.entity.Snowman) {
      if (!yamlConfiguration.getBoolean("mobSettings.mobSpawn.snowman"))
        event.setCancelled(true); 
    } else if (livingEntity instanceof org.bukkit.entity.Spider) {
      if (!yamlConfiguration.getBoolean("mobSettings.mobSpawn.spider"))
        event.setCancelled(true); 
    } else if (livingEntity instanceof org.bukkit.entity.Squid) {
      if (!yamlConfiguration.getBoolean("mobSettings.mobSpawn.squid"))
        event.setCancelled(true); 
    } else if (livingEntity instanceof org.bukkit.entity.Stray) {
      if (!yamlConfiguration.getBoolean("mobSettings.mobSpawn.stray"))
        event.setCancelled(true); 
    } else if (livingEntity instanceof org.bukkit.entity.Strider) {
      if (!yamlConfiguration.getBoolean("mobSettings.mobSpawn.strider"))
        event.setCancelled(true); 
    } else if (livingEntity instanceof org.bukkit.entity.TraderLlama) {
      if (!yamlConfiguration.getBoolean("mobSettings.mobSpawn.traderllama"))
        event.setCancelled(true); 
    } else if (livingEntity instanceof org.bukkit.entity.TropicalFish) {
      if (!yamlConfiguration.getBoolean("mobSettings.mobSpawn.tropicalfish"))
        event.setCancelled(true); 
    } else if (livingEntity instanceof org.bukkit.entity.Turtle) {
      if (!yamlConfiguration.getBoolean("mobSettings.mobSpawn.turtle"))
        event.setCancelled(true); 
    } else if (livingEntity instanceof org.bukkit.entity.Vex) {
      if (!yamlConfiguration.getBoolean("mobSettings.mobSpawn.vex"))
        event.setCancelled(true); 
    } else if (livingEntity instanceof org.bukkit.entity.Villager) {
      if (!yamlConfiguration.getBoolean("mobSettings.mobSpawn.villager"))
        event.setCancelled(true); 
    } else if (livingEntity instanceof org.bukkit.entity.Vindicator) {
      if (!yamlConfiguration.getBoolean("mobSettings.mobSpawn.vindicator"))
        event.setCancelled(true); 
    } else if (livingEntity instanceof org.bukkit.entity.WanderingTrader) {
      if (!yamlConfiguration.getBoolean("mobSettings.mobSpawn.wanderingtrader"))
        event.setCancelled(true); 
    } else if (livingEntity instanceof org.bukkit.entity.Witch) {
      if (!yamlConfiguration.getBoolean("mobSettings.mobSpawn.witch"))
        event.setCancelled(true); 
    } else if (livingEntity instanceof org.bukkit.entity.Wither) {
      if (!yamlConfiguration.getBoolean("mobSettings.mobSpawn.wither"))
        event.setCancelled(true); 
    } else if (livingEntity instanceof org.bukkit.entity.WitherSkeleton) {
      if (!yamlConfiguration.getBoolean("mobSettings.mobSpawn.witherskeleton"))
        event.setCancelled(true); 
    } else if (livingEntity instanceof org.bukkit.entity.Wolf) {
      if (!yamlConfiguration.getBoolean("mobSettings.mobSpawn.wolf"))
        event.setCancelled(true); 
    } else if (livingEntity instanceof org.bukkit.entity.Zoglin) {
      if (!yamlConfiguration.getBoolean("mobSettings.mobSpawn.zoglin"))
        event.setCancelled(true); 
    } else if (livingEntity instanceof org.bukkit.entity.Zombie) {
      if (!yamlConfiguration.getBoolean("mobSettings.mobSpawn.zombie"))
        event.setCancelled(true); 
    } else if (livingEntity instanceof org.bukkit.entity.ZombieHorse) {
      if (!yamlConfiguration.getBoolean("mobSettings.mobSpawn.zombiehorse"))
        event.setCancelled(true); 
    } else if (livingEntity instanceof org.bukkit.entity.ZombieVillager) {
      if (!yamlConfiguration.getBoolean("mobSettings.mobSpawn.zombievillager"))
        event.setCancelled(true); 
    } else if (livingEntity instanceof org.bukkit.entity.PigZombie && 
      !yamlConfiguration.getBoolean("mobSettings.mobSpawn.zombifiedpiglin")) {
      event.setCancelled(true);
    } 
  }
}
