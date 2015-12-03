package com.nekokittygames.mffs.common.blocks

import com.nekokittygames.mffs.common.core.GuiHandler
import com.nekokittygames.mffs.common.libs.LibNames
import com.nekokittygames.mffs.common.tileentities.{TileEntityMFFS, TileEntityCapacitor}

import net.minecraft.tileentity.TileEntity

/**
  * Created by katsw on 03/12/2015.
  */
object MffsCapacitor extends MachineBlock(LibNames.CAPACITOR) {
  override def getTileEntityClass: Class[_ <: TileEntityMFFS] = classOf[TileEntityCapacitor]

  override def GuiID: Int =GuiHandler.CAPACITOR_GUI
}
