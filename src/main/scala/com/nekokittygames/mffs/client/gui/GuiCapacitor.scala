package com.nekokittygames.mffs.client.gui


import com.nekokittygames.mffs.common.containers.ContainerCapacitor
import com.nekokittygames.mffs.common.tileentities.TileEntityCapacitor
import net.minecraft.client.gui.GuiButton
import net.minecraft.client.gui.inventory.GuiContainer
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.util.ResourceLocation
import org.lwjgl.opengl.GL11

/**
  * Created by katsw on 03/12/2015.
  */
class GuiCapacitor(player:EntityPlayer,Core:TileEntityCapacitor)  extends GuiContainer(new ContainerCapacitor(player,Core)) {

  this.xSize=176
  this.ySize=207
  var editMode: Boolean = false








  override def drawGuiContainerBackgroundLayer(partialTicks: Float, mouseX: Int, mouseY: Int): Unit =
  {
    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F)
    this.mc.renderEngine.bindTexture(new ResourceLocation("modularforcefieldsystem:textures/gui/GuiCapacitor.png"))
    val w = (width - xSize) / 2
    val k = (height - ySize) / 2
    this.drawTexturedModalRect(w, k, 0, 0, xSize, ySize)
    val i1: Int = (79 * Core.getPercentageStorage).toInt
    //print(Core.getPercentageStorage)
    drawTexturedModalRect(w + 8, k + 112, 176, 0, i1 + 1, 79)
  }

  protected override def drawGuiContainerForegroundLayer(par1: Int, par2: Int) {
    fontRendererObj.drawString("Force Energy Capacitor", 8, 25, 0x404040)
    //fontRendererObj.drawString(Core.getDeviceName, 8, 8, 0x404040)
    fontRendererObj.drawString("FE: " + String.valueOf(Core.getEnergyStored()), 8, 100, 0x404040)
    fontRendererObj.drawString("Power Uplink: ", 8, 80, 0x404040)
    //fontRendererObj.drawString("transmit range:", 8, 60, 0x404040)
    //fontRendererObj.drawString((new StringBuilder).append(" ").append(Core.getTransmitRange).toString, 90, 60, 0x404040)
    //fontRendererObj.drawString("linked device:", 8, 43, 0x404040)
    //fontRendererObj.drawString((new StringBuilder).append(" ").append(Core.linkedprojector).toString, 90, 45, 0x404040)
  }

  override def initGui {
    //this.buttonList.asInstanceOf[java.util.List[GuiButton]].add(new GraphicButton(0, (width / 2) + 65, (height / 2) - 100, Core, 0))
    //this.buttonList.asInstanceOf[java.util.List[GuiButton]].add(new GraphicButton(1, (width / 2) + 20, (height / 2) - 28, Core, 1))
    super.initGui
  }

}
