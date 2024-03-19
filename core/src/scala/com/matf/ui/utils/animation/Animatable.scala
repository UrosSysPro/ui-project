package com.matf.ui.utils.animation

trait Animatable {
  var controllers:Array[AnimationController]=Array()
  def register(controller: AnimationController): Unit = {
    controllers=controllers:+controller
  }
}
