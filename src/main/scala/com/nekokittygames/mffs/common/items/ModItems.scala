package com.nekokittygames.mffs.common.items

import com.nekokittygames.mffs.common.items.multitool.DebugTool
import com.nekokittygames.mffs.common.libs.LibItemNames
import net.minecraft.item.Item
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import net.minecraftforge.fml.common.registry.GameRegistry

/**
  * Created by katsw on 03/12/2015.
  */
object ModItems {

  def registerItems(fMLPreInitializationEvent: FMLPreInitializationEvent) = {
    def registerItem(item: Item, name: String) = {
      GameRegistry.registerItem(item, name)
      item match {
        case itm: ModItem => itm.initItem(fMLPreInitializationEvent)
        case _ => {}
      }
    }
    registerItem(DebugTool,LibItemNames.DEBUG)
  }


  def registerInventoryItems(): Unit =
  {

  }
}
