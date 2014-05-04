package com.awesomesauce.minecraft.forge.treespirit.core.te

import com.awesomesauce.minecraft.forge.treespirit.core.api.tree.TTreePart
import net.minecraft.tileentity.TileEntity
import com.awesomesauce.minecraft.forge.treespirit.core.api.tree.Tree

class TileEntityTreeSolar extends TileEntity with TTreePart {
  var tree : Tree = null
  def getTree(): Tree = tree
  def setTree(Tree: Tree) = tree = Tree
  override def updateEntity() = {
    if (tree != null)
    {
      tree.changeSolarEnergy(worldObj.getBlockLightValue(xCoord, yCoord+1, zCoord)*0.1)
    }
  }
}