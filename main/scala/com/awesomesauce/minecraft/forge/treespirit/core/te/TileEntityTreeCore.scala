package com.awesomesauce.minecraft.forge.treespirit.core.te

import com.awesomesauce.minecraft.forge.core.lib.item.TActivatedTileEntity
import com.awesomesauce.minecraft.forge.core.lib.util.PlayerUtil
import com.awesomesauce.minecraft.forge.core.lib.util.vec.ISidePosition
import com.awesomesauce.minecraft.forge.core.lib.util.vec.SidePositionImpl
import com.awesomesauce.minecraft.forge.treespirit.core.api.tree.TTreePart
import com.awesomesauce.minecraft.forge.treespirit.core.api.tree.Tree
import cpw.mods.fml.common.FMLLog
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.tileentity.TileEntity
import net.minecraftforge.common.util.ForgeDirection
import com.awesomesauce.minecraft.forge.treespirit.core.api.TreeSpiritTree
import com.awesomesauce.minecraft.forge.core.lib.util.vec.ISidePosition

class TileEntityTreeCore extends TileEntity with TTreePart with TActivatedTileEntity {
  var blocksToTest = scala.collection.mutable.Set[ISidePosition]()
  var testedBlocks = scala.collection.mutable.Set[ISidePosition]()
  object tree extends Tree {
    val treeParts = scala.collection.mutable.Set[TTreePart]()
    var solarEnergy: Double = 0
    var magicEnergy: Double = 0
    def getAllTreeParts = treeParts
    def getSolarEnergy: Double = solarEnergy
    def getMagicEnergy: Double = magicEnergy
    def changeSolarEnergy(energy: Double) = solarEnergy += energy
    def changeMagicEnergy(energy: Double) = magicEnergy += energy
  }
  var testedBlock: ISidePosition = null
  var resetCounter: Int = 1000
  def getTree(): Tree = tree
  def setTree(tree: Tree): Unit = FMLLog.warning("[TreeSpirit] This should really not be called. Something bad is happening.")
  override def updateEntity() = {
    resetCounter -= 1
    if (tree.getSolarEnergy > 1) {
      tree.changeSolarEnergy(-1)
      tree.changeMagicEnergy(0.2)
    }
    if (tree.getMagicEnergy > 0.1)
      tree.changeMagicEnergy(-0.1)
    tree.changeSolarEnergy(0.01 * worldObj.getBlockLightValue(xCoord, yCoord+1, zCoord))
    if (blocksToTest.size != 0 && resetCounter > 0) {
      testedBlock = blocksToTest.last
      blocksToTest.remove(testedBlock)
      while (testedBlocks.contains(testedBlock)) {
        FMLLog.info("Already tested: "+testedBlock.toString())
        testedBlock = blocksToTest.last
        blocksToTest.remove(testedBlock)
      }
      if (TreeSpiritTree.isRegisteredPart(worldObj.getBlock(testedBlock.getX().asInstanceOf[Int], testedBlock.getY().asInstanceOf[Int], testedBlock.getZ().asInstanceOf[Int]))) {
        
        for (i <- ForgeDirection.values()) {
          if (i != ForgeDirection.UNKNOWN || i != testedBlock.getDirection())
            blocksToTest += new SidePositionImpl(xCoord + i.offsetX, yCoord + i.offsetY, zCoord + i.offsetZ, i.getOpposite())
        }
      }
      if (worldObj.getTileEntity(testedBlock.getX().asInstanceOf[Int], testedBlock.getY().asInstanceOf[Int], testedBlock.getZ().asInstanceOf[Int]).isInstanceOf[TTreePart]) {
        tree.treeParts.add(worldObj.getTileEntity(testedBlock.getX().asInstanceOf[Int], testedBlock.getY().asInstanceOf[Int], testedBlock.getZ().asInstanceOf[Int]).asInstanceOf[TTreePart])
      }
    } else {
      blocksToTest = scala.collection.mutable.Set[ISidePosition]()
      testedBlocks = scala.collection.mutable.Set[ISidePosition]()
      for (i <- ForgeDirection.values()) {
        if (i != ForgeDirection.UNKNOWN)
          blocksToTest += new SidePositionImpl(xCoord + i.offsetX, yCoord + i.offsetY, zCoord + i.offsetZ, i.getOpposite())
      }
    }
  }
  def activate(player: EntityPlayer, side: Int, partx: Float, party: Float, partz: Float): Boolean = {
    PlayerUtil.sendChatMessage(player, "Solar Energy: " + tree.getSolarEnergy)
    PlayerUtil.sendChatMessage(player, "Magic Energy: " + tree.getMagicEnergy)
    return true
  }
}