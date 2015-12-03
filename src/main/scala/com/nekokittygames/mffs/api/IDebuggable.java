package com.nekokittygames.mffs.api;

import net.minecraft.entity.player.EntityPlayer;

import java.util.List;

/**
 * interface to register a block as debuggable by the debug item
 * Created by katsw on 03/12/2015.
 */
public interface IDebuggable {

    /**
     * Returns a list of debug strings
     * @param player player doing the debugging.
     * @return List of strings
     */
    void getDebugInfo(EntityPlayer player,List<String> debugList);
}
