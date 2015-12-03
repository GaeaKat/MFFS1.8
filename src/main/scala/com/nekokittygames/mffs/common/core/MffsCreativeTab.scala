package com.nekokittygames.mffs.common.core

import net.minecraft.creativetab.CreativeTabs
import net.minecraft.init.Items
import net.minecraft.item.Item

/**
  * Created by katsw on 03/12/2015.
  */
object MffsCreativeTab extends CreativeTabs("MFFS"){
  override def getTabIconItem: Item = Items.blaze_rod
}
