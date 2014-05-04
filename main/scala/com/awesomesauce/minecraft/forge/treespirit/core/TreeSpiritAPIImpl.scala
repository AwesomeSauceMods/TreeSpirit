package com.awesomesauce.minecraft.forge.treespirit.core

import com.awesomesauce.minecraft.forge.treespirit.core.api.TTreeSpiritAPI
import com.awesomesauce.minecraft.forge.treespirit.core.api.TreeSpiritTree

object TreeSpiritAPIImpl extends TTreeSpiritAPI {
  TreeSpiritTree.api = this
  val registeredParts = scala.collection.mutable.Set[net.minecraft.block.Block]()
  def isRegisteredPart(block: net.minecraft.block.Block): Boolean = registeredParts.contains(block)
  def registerPart(block: net.minecraft.block.Block): Unit = registeredParts.add(block)
}