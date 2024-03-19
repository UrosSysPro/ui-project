package com.matf.ui

import com.matf.ui.utils.context.{BuildContext, DrawContext}
import com.matf.ui.widgets.GestureDetector
import org.joml.Vector2f

import scala.collection.mutable

//  ui=build(state)

abstract class Widget {
  val position:Vector2f=new Vector2f()
  val size:Vector2f=new Vector2f()

  def build(context:BuildContext): Widget
  def calculateSize(maxParentSize: Vector2f): Vector2f
  def calculatePosition(parentPosition:Vector2f): Unit
  def draw(context:DrawContext): Unit
  def debugPrint(tabs:String): Unit={
    println(s"$tabs${this.getClass.getSimpleName}")
    for(child<-getChildren())if(child!=null)child.debugPrint(s"\t$tabs")
  }
  def contains(x:Float,y:Float):Boolean=
    x>=position.x&&
    x<=position.x+size.x&&
    y>=position.y&&
    y<=position.y+size.y
  def getChildren():Array[Widget]
}