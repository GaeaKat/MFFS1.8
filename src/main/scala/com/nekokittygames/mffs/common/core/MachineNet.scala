package com.nekokittygames.mffs.common.core

import java.util.UUID


import com.nekokittygames.mffs.common.tileentities.TileEntityMFFS
import com.google.common.collect.MapMaker
import net.minecraft.world.World
import scala.collection.mutable

/**
  * Created by katsw on 03/12/2015.
  */
object MachineNet {
  val WorldNets=new MapMaker().weakKeys().makeMap[World,WorldGrid]()
  val machines:mutable.Map[UUID,TileEntityMFFS]=new mutable.HashMap[UUID,TileEntityMFFS]()

  class WorldGrid
  {
    val machines:mutable.Map[UUID,TileEntityMFFS]=new mutable.HashMap[UUID,TileEntityMFFS]()

    def registerMachine(tileEntityMFFS: TileEntityMFFS,uUID: UUID): Unit =
    {
      if(!machines.contains(uUID))
        machines.put(uUID,tileEntityMFFS)
    }
  }


  def getMachineNet(world: World):WorldGrid= {
    if (world != null) {
      if (!WorldNets.containsKey(world)) {
        WorldNets.put(world, new WorldGrid)

      }
      WorldNets.get(world)
    }
    else
      null
  }
}
