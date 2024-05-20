package com.matf.materialapp

import com.matf.ui.runApp
import com.systemvi.engine.utils.Utils

object Main {
  def main(args: Array[String]): Unit = {
    Utils.assetsFolder="../"
    runApp("Title",new App)
  }
}
