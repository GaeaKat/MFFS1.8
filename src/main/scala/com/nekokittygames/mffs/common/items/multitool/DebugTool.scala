package com.nekokittygames.mffs.common.items.multitool


import java.util

import com.nekokittygames.mffs.api.IDebuggable
import com.nekokittygames.mffs.common.items.ModItem
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.{ItemStack, Item}
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.{ChatComponentText, EnumFacing, BlockPos}
import net.minecraft.world.World
import scala.collection.JavaConversions._


/**
  * Created by katsw on 03/12/2015.
  */
object DebugTool extends Item with ModItem{
  setUnlocalizedName("multitoolItem.debugger")

  override def onItemUseFirst(stack: ItemStack, player: EntityPlayer, world: World, pos: BlockPos, side: EnumFacing, hitX: Float, hitY: Float, hitZ: Float): Boolean = {
    val tileEntity: TileEntity = world.getTileEntity(pos)
    if (world.isRemote) {
      if(tileEntity.isInstanceOf[IDebuggable])
        {
          val debugInfo=new util.ArrayList[String]()
          tileEntity.asInstanceOf[IDebuggable].getDebugInfo(player,debugInfo)

          for(msg <- debugInfo)
            {
              player.addChatComponentMessage(new ChatComponentText(msg))
            }
          return true
        }
    }
    return false
  }
}
