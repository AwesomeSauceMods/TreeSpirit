package com.awesomesauce.minecraft.forge.treespirit.core.api.tree

trait Tree {
	def getAllTreeParts: scala.collection.Set[TTreePart]
	def getSolarEnergy: Double
	def getMagicEnergy: Double
	def changeSolarEnergy(energy:Double)
	def changeMagicEnergy(energy:Double)
}