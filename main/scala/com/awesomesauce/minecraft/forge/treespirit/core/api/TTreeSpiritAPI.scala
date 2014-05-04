package com.awesomesauce.minecraft.forge.treespirit.core.api

import com.awesomesauce.minecraft.forge.treespirit.core.api.tree.TTreePart
import net.minecraft.block.Block

trait TTreeSpiritAPI {
  def registerPart(block : Block)
  def isRegisteredPart(block:Block) : Boolean
}