package com.matf.switchsnake

import com.systemvi.engine.utils.Utils

object Main{
  def main(args: Array[String]): Unit = {
    Utils.assetsFolder="../"
    new App().run()
  }
}