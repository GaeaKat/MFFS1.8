package com.nekokittygames.mffs.common.util

import com.nekokittygames.mffs.common.tileentities.TileEntityMFFS
import net.minecraft.inventory.{Slot, IInventory}
import net.minecraft.item.ItemStack

/**
  * Created by katsw on 03/12/2015.
  */
class SlotHelper(par2Inventory:IInventory,par3:Int,par4:Int,par5:Int) extends Slot(par2Inventory,par3,par4,par5){

  val te:TileEntityMFFS=par2Inventory.asInstanceOf[TileEntityMFFS]
  val Slot=par3

  override def getSlotStackLimit: Int =
  {
    te.getSlotStackLimit(Slot)
  }

  override def isItemValid(stack: ItemStack): Boolean =
  {
    te.isItemValid(stack,Slot)
  }
}