package com.nekokittygames.mffs.common.tileentities

import java.util
import java.util.UUID

import cofh.api.energy.{EnergyStorage, IEnergyStorage}
import com.nekokittygames.mffs.api.IDebuggable
import com.nekokittygames.mffs.common.MFFS
import com.nekokittygames.mffs.common.core.MachineNet
import net.minecraft.client.resources.Language
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.inventory.ISidedInventory
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.network.{Packet, NetworkManager}
import net.minecraft.network.play.server.S35PacketUpdateTileEntity
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.{ChatComponentText, EnumFacing, IChatComponent}

/**
  * Created by katsw on 03/12/2015.
  */
abstract class TileEntityMFFS extends TileEntity with IEnergyStorage with ISidedInventory with IDebuggable{


  var machineID=UUID.randomUUID()

  var EnergyStorage: EnergyStorage = new EnergyStorage(getRFCapacity())

  val inventory:Array[ItemStack]=new Array[ItemStack](getInventorySize)

  override def readFromNBT(compound: NBTTagCompound): Unit = {
    super.readFromNBT(compound)
    readMainNBT(compound)
    readVolitileNBT(compound)
  }

  def readMainNBT(compound: NBTTagCompound): Unit = {

    if(compound.hasKey("lsbID")) {
      val lsb = compound.getLong("lsbID");
      val msb = compound.getLong("msbID");
      machineID = new UUID(msb, lsb)
    }
  }

  def readVolitileNBT(compound: NBTTagCompound) = {
    EnergyStorage = EnergyStorage.readFromNBT(compound)
  }

  def getInventorySize:Integer

  override def writeToNBT(compound: NBTTagCompound): Unit =
    {
      super.writeToNBT(compound)
      writeMainNBT(compound)
      writeVolitileNBT(compound)
    }

  def writeMainNBT(compound: NBTTagCompound): Unit = {
    compound.setLong("lsbID",machineID.getLeastSignificantBits)
    compound.setLong("msbID",machineID.getMostSignificantBits)
  }

  def writeVolitileNBT(compound: NBTTagCompound) = {
    EnergyStorage.writeToNBT(compound)
  }

  def getRFCapacity(): Int = 10000000

  def getSlotStackLimit(Slot: Int): Int = getInventoryStackLimit

  def isItemValid(stack: ItemStack, Slot: Int): Boolean = true

  override def getDebugInfo(player: EntityPlayer,debugList:util.List[String]):Unit  =
  {
    debugList.add(MFFS.lang.format("debug.id",machineID))
    debugList.add(MFFS.lang.format("debug.capacity",new Integer(getRFCapacity())))
    debugList.add(MFFS.lang.format("debug.amount",new Integer(getEnergyStored())))
  }


  def getPercentageStorage:Float=getEnergyStored.toFloat/getMaxEnergyStored.toFloat


  override def validate(): Unit =
    {
      super.validate()
      MachineNet.getMachineNet(worldObj).registerMachine(this,machineID)
    }

  // IEnergyStorage Implementation
  override def extractEnergy(maxExtract: Int, simulate: Boolean): Int = EnergyStorage.extractEnergy(maxExtract,simulate)

  override def getEnergyStored: Int = EnergyStorage.getEnergyStored

  override def getMaxEnergyStored: Int = EnergyStorage.getMaxEnergyStored

  override def receiveEnergy(maxReceive: Int, simulate: Boolean): Int = EnergyStorage.receiveEnergy(maxReceive,simulate)


  // Inventory Implementation
  override def getSlotsForFace(side: EnumFacing): Array[Int] = Array[Int]()

  override def canExtractItem(index: Int, stack: ItemStack, direction: EnumFacing): Boolean = false

  override def canInsertItem(index: Int, itemStackIn: ItemStack, direction: EnumFacing): Boolean = false

  override def decrStackSize(index: Int, count: Int): ItemStack =
  {
    if (this.inventory(index) != null) {
      var itemstack: ItemStack = null
      if (this.inventory(index).stackSize <= count) {
        itemstack = this.inventory(index)
        this.inventory(index) = null
        this.markDirty
        return itemstack
      }
      else {
        itemstack = this.inventory(index).splitStack(count)
        if (this.inventory(index).stackSize == 0) {
          this.inventory(index) = null
        }
        this.markDirty
        return itemstack
      }
    }
    else {
      return null
    }
  }

  override def closeInventory(player: EntityPlayer): Unit = {}

  override def getSizeInventory: Int = getInventorySize

  override def getInventoryStackLimit: Int = 64

  override def clear(): Unit = {
    for (stack <- inventory.indices) {
      {
        this.inventory(stack) = null
      }
    }
  }


  override def isItemValidForSlot(index: Int, stack: ItemStack): Boolean = true

  override def getStackInSlotOnClosing(index: Int): ItemStack = {
    if (this.inventory(index) != null) {
      val itemstack: ItemStack = this.inventory(index)
      this.inventory(index) = null
      return itemstack
    }
    else {
      return null
    }
  }

  override def openInventory(player: EntityPlayer): Unit =
  {

  }

  override def getFieldCount: Int = 0

  override def getField(id: Int): Int = 0

  override def setInventorySlotContents(index: Int, stack: ItemStack): Unit =
  {
    this.inventory(index) = stack

    if (stack != null && stack.stackSize > this.getInventoryStackLimit) {
      stack.stackSize = this.getInventoryStackLimit
    }

    this.markDirty
  }

  override def isUseableByPlayer(player: EntityPlayer): Boolean = return if (this.worldObj.getTileEntity(this.pos) ne this) false else player.getDistanceSq(this.pos.getX.toDouble + 0.5D, this.pos.getY.toDouble + 0.5D, this.pos.getZ.toDouble + 0.5D) <= 64.0D

  override def getStackInSlot(index: Int): ItemStack = return inventory(index)

  override def setField(id: Int, value: Int): Unit = {}

  override def getDisplayName: IChatComponent = new ChatComponentText(getCommandSenderName)

  override def getCommandSenderName: String = "MFFSCapacitor"

  override def hasCustomName: Boolean = false

  override def onDataPacket(net: NetworkManager, pkt: S35PacketUpdateTileEntity): Unit =
    {
      super.onDataPacket(net, pkt)
      readMainNBT(pkt.getNbtCompound)
      readVolitileNBT(pkt.getNbtCompound)
      worldObj.markBlockRangeForRenderUpdate(pos,pos)
    }

  override def getDescriptionPacket: Packet =
    {
      val cmp:NBTTagCompound=new NBTTagCompound()
      writeMainNBT(cmp)
      writeVolitileNBT(cmp)
      new S35PacketUpdateTileEntity(pos,-1,cmp)
    }

  override def markDirty(): Unit =
    {
      super.markDirty()
      worldObj.markBlockForUpdate(pos)
    }
}
