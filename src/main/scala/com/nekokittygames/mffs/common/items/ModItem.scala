package com.nekokittygames.mffs.common.items

import codechicken.lib.util.LangProxy
import com.nekokittygames.mffs.common.core.MffsCreativeTab
import net.minecraft.item.Item
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent

/**
  * Created by katsw on 03/12/2015.
  */
trait ModItem extends Item{
  def initItem(fMLPreInitializationEvent: FMLPreInitializationEvent)=
  {

  }

  final var lang: LangProxy = new LangProxy("mffsitems")
  if(registerInCreative)
    setCreativeTab(MffsCreativeTab)
  def registerInCreative:Boolean =true
}
