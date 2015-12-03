package com.nekokittygames.mffs.common.blocks

import com.nekokittygames.mffs.common.libs.LibNames
import net.minecraft.block.Block
import net.minecraftforge.fml.common.registry.GameRegistry

/**
  * Created by katsw on 03/12/2015.
  */
object ModBlocks {

  def registerBlocks(): Unit =
  {
    def registerBlock(block:Block,name:String): Unit =
    {
      GameRegistry.registerBlock(block,name)
      block match {
        case blk:MachineBlock => GameRegistry.registerTileEntity(blk.getTileEntityClass,name)
      }
    }
    registerBlock(MffsCapacitor,LibNames.CAPACITOR)
  }
  def registerBlocksInventory(): Unit =
  {

  }

}
