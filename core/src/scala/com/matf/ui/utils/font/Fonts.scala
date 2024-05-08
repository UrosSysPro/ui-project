package com.matf.ui.utils.font

import com.systemvi.engine.ui.utils.font.Font

object Fonts {
  var pixels:Font=null

  def load(): Unit = {
    pixels=Font.load("assets/font.PNG","assets/font.json")
  }
}
