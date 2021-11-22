package com.HideOrHunt.utilities;

import org.bukkit.Material;
import org.bukkit.block.Block;

public class SpawnUtilities {
  public static boolean isImpactingBlock(Block bottomBlock, Block topBlock) {
    if (bottomBlock.getType() == Material.BEACON || topBlock.getType() == Material.BEACON || bottomBlock.getType() == Material.DROPPER || topBlock.getType() == Material.DROPPER || bottomBlock.getType() == Material.FURNACE || topBlock.getType() == Material.FURNACE || bottomBlock.getType() == Material.DISPENSER || topBlock.getType() == Material.DISPENSER || bottomBlock.getType() == Material.CHEST || topBlock.getType() == Material.CHEST || bottomBlock.getType() == Material.TRAPPED_CHEST || topBlock.getType() == Material.TRAPPED_CHEST)
      return true; 
    return false;
  }
}
