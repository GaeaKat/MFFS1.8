package com.nekokittygames.mffs.common.core

import com.nekokittygames.mffs.client.gui.GuiCapacitor
import com.nekokittygames.mffs.common.containers.ContainerCapacitor
import com.nekokittygames.mffs.common.tileentities.TileEntityCapacitor
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.util.BlockPos
import net.minecraft.world.World
import net.minecraftforge.fml.common.network.IGuiHandler

/**
  * Created by katsw on 03/12/2015.
  */
object GuiHandler extends IGuiHandler{

  final val CAPACITOR_GUI:Int =1

  override def getClientGuiElement(ID: Int, player: EntityPlayer, world: World, x: Int, y: Int, z: Int): AnyRef =
  {
      ID match
        {
        case CAPACITOR_GUI=> new GuiCapacitor(player,world.getTileEntity(new BlockPos(x,y,z)).asInstanceOf[TileEntityCapacitor])
      }
  }

  override def getServerGuiElement(ID: Int, player: EntityPlayer, world: World, x: Int, y: Int, z: Int): AnyRef =
  {
    ID match
    {
      case CAPACITOR_GUI => new ContainerCapacitor(player,world.getTileEntity(new BlockPos(x,y,z)).asInstanceOf[TileEntityCapacitor])
      case _ => null
    }
  }
}
