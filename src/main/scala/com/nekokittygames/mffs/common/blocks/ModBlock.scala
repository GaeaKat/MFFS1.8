package com.nekokittygames.mffs.common.blocks

import com.nekokittygames.mffs.common.core.MffsCreativeTab
import net.minecraft.block.Block
import net.minecraft.block.material.Material
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent

/**
  * Created by katsw on 03/12/2015.
  */
class ModBlock(mat:Material) extends Block(mat) {

  def initBlock(fMLPreInitializationEvent: FMLPreInitializationEvent)=
  {

  }
  if(registerInCreative)
    setCreativeTab(MffsCreativeTab)
  def registerInCreative:Boolean =true
}
