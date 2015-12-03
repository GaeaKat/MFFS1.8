package com.nekokittygames.mffs.common.blocks

import com.nekokittygames.mffs.common.MFFS
import com.nekokittygames.mffs.common.tileentities.TileEntityMFFS
import net.minecraft.block.state.{BlockState, IBlockState}
import net.minecraft.block.{BlockPistonBase, ITileEntityProvider, Block}
import net.minecraft.block.material.Material
import net.minecraft.block.properties.{PropertyBool, PropertyDirection}
import net.minecraft.entity.EntityLivingBase
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.{BlockPos, EnumFacing}
import net.minecraft.world.World

/**
  * Created by katsw on 03/12/2015.
  */
abstract class MachineBlock(blockName:String) extends {
  val FACING: PropertyDirection = PropertyDirection.create("facing")
  val ACTIVE: PropertyBool = PropertyBool.create("active")
} with ModBlock(Material.iron) with ITileEntityProvider {
  setHardness(5f)
  setResistance(15f)
  setUnlocalizedName(blockName)

  this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(ACTIVE, false))
  override def getStateFromMeta(meta: Int): IBlockState =
  {
    val facing:EnumFacing=BlockPistonBase.getFacing(meta)
    return this.getDefaultState.withProperty(FACING,facing).withProperty(ACTIVE,(meta & 8) > 0)
  }

  override def getMetaFromState(state: IBlockState): Int =
  {
    val b0: Byte = 0
    var i: Int = b0 | state.getValue(FACING).asInstanceOf[EnumFacing].getIndex
    if(state.getValue(ACTIVE)==true)
      i |= 8
    i
  }

  def getTileEntityClass: Class[_ <: TileEntityMFFS] = null
  def GuiID:Int


  override def onBlockActivated(worldIn: World, pos: BlockPos, state: IBlockState, playerIn: EntityPlayer, side: EnumFacing, hitX: Float, hitY: Float, hitZ: Float): Boolean =
  {
    if(playerIn.isSneaking)
      return false

    if(worldIn.isRemote)
      return true


    playerIn.openGui(MFFS,GuiID,worldIn,pos.getX,pos.getY,pos.getZ)
    true
  }

  override def createNewTileEntity(worldIn: World, meta: Int): TileEntity = getTileEntityClass.newInstance()
  override def createBlockState(): BlockState = new BlockState(this, FACING,ACTIVE);

  override def onBlockPlaced(worldIn: World, pos: BlockPos, facing: EnumFacing, hitX: Float, hitY: Float, hitZ: Float, meta: Int, placer: EntityLivingBase): IBlockState = {

    this.getDefaultState.withProperty(FACING,BlockPistonBase.getFacingFromEntity(worldIn,pos,placer))
  }
}
