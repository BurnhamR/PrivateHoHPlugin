package com.HideOrHunt.customgames;

import io.netty.util.internal.ThreadLocalRandom;
import org.bukkit.scheduler.BukkitRunnable;

public class TimerInventorySwap {
  public static void runnable() {
    BukkitRunnable taskID = new BukkitRunnable() {
        public void run() {
          int randomNum = 0;
          randomNum = ThreadLocalRandom.current().nextInt(0, 35);
        }
      };
    taskID.getTaskId();
  }
}
