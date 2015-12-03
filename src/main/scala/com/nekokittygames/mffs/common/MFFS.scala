package com.nekokittygames.mffs.common

import codechicken.lib.util.LangProxy
import com.nekokittygames.mffs.common.blocks.ModBlocks
import com.nekokittygames.mffs.common.core.GuiHandler
import com.nekokittygames.mffs.common.core.proxy.MffsCommonProxy
import com.nekokittygames.mffs.common.items.ModItems
import com.nekokittygames.mffs.common.libs.LibMisc
import mcp.mobius.waila.network.NetworkHandler
import net.minecraftforge.fml.common.network.NetworkRegistry
import net.minecraftforge.fml.common.{SidedProxy, Mod}
import net.minecraftforge.fml.common.Mod.EventHandler
import net.minecraftforge.fml.common.event.{FMLInitializationEvent, FMLPostInitializationEvent, FMLPreInitializationEvent}
import org.apache.logging.log4j.Logger

/**
  * Created by katsw on 03/12/2015.
  */
@Mod(modid = LibMisc.MOD_ID,version = LibMisc.VERSION,modLanguage = "scala")
object MFFS {
  final var logger:Logger=null

  final val lang:LangProxy=new LangProxy("mffs")
  @SidedProxy(clientSide = LibMisc.CLIENT_PROXY, serverSide = LibMisc.COMMON_PROXY)
  var proxy: MffsCommonProxy = null

  @EventHandler
  def preInit(eventArgs: FMLPreInitializationEvent) {
    logger=eventArgs.getModLog
    ModBlocks.registerBlocks()
    ModItems.registerItems(eventArgs)

  }


  @EventHandler
  def Init(eventArgs:FMLInitializationEvent): Unit =
  {
    NetworkRegistry.INSTANCE.registerGuiHandler(this,GuiHandler)
  }

  @EventHandler
  def postInit(eventArgs:FMLPostInitializationEvent): Unit = {
    ModBlocks.registerBlocksInventory()
    ModItems.registerInventoryItems()
  }
}
