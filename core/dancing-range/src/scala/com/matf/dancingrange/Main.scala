package com.matf.dancingrange

import com.matf.ui.runApp
import com.systemvi.engine.utils.Utils

object Main{
  def main(args: Array[String]): Unit = {
    Utils.assetsFolder="../"
    runApp(
      "Dancing Range",
      new DancingRange()
    )
  }
}