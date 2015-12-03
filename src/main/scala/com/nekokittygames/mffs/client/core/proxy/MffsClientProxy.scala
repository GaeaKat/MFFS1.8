package com.nekokittygames.mffs.client.core.proxy

import com.nekokittygames.mffs.common.core.proxy.MffsCommonProxy
import com.nekokittygames.mffs.common.libs.LibMisc
import net.minecraft.block.Block
import net.minecraft.client.Minecraft
import net.minecraft.client.resources.model.ModelResourceLocation
import net.minecraft.item.Item

/**
  * Created by katsw on 03/12/2015.
  */
class MffsClientProxy extends  MffsCommonProxy{

  override def registerInventoryBlock(block: Block, name: String): Unit =
  {
    Minecraft.getMinecraft.getRenderItem.getItemModelMesher.register(Item.getItemFromBlock(block),0,new ModelResourceLocation(LibMisc.MOD_ID+":"+name,"inventory"))
  }

  override def registerInventoryItem(item: Item, name: String): Unit =
  {
    Minecraft.getMinecraft.getRenderItem.getItemModelMesher.register(item, 0, new ModelResourceLocation(LibMisc.MOD_ID+":" + name, "inventory"))
    //Minecraft.getMinecraft.getRenderItem.getItemModelMesher.

  }
}
