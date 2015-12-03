package com.nekokittygames.mffs.common.containers

import com.nekokittygames.mffs.common.tileentities.TileEntityCapacitor
import com.nekokittygames.mffs.common.util.SlotHelper
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.inventory.{Container, Slot}

/**
  * Created by katsw on 03/12/2015.
  */
class ContainerCapacitor(player:EntityPlayer, capacitorEntity:TileEntityCapacitor)  extends Container{

  var forcePower= -1
  var linkedProjector= -1
  var powerLinkMode = -1
  var capacity = -1

  addSlotToContainer(new SlotHelper(capacitorEntity,3,154,88)) //   Security Link
  addSlotToContainer(new SlotHelper(capacitorEntity,0,154,47)) // Capacity upgrade
  addSlotToContainer(new SlotHelper(capacitorEntity,1,154,67)) // Range upgrade
  addSlotToContainer(new SlotHelper(capacitorEntity,2,87,76)) // Force Energy

  for(i <- 0 until 3 ; j <- 0 until 9)
  {
    this.addSlotToContainer(new Slot(player.inventory,j+i*9+9,8+j*18,125+i*18))
  }

  for(i<-0 until 9)
    this.addSlotToContainer(new Slot(player.inventory,i,8+i*18,183))

  override def canInteractWith(playerIn: EntityPlayer): Boolean = return capacitorEntity.isUseableByPlayer(playerIn)

}
