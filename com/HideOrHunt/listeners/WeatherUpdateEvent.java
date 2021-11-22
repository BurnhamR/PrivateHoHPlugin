package com.HideOrHunt.listeners;

import java.io.File;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

public class WeatherUpdateEvent implements Listener {
  @EventHandler
  public void onWeatherChange(WeatherChangeEvent event) {
    File settingsFile = new File("plugins/PrivateHideOrHunt/", "gamesettings.yml");
    YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(settingsFile);
    if (yamlConfiguration.getString("serverSettings.weather").equals("sun") && event.toWeatherState()) {
      event.setCancelled(true);
    } else if (yamlConfiguration.getString("serverSettings.weather").equals("rain") && !event.toWeatherState()) {
      event.setCancelled(true);
    } else if (yamlConfiguration.getString("serverSettings.weather").equals("storm") && !event.toWeatherState()) {
      event.setCancelled(true);
    } 
  }
}
