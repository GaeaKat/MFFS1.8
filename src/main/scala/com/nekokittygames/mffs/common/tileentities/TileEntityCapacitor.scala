package com.nekokittygames.mffs.common.tileentities

import cofh.api.energy.IEnergyProvider
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.EnumFacing

/**
  * Created by katsw on 03/12/2015.
  */
class TileEntityCapacitor extends TileEntityEnergySink with IEnergyProvider{
  override def extractEnergy(from: EnumFacing, maxExtract: Int, simulate: Boolean): Int = EnergyStorage.extractEnergy(maxExtract,simulate)

  override def getInventorySize: Integer = 4
}
