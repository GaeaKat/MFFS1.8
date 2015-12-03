package com.nekokittygames.mffs.common.tileentities

import cofh.api.energy.IEnergyReceiver
import com.nekokittygames.mffs.common.blocks.MffsCapacitor
import net.minecraft.util.EnumFacing

/**
  * Created by katsw on 03/12/2015.
  */
abstract class TileEntityEnergySink extends TileEntityMFFS with IEnergyReceiver{
  override def getEnergyStored(from: EnumFacing): Int = EnergyStorage.getEnergyStored

  override def getMaxEnergyStored(from: EnumFacing): Int = EnergyStorage.getMaxEnergyStored

  override def receiveEnergy(from: EnumFacing, maxReceive: Int, simulate: Boolean): Int =
    {
      val amt=EnergyStorage.receiveEnergy(maxReceive,simulate)
      if(!simulate)
        this.markDirty()
      amt
    }

  override def canConnectEnergy(from: EnumFacing): Boolean = ! (worldObj.getBlockState(pos).getValue(MffsCapacitor.FACING) == from)
}
