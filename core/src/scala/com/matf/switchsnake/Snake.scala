package com.matf.switchsnake

case class SnakePart(var x: Int,var y: Int)
class Snake {
  var vx: Int = 1
  var vy: Int = 0
  var parts: Array[SnakePart] = Array()

  def add(): Unit = {
    parts = parts :+ SnakePart(parts.last.x, parts.last.y)
  }
  def init(width: Int,height: Int): Unit = {
    parts = parts :+ SnakePart(width / 2, height / 2)
  }
  def update(): Unit = {
    for(i <- 0 until parts.length-1){
      parts(parts.length-i-1).x=parts(parts.length-i-2).x
      parts(parts.length-i-1).y=parts(parts.length-i-2).y
    }
    parts.head.x+=vx
    parts.head.y+=vy
  }

  def checkSelfCollision(): Boolean = {
    for (part <- parts) if (part != parts.head && part.x == parts.head.x && part.y == parts.head.y) {
      return true
    }
    false
  }
  def checkGameBorder(width:Int,height:Int): Unit = {
    if(parts.head.x<0)parts.head.x=width-1
    if(parts.head.y<0)parts.head.y=height-1
    if(parts.head.x>=width)parts.head.x=0
    if(parts.head.y>=height)parts.head.y=0
  }
}
