package com.awesomesauce.minecraft.forge.treespirit.core.api

import net.minecraft.block.Block
import com.awesomesauce.minecraft.forge.treespirit.core.api.tree.TTreePart

object TreeSpiritTree {
  var api: TTreeSpiritAPI = null
  def registerPart(block : Block) = api.registerPart(block)
  def isRegisteredPart(block : Block) = api.isRegisteredPart(block)
}