package com.awesomesauce.minecraft.forge.treespirit.core

import com.awesomesauce.minecraft.forge.core.lib.TAwesomeSauceMod
import com.awesomesauce.minecraft.forge.core.lib.util.ItemUtil
import com.awesomesauce.minecraft.forge.treespirit.core.te.TileEntityTreeCore
import cpw.mods.fml.common.Mod
import cpw.mods.fml.common.event.FMLInitializationEvent
import cpw.mods.fml.common.event.FMLPostInitializationEvent
import cpw.mods.fml.common.event.FMLPreInitializationEvent
import net.minecraft.block.Block
import net.minecraft.block.material.Material
import net.minecraft.item.Item
import cpw.mods.fml.common.Mod.EventHandler
import com.awesomesauce.minecraft.forge.treespirit.core.api.TreeSpiritTree
import com.awesomesauce.minecraft.forge.treespirit.core.te.TileEntityTreeSolar

@Mod(modid = TreeSpiritCore.MODID, name = TreeSpiritCore.MODNAME, modLanguage = "scala")
object TreeSpiritCore extends TAwesomeSauceMod {
  @EventHandler
  def aspri(e: FMLPreInitializationEvent) = super.awesomesaucepreinit(e)
  @EventHandler
  def asi(e: FMLInitializationEvent) = super.awesomesauceinit(e)
  @EventHandler
  def aspoi(e: FMLPostInitializationEvent) = super.awesomesaucepostinit(e)
  final val MODID = "TreeSpirit"
  final val MODNAME = "TreeSpirit"
  var magicalEssence: Item = null
  var treeCore: Block = null
  var treeSolar: Block = null
  var treeWood: Block = null
  var treePlanks: Block = null
  def getModID: String = MODID
  def getModName: String = MODNAME
  def getTabIconItem: () => net.minecraft.item.Item = () => magicalEssence
  def getTextureDomain: String = "treespirit"
  def preInit() = {
    TreeSpiritAPIImpl
  }
  def init() = {
    magicalEssence = ItemUtil.makeItem(this, "magicalEssence")
    treeCore = ItemUtil.makeBlock(this, "treeCore", Material.wood, () => new TileEntityTreeCore)
    treeSolar = ItemUtil.makeBlock(this, "treeSolar", Material.wood, () => new TileEntityTreeSolar)
    treeWood = ItemUtil.makeBlock(this, "treeWood", Material.wood)
    treePlanks = ItemUtil.makeBlock(this, "treePlanks", Material.wood)
    TreeSpiritTree.registerPart(treeCore)
    TreeSpiritTree.registerPart(treeSolar)
    TreeSpiritTree.registerPart(treeWood)
    TreeSpiritTree.registerPart(treePlanks)
  }
  def postInit() = {}
}